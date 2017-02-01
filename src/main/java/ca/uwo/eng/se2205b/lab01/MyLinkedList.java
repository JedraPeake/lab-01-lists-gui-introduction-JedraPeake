package ca.uwo.eng.se2205b.lab01;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/**
 * Doubly linked list implementation
 */
public class MyLinkedList<T> extends AbstractList<T> {

    public MyLinkedList(List<? extends T> base) {
//        m_head = new Node<>(null, null, null); // create empty DLL...
//        m_tail = new Node<>(null, m_head, null);
        addAll(size , base);
    }

    private Node<T> m_head;
    private Node<T> m_tail;
    private int size =0;

    @Override
    public int size( ) { return size; }

    @Override
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

    Node<T> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<T> x = m_head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = m_tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    @Override
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

    @Override
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

//    public boolean add(T e){}

    private void checkIndex(int i, int n) throws IndexOutOfBoundsException{
    if((i<0) || (i >= n))
        throw new IndexOutOfBoundsException("Illegal index: "+ i);
    }

    @Override
    public void add(int index, T element)throws IndexOutOfBoundsException{
        checkIndex(index , size+1);

        if (index == 0){
            m_head = new Node<T>(element, m_head, null);
            if (m_tail == null) {
                m_tail = m_head;
            }
            size++;}

        else if (index == size()) {// construct new element
            m_tail = new Node<T>(element, null, m_tail);
            // fix up head
            if (m_head == null) {
                m_head = m_tail;
            }
            size++;
        }

        else {
            Node<T> before = null;
            Node<T> after = m_head;
            // search for ith position, or end of list
            while (index > 0)
            {
                before = after;
                after = after.next;
                index--;
            }
            // create new value to insert in correct position
            Node<T> current = new Node<T>(element,after,before);
            size++;
            // make after and before value point to new value
            before.setNext(current);
            after.setPrev(current);
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)throws IndexOutOfBoundsException, NullPointerException{
        Object[] a = c.toArray();
       int n = a.length;
       if (n == 0) {
           return false;
       }
       Node<T> pred, succ;
       if (index == size) {
           succ = null;
           pred = m_tail;
       }
       else {
           succ = node(index);
           pred = succ.prev;
       }

       for (Object o : a) {
            T e = (T) o;
           Node<T> newNode = new Node<>(e, pred, null);
           if (pred == null)
               m_head = newNode;
           else
               pred.next = newNode;
           pred = newNode;
       }

       if (succ == null) {
           m_tail = pred;
       } else {
           pred.next = succ;
           succ.prev = pred;
       }

       size += n;
       modCount++;
       return true;
   }

    @Override
    public T remove(int index){
        checkIndex(index, size);

        if (index == 0) {
            Node<T> temp = m_head;
            m_head = m_head.next;
            if (m_head != null) {
                m_head.setPrev(null);
            } else {
                m_tail = null; // remove final value
            }
            temp.setNext(null);// helps clean things up; temp is free
            size--;
            return temp.value;
        }
        else if (index == size()-1) {
            Node<T> temp = m_tail;
            m_tail = m_tail.prev;
            m_tail.setNext(null);
            size--;
            return temp.value;
        }
        Node<T> previous = null;
        Node<T> finger = m_head;
        // search for value indexed, keep track of previous
        while (index > 0)
        {
            previous = finger;
            finger = finger.next;
            index --;
        }
        previous.setNext(finger.next);
        finger.next.setPrev(previous);
        size--;
        // finger's value is old value, return it
        return finger.value;
    }

    @Override
    public boolean equals(Object o){
        if(! (o instanceof List<?>)) {
            return false;
        }
        if(o == this) {
            return true;
        }

        List<?> tmp = (List<?>)o;

        if(size() == tmp.size()) {
            for(int i = 0; i < size(); i++ ) {
                if ( get(i) == null && get(i) != tmp.get(i)) {
                    return false;
                } else if (get(i) != null && !(get(i)).equals(tmp.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
