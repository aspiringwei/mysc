## [Spring](https://spring.io/)

##### ContextLoaderListener
    - 继承并私有ContextLoader this
    - contextInitialized(ServletContextEvent event)
    
ServletContext
ApplicationContextFacade

conf_center 配置中心 ConfigCenterConfig

DispatcherServletExtend

##### 事务管理
- 事务传播机制(理解验证)
    1. PROPAGATION_REQUIRED 如果当前没有事务，就新建；如果存在，则加入
    2. PROPAGATION_SUPPORTS 支持当前事务，如果没有事务则以非事务方式执行
    4. PROPAGATION_REQUIRES_NEW 创建一个新事务与外层无关，内部事务执行外部事务被挂起
    5. PROPAGATION_NOT_SUPPORTED 外层事务挂起，如果外层没有事务直接执行
    6. PROPAGATION_NEVER 不能被拥有事务的方法调用
    3. PROPAGATION_MANDATORY 不能被非事务的方法调用否则抛异常
    7. PROPAGATION_NESTED 创建一个依赖外部事务的子事务，外层的提交回滚子事务也会提交回滚；如果没有类似1
    
    

#### spring-beans
- bean 工厂的实现应该尽可能支持标准bean的生命周期接口
- Aware 标记接口 BeanFactory AbstractBeanFactory


- Instantiate the bean 实例化(Don't override the class with CGLIB if no overrides.否则 Must generate CGLIB subclass.)
-> createBeanInstance(autowireConstructor、instantiateBean(使用默认无参构造器))
BeanUtils.instantiateClass(constructorToUse); Constructor.newInstance(args);

- Initialize the bean instance. 初始化
 initializeBean: AbstractAutowireCapableBeanFactory
1. invokeAwareMethods
-> setBeanName()
-> setBeanClassLoader()
-> setBeanFactory()

2. invokeAwareInterfaces
: applyBeanPostProcessorsBeforeInitialization(BeanPostProcessor.postProcessBeforeInitialization) -> 
-> setEnvironment
-> setEmbeddedValueResolver
-> setResourceLoader
-> setApplicationEventPublisher
-> setMessageSource
-> setApplicationContext

3. invokeInitMethods
-> InitializingBean.afterPropertiesSet() / invokeCustomInitMethod()
4. applyBeanPostProcessorsAfterInitialization(BeanPostProcessor.postProcessAfterInitialization)

- Register bean as disposable. 添加到当前工厂 disposable beans 列表中
-> registerDisposableBeanIfNecessary

* bean 的创建:
实例化、初始化、注册添加 DisposableBean
* bean 的销毁:
DisposableBean.destroy()、自身Bean的destroy()方法
* bean 的作用域:
singleton/prototype/request/session/globalSession(Portlet容器)

AOP：底层JDK动态代理、cglib字节码操纵
- Aspect 方面 xml配置的普通类、@Aspect声明的类
- Join Point 方法 可利用的机会
- Advice 
- Pointcut 定义Aspect被应用到哪些Join Point