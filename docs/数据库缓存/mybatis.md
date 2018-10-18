## [mybatis](http://www.mybatis.org/mybatis-3/index.html)
1. 整体架构
2. 源码学习


### SqlSessions
> 执行命令、获取mappers、管理事务。 由 SqlSessionFactory 创建实例，SqlSessionFactory 包含创建 SqlSession 实例的所有不同方式的方法。
SqlSessionFactory 本身由 SqlSessionFactoryBuilder 创建，支持 XML、注解、手动 Java 配置

1. SqlSessionFactoryBuilder
    > 提供从不同源构建一个 SqlSessionFactory (SqlSession?)

2. SqlSessionFactory
    - Transaction 是否使用事务 会话级别(session scope) 还是自动提交 事务的隔离级别见 sql.md (sessions/transactions/batches都包含在一个会话范围内)
    - Connection 是否从配置的数据源获取连接
    - Execution 执行语句 是否复用 preparedStatements/批量更新(插入、删除)
        - ExecutorType.Simple 每次执行语句创建一个新的 PreparedStatement
        - ExecutorType.REUSE executors 重用 preparedStatements
        - ExecutorType.BATCH executor 批处理所有的更新语句并根据需要进行划分(如果他们间有查询),以确保易于理解的行为
        
3. SqlSession
    - 语句执行方法 每个sql mapping 都使用语句id和参数对象 包括基本类型也称为原语(自动装箱或包装类),JavaBean,POJO,Map. selectOne/selectList/selectMap...
        - 参数解释：RowBounds 限制返回行范围 offset/limit; ResultHandler 
    - 批量更新语句刷新(Flush)方法 刷新(执行)存储在jdbc driver 类中的批量更新语句 ExecutorType为BATCH时 List<BatchResult> flushStatements()
    - 事务控制方法 commit rollback 参数 force 可选
    - Local Cache mybatis提供两种缓存：本地缓存，二级缓存
        - 本地缓存：每次session创建都会附带一个本地缓存，任何的查询都会被存储在本地缓存，因此具有相同输入参数的相同查询将不会命中数据库,本地缓存再更新、提交
        回滚、关闭时被clear。本地缓存在整个session生命周期有效。需要该缓存来解析循环引用和加速重复的嵌套查询速度，因此不要完全禁用他但是你可以配置本地缓存只用于
        语句执行区间，利用localCacheScope=STATEMENT。当localCacheScope设置为Session时，mybatis将返回和存储在本地缓存相同的对象引用。因此最佳实践，
        不要去修改mybatis返回的对象，因为任何对返回对象的修改列表等都会影响到本地缓存内存。
    - Using Mappers
         - mapper 类是一个与SqlSession方法匹配的方法定义的简单接口，不需要任何的接口实现或继承任何类。方法签名唯一标识映射语句。mapper接口可以扩展其他接口，当
         使用xml绑定到mapper接口请确保在适当的命名空间中有语句，另外在相同结构层次中的两个接口不能有相同的方法签名。如果一个mapper方法有多个参数，默认命名为
         '#{param1}，#{param2}',可以利用@Param("paramName") 改变名称。注解提供了一种有效的方法实现简单的映射语句且没有增加开销
         - 具体注解详见http://www.mybatis.org/mybatis-3/java-api.html 文末



cacheEnabled 二级缓存