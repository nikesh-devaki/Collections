package com.ndevaki.collections.utils.set;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.AbstractSet;

/*
 * Implemented using HashMap.
 * add,remove,get,contains,size operations run on constant time
 * sequence of objects isnot guaranteed
 * Other operations takes in the order of size and capacity of map.
 *
 * Not synchronized. so use Collections.synchronizedSet
 *
 * Iterators workd on fail-fast basis and works on best-effort basis.
 *
 */
public class HashSet<E> extends AbstractSet<E> implements Set<E>,Cloneable, Serializable {

    int size;
    int modCount=0;
    private transient HashMap<E,Object> map;
    private static final Object PRESENT = new Object();

    //Initial capacity 16 and load factor 0.75
    public HashSet(){
        map=new HashMap<E,Object>();
    }

    public HashSet(Collection collection){
        map=new HashMap<E, Object>((Math.max((int) (collection.size()/0.75)+1,16)));
        addAll(collection);
    }

    public HashSet(int initialCapacity,int loadFactor){
        map=new HashMap<E, Object>(initialCapacity,loadFactor);
    }

    public HashSet(int loadFactor){
        map=new HashMap<E, Object>(loadFactor);
    }

    @Override
    public int size(){
        return map.size();
    }

    public boolean isEmpty(){
        return map.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    public boolean add(Object obj){
       return map.put((E)obj,PRESENT)==null;
    }

    public boolean containsKey(Object obj){
        return map.containsKey(obj);
    }

    public boolean remove(Object obj){
        return map.remove(obj)==PRESENT;
    }

    public Object clone(){
        HashMap<E,Object> copy=new HashMap<E,Object>();
        copy=(HashMap<E,Object>)map.clone();
        return copy;
    }

    public void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
        outputStream.writeInt(map.capacity());
        outputStream.writeFloat(map.loadFactor());
        outputStream.writeInt(map.size());
        for(Object key:map.keySet()){
            outputStream.writeObject(key);
        }
    }

    public void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        int capacity= inputStream.readInt();
        float loadFactor=inputStream.readFloat();
        map= ((HashSet)this) instanceof  LinkedHashSet ?
                new LinkedHashMap<E, Object>(capacity,loadFactor):
                new HashMap<E, Object>(capacity,loadFactor);
        int size=inputStream.readInt();
        for(int i=1;i<=size;i++){
            map.put((E)inputStream.readObject(),PRESENT);
        }
    }

}
