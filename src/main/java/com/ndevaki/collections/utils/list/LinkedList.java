package com.ndevaki.collections.utils.list;

import java.io.Serializable;
import java.util.ListIterator;
import java.util.RandomAccess;

public class LinkedList<T> extends AbstractList
                        implements List,Cloneable, RandomAccess, Serializable {

    int size;
    int modCount;
    Node head;
    Node tail;
    class Node{
        T data;
        Node next;
        Node previous;
        public T getData(){
            return data;
        }
        public void setData(T data){
            this.data=data;
        }
        public boolean equals(Node n){
            if(n==null){
                return false;
            }
            if(n.data==null){
                return (this.data==null);
            }else{
                return this.data.equals(n.data);
            }
        }
    }

    public LinkedList(){

    }

    @Override
    public int size() {
       return this.size;
    }

    public boolean isEmpty(){
        return head==null;
    }

    public boolean contains(Object o,Node n){
        if(n==null){
            return false;
        }
        return n.equals(o)?true:contains(o,n.next);
    }
    public boolean contains(Object o){
       return contains(o,head);
    }
    public int indexOf(Object o) {
        ListIterator iterator=(ListIterator)getIterator();
        while(iterator.hasNext()){
            Node node=(Node)iterator.next();
           if(o.equals(iterator.next())){
               return iterator.previousIndex();
           }
        }
        return -1;
    }

        @Override
    public Object[] toArray(Object[] array) {

    }

    @Override
    public ListIterator getListIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }
}
