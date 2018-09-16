package no.lwb.leetcode.structures;

/**
 * @author WeiBin Lin
 * @since 2018/9/10
 */
public class MyLinkedList {

    private int size;

    private Node first;

    private Node last;

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(E item, MyLinkedList.Node<E> prev, MyLinkedList.Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

}
