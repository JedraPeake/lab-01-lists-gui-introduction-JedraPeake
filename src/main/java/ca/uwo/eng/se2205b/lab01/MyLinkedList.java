package ca.uwo.eng.se2205b.lab01;

import java.util.AbstractList;
import java.util.List;
/**
 * Doubly linked list implementation
 */
public class MyLinkedList<T> extends AbstractList<T> {

    public MyLinkedList(List<? extends T> base) {
        m_head = new Node<> (null,null,null);
        m_tail= new Node<> (null,m_head,null);
    }

    private Node<T> m_head;
    private Node<T> m_tail;
    private int size =0;

    public int size( ) { return size; }

    public boolean isEmpty( ) { return size == 0; }

    private void addBetween(T e, Node<T> predecessor, Node<T> successor) {
        Node<T> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    public void addLast(T e) {
        addBetween(e, m_tail.getPrev( ), m_tail); // place just before the trailer
    }

    public void addFirst(T e) {
        addBetween(e, m_head, m_head.getNext()); // place just before the trailer
    }

    private T remove(Node<T> node) {
        Node<T> predecessor = node.getPrev( );
        Node<T> successor = node.getNext( );
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement( );
    }

    public T removeFirst( ) {
        if (isEmpty( )) {
            return null;
        }
        return remove(m_head.getNext( )); // first element is beyond header
    }


    public T removeLast( ) {
        if (isEmpty( )) {
            return null;
        }
        return remove(m_tail.getPrev( )); // last element is before trailer
    }


    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        return true;
    }


    public String toString(){
        String string="a";
        return string;
    }

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
    }


    public T get(int a)
    {
        Node it = m_head;
        int count = 0;
        while(it!=null && count<a)
        {
            it = it.next;
        }

        if(it!=null)
            return (T) it.getElement();
        else
            return null;
    }

    public static void main(String[] args) {



    }
}
