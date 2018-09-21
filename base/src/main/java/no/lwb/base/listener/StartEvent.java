package no.lwb.base.listener;

import java.util.EventObject;

/**
 *
 * 启动事件
 *
 * @author WeiBin Lin
 * @since 2018/9/20
 */
public class StartEvent extends EventObject {

    /**
     * Constructs a prototypical Event. 构造一个典型的事件
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public StartEvent(Object source) {
        super(source);
    }

}
