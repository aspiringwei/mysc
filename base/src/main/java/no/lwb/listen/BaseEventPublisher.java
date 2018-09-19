package no.lwb.listen;

/**
 *
 * 事件发布
 * @author WeiBin Lin
 * @since 2018/9/19
 */
public interface BaseEventPublisher {

    void publishEvent(BaseEventObject event);

}
