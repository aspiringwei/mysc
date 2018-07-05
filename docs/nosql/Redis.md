### [Redis](https://redis.io)
retrieve检索

#### [数据结构](https://redis.io/topics/data-types-intro)
1. strings
    >二级制安全的字符串 Binary-safe strings.
    1. 命令
        ```
        ## append <key> <value>  #将给定的value 追加到原值的末尾
        > set mykey somevalue
        OK
        > get mykey
        "somevalue"
        ## 带参数选项 options
        > set mykey newval nx
        (nil) 存在则设置失败
        > set mykey newval xx
        OK    存在则设置成功
        ## 原子操作 INCR(++) DECR(--) INCRBY DECRBY
        > set counter 100
        OK
        > incr counter
        (integer) 101
        > incr counter
        (integer) 102
        > incrby counter 50
        (integer) 152
        ## 利用 MSET MGET 设置和检索 multiple keys 的值以减少延迟
        > mset a 10 b 20 c 30
        OK
        > mget a b c
        1) "10"
        2) "20"
        3) "30"
        ## key 修改和查询 exists del type 对 keys of any type 有效
        > set mykey hello
        OK
        > exists mykey
        (integer) 1
        > type mykey
        string
        > del mykey
        (integer) 1
        > exists mykey
        (integer) 0
        > type mykey
        none
        ```
2. hashes
    >which are maps composed of fields associated with values. Both the field and the value are strings. This is very similar to Ruby or Python hashes.
    1. 命令
        ```
        ## hset 设置单个 field，hmset sets multiple fields of the hash
        ## hget retrieves a single field，hmget return an array of values
        ## 操作单个字段的命令 如：HINCRBY 
        ## 命令集合 https://redis.io/commands#hash
        > hmset user:1000 username antirez birthyear 1977 verified 1
        OK
        > hget user:1000 username
        "antirez"
        > hget user:1000 birthyear
        "1977"
        > hgetall user:1000
        1) "username"
        2) "antirez"
        3) "birthyear"
        4) "1977"
        5) "verified"
        6) "1"
        ```
3. lists
    >按插入顺序排序的字符串元素集合，主要基于链表 collections of string elements sorted according to the order of insertion. They are basically linked lists.  
    链表在头部 head 和尾部 tail 加入新元素的时间是常量固定的,查询慢，和数组相反  
    An important operation defined on Redis lists is the ability to pop elements. 
    1. 命令
        ```
        ## LPUSH adds a new element into a list, on the left (at the head).
        ## RPUSH adds a new element into a list, on the right (at the tail). 
        ## lpop rpop 弹出\取出元素
        > rpush mylist A
        (integer) 1
        > rpush mylist B
        (integer) 2
        > lpush mylist first
        (integer) 3
        > lrange mylist 0 -1
        1) "first"
        2) "A"
        3) "B"
        ```
    2. 使用场景
        - Remember the latest updates posted by users into a social network.
        - Communication between processes, using a consumer-producer pattern where the producer pushes items into a list, and a consumer (usually a worker) consumes those items and executed actions. Redis has special list commands to make this use case both more reliable and efficient.
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
4. sets
    >唯一无序的字符串元素的集合 collections of unique, unsorted(unordered) string elements.
    1. 命令
        ```
        ## SUNIONSTORE 合并多个 sets 并存储到另外一个 set 中
        ##  SPOP 随机删除一个元素 SRANDMEMBER 随机返回不删除元素
        > sadd myset 1 2 3
        (integer) 3
        > smembers myset
        1. 3
        2. 1
        3. 2
        > sismember myset 3
        (integer) 1 存在
        > sismember myset 30
        (integer) 0 不存在
        > sunionstore game:1:myset myset
        (integer) 3
        > spop game:1:myset
        "2"
        > scard game:1:myset
        (integer) 2
        ```
    2. 场景
        - 表示对象间关系，如实现标签
            ```
            ## news article ID 1000 is tagged with tags 1, 2, 5 and 77
            > sadd news:1000:tags 1 2 5 77
            (integer) 4
            ```
