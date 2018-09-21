package no.lwb.base.listener;

import lombok.extern.slf4j.Slf4j;

/**
 * 避免实现空方法体,使用适配器实现所有接口的空版本。
 * 创建适配器子类的方式实现监听器方法
 * @author WeiBin Lin
 * @since 2018/9/20
 */
@Slf4j
public abstract class StartAdapter implements StartListener {

    @Override
    public void start(StartEvent event) {
        log.debug("non impl");
    }

    @Override
    public void started(StartEvent event) {
        log.debug("non impl");
    }
}
