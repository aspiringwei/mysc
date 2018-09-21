/**
 *
 * 事件监听机制
 *  原则：
 *      1. 应该非常快地执行,如果操作冗长利用线程方式
 *      2. 不同类型的事件监听器实现单独的类,当然类多也意味着性能下降
 *
 * 任意数量的事件监听器可以从任意数量的事件源对象监听各种类型的事件
 *
 * @see java.util.EventObject 事件对象
 * @see java.util.EventListener
 *
 * 事件发生器、事件发布
 * 事件监听器：<br>
 *  https://docs.oracle.com/javase/tutorial/uiswing/events/index.html
 *
 * @author WeiBin Lin
 * @since 2018/9/19
 */
package no.lwb.base.listener;