### synchronized (Intrinsic Lock)
> java内置的同步机制，提供互斥的语义和可见性.当一个线程已经获取锁时，其他线程只能等待或阻塞
使用简单可以用来修饰方法、代码块

### ReentrantLock
> 再入锁. 通过 lock() 和 unlock() 来控制. 提供更细节的控制,如控制公平性(fairness)、利用定义条件变量

### ReentrantReadWriteLock(使用场景：以并发读取为主,而不需要大量竞争的写操作) RWSample.java
- 由于开销比较大,jdk1.8 引入 StampedLock 提供类似的读写锁的同时，优化读模式.优化读基于假设，大多数情况读不会和写冲突，先试着修改，然后通过validate确定是否进入写模式，如果没有则避免开销
1. AQS(AbstractQueuedSynchronizer) 
1. ReadLock：
    - 如果写锁没有被另一个线程所持有，立即返回;
    - 如果写锁被另一个线程所持有则当前线程由于线程调度目的而被禁止并休眠直到获取到读锁.
```$xslt
public void lock() {sync.acquireShared(1);}
public void lockInterruptibly() throws InterruptedException {sync.acquireSharedInterruptibly(1);}
public boolean tryLock() {return sync.tryReadLock();}
public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
    return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
}
public void unlock() {sync.releaseShared(1);}
public Condition newCondition() {throw new UnsupportedOperationException();}
```
2. WriteLock：
    - 如果其他线程既没有持有读锁也不持有写锁,立即返回,写锁计数为1
    - 如果当前线程已经持有锁则计数+1,立即返回
    - 如果锁被其他线程持有则当前线程由于线程调度被禁止并休眠直到获取到写锁,那时，写锁计数设置为1
```$xslt
public void lock() {sync.acquire(1);}
public void lockInterruptibly() throws InterruptedException {sync.acquireInterruptibly(1);}
public boolean tryLock( ) {return sync.tryWriteLock();}
public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
    return sync.tryAcquireNanos(1, unit.toNanos(timeout));
}
public void unlock() {sync.release(1);}
public Condition newCondition() {return sync.newCondition();}
```


### AQS 同步器
    - The synchronization state.    


### 概念理解 JVM 优化 synchronized
> 锁的升级、降级：当 JVM 检测到不同的竞争状况时，会自动切换到适合的锁实现.
1. **偏斜锁**：当没有竞争出现时，默认使用偏斜锁。JVM 利用 CAS 操作，在对象头上的 Mark Word 部分设置线程 ID，
以表示这个对象偏向于当前线程.
2. **轻量级锁**：当其他线程试图锁定某个已经被偏斜过的对象，JVM就需要撤销(revoke,比较重的行为)偏斜锁，并切换到轻量级锁的实现。轻量级
锁依赖CAS操作Mark Word来试图获取锁，如果重试成功，就会使用普通的轻量级锁，否则升级为**重量级锁(锁膨胀)**
3. **锁降级**：当JVM进入安全点(SafePoint)时，检查是否有闲置的Monitor，然后试图降级
4. **自旋锁**：锁竞争失败的线程不会真正的在操作系统层面挂起,而是jvm会让线程做几个空循环(基于预测不久的将来就能获取到锁)，
若干次循环后，如果可以获取锁，进入临界区，如果不能则系统层面挂起
适用于占用锁的时间短，自旋的消耗小于线程阻塞挂起操作的消耗
```bash
关闭偏斜锁
-XX:-UseBiasedLocking
```

