### [Redis](https://redis.io) 基于2.4.10 retrieve检索 
1. 数据结构
1. Redis keys key的使用姿势
1. Redis expires 过期策略
1. 内存淘汰策略 配置 redis.conf
1. High Availability
1. 安装使用
1. 其他命令学习

#### [数据结构](https://redis.io/topics/data-types-intro) 

1. strings 字符串 动态字符串 类似 java 的 ArrayList
    >二级制安全的字符串 Binary-safe strings. 
    
    1. 命令 help @string
    
        ```bash
          APPEND key value
          summary: Append a value to a key 将值附加到key中
          since: 1.3.3
        
          DECR key
          summary: Decrement the integer value of a key by one 递减1 [计数]
          since: 0.07
        
          DECRBY key decrement
          summary: Decrement the integer value of a key by the given number 递减给定值 [计数]
          since: 0.07
        
          GET key
          summary: Get the value of a key 获取key值
          since: 0.07
        
          GETBIT key offset
          summary: Returns the bit value at offset in the string value stored at key
          since: 2.1.8
        
          GETSET key value
          summary: Set the string value of a key and return its old value 设置key的string值并返回旧值
          since: 0.091
        
          INCR key
          summary: Increment the integer value of a key by one 递增1 [计数]
          since: 0.07
        
          INCRBY key increment
          summary: Increment the integer value of a key by the given number 递增给定值 [计数]
          since: 0.07
        
          MGET key [key ...]
          summary: Get the values of all the given keys 获取所有给定key的值 [批量]
          since: 0.07
        
          MSET key value [key value ...]
          summary: Set multiple keys to multiple values 给多个key设置多个值 [批量]
          since: 1.001
        
          MSETNX key value [key value ...]
          summary: Set multiple keys to multiple values, only if none of the keys exist 不存在任何key时, 给多个key设置多个值
          since: 1.001
        
          SET key value
          summary: Set the string value of a key 设置key值
          since: 0.07
        
          SETBIT key offset value
          summary: Sets or clears the bit at offset in the string value stored at key
          since: 2.1.8
        
          SETEX key seconds value
          summary: Set the value and expiration of a key 设置key的值和到期时间
          since: 1.3.10
        
          SETNX key value
          summary: Set the value of a key, only if the key does not exist 如果key不存在则设置值
          since: 0.07
        
          SETRANGE key offset value
          summary: Overwrite part of a string at key starting at the specified offset
          since: 2.1.8
        
          STRLEN key
          summary: Get the length of the value stored in a key 获取存储在key的值的长度
          since: 2.1.2
        
          SUBSTR key start end
          summary: Get a substring of the string stored at a key
          since: 1.3.4

        ```
        
    2. 使用场景
        - string 类型存取、子字符串、长度 set/get/substr/strlen
        - 计数器实现 incr/decr
        - key指定过期时间 setex
        - 不存在设值 setnx
    
2. hashes 散列/字典 类似 java 的 HashMap
    >which are maps composed of fields associated with values. 
    Both the field and the value are strings. 
    
    1. 命令 help @hash
    
        ```bash
          HDEL key field
          summary: Delete a hash field 删除hash字段
          since: 1.3.10
        
          HEXISTS key field
          summary: Determine if a hash field exists 确定hash字段是否存在
          since: 1.3.10
        
          HGET key field
          summary: Get the value of a hash field 获取hash字段的值
          since: 1.3.10
        
          HGETALL key
          summary: Get all the fields and values in a hash 获取hash中所有字段和值
          since: 1.3.10
        
          HINCRBY key field increment
          summary: Increment the integer value of a hash field by the given number 给hash字段的int值递增给定值
          since: 1.3.10
        
          HKEYS key
          summary: Get all the fields in a hash 获取hash中所有字段
          since: 1.3.10
        
          HLEN key
          summary: Get the number of fields in a hash 获取hash中字段数
          since: 1.3.10
        
          HMGET key field [field ...]
          summary: Get the values of all the given hash fields 获取所有给定hash字段的值
          since: 1.3.10
        
          HMSET key field value [field value ...]
          summary: Set multiple hash fields to multiple values
          since: 1.3.8
        
          HSET key field value
          summary: Set the string value of a hash field
          since: 1.3.10
        
          HSETNX key field value
          summary: Set the value of a hash field, only if the field does not exist
          since: 1.3.8
        
          HVALS key
          summary: Get all the values in a hash 获取hash中所有值
          since: 1.3.10
    
        ```
    
    2. 使用场景 hash结构存储着结构化数据，方便操作指定字段
        
