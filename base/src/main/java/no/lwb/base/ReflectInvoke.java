package no.lwb.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * method.invoke()
 *         MethodAccessor ma = methodAccessor;             // read volatile
 *         if (ma == null) {
 *             ma = acquireMethodAccessor();
 *         }
 * 委派给 MethodAccessor 处理
 * MethodAccessor 又分为 本地方法实现(NativeMethodAccessorImpl)和委派实现(DelegatingMethodAccessorImpl)
 * 委派实现->本地实现
 * 委派实现是为了能够本地实现和动态实现(动态生成字节码)中切换
 * 动态实现和本地实现相比，其运行效率要快20倍，动态实现无需经过Java到C++再到Java的切换；
 * 由于生成字节码十分耗时，仅调用一次的话，反而本地方法要快上3~4倍.
 * 鉴于此,虚拟机设置一个阈值15(-Dsun.reflect.inflationThreshold=N),当反射调用次数达到15时便开始动态生成字节码，并将委派实现的
 * 委派对象切换至动态实现 Inflation机制 (-Dsun.reflect.noInflation=true)来关闭，这样反射调用一开始就直接生成动态实现.
 * java -verbose:class Clazz
 * [Loaded sun.reflect.GeneratedMethodAccessor1 from __JVM_DefineClass__]
 * 之后就走动态实现
 *
 * -verbose:class
 * -XX:+PrintGC 打印GC
 * Integer缓存[-128,127]
 * -Djava.lang.Integer.IntegerCache.high=N：扩大Integer的缓存范围
 *
 * - 获取 Class 对象
 * 1. 使用静态方法 Class.forName() 2. 调用对象的 getClass() 方法 3. 直接用 类名.class
 * - 生成该类对象实例
 * 1. newInstance() 2. Array.newInstance(Class, int)构造该类型的数组
 * - 访问成员
 * 1. getFields()/getConstructors().. 方法名带Declared的不会返回父类的成员,但回返回私有成员;不带则相反
 * - 操作
 * 1. 使用 Constructor/Filed/Method.setAccessible(true)绕开Java语言的访问限制
 * 2. 使用 Constructor.newInstance(Object[])来生成该类的实例
 * 3. 使用 Field.get/set(Object) 来访问字段的值
 * 4. 使用 Method.invoke(Object, Object[]) 来调用方法
 * https://docs.oracle.com/javase/tutorial/reflect/
 * @author ixm.
 * @date 2018/8/8
 */
public class ReflectInvoke {

    public static void target(int i) {
//        new Exception("#" + i).printStackTrace();
    }

    private void test() {

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // ClassLoader.loadClass()
        // 获取 Class 对象
        // 1. 使用静态方法 Class.forName() 2. 调用对象的 getClass() 方法 3. 直接用 类名.class
        Class<?> clazz = Class.forName("no.lwb.base.ReflectInvoke");
//        clazz = ReflectInvoke.class
//        ReflectInvoke reflectInvoke = new ReflectInvoke()
//        clazz = reflectInvoke.getClass()
        Method[] methods = clazz.getDeclaredMethods();
        methods = clazz.getMethods();
        Method method = clazz.getMethod("target", int.class);
        int times = 2000000000;
        Object[] arg = new Object[1];
        arg[0] = 127;
        long current = System.currentTimeMillis();
        for (int i = 0; i <= times; i++) {
            if (i % (times/2) == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp -current);
                current = temp;
            }
            method.invoke(null, arg);
        }
    }
}
