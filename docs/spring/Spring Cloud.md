  ```$xslt
1.SpringCloud相关概念
1.1Spring Cloud介绍
1.2微服务架构
1.3服务治理
1.4与Dubbo的对比
2.SpringCloud的组件应用
2.1服务注册
2.2服务消费者Ribbon
2.3服务消费者Feign
2.4断路器
2.5分布式配置中心SpringCloudConfig
2.6路由网关zuul
2.7服务认证Client
2.8消息总线Bus
```
### Spring Cloud

##### 服务注册中心 eureka
- 对比 https://blog.csdn.net/dengyisheng/article/details/71215234

##### 断路器 hystrix
> 1. Netflix 已经为我们创建了 Hystrix 库来实现服务降级、服务熔断、线程隔离、请求缓存、请求合并以及服务监控（Hystrix Dashboard）等强大功能，在微服务架构中，多层服务调用是非常常见的。
> 2. 较底层的服务中的服务故障可能导致级联故障，当对特定的服务的调用达到一个阀值（Hystric 是5秒20次） 断路器将会被打开，故障百分比大于circuitBreaker.errorThresholdPercentage（默认值：> 50％）时metrics.rollingStats.timeInMilliseconds（默认10秒），断路打开后，开发人员可以回退机制。

##### 分布式配置中心 SpringCloudConfig
- 默认采用git来存储配置信息
 
##### 网关 zuul http://wiki.10101111.com/pages/viewpage.action?pageId=177578215
> zuul默认和Ribbon结合实现了负载均衡的功能
- 身份验证
- 压力测试
- Canary Testing
- 动态路由
- 安全控制

##### 服务认证 Client JWT 
> 相比于session，它无需保存在服务器，不占用服务器内存开销

##### 消息总线 Spring Cloud Bus
> Spring cloud bus主要由发送端、接收端和事件组成

![image.png](https://upload-images.jianshu.io/upload_images/2304810-ded42e0fd78edd19.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)