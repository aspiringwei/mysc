package no.lwb.base.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author WeiBin Lin
 * @since 2018/9/15
 */
public class CyclicBarrierUse implements Runnable {
    
    private static final Logger LOG = LoggerFactory.getLogger(CyclicBarrierUse.class);

    private String player;

    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierUse(String player, CyclicBarrier cyclicBarrier) {
        this.player = player;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        try {
            LOG.info("匹配玩家");
            cyclicBarrier.await();
            LOG.info("选择角色");
            cyclicBarrier.await();
            LOG.info("等待其他玩家");
            cyclicBarrier.await();
            LOG.info("开始游戏");

        } catch (InterruptedException e) {
            LOG.error("", e);
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            LOG.error("", e);
        }

    }
}
