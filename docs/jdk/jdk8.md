翻译：http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html
http://openjdk.java.net/projects/jdk8/features
- Java Programming Language
	- Lambda Expressions 将功能视为方法参数,代码视为数据.
	```
	new Thread(()->{synchronized (lock){lock.notify();}}).start();
	```
	- 接口默认方法
- Collections
	- java.util.stream 提供Stream API支持函数式操作，如 sequential or parallel map-reduce 转换
	```
	Integer[] intArr = new Integer[]{1,4,5,9};
	List list = Arrays.asList(intArr);
	for (int i = 0, j = list.size(); i < j; i++) {
	    System.out.println(list.get(i));
	}
	list.forEach((value)-> System.out.println(value));
	```
- Date-Time Package 
- [Concurrency](https://docs.oracle.com/javase/8/docs/technotes/guides/concurrency/changes8.html)
	- StampedLock 基于功能/能力的锁
- HotSpot
	- Removal of PermGen.



```$xslt
1、Lambda 表达式 − Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中。
2、方法引用 − 方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
3、默认方法 − 默认方法就是一个在接口里面有了一个实现的方法。
4、新工具 − 新的编译工具，如：Nashorn引擎 jjs、 类依赖分析器jdeps。
5、Stream API −新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。
6、Date Time API − 加强对日期与时间的处理。
7、Optional 类 − Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。
8、Nashorn, JavaScript 引擎 − Java 8提供了一个新的Nashorn javascript引擎，它允许我们在JVM上运行特定的javascript应用。
```


https://docs.oracle.com/javase/8/docs/