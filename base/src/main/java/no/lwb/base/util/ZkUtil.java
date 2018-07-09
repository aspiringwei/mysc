package no.lwb.base.util;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryForever;
import org.apache.curator.utils.DefaultZookeeperFactory;
import org.apache.curator.utils.ZookeeperFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.TimeUnit;

/**
 * http://curator.apache.org/getting-started.html
 * <br> zk 版本问题. Exception in thread "main" org.apache.zookeeper.KeeperException$NodeExistsException: KeeperErrorCode = NodeExists for /zk/test
 * <br> curator(馆长) 使用流式风格
 * @author ixm
 */
public class ZkUtil {

    public static void main(String[] args) throws Exception {
        // 创建连接
        CuratorFramework curatorFramework = getZkConnection();
        // 直接调用 zk
//        String path = curatorFramework.create().forPath("/uc/xm", "我喜欢你".getBytes());
//        System.out.println(path);
        // 锁
        getMuxLock(curatorFramework);


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
        return curatorFramework;
    }

    /**
     * Distributed Lock
     */
    public static void getMuxLock(CuratorFramework client) throws Exception {
        InterProcessMutex lock = new InterProcessMutex(client, "/uc/xm/test");
        if (lock.acquire(5, TimeUnit.SECONDS)) {
            try {
                // do some work inside of the critical section here
                System.out.println("lock.");
            } finally {
                lock.release();
            }
        }
    }
}