3. lists 列表 类似 java 的 LinkedList 增删O(1)/查询O(n)
    >按插入顺序排序的字符串元素集合，主要基于链表 collections of string elements sorted according to the order of insertion. 
    They are basically linked lists.  
    链表在头部 head 和尾部 tail 加入新元素的时间是常量固定的,查询慢，和数组相反  
    An important operation defined on Redis lists is the ability to pop elements. 
    1. 命令 help @list
    
        ```bash
           BLPOP key [key ...] timeout
          summary: Remove and get the first element in a list, or block until one is available 删除并返回list的第一个元素或阻塞timeout直到一个元素有效的
          since: 1.3.1
        
          BRPOP key [key ...] timeout
          summary: Remove and get the last element in a list, or block until one is available 删除并返回list的最后一个元素或阻塞timeout直到一个元素有效的
          since: 1.3.1
        
          BRPOPLPUSH source destination timeout
          summary: Pop a value from a list, push it to another list and return it; or block until one is available 
          since: 2.1.7
        
          LINDEX key index
          summary: Get an element from a list by its index 通过索引index从列表list中获取元素
          since: 0.07
        
          LINSERT key BEFORE|AFTER pivot value
          summary: Insert an element before or after another element in a list 在指定元素的前或后插入一个元素
          since: 2.1.1
        
          LLEN key
          summary: Get the length of a list 获取list长度
          since: 0.07
        
          LPOP key
          summary: Remove and get the first element in a list 删除并获取列表中的第一个元素
          since: 0.07
        
          LPUSH key value
          summary: Prepend a value to a list 前置添加值到列表中
          since: 0.07
        
          LPUSHX key value
          summary: Prepend a value to a list, only if the list exists 只有list存在,才会前置添加值到list
          since: 2.1.1
        
          LRANGE key start stop
          summary: Get a range of elements from a list 从列表中获取一系列元素 分页查询
          since: 0.07
        
          LREM key count value
          summary: Remove elements from a list 从列表中删除元素
          since: 0.07
        
          LSET key index value
          summary: Set the value of an element in a list by its index 通过索引设置列表中元素的值
          since: 0.07
        
          LTRIM key start stop
          summary: Trim a list to the specified range 将列表修整到指定范围
          since: 0.07
        
          RPOP key
          summary: Remove and get the last element in a list 删除并返回list的最后一个元素
          since: 0.07
        
          RPOPLPUSH source destination
          summary: Remove the last element in a list, append it to another list and return it
          since: 1.1
        
          RPUSH key value
          summary: Append a value to a list
          since: 0.07
        
          RPUSHX key value
          summary: Append a value to a list, only if the list exists
          since: 2.1.1

        ```
    2. 使用场景
        - rpush/lpop 队列 右进左出; rpush/rpop 栈
        - 消息队列
        - LRANGE 分页查询
        - Remember the latest updates posted by users into a social network.
        - Communication between processes, using a consumer-producer pattern where the producer pushes items into a list, 
        and a consumer (usually a worker) consumes those items and executed actions. Redis has special list commands to 
        make this use case both more reliable and efficient.
        - 例子： 朋友圈发布一条动态 lpush 到 list 中，查看最新10条动态 lrange 0 9
        - 作为上限集合 capped collection. 使用 ltrim 命令记住保留最新N项，丢弃最老项
        - Blocking operations on lists 
            1. To push items into the list, producers call LPUSH.
            2. To extract / process items from the list, consumers call RPOP.
            3. polling 迫使 redis 和客户端执行无用的命令。redis 使用 BRPOP、BLPOP 来 block
            4. It is possible to build safer queues or rotating queues using RPOPLPUSH.
            5. There is also a blocking variant of the command, called BRPOPLPUSH.
            ```
            ## 等待列表中元素，如果 5s 内没有新元素则返回null
            > brpop tasks 5
            1) "tasks"
            2) "do_something"
           ```

