package com.ndevaki.collections;

import com.ndevaki.collections.utils.Iterator;

import java.io.InvalidClassException;

public interface Collections<T> extends Iteratable {
    boolean add(T obj);
    void addAll(Collections<T> collection);
    boolean remove(T obj);
    boolean removeAll();
    int size();
    boolean containsValue(T obj) throws InvalidClassException;
    boolean isEmpty();
    void clear();
    Iterator getIterator();
    boolean equals(Object obj);
    T[] toArray();
    T[] toArray(T[] array);
    boolean containsAll(Collections<T> coll) throws InvalidClassException;
    String toString();
    boolean retainAll(Collections<T> coll);
}
