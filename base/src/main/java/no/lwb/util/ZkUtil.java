package no.lwb.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryForever;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 参考 com.zuche.framework.zk.utils.ZkUtils
 * http://curator.apache.org/getting-started.html
 * <br> zk 版本问题. Exception in thread "main" org.apache.zookeeper.KeeperException$NodeExistsException: KeeperErrorCode = NodeExists for /zk/test
 * <br> curator(馆长) 使用流式风格
 *
 * zk 集群-奇数个(原理分析)
 * @author ixm
 */
public class ZkUtil {

    public static void main(String[] args) throws Exception {
        // 创建连接
        CuratorFramework curatorFramework = getZkConnection();
        // 直接调用 zk
        String path = curatorFramework.create().forPath("/uc/xm", "我喜欢你".getBytes());
        System.out.println(path);
        // 锁
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1; i++) {
            executorService.submit(() -> {
                try {
                    getMuxLock(curatorFramework);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();

    }

    /**
     * Getting a Connection
     * @return
     * @throws Exception
     */
    public static CuratorFramework getZkConnection() throws Exception {
        String zookeeperConnectionString = "10.104.90.238:5181,10.104.108.100:5181,10.104.108.102:5181";
        RetryPolicy retryPolicy = new RetryForever(1000);
        retryPolicy = new ExponentialBackoffRetry(1000, 3);
//        CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(zookeeperConnectionString)
                .sessionTimeoutMs(60 * 1000).connectionTimeoutMs(10 * 1000).retryPolicy(retryPolicy).build();
        curatorFramework.start();
        curatorFramework.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println(event.getChildren() + "---------------------------------------------------------------");
            }
        });
        return curatorFramework;
    }

    /**
     * Distributed Lock
     * ourPath = (String)((ACLBackgroundPathAndBytesable)client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)).forPath(path);
     * 同个线程 re-enter lockCount + 1
     * createsTheLock 创建临时节点
     * maxLeases = 1 当节点为1个时候自动获取到锁 index = 0 haveTheLock
     * 否则 获取前一个节点为监听节点 (wait - notifyAll) 超时删除节点
     * release 释放锁 lockCount -1 decrementAndGet，删除节点deleteOurPath()，threadData.remove()
     */
    public static void getMuxLock(CuratorFramework client) throws Exception {
        InterProcessMutex lock = new InterProcessMutex(client, "/uc/xm/test1");
        System.out.println(Thread.currentThread() + "getting lock.");
        if (lock.acquire(5000, TimeUnit.SECONDS)) {
            try {
                // do some work inside of the critical section here
                System.out.println(Thread.currentThread() + " lock.");
            } finally {
                lock.release();
            }
        }
    }

    public void getLeaderElection() {
//        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
//            public void takeLeadership(CuratorFramework client) throws Exception {
//                // this callback will get called when you are the leader
//                // do whatever leader work you need to and only exit
//                // this method when you want to relinquish leadership
//            }
//        };
//
//        LeaderSelector selector = new LeaderSelector(client, path, listener);
//        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
//        selector.start();
    }

}

/*
{
"actualServerPolicy": "all",
"expectServerPolicy": "all",
"hostInfoList": [
{
"host": "luckybusinesszk01.pub.prod.bj2",
"ip": "10.204.95.174",
"port": 5181,
"roles": 0
},
{
"host": "luckybusinesszk02.pub.prod.bj2",
"ip": "10.204.95.175",
"port": 5181,
"roles": 0
},
{
"host": "luckybusinesszk03-prod-rg2-b28",
"ip": "10.212.2.106",
"port": 5181,
"roles": 0
},
{
"host": "luckybusinesszk04-prod-rg2-b28",
"ip": "10.212.2.107",
"port": 5181,
"roles": 0
},
{
"host": "luckybusinesszk05-prod-rg2-b28",
"ip": "10.212.2.108",
"port": 5181,
"roles": 0
}
],
"prefix": "/lucky",
"timeout": 1000
}
 */