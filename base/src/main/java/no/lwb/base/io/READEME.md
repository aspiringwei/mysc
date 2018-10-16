[IO](https://docs.oracle.com/javase/tutorial/essential/io/)
---
> 文件、Socket 网络通信

0. 
    - 同步/异步: 可靠有序的运行机制,后续任务需要等待当前任务结束才可以执行.异步则相反,其他任务不需要等待当前任务调用返回,通常依靠事件/回调机制保证任务间次序
      
    - 阻塞/非阻塞: 执行阻塞操作,当前线程会处于阻塞状态无法执行其他操作,需要等条件满足才可以继续执行. 非阻塞不管IO操作是否结束直接返回,相应操作在后台继续处理
    
1. BIO 同步阻塞 IO Basic I/O

    Socket、serverSocket、HttpURLConnection 
     
    基础API: 
    - InputStream/OutputStream 字节操作
        - BufferedOutputStream 带缓冲区批处理数据 flush
        - FileInputStream 对应文件描述符 FileDescriptor
        - ByteArrayOutputStream 
    - Writer/Reader 字符操作 字符编码 构建应用逻辑和原始数据的桥梁
        - BufferedWriter
        - PipedWriter
        - OutputStreamWriter
        
    实现:
    
    - 服务端启动 ServerSocket绑定端口，调用 accept 方法阻塞等待客户端连接. 连接建立后服务端开启线程(线程池方式优化线程的创建和销毁)处理请求
    

2. NIO 多路复用、同步非阻塞 IO jdk1.4

    - Buffer 高效的数据容器 除了布尔类型，所有原始数据类型都有相应的 Buffer 实现
    - Channel 被用来支持批量式IO操作的一种抽象(更加OS底层) 类似 LinuxOS 上的文件描述符
    - Selector 多路复用的基础 epoll
    - Charset 提供 Unicode 语义 编解码
    
    实现: 
    - 利用单线程轮询事件,定位就绪的 Channel,其中 Selector 阶段是阻塞的 等待 accept/write/read [阻塞唤醒](https://blog.csdn.net/god8816/article/details/54320053) 
    
    
3. AIO/NIO 2 异步非阻塞 IO jdk7 基于事件和回调机制 
    - AsynchronousServerSocketChannel
    - CompletionHandler
    - Future

4. Reactor、Proactor 模型

5. Netty