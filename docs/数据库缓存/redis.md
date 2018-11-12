### [Redis](https://redis.io) 基于4.0.11版本 retrieve检索 
> 类似一个可持久化的memcached
1. 数据结构
1. Redis keys key的使用姿势
1. Redis expires 过期策略
1. 内存淘汰策略 配置 redis.conf
1. High Availability
1. 安装使用
1. 其他命令学习

#### [数据结构](https://redis.io/topics/data-types-intro) 

1. string 字符串 动态字符串 类似 java 的 ArrayList
    >二级制安全的字符串 Binary-safe strings. 
    
    1. 命令 help @string
    
        ```bash
           SETEX key seconds value 设置value并给key一个过期时间
           SETNX key value 给key设置一个value,除非key不存在
        ```
        
    2. 使用场景
        - string 类型存取、子字符串、长度 set/get/getrange/strlen
        - 计数器实现 incr/decr/incrby/decrby 原子操作
        - key指定过期时间 setex key value seconds value/set key value [ex seconds] [px milliseconds] [NX|XX]
        - 不存在设值 setnx key value
    
2. hash 散列/字典 类似 java 的 HashMap
    >which are maps composed of fields associated with values. 
    Both the field and the value are strings. 
    
    1. 命令 help @hash
    
        ```bash
           hset key field value
           hget key field
           hdel key field [field ...]
           hmset key field value[field value ...]
        ```
    
    2. 使用场景 hash结构存储着结构化数据，方便操作指定字段
        
3. list 列表基于链表实现 类似 java 的 LinkedList 增删O(1)/查询O(n)
    >按插入顺序排序的字符串元素集合，主要基于链表 collections of string elements sorted according to the order of insertion. 
    They are basically linked lists.  
    链表在头部 head 和尾部 tail 加入新元素的时间是常量固定的,查询慢，和数组相反  
    An important operation defined on Redis lists is the ability to pop elements. 
    1. 命令 help @list
    
        ```
           lpush key value [value ...] 头部(左边)添加元素
           rpush key value [value ...] 尾部(右边)添加元素
           lrange key start stop
           brpop key [key ...] timeout 阻塞规定时间返回
           rpoplpush source destination 安全的队列/循环列表
           brpoplpush source destination timeout
        ```
    2. 使用场景
        - rpush/lpop 队列 右进左出; rpush/rpop 堆栈
        - 消息队列 
        - LRANGE 分页查询
        - Communication between processes, using a consumer-producer pattern where the producer pushes items into a list, 
        and a consumer (usually a worker) consumes those items and executed actions. Redis has special list commands to 
        make this use case both more reliable and efficient.
        - 朋友圈发布一条动态 lpush 到 list 中，查看最新10条动态 lrange 0 9
        - 作为上限集合 capped collection. 使用 ltrim 命令记住保留最新N项，丢弃最老项
        - Blocking operations on lists 阻塞操作 
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

4. set 唯一无序字符串集合 类似 java 的 HashSet 
    >不重复、无序的字符串元素的集合 collections of unique, unsorted(unordered) string elements. 
    
    1. 命令 help @set
        ```bash
          sadd key member [member ...] 将成员添加到集合中
          SCARD key 获取集合中的成员数
   
          SDIFF key [key ...] 差集
          SDIFFSTORE destination key [key ...] 差集,存储结果在key目标集合中
          SINTER key [key ...] 取交集
          SINTERSTORE destination key [key ...]
          SUNION key [key ...] 并集
          SUNIONSTORE destination key [key ...]
        
          SISMEMBER key member 确定给定值是否是集合的成员
          SMEMBERS key 获取集合中所有成员
        
          SMOVE source destination member 将成员从一个集合移动到另一个集合
        
          SPOP key 随机删除一个元素并返回该元素
          SRANDMEMBER key 从集合中获取随机成员 不删除
          SREM key member 删除成员
        ```

    2. 场景
        - 全局去重
        - 表示对象间关系，如实现用户标签
            ```
            ## news article ID 1000 is tagged with tags 1, 2, 5 and 77
            > sadd news:1000:tags 1 2 5 77
            (integer) 4
            ```

5. sorted_set 有序集合 基于跳表数据结构实现 访问速度快，唯一性，不重复
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
        
           zadd key [NX|XX] [CH] [INCR] score member [score member ...] 添加成员到有序集,如果存在则更新score
           zcard key 获取已排序集中的成员数
           zcount key min max 统计具有给定值内分数的有序集合中的成员
           zincrby key increment member 增加已排序集中成员的分数
           
           zscore key member 返回成员分数
           zrank key member 返回排序集中成员的索引
           zrange key start stop [WITHSCORES] 通过索引返回排序集的范围成员
           zrangebyscore key min max [WITHSCORES] [LIMIT offset count] - Return a range of members in a sorted set, by score
     
           zrem key member - Remove a member from a sorted set
           zremrangebyrank key start top - Remove all members in a sorted set within the given indexes
           zremrangebyscore key min max - Remove all members in a sorted set within the given scores
           
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
    # :::淘汰策略  ::: Least recently used 最近最少使用
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
1. 官网下载安装包 wget url 、make或 yum install -y redis
3. 配置 redis-config
4. [安装问题](https://blog.csdn.net/wzygis/article/details/51705559)

./redis-cli -h ip -p port -a auth 

#### 其他命令学习
1. help @generic
2. help @server

https://juejin.im/book/m/5afc2e5f6fb9a07a9b362527/section/5afc2e5f51882542714ff291



- session: hash
