package no.lwb.base.listener.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Observable;

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
        log.info("Subject singing::{}", message);
        setChanged();
        // 推模型
        notifyObservers(message);
        // 拉模型
        notifyObservers();
        if (hasChanged()) {
            clearChanged();
        }
    }

    public String getMsg() {
        return msg;
    }
}
