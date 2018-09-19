package no.lwb.listen;

import java.util.EventListener;

/**
 * @author WeiBin Lin
 * @since 2018/9/19
 */
public interface MethodMonitorEventListener<E extends MethodMonitorEvent> extends EventListener {

    void onMethodMonitorEventBegin(E methodMonitorEvent);

    void onMethodMonitorEventEnd(E methodMonitorEvent);
}
