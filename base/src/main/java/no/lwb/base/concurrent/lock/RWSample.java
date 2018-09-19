package no.lwb.base.concurrent.lock;

import no.lwb.vo.Apple;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author WeiBin Lin
 */
public class RWSample {
    private final Map<String, Apple> m = new TreeMap<>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();
 
    public Apple get(String key) {
        r.lock();
        System.out.println("read lock");
        try {
            TimeUnit.DAYS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try { return m.get(key); }
        finally { r.unlock(); }
    }
    public String[] allKeys() {
        r.lock();
        try { return (String[]) m.keySet().toArray(); }
        finally { r.unlock(); }
    }
    public Apple put(String key, Apple value) {
        w.lock();
        System.out.println("write lock");
        try {
            TimeUnit.DAYS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try { return m.put(key, value); }
        finally { w.unlock(); }
    }
    public void clear() {
        w.lock();
        try { m.clear(); }
        finally { w.unlock(); }
    }


    /**
     * 运行过程，
     * 如果读锁试图锁定时，写锁是被某个线程所持有的，则读锁无法获得，只好等待对方操作结束释放锁，这样可以保证不会读到有争议的数据
     * 写锁，同理
     * 写写 阻塞等待
     * 读写 阻塞等待
     * 写读 阻塞等待
     * 读读 不影响
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        RWSample threadSafeSample = new RWSample();
        Thread t1 = new Thread(()->{
//            System.out.println(threadSafeSample.get("123"));
            threadSafeSample.put("123", new Apple(1, "test"));
        });
        t1.start();
        Thread t2 = new Thread(()->{
//            System.out.println(threadSafeSample.get("123"));
            threadSafeSample.put("123", new Apple(1, "test"));
        });
        t2.start();
        t1.join();
        t2.join();
    }
}
