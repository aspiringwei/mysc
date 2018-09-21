package no.lwb.base.concurrent;



import lombok.extern.log4j.Log4j2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author WeiBin Lin
 * @since 2018/9/15
 */
@Log4j2
public class CyclicBarrierUse implements Runnable {
    

    private String player;

    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierUse(String player, CyclicBarrier cyclicBarrier) {
        this.player = player;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        try {
            log.info("匹配玩家");
            cyclicBarrier.await();
            log.info("选择角色");
            cyclicBarrier.await();
            log.info("等待其他玩家");
            cyclicBarrier.await();
            log.info("开始游戏");

        } catch (InterruptedException e) {
            log.error("", e);
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            log.error("", e);
        }

    }
}