5. sorted sets 
    > similar to Sets but where every string element is associated to a floating number value, called score. The elements are always taken sorted by their score, so unlike Sets it is possible to retrieve a range of elements (for example you may ask: give me the top 10, or the bottom 10).  
    Sorted sets are a data type which is similar to a mix between a Set(unique,non-repeated) and a Hash(score-value).    
    访问速度快，唯一性，不重复
    1. 排序规则
        1. score 比较 If A and B are two elements with a different score, then A > B if A.score is > B.score.
        2. 字段顺序比较 If A and B have exactly the same score, then A > B if the A string is lexicographically greater than the B string. A and B strings can't be equal since sorted sets only have unique elements.
    2. 命令
        ```
        ## zadd 添加 一个元素 在元素之前的是 score O(log(N))
        ## zrange 检索数据 zrevrange 相反顺序 options:withscores return scores
        ## 范围操作 zrangebyscore .infinity无穷大 -infinity 无穷小-inf
        ## zremrangebyscore 删除集合，并返回已删除元素的数量。
        ## 字典 score 操作 zrangebylex zrevrangebylex zremrangebylex zlexcount
        ## The ZREVRANK command is also available in order to get the rank, considering the elements sorted a descending way.
        > zadd hackers 1969 "Linus Torvalds"
        (integer) 1
        > zadd hackers 1912 "Alan Turing"
        (integer) 1
        > zrange hackers 0 -1
        1) "Alan Turing"
        2) "Linus Torvalds"
        > zrangebyscore hackers -inf 1950
        1) "Alan Turing"
        > zremrangebyscore hackers 1940 1960
        (integer) 1
        > zrangebylex hackers [B [P
        3) "Linus Torvalds"
        ```
6. Bit arrays (or simply bitmaps): it is possible, using special commands, to handle String values like an array of bits: you can set and clear individual bits, count all the bits set to 1, find the first set or unset bit, and so forth.
7. HyperLogLogs: this is a probabilistic data structure which is used in order to estimate the cardinality of a set. Don't be scared, it is simpler than it seems... See later in the HyperLogLog section of this tutorial.

##### Redis keys
- The empty string is also a valid key. key 查找和比较代价比较昂贵 比如可以 hash 如 sha1 
- Very long keys are not a good idea. 例如：u1000flw > user:1000:followers
- Very short keys are often not a good idea. 
- Try to stick with a schema. For instance "object-type:id" is a good idea, as in "user:1000". Dots or dashes are often used for multi-word fields, as in "comment:1234:reply.to" or "comment:1234:reply-to".
- The maximum allowed key size is 512 MB. 最大存储大小不能超过 512 mb

##### Redis expires
- They can be set both using seconds or milliseconds precision. 精度：秒或毫秒
- However the expire time resolution is always 1 millisecond.
- Information about expires are replicated and persisted on disk, the time virtually passes when your Redis server remains stopped (this means that Redis saves the date at which a key will expire).
    ```shell
    > set key 100 ex 10
    OK
    ## ttl 命令用来检查 key 剩余存活时间
    > ttl key
    (integer) 9
    ```
    
Note: 0 and -1 means from element index 0 to the last element (-1 works here just as it does in the case of the LRANGE command).


#### High Availability[Sentinel](https://redis.io/topics/sentinel)
- **Monitoring** Sentinel constantly checks if your master and slave instances are working as expected.
- **Notification** Sentinel can notify the system administrator, another computer programs, via an API, that something is wrong with one of the monitored Redis instances.
- **Automatic failover** If a master is not working as expected, Sentinel can start a failover process where a slave is promoted to master, the other additional slaves are reconfigured to use the new master, and the applications using the Redis server informed about the new address to use when connecting.
- **Configuration provider.** Sentinel acts as a source of authority for clients service discovery: clients connect to Sentinels in order to ask for the address of the current Redis master responsible for a given service. If a failover occurs, Sentinels will report the new address.