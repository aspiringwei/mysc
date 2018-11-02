package no.lwb.base.datastructure;

/**
 * @author WeiBin Lin
 */
public class MyLinkedList<T> {

    private Node head;

    private Node tail;

    /**
     * 节点类
     */
    private static class Node<T> {

        private Node prev;

        private Node next;

        private T data;

        public Node(Node prev, Node next, T data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList<Integer>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(2);
    }

    private void add(T i) {
        final Node<T> l = this.tail;
        final Node<T> newNode = new Node<>(l, null, i);
        this.tail = newNode;
        if (l == null) {
            this.head = newNode;
        } else {
            l.next = newNode;
        }
        // 计数+1
    }


}
