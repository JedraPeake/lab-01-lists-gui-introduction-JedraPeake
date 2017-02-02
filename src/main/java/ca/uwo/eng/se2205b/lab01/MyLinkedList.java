package ca.uwo.eng.se2205b.lab01;

import java.util.AbstractList;
import java.util.List;

/**
 * Doubly linked list implementation
 */
public class MyLinkedList<T> extends AbstractList<T> {

    public MyLinkedList(List<? extends T> base) {
        T[] temp = (T[]) base.toArray();
        for(int i = 0; i<temp.length;i++){
            add(i, temp[i] );
        }
    }
    private int size;
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        T data;
        Node<T> nextElement;
        Node<T> previousElement;

        private Node(T v, Node<T> next, Node<T> previous) {
            data = v;
            nextElement = next;
            if (nextElement != null) {
                nextElement.previousElement = this;
            }
            previousElement = previous;
            if (previousElement != null){
                previousElement.nextElement = this;
            }
        }

        private Node<T> next()
        {
            return nextElement;
        }

        private Node<T> previous()
        {
            return previousElement;
        }

        private T value()
        {
            return data;
        }

        private void setNext(Node<T> next)
        {
            nextElement = next;
        }

        private void setPrevious(Node<T> previous)
        {
            previousElement = previous;
        }

        private void setValue(T value)
        {
            data = value;
        }
    }

    public T get(int i) {
        if((i<0) || (i >= size())) {
            throw new IndexOutOfBoundsException("Illegal index: "+ i);
        }
        Node<T> curr = head;
        while (i > 0)
        {
            curr = curr.next();
            i--;
        }
        return curr.value();
    }

    public T set(int i, T o) {
        if((i<0) || (i >= size())) {
            throw new IndexOutOfBoundsException("Illegal index: "+ i);
        }

        Node<T> curr = head;
        while (i > 0)
        {
            curr = curr.next();
            i--;
        }
        T result = curr.value();
        curr.setValue(o);
        return result;
    }

    public void add(int i, T o) {

        if((i<0) || (i >= size()+1)) {
            throw new IndexOutOfBoundsException("Illegal index: "+ i);
        }

        if (i == 0) {
            addFirst(o);
        }

        else if (i == size()) {
            addLast(o);
        }

        else {
            Node<T> before = null;
            Node<T> after = head;

            while (i > 0)
            {
                before = after;
                after = after.next();
                i--;
            }

            Node<T> current = new Node<T>(o,after,before);
            size++;

            before.setNext(current);
            after.setPrevious(current);
        }
    }

    private void addFirst(T value) {
        head = new Node<T>(value, head, null);
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    private void addLast(T value) {

        tail = new Node<T>(value, null, tail);

        if (head == null) {
            head = tail;
        }
        size++;
    }

    public T remove(int i) {
        if((i<0) || (i >= size())) {
            throw new IndexOutOfBoundsException("Illegal index: "+ i);
        }

        if (i == 0) {
            return removeFirst();
        }

        else if (i == size()-1) {
            return removeLast();
        }

        Node<T> previous = null;
        Node<T> curr = head;

        while (i > 0)
        {
            previous = curr;
            curr = curr.next();
            i--;
        }

        previous.setNext(curr.next());
        curr.next().setPrevious(previous);
        size--;

        return curr.value();
    }

    private T removeFirst() {
        Node<T> temp = head;
        head = head.next();

        if (head != null) {
            head.setPrevious(null);
        }
        else {
            tail = null; // remove final value
        }

        temp.setNext(null);// helps clean things up; temp is free
        size--;
        return temp.value();
    }

    private T removeLast() {
        Node<T> temp = tail;
        tail = tail.previous();
        if (tail == null) {
            head = null;
        }
        else {
            tail.setNext(null);
        }
        size--;
        return temp.value();
    }

    public int size()
    {
        return size;
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> curr = head;
        for( int i =0; i< size; i++) {
            if(i==0){
                sb.append(curr.data);
                curr=curr.next();
            }
            else {
                sb.append(", ");
                sb.append(curr.data);
                curr=curr.next();
            }
        }
        return sb +"]";
    }

}
