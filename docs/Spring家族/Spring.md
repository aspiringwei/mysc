##### ContextLoaderListener
    - 继承并私有ContextLoader this
    - contextInitialized(ServletContextEvent event)
    
ServletContext
ApplicationContextFacade

conf_center 配置中心 ConfigCenterConfig

DispatcherServletExtend

##### 事务管理
- 事务传播机制(理解验证)
    1. PROPAGATION_REQUIRED
    2. PROPAGATION_SUPPORTS
    3. PROPAGATION_MANDATORY
    4. PROPAGATION_REQUIRES_NEW
    5. PROPAGATION_NOT_SUPPORTED
    6. PROPAGATION_NEVER
    7. PROPAGATION_NESTED
    
    

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