package no.lwb.base.concurrent.thread;

import java.util.Date;

/**
 * @author WeiBin Lin
 */
public class ThreadSafeSample {

    public int count;
    private static int LIMIT = 1000;
    public void nonSafe() {
        while (count < LIMIT) {
            //通过反编译 查看字节码 synchronized利用monitorenter/monitorexit实现同步语义
            synchronized (this) {
                int former = count++;
                int later = count;
                if (former != later - 1) {
                    System.out.println(former+"--"+later);
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Date());
        ThreadSafeSample threadSafeSample = new ThreadSafeSample();
        Thread t1 = new Thread(()->{
            threadSafeSample.nonSafe();
        });
        t1.start();
        Thread t2 = new Thread(()->{
            threadSafeSample.nonSafe();
        });
        t2.start();
        t1.join();
        t2.join();
        System.out.println(new Date());
    }
}
