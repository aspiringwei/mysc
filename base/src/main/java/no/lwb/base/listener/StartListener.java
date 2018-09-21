package no.lwb.base.listener;

import java.util.EventListener;

/**
 * 启动监听
 * @author WeiBin Lin
 * @since 2018/9/20
 */
public interface StartListener extends EventListener {

    void start(StartEvent event);

    void started(StartEvent event);
}
