package no.lwb.base.listener.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author WeiBin Lin
 */
@Slf4j
public class SubjectObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        // 推模型
        log.info("i am listening.. {}", arg);
        // 拉模型
        if (!Objects.isNull(arg)) {
            Subject subject = (Subject)o;
            log.info("pull i am listening.. {}", subject.getMsg());
        }
    }
}