4. sets 唯一无序字符串集合 类似 java 的 HashSet 
    >不重复、无序的字符串元素的集合 collections of unique, unsorted(unordered) string elements. 
    
    1. 命令 help @set
        ```bash
          SADD key member
          summary: Add a member to a set 将成员添加到集合中
          since: 0.07
        
          SCARD key
          summary: Get the number of members in a set 获取集合中的成员数
          since: 0.07
        
          SDIFF key [key ...]
          summary: Subtract multiple sets 差集
          since: 0.100
        
          SDIFFSTORE destination key [key ...]
          summary: Subtract multiple sets and store the resulting set in a key 差集,存储结果在key目的地中
          since: 0.100
        
          SINTER key [key ...]
          summary: Intersect multiple sets 取交集
          since: 0.07
        
          SINTERSTORE destination key [key ...]
          summary: Intersect multiple sets and store the resulting set in a key
          since: 0.07
        
          SISMEMBER key member
          summary: Determine if a given value is a member of a set 确定给定值是否是集合的成员
          since: 0.07
        
          SMEMBERS key
          summary: Get all the members in a set 获取集合中所有成员
          since: 0.07
        
          SMOVE source destination member
          summary: Move a member from one set to another 将成员从一个集合移动到另一个集合
          since: 0.091
        
          SPOP key
          summary: Remove and return a random member from a set 随机删除一个元素并返回该元素
          since: 0.101
        
          SRANDMEMBER key
          summary: Get a random member from a set 从集合中获取随机成员
          since: 1.001
        
          SREM key member
          summary: Remove a member from a set 删除
          since: 0.07
        
          SUNION key [key ...]
          summary: Add multiple sets 并集
          since: 0.091
        
          SUNIONSTORE destination key [key ...]
          summary: Add multiple sets and store the resulting set in a key
          since: 0.091

    2. 场景
        - 全局去重
        - 表示对象间关系，如实现标签
            ```
            ## news article ID 1000 is tagged with tags 1, 2, 5 and 77
            > sadd news:1000:tags 1 2 5 77
            (integer) 4
            ```

5. sorted sets 有序集合 访问速度快，唯一性，不重复 跳表
    > similar to Sets but where every string element is associated to a floating number value, called score. 
    The elements are always taken sorted by their score, so unlike Sets it is possible to retrieve a range of elements 
    (for example you may ask: give me the top 10, or the bottom 10).  
    Sorted sets are a data type which is similar to a mix between a Set(unique,non-repeated) and a Hash(score-value).
    和集合类似但是每一个元素都有一个浮点数(得分)相关联.元素总是按score排序,可以检索一系列元素如查找top 10.
    
    1. 排序规则
        1. score 比较 If A and B are two elements with a different score, then A > B if A.score is > B.score.
        2. 字段顺序比较 If A and B have exactly the same score, then A > B if the A string is lexicographically greater than
         the B string. A and B strings can't be equal since sorted sets only have unique elements.
    
    2. 命令 help @sorted_set
        
        ```bash
        
          ZADD key score member
          summary: Add a member to a sorted set, or update its score if it already exists 添加成员到有序集,如果存在则更新score
          since: 1.1
        
          ZCARD key
          summary: Get the number of members in a sorted set 获取已排序集中的成员数
          since: 1.1
        
          ZCOUNT key min max
          summary: Count the members in a sorted set with scores within the given values 计算具有给定值内分数的有序集合中的成员
          since: 1.3.3
        
          ZINCRBY key increment member
          summary: Increment the score of a member in a sorted set 增加已排序集中成员的分数
          since: 1.1
        
          ZINTERSTORE destination numkeys key [key ...] [WEIGHTS weight] [AGGREGATE SUM|MIN|MAX]
          summary: Intersect multiple sorted sets and store the resulting sorted set in a new key 
          since: 1.3.10
        
          ZRANGE key start stop [WITHSCORES]
          summary: Return a range of members in a sorted set, by index 无穷小-inf
          since: 1.1
        
          ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count]
          summary: Return a range of members in a sorted set, by score
          since: 1.050
        
          ZRANK key member
          summary: Determine the index of a member in a sorted set 确定已排序集中成员的索引
          since: 1.3.4
        
          ZREM key member
          summary: Remove a member from a sorted set 删除元素
          since: 1.1
        
          ZREMRANGEBYRANK key start stop
          summary: Remove all members in a sorted set within the given indexes
          since: 1.3.4
        
          ZREMRANGEBYSCORE key min max
          summary: Remove all members in a sorted set within the given scores
          since: 1.1
        
          ZREVRANGE key start stop [WITHSCORES]
          summary: Return a range of members in a sorted set, by index, with scores ordered from high to low
          since: 1.1
        
          ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT offset count]
          summary: Return a range of members in a sorted set, by score, with scores ordered from high to low
          since: 2.1.6
        
          ZREVRANK key member
          summary: Determine the index of a member in a sorted set, with scores ordered from high to low 成员索引
          since: 1.3.4
        
          ZSCORE key member
          summary: Get the score associated with the given member in a sorted set 成员分数
          since: 1.1
        
          ZUNIONSTORE destination numkeys key [key ...] [WEIGHTS weight] [AGGREGATE SUM|MIN|MAX]
          summary: Add multiple sorted sets and store the resulting sorted set in a new key
          since: 1.3.10
    
        ```

    3. 应用场景
        - 排行榜 top N
        - 

6. Bit arrays (or simply bitmaps ): it is possible, using special commands, to handle String values like an array of bits: 
you can set and clear individual bits, count all the bits set to 1, find the first set or unset bit, and so forth.
7. HyperLogLogs: this is a probabilistic data structure which is used in order to estimate the cardinality of a set. 
Don't be scared, it is simpler than it seems... See later in the HyperLogLog section of this tutorial.

#### Redis keys key的使用姿势
- The empty string is also a valid key. key 查找和比较代价比较昂贵 比如可以 hash 如 sha1 
- Very long keys are not a good idea. 例如：u1000flw > user:1000:followers
- Very short keys are often not a good idea. 
- Try to stick with a schema. For instance "object-type:id" is a good idea, as in "user:1000". Dots or dashes are often used for multi-word fields, 
as in "comment:1234:reply.to" or "comment:1234:reply-to".
- The maximum allowed key size is 512 MB. 最大存储大小不能超过 512 mb

#### Redis expires 过期策略

- They can be set both using seconds or milliseconds(毫秒) precision. However the expire time resolution(解析度/分辨率) is always 1 millisecond. 
Information about expires are replicated and persisted on disk, the time virtually passes when your Redis server remains stopped (this means 
that Redis saves the date at which a key will expire).

    ```base
    ## expireAt key timestamp 将key的到期时间设置为UNIX时间戳
    ## expire key seconds 设置key的生存时间，以秒为单位
    ## setex key seconds value 设置key的值和过期时间
    ## ttl key 命令用来检查 key 存活时间 -1: 不存在key或没有设置过期时间
    ```
    
    Note: 0 and -1 means from element index 0 to the last element (-1 works here just as it does in the case of the LRANGE command).


#### 内存淘汰策略 配置 redis.conf
    
    ```base
    # 配置最大内存
    # maxmemory <bytes>
    # redis 达到 maxmemory 如何选择要删除的内容
    # MAXMEMORY POLICY: how Redis will select what to remove when maxmemory
    # is reached? You can select among five behavior:
    # :::淘汰策略::: Least recently used 最近最少使用
    # volatile-lru -> remove the key with an expire set using an LRU algorithm 使用LRU算法删除带有过期设置的key 【默认策略】 
    # allkeys-lru -> remove any key accordingly to the LRU algorithm 根据LRU算法删除任意key
    # volatile-random -> remove a random key with an expire set 随机删除带有过期设置的key
    # allkeys->random -> remove a random key, any key 随机删除任意key
    # volatile-ttl -> remove the key with the nearest expire time (minor TTL) 删除最近到期时间的key
    # noeviction -> don't expire at all, just return an error on write operations 不要过期,只是在写操作上返回错误
    
    ```

#### High Availability[Sentinel](https://redis.io/topics/sentinel)
- **Monitoring** Sentinel constantly checks if your master and slave instances are working as expected.
- **Notification** Sentinel can notify the system administrator, another computer programs, via an API, that something is wrong with one of the monitored Redis instances.
- **Automatic failover** If a master is not working as expected, Sentinel can start a failover process where a slave is promoted to master, the other additional slaves are reconfigured to use the new master, and the applications using the Redis server informed about the new address to use when connecting.
- **Configuration provider.** Sentinel acts as a source of authority for clients service discovery: clients connect to Sentinels in order to ask for the address of the current Redis master responsible for a given service. If a failover occurs, Sentinels will report the new address.



#### 安装使用
1. 官网下载安装包 wget url 或 yum install -y redis
3. 配置 redis-config
4. [安装问题](https://blog.csdn.net/wzygis/article/details/51705559)

./redis-cli -h ip -p port -a auth 

#### 其他命令学习
1. help @generic
2. help @server

https://juejin.im/book/m/5afc2e5f6fb9a07a9b362527/section/5afc2e5f51882542714ff291



- session: hash
