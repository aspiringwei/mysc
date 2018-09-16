翻译：http://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html
http://openjdk.java.net/projects/jdk7/features/b

- Concurrency Utilities 并发实用程序  
	fork/join框架基于ForkJoinPool类实现Executor接口 实用工作线程池(pool of worker threads)高效的运行大量任务.work-stealing(窃取工作) 技术充分利用多处理器用来保持工作线程忙碌

- Java Progamming Language
	- Binary Literals 二进制字面量表示(byte,short,int,long)
	- Strings in switch statements switch支持String对象
	- The try-with-resources Statement 
	- Catching Multiple Exception and Rethrowing Exceptions with improved Type Checking 改进类型检查检查捕获多个异常并重新抛出异常
	- Underscores in Numeric Literals 下划线_可以出现在数字文字中的数字的任意位置
	- Type Inference for Generic Instance Creation 泛型实例创建的类型推断 <> 菱形
	- Varargs
- Java Virtual Machine (JVM)
	- Java Virtual Machine Support for Non-Java Languages
	- Garbage-First Collector 替换 Concurrent Mark-Sweep Collector (CMS)  
		主要针对大内存的多处理器机器
		并行的全标记确定堆中对象的存活度，标记阶段完成后，G1知道哪块区域基本是空的，它首先收集这些区域(产生大量自由空间).G1集中收集和压实(压紧)活跃在可能充满了可回收对象(垃圾)的堆区域,G1使用暂停预测模型来
		满足用户定义的暂停时间目标并根据特定的暂停时间目标来选择回收的区域，由G1定义为回收成熟的区域是使用
		疏散收集的垃圾，G1从一个或多个区域拷贝对象到堆的单一区域，过程中压实(压紧)和释放内存。这种疏散(evacuation)在多处理器并行执行，降低暂停时间和挺高吞吐量(throughput).这样对于每次垃圾收集，G1持续工作减少碎片，在用户定义的暂停时间内工作。这超出之前两种方法的能力。CMS垃圾收集没有做压实(压紧)(compaction)，ParallelOld对整个堆进行压实(压紧)，导致相当长的暂停时间。
		值得注意G1不是实时收集器。G1在用户特定目标时间估计多少个区域被收集，因此收集器具有相当准确的模型(收集区域的成本)，且他使用该模型来确定哪些和多少在停留在暂停时间内可收集的区域.
		推荐使用G1,为运行需要具有有限GC延迟的大堆的应用程序提供解决方案。This means heap sizes of around 6GB or larger, and stable and predictable pause time below 0.5 seconds
		G1足够紧凑以完全避免细粒度的自由列表进行分配而是基于区域，大大简化收集器的部分，主要消除潜在的碎片问题。同时G1提供比cms更可预测的垃圾收集暂停并允许用户指定所需的暂停目标。  
		满足以下三种特征之一，可CMS、ParallelOld切换到G1
		- 堆中超过50%的存活对象
		- 对象的分配率或提升率变化显著？？
		- 不希望长垃圾收集或压实(压紧)暂停(超过0.5到1s)
	- Java HotSpot Virtual Machine Performance Enhancements