package com.ndevaki.collections.utils.list;

import com.ndevaki.collections.AbstractCollection;
import com.ndevaki.collections.Collections;
import com.ndevaki.collections.utils.Iterator;

import java.io.InvalidClassException;
import java.util.Collection;
import java.util.ListIterator;

//This class works based based get(),set(),size() unimplemented classes.
public abstract class AbstractList<T> extends AbstractCollection implements List {

    public boolean add(Object obj){
        add(size(),obj);
        return true;
    }
    public boolean removeAll() {
        return false;
    }

    public abstract int size();

    @Override
    public Iterator getIterator() {
        return null;
    }

    public abstract ListIterator getListIterator();

    @Override
    public boolean retainAll(Collections coll) {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public boolean remove() {
        return false;
    }

    @Override
    public void add(int index, Object value) {
        throw new UnsupportedOperationException();
    }

    public Object get(int index){
        throw new UnsupportedOperationException();
    }

    public Object set(int index, Object value){
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(int startIndex, int endIndex) {
        ListIterator iterator=getListIterator(startIndex);
        for(int i=0;i<endIndex-startIndex;i++){
            iterator.next();
            iterator.remove();
        }
    }

    @Override
    public void replace(int index, Object value) throws InvalidClassException {

    }

    @Override
    public Object getValueAtIndex(int index) {
        return null;
    }

    @Override
    public int getIndexOf(Object obj) throws InvalidClassException {
       ListIterator iterator=getListIterator();
       if(obj==null) {
           while (iterator.hasNext()) {
               if (iterator.next() == null) {
                   return iterator.previousIndex();
               }
           }
       }else {
           while (iterator.hasNext()) {
               if (iterator.next().equals(obj)) {
                   return iterator.previousIndex();
               }
           }
       }
       return -1;
    }

    public int getLastIndex(Object obj){
        ListIterator iterator=getListIterator();
        while(iterator.hasNext()){
            iterator.next();
        }
        if(obj==null){
            while(iterator.hasPrevious()){
                if(iterator.previous()==null){
                    iterator.nextIndex();
                }
            }
        }else{
            while(iterator.hasPrevious()){
                if(iterator.previous().equals(obj)){
                    iterator.nextIndex();
                }
            }
        }
        return -1;
    }

    public void removeRange(int startIndex,int endIndex){
      ListIterator iterator=getListIterator(startIndex);
      for(int i=0;i<endIndex-startIndex;i++){
          iterator.next();
          iterator.remove();
      }
    }

    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(!(obj instanceof List)){
            return false;
        }
        ListIterator current=getListIterator();
        ListIterator objIterator=((AbstractList)obj).getListIterator();
        while(current.hasNext()&&objIterator.hasNext()){
            T lhs=(T)current.next();
            Object rhs=objIterator.next();
            if(lhs==null||rhs==null){
             if(!(lhs==null&&rhs==null)){
                 return false;
             }
            }else {
                if (!current.next().equals(objIterator.next())) {
                    return false;
                }
            }
        }
        if(current.hasNext()||objIterator.hasNext()){
            return false;
        }
        return true;
    }

    public boolean addAll(int index, Collection collection){
        validateRange(index);
        boolean modified=false;
        for(Object t:collection){
            add(index++,t);
            modified=true;
        }
        return modified;
    }

    boolean validateRange(int index){
        if(index<0||index>=size()){
            return false;
        }
        return true;
    }

    @Override
    public List subList(int startIndex, int endIndex) {
        return null;
    }
}
