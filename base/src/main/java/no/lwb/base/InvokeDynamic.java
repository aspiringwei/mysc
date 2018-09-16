package no.lwb.base;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * 方法调用
 * invokestatic 静态方法调用 java.lang.invoke.MethodHandles.Lookup.findStatic
 * invokespecial 实例方法、Lookup.findSpecial 静态绑定
 * invokevirtual 实例方法 Lookup.findVirtual 动态绑定
 * invokeinterface 接口方法 Lookup.findVirtual
 * 这些指令与包含目标方法类名、方法名以及方法描述符的符号引用捆绑
 * invokedynamic
 *
 * @see java.lang.invoke.MethodHandles
 * 方法句柄 MethodHandle 强类型的，能够被直接执行的引用
 * 方法句柄类型 MethodType 由方法的参数类型以及返回类型组成
 * 方法句柄的权限检查发生在创建过程中,相对反射调用节省了调用时反复校验权限的开销
 *
 * javap -c 对代码进行反汇编，字节码
 * javap -v 输出附加信息
 *
 * @author ixm.
 * @date 2018/8/8
 */
public class InvokeDynamic {

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // 反射
        Method method = Foo.class.getDeclaredMethod("bar", Object.class);
        MethodHandle methodHandle = lookup.unreflect(method);
        methodHandle.invokeExact(new Object());
        // 根据类、方法名以及方法句柄类型来查找
        MethodType methodType = MethodType.methodType(void.class, Object.class);
        MethodHandle mh1 = lookup.findStatic(Foo.class, "bar", methodType);
        long current = System.currentTimeMillis();
        for (int i = 1; i <= 2000000000; i++) {
            if (i % 1000000000 == 0) {
                // 10s 7s
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            mh1.invokeExact(new Object());
        }

    }

    public void test(MethodHandle mh, String s) throws Throwable {
        mh.invokeExact(s);
        mh.invokeExact((Object)s);
    }

}

class Horse {
    public void race() {
        System.out.println("horse race");
    }
}

class Deer {
    public void race() {
        System.out.println("dear race");
    }
}

class Cobra {
    public void race() {
        System.out.println("how do you turn this on?");
    }
}

class Foo {
    public static void bar(Object o) {
    }
    public static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }
}