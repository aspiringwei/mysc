package no.lwb.base.datastructure;


import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

/**
 *
 * 数组思路::
 * 1. 构造器 new MyArray()
 * 2. 操作方法 todo
 *  数组移动，扩容缩容
 *  边界问题 垃圾回收等
 * @author WeiBin Lin
 */
@Log4j2
public class MyArray<T> {

    private T[] data;

    private int size;

    @SuppressWarnings("unchecked")
    public MyArray(int capacity) {
        this.data = (T[])new Object[capacity];
        this.size = 0;
    }

    public MyArray() {
        this(10);
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newData = (T[])new Object[capacity];
        // 不要循环赋值 利用native方式
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public static void main(String[] args) {
        MyArray myArray = new MyArray<Integer>(5);
        log.info(myArray.isEmpty());
        myArray.add(12);
        log.info(myArray);
    }

    private void add(T i) {
        // 是否需要扩容判断 todo
        data[size] = i;
        size++;
    }

    private int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return "MyArray{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}
