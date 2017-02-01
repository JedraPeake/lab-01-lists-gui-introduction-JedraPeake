package ca.uwo.eng.se2205b.lab01;

import java.util.AbstractList;
import java.util.List;

/**
 * Doubly linked list implementation
 */
public class MyLinkedList<T> extends AbstractList<T> {

    public MyLinkedList(List<? extends T> base) {
        m_head = new Node<>(null, null, null); // create empty DLL...
        m_tail = new Node<>(null, m_head, null);

    }

    private Node<T> m_head;
    private Node<T> m_tail;
    private int size =0;

    public int size( ) { return size; }

    public boolean isEmpty( ) { return size == 0; }

    private static class Node <T>{
        private Node<T> next;
        private Node<T> prev;
        private T value;
        public Node(T e, Node<T> p, Node<T> n){
            value = e;
            prev = p;
            next = n;
        }
        public T getElement( ){
            return value;
        }
        public Node<T> getPrev( ) {
            return prev;
        }
        public Node<T> getNext( ) {
            return next;
        }
        public void setPrev(Node<T> p) {
            prev = p;
        }
        public void setNext(Node<T> n) {
            next = n;
        }
        public void setValue(T e) {
            value =e;
        }
    }

    public T get(int index) {
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }

        Node<T> curr = m_head;
        while (index > 0)
        {
            curr = curr.next;
            index--;
        }
        return curr.value;
    }

    public T set(int index, T element) {
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }

        Node<T> curr = m_head;
        // search for ith element or end of list
        while (index > 0)
        {
            curr = curr.next;
            index--;
        }
        // get old value, update new value
        T result = curr.value;
        curr.setValue(element);
        return result;
    }

}
