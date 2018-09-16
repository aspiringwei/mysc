package no.lwb.leetcode.structures;


/**
 * 自定义队列实现
 *
 * 队列两个基本操作，入队、出队，遵循先进先出原则
 * @see java.util.LinkedList
 * @author WeiBin Lin
 * @since 2018/9/6
 */
public class MyQueue {

    /**
     * 元素集
     */
    private Object[] items;


    private int head;

    private int tail;

    private int size;

    public boolean isEmpty() {
        return head == -1;
    }
    public boolean isFull() {
        return (tail + 1) % size == head;
    }
    public int size() {
        return size;
    }
    public MyQueue(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
        this.size = initialCapacity;
        this.head = -1;
        this.tail = -1;
        this.items = new Object[initialCapacity];
    }

    public boolean enQueue(Object value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head = 0;
        }
        tail = (tail + 1) % size;
        items[tail] = value;
        return true;
    }

    public Object deQueue() {
        if (isEmpty()) {
            return null;
        }
        Object value = items[head];
        items[head] = null;
        if (head == tail) {
            head = -1;
            tail = -1;
        } else {
         }
        return value;
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(3);
        System.out.println(myQueue.size());
        myQueue.enQueue(1);
        myQueue.enQueue(2);
        myQueue.enQueue(3);
        System.out.println(myQueue.deQueue());
        myQueue.enQueue(4);
        System.out.println(myQueue.deQueue());
        System.out.println(myQueue.deQueue());
        System.out.println(myQueue.deQueue());
        System.out.println(myQueue);
    }
}
