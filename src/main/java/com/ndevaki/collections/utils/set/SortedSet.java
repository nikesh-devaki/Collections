package com.ndevaki.collections.utils.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

/*
 * Eleemts must implement Comparable must be implemented for sorting
 * all elements must be mutually comparable
 * (such as e1.comapreTo(e2) or Comparator.compare(e1,e2);
 * TODO: read about general contract between compareTo and equals
 */
public interface SortedSet<E> extends Set {

    public Comparator<E> comparator(Set set);
    public SortedSet<E> headSet(E toElement);
    public SortedSet<E> tailSet(E fromElement);
    public SortedSet<E> subSet(E fromElement,E toElement);
    public E first();
    public E last();

}
