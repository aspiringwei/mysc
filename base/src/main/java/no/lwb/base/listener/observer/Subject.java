package no.lwb.base.listener.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

/**
 * @author WeiBin Lin
 */
@Slf4j
public class Subject extends Observable {


    private String msg;

    /**
     * 具体业务XXX
     * @param message msg
     */
    public void saySomething(String message) {
        this.msg = message;

        log.info("push 推----");
        setChanged();
        notifyObservers(message);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error("", e.getMessage());
            Thread.currentThread().interrupt();
        }
        log.info("pull 拉----");
        setChanged();
        notifyObservers();
    }

    public String getMsg() {
        return msg;
    }
}
