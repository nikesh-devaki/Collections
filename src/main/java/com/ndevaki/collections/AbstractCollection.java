package com.ndevaki.collections;

import com.ndevaki.collections.utils.Iterator;

import java.io.InvalidClassException;
import java.util.Arrays;
import java.util.Collection;

/*
 *This class provides unmodifiable collection operations.
 * classes extending this class must override add,remove methods.
 * It uses iterator.remove method. remove doesnt happen until it is overriden
 */
public abstract class AbstractCollection<T> implements Collections{

    public boolean add(Object obj){
        throw new UnsupportedOperationException();
    }

    //this implementation can throw "Unsupported operation error" until add() is overriden
    public void addAll(Collections collection) {
      Iterator iterator=collection.getIterator();
      while(iterator.hasNext()){
          add(iterator.next());
      }
    }

    public boolean remove(Object obj) {
        Iterator iterator=getIterator();
       if(obj==null){
           while(iterator.hasNext()){
               if(iterator.next()==null){
                   iterator.remove();
                   return true;
               }
           }
       }else{
           while(iterator.hasNext()){
               if(iterator.next().equals(obj)){
                   return true;
               }
           }
       }
       return false;
    }

    public boolean removeAll(Collections collection) throws InvalidClassException {
        Iterator iterator=collection.getIterator();
        boolean modified=false;
        while(iterator.hasNext()){
            if(containsValue(iterator.next())){
                iterator.remove();
                modified=true;
            }
        }
        return modified;
    }

    public abstract int size() ;
    public boolean containsAll(Collections collection) throws InvalidClassException {
        Iterator iterator=collection.getIterator();
        while(iterator.hasNext()){
            return (!containsValue(iterator.next()));
        }
        return true;
    }

    public boolean containsValue(Object obj) throws InvalidClassException {
       Iterator iterator=getIterator();
       if(obj==null){
           while (iterator.hasNext()) {
               Object o = iterator.next();
               if (o.equals(null)) {
                   return true;
               }
           }
       }else {
           while (iterator.hasNext()) {
               Object o = iterator.next();
               if (o.equals(obj)) {
                   return true;
               }
           }
       }
       return false;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public void clear() {
        Iterator iterator=getIterator();
        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
    }

    //This method takes care if there wer any concurrent modifications to the list object.
    public Object[] toArray(){
        Object[] result=new Object[size()];
        Iterator iterator=getIterator();
        for(int i=0;i<result.length;i++){
            if(iterator.hasNext()){
                result[i]=iterator.next();
            }else{
                return Arrays.copyOf(result,i);
            }
        }
        return iterator.hasNext()? strech(iterator,result):result;
    }

    public Object[] strech(Iterator iterator,Object[] result) {
        int len=result.length;
        int i=len;
        while(iterator.hasNext()){
            //if full increemnt size by 0.5 times
            if(len==i){
                len+=(len>>1)+1;
                validateSize(len);
                result=Arrays.copyOf(result,len);
            }
            result[i++]=iterator.next();
        }
        //trim if there are any empty spaces
        return result.length==i?result: Arrays.copyOf(result, i);
    }

    public void validateSize(int n)  {
        if(n<0){
            throw new OutOfMemoryError("Size cannot be less than 0");
        }
        if(n>Integer.MAX_VALUE) {
            throw new OutOfMemoryError("Requested memmory allocation great than limit");
        }
    }

    public String toString(){
        Iterator iterator=getIterator();
        StringBuilder string=new StringBuilder("");
        if(iterator.hasNext()){
            string.append("[");
            while(iterator.hasNext()){
                string.append(iterator.next().toString());
                if(iterator.hasNext()){
                    string.append(", ");
                }else{
                    string.append("]");
                }
            }
        }else{
            string.append("[]");
        }
        return string.toString();
    }

    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator it = getIterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    public abstract Iterator<T> getIterator();

    public abstract boolean hasNext();

    public abstract Object next();

    public abstract boolean remove();
}
