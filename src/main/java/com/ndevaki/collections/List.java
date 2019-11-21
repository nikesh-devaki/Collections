package com.ndevaki.collections;

import java.io.InvalidClassException;

public interface List<T> extends Collections<T> {
    int getIndexOf(T obj) throws InvalidClassException;
    T getValueAtIndex(int index);
    List<T> subList(int startIndex,int endIndex);
    void replace(int index,T value) throws InvalidClassException;
    void add(int index,T value);
}
