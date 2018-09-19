## Mongodb http://wiki.10101111.com/display/WUHANTECHCENTER/Monodb
#### 背景
    - 业务复杂度增加，关系型数据库的I/O压力增加
    - 读写分离的基础之上，由于大量数据的增加，写压力开始出现瓶颈
    - 集群或者分片复杂度高，不易维护
    - 高并发下效率低下
    - 结构化数据的设计，不够灵活
    - 关系型数据库数据挖掘困难
#### 特点
    - 面向文档 : MongoDB的数据结构是Json形式，存储形式是Bson ,Bson是Json的二进制形式，一文档，就相当于一Json;
    - 高性能 : MongoDB做为内存型数据库，数据操作会先写入内存，然后再会持久化到硬盘中去,读取数据，是先从内存中读取，速度很快。
    - 高可用 : MongoDB支持Master-Slave的主从复制，故障切换模式，在启动时在配置文件中配置replSet=主从名称，就可以实现。
    - 易扩展 : MongoDB支持Sharding分片，可以任意添加Shard,采用不同的分片策咯如：id,hash等
    - 丰富的查询语言 : MongoDB支持关系型数据库那种增/删/改/查，查询语句多样化，比如$gt，$gte，$lt，$lte，$all，$in，$exists，$mod，$rege，$size等查询语句