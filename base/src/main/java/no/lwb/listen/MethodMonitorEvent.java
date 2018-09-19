package no.lwb.listen;

import java.util.EventObject;

/**
 * @author WeiBin Lin
 * @since 2018/9/19
 */
public class MethodMonitorEvent extends EventObject {

    private final Long timestamp;

    public Long getTimestamp() {
        return timestamp;
    }


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MethodMonitorEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }
}
