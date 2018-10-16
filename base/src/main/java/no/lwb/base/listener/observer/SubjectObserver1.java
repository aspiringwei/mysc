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
public class SubjectObserver1 implements Observer {

    private Observable observable;

    public SubjectObserver1(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        // 拉模型
        if (Objects.isNull(arg)) {
            Subject subject = (Subject)o;
            log.info("pull i am listening.. {}", subject.getMsg());
        } else {
            // 推模型
            log.info("i am listening.. {}", arg);
        }
    }

    public void remove() {
        observable.deleteObserver(this);
    }
}
