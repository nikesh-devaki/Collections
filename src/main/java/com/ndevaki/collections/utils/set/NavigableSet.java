package com.ndevaki.collections.utils.set;

import java.util.Iterator;

public interface NavigableSet<E> extends SortedSet<E> {

    public E floor(E obj);
    public E ceiling(E obj);
    public Set<E> higher(E obj);
    public Set<E> lower(E obj);
    public E pollFirst();
    public E pollLast();
    //iterates through sorted order
    public Iterator iterator();
    public SortedSet<E> headSet(E toElement,boolean inclusive);
    public SortedSet<E> tailSet(E fromElement,boolean inclusive);
    public java.util.SortedSet subset(E fromElement, E toElment, boolean startInclusive, boolean endInlcusive);
    public Iterator descendingIterator();
    public java.util.SortedSet descendingSet();
}
