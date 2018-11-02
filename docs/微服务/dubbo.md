[Dubbo](http://dubbo.apache.org/zh-cn/docs/user/quick-start.html)
---
> 阿里开源的高性能的rpc分布式服务框架 Apache孵化项目
支持 dubbo://、rmi://、hessian://、http://、rest://等协议。
dubbo协议序列化缺省为hessian




#### 节点角色
1. Provider
2. Consumer
3. Register
4. Monitor
5. Container
![调用关系](http://dubbo.apache.org/docs/zh-cn/dev/sources/images/dubbo-relation.jpg)


#### 配置属性
1. timeout 方法调用超时 默认1000 毫秒
2. retries 失败重试次数 默认重试2次
3. loadbalance 负载均衡算法(随机、轮询、最少活跃调用数、一致性hash) 默认随机
4. actives 最大并发调用限制
5. cluster 集群容错 默认failover 失败自动切换
    - failover 失败自动切换 通常用于读操作
    - failfast 快速失败 通常用于非幂等性的写操作
    - failsafe 出现异常直接忽略 通常用于写审计日志等操作
    - failback 失败自动恢复 后台记录失败请求，定时重发，通常用于消息通知操作
    - forking 并行调用多个服务器 只要一个成功返回 通常用于实时性要求较高的读操作


#### dubbo 部分源码学习**