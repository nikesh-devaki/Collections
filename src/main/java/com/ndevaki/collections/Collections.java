package com.ndevaki.collections;

import com.ndevaki.collections.utils.Iterator;
import com.sun.istack.internal.NotNull;

import java.io.InvalidClassException;

public interface Collections<T> extends Iteratable {
    void add(T obj);
    void addAll(Collections<T> collection);
    boolean remove(T obj);
    boolean removeAll();
    int size();
    boolean containsValue(@NotNull  T obj) throws InvalidClassException;
    boolean isEmpty();
    void clear();
    Iterator getIterator();
}
