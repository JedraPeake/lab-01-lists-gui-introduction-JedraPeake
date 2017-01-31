package ca.uwo.eng.se2205b.lab01;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Array List implementation
 */
public class MyArrayList<T> extends AbstractList<T> {

    private int size;
    private T[] myArray;
    private int capacity;

    @SuppressWarnings("unchecked")
    public MyArrayList(List<? extends T> base) {
        //myArray = (T[])(new Object[startCapacity]);
        myArray = (T[])base.toArray();// new Object[startCapacity]);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        capacity = initialCapacity;
        size = 0;
        myArray = (T[]) new Object[capacity];
        //myArray = (T[])(new Object[initialCapacity]);
    }

    @Override
    public int size(){return size;}

    @Override
    public boolean isEmpty(){return size==0;}

    @Override
    public boolean remove(Object o){
        int indexOfO = indexOf(o);

        if(indexOfO == -1) {
            return false;
        }

        remove(indexOfO);
        return true;
    }

    @Override
    public T remove(int index){
        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        T temp = myArray[index];

        for(int i = index + 1; i < size; i++) {
            myArray[i - 1] = myArray[i];
        }

        size--;
        return temp;
    }

    @Override
    public int indexOf(Object o)throws NullPointerException{
        if(o == null) {
            throw new NullPointerException();
        }
        for(int i = 0; i < size; i++) {
            if(myArray[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(int i, T e){//throws IndexOutOfBoundsException{
        checkIndex(i,size+1);
        if(size == capacity){
            resize(2*myArray.length);
        }
        for(int k=size-1;k>=i;k--){
            myArray[k+1]=myArray[k];
        }
        myArray[i]=e;
        size++;
        System.out.println("adding i"+i +"e "+e);
        System.out.println("size is" + size);
    }

    @Override
    public boolean add(T e){
        if(size == myArray.length){
            resize(2*myArray.length);
        }
        this.myArray[size++] = e;
        return true;
    }

    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
        if(i<0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: "+ i);
    }

    protected void resize(int capacity){
        T[] temp = (T[])new Object[capacity];
        for(int i = 0; i<size;i++){
            temp[i]=myArray[i];
        }
        myArray=temp;
    }

    @Override
    public boolean equals(Object o){
        if(! (o instanceof List<?>)) {
            return false;
        }
        if(o == this) {
            return true;
        }

        ArrayList<T> tmp = (ArrayList<T>)o;

        if(this.size() == tmp.size()) {
            for(int i = 0; i < this.size(); i++ ) {
                if ( this.get(i) == null && this.get(i) != tmp.get(i)) {
                    return false;
                } else if (this.get(i) != null && !(this.get(i)).equals(tmp.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String results = "[";
        for( int i =0; i< size; i++) {
            if(i==0){
                results += myArray[i].toString();
            }
            else {
                results += "," + myArray[i].toString();
            }
        }
        return results +"]";
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException{   //throw-in check-index
        checkIndex(index,size);
        return myArray[index];
    }

    @Override
    public T set(int index, T e) throws IndexOutOfBoundsException{      //throw-in check-index
        checkIndex(index,size);
        T temp = myArray[index];
        myArray[index]=e;
        return temp;
    }

    public static void main(String[] args) {


    }
}


