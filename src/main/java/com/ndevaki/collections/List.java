package com.ndevaki.collections;

import java.io.InvalidClassException;
import java.util.Collection;
import java.util.ListIterator;

public interface List<T> extends Collections<T> {
    //positional operations
    void add(int index,T value);
    Object get(int index);
     //returns original value
    Object set(int index,Object value);
     //returns original value
    Object remove(int index);
    void remove(int startIndex,int endIndex);
    //Should accept Object or T? Which is correct?
    void replace(int index,T value) throws InvalidClassException;
    Object getValueAtIndex(int index);
    int getLastIndex(Object obj);
    // search/Query operations
    int getIndexOf(T obj) throws InvalidClassException;
    boolean equals(Object obj);
    //Iteration operations
    ListIterator<T> listIterator();
    //Range operations
    List<T> subList(int startIndex,int endIndex);
    void removeRange(int startIndex,int endIndex);
    boolean addAll(int index, Collection collection);
}
