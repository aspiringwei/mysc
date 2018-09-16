select * from a,b where a.id = b.aid;  
select * from a inner join b on a.id = b.aid;  
作用一样，explain分析，如果数据库对sql做过优化，性能应该一样


[隔离级别](https://en.wikipedia.org/wiki/Isolation_(database_systems)#Isolation_levels)

    - 0 Connection.TRANSACTION_NONE 不支持事务
    - 1 Connection.TRANSACTION_READ_UNCOMMITTED 读未提交 脏读、不可重复读、幻读. 一个事务的行修改在提交前可以被其他事务.
    如果任何修改回滚,另一个事务立马检索到无效的行
    - 2 Connection.TRANSACTION_READ_COMMITTED 读已提交 阻止脏读. 不可重复读、幻读. 只禁止一个事务从未提交的行读取
    - 4 Connection.TRANSACTION_READ_UNCOMMITTED TRANSACTION_REPEATABLE_READ 可重复读 阻止脏读、不可重复读. 幻读. 
    禁止未提交的行读取事务;禁止一个事务读取一行,另一个事务修改该行,第一个事务重复读取该行获取到不同记录的情况
    - 8 Connection.TRANSACTION_SERIALIZABLE 串行化读 阻止脏读、不可重复读.、幻读.  包括TRANSACTION_REPEATABLE_READ,
    阻止一个事务读取满足条件的所有行,另一个事务插入满足条件的一行,第一个事务第二次重取读取会检索到额外的幻读记录.


### 索引
https://dev.mysql.com/doc/refman/8.0/en/mysql-indexes.html

1. [B-tree](https://dev.mysql.com/doc/refman/8.0/en/glossary.html#glos_b_tree)

特点：

    - 始终保持排序
    - 精确匹配(=)exact matches
    - 范围(>、<、between)

与二叉树不同，B树可以有多个子节点



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
    
    
    




注意：

    - 类型 长度默认 ：int 11;bigint 20
    - describe table_name 获取表结构信息 obtaining table structure information