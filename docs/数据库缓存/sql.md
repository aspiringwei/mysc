https://dev.mysql.com/doc/refman/5.7/en/

本节包含数据库三范式，关联查询，事务管理，索引优化


### 三范式
1. 第一范式 每一列都是不可分割的基本数据项
1. 第二范式 满足1且每个实例或行必须可以被惟一地区分
1. 第三范式 满足2且不包含已在其它表中已包含的非主关键字信息

### 关联查询
1. 左连接 left join/left outer join
2. 右连接 right join/right outer join
3. 内连接 inner join/join
4. 完整外部联接 full join/full outer join
```sql
-- 作用一样，explain分析，如果数据库对sql做过优化，性能应该一样
select * from a,b where a.id = b.aid;  
select * from a inner join b on a.id = b.aid;  
```


### 数据库事务
0. ACID
    - atomicity 原子性
    - consistency 一致性
    - isolation 隔离性
    - durability 持久性
    
1. mysql数据库[隔离级别](https://en.wikipedia.org/wiki/Isolation_(database_systems)#Isolation_levels)：默认隔离级别 可重复读

    - 0 Connection.TRANSACTION_NONE 不支持事务
    - 1 Connection.TRANSACTION_READ_UNCOMMITTED 读未提交 脏读、不可重复读、幻读. 一个事务的行修改在提交前可以被其他事务.
    如果任何修改回滚,另一个事务立马检索到无效的行
    - 2 Connection.TRANSACTION_READ_COMMITTED 读已提交 阻止脏读. 不可重复读、幻读. 只禁止一个事务从未提交的行读取
    - 4 Connection.TRANSACTION_REPEATABLE_READ 可重复读 阻止脏读、不可重复读. 幻读. (innoDB引擎中利用MVCC多版本并发控制解决了这个问题)
    禁止未提交的行读取事务;禁止一个事务读取一行,另一个事务修改该行,第一个事务重复读取该行获取到不同记录的情况
    - 8 Connection.TRANSACTION_SERIALIZABLE 串行化读 阻止脏读、不可重复读.、幻读.  包括TRANSACTION_REPEATABLE_READ,
    阻止一个事务读取满足条件的所有行,另一个事务插入满足条件的一行,第一个事务第二次重取读取会检索到额外的幻读记录.
        
    |隔离级别|脏读|不可重复读|幻读|
    |:---|:---|:---|:---|
    |TRANSACTION_NONE|-|-|-|
    |TRANSACTION_READ_UNCOMMITTED|可能|可能|可能|
    |TRANSACTION_READ_COMMITTED|不会|可能|可能|
    |TRANSACTION_REPEATABLE_READ|不会|不会|可能|
    |TRANSACTION_SERIALIZABLE|不会|不会|不会|

2. Spring 事务传播特性 逻辑事务
    - 编程方式(编程式、声明式)
    - 传播行为(../spring家族/spring.md 事务管理)
    
### 索引
https://dev.mysql.com/doc/refman/8.0/en/mysql-indexes.html

1. [B-tree](https://dev.mysql.com/doc/refman/8.0/en/glossary.html#glos_b_tree)

特点：

    - 始终保持排序
    - 精确匹配(=)exact matches
    - 范围(>、<、between)

与二叉树不同，B树可以有多个子节点


类型：

    - 主键索引
    - 聚集索引
    - 唯一索引
    
操作：

    - 查看索引 show index from table_name
    - 建表时候声明 key idx_name(table_column...)、index idx_name(table_column...)
    - create index idx_name on table_name(table_column...)
    - alter table table_name add(drop) index idx_name (table_column...)
    
场景：

    - 快速查找 where 字句匹配的行
    - To eliminate rows from consideration 考虑排除行 mysql通常选择可以从行中查找出最小行数的索引
    - 多列索引，优化器使用索引的最左匹配来查找行.如：(col1,col2,col3),(col1)/(col1,col2)/(col1,col2,col3)具备索引搜索功能
    - 使用 join 从其他表检索数据 如果列声明为相同类型和大小可以更有效的使用索引 这种情况下，varchar(10)和char(10)是一样的
    - 排序、分组 使用索引可以避免优化器进行排序
    
### 索引优化
1. 在 where 和 order by 涉及的列建立索引
2. 避免 select * 返回数据量太多及 orm 字段映射问题
3. 避免在 where 字句中做 null 判断 is null/is not null
4. 避免使用 != <> not in
5. 避免在索引列使用函数
6. 使用 exists 替换 in 提高效率
7. 区分度高的列作为索引
8. 最左匹配原则 联合索引遇到范围查询(between/>/</like)后停止匹配
9. 使用同类型进行比较
10. 连续数值使用between不用in

   
参考学习：
  
1. [数据库索引，到底是什么做的？](https://mp.weixin.qq.com/s/YMbRJwyjutGMD1KpI_fS0A)   
2.[mySql---数据库索引原理及优化](https://mp.weixin.qq.com/s/yPttZnuioV71a_qmE1P3ww)
3. [索引、锁](https://mp.weixin.qq.com/s/FSyE7Tz5A-Rc1bkC-tDqvA)
   
   

### 数据类型选择

注意：

    - 类型 长度默认 ：int 11;bigint 20
    - describe table_name 获取表结构信息 obtaining table structure information
   
   
|char|varchar|
|---|---|
|固定长度|可变长,按实际长度存储|

|timestamp|datetime|
|---|---|
|以UTC(世界标准时间)格式存储 空间上更有效|实际格式存储，存储字节、支持范围的区别|



    
### mysql 日志类别
1. 错误日志
2. 查询日志
3. 慢查询日志 设定阈值
4. 二进制日志 所有的更改操作
5. 事务日志




mysql 性能优化
1. https://mp.weixin.qq.com/s/Uib99Ogmjbs3Vym2vkXw5g
2. https://mp.weixin.qq.com/s/8W5KQKuHyJd9BQDwu0uY5Q



## 常用命令操作
1. 数据库登录 mysql -uroot -p123456
2. 查看数据库 show databases;
3. 查看表结构信息 describe table_name;
4. 帮助命令 ? 命令名称 eg: ? contents; ? show create table;...
5. 查看状态 show status; show global status like 'Max_used_connections'
6. 查看可设置的变量 show variables\G; show variables like '%max_con%';
8. 查看进程连接 show full processlist;
9. 执行过程分析 explain
## 数据类型
1. String type
    1. char 固定长度 0~255 数据检索时remove trailing spaces
    2. varchar 可变长度 0~65535 不考虑trailing spaces 使用1~2字节存储值的长度 <=255使用1字节否则2字节
    3. 通配符 4  
## 编码
1. lantin1 1个字符1个字节
2. gbk 1个字符2个字节
3. utf8 1个字符3个字节

使用整数存ip
