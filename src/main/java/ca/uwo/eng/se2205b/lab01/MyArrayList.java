package ca.uwo.eng.se2205b.lab01;

import java.util.List;
import java.util.AbstractList;
/**
 * Array List implementation
 */
public class MyArrayList<T> {

    private int size;
    private T myArray[];
    private int capacity;
    private static final int startCapacity=10;

    @SuppressWarnings("unchecked")
    public MyArrayList(List<? extends T> base) {
        myArray = (T[])(new Object[startCapacity]);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        myArray = (T[])(new Object[initialCapacity]);
    }

    public int size(){return size;}

    public boolean isEmpty(){return size==0;}

    public boolean remove(Object o){
        int indexOfO = indexOf(o);

        if(indexOfO == -1) {
            return false;
        }

        remove(indexOfO);
        return true;
    }

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

    public void add(int i, T e)throws IndexOutOfBoundsException{
        checkIndex(i,size+1);
        if(size == myArray.length){
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

    public boolean add(T e){
        if(size == myArray.length){
            resize(2*myArray.length);
        }
        System.out.println(size);
        myArray[size++] = e;
        System.out.println(size);
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

    public boolean equals(Object obj){
        return true;
    }

    public T get(int i) throws IndexOutOfBoundsException{
        checkIndex(i,size);
        return myArray[i];
    }

    public T set(int i, T e) throws IndexOutOfBoundsException{
        checkIndex(i,size);
        T temp = myArray[i];
        myArray[i]=e;
        return temp;
    }

    public String toString(){
        String results = "[";
        for( int i =0; i< size; i++) {
            if(i==0){
                results += myArray[i].toString();
            }
            else {
                results += "," + myArray[i].toString();
            }
            System.out.println("trying "+myArray[i]);
        }
        return results +"]";
    }

    public static void main(String[] args) {
        MyArrayList test = new MyArrayList<>(1);
        test.add(0,2);
        String s =test.toString();
        System.out.println(s);
        test.add(1);
        s=test.toString();
        System.out.println(s);
        test.remove(2);


    }
}


