package no.lwb.listen;

import java.util.EventListener;

/**
 * @author WeiBin Lin
 * @since 2018/9/19
 */
public interface BaseEventObjectListener<E extends BaseEventObject> extends EventListener {

    void onBaseEvent(E methodMonitorEvent);

}
