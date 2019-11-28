package com.ndevaki.collections.utils.set;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.util.*;
import java.util.NavigableSet;
import java.util.SortedSet;

/*
 * https://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html
 * The natural ordering for a class C is said to be consistent with equals if and only if e1.compareTo(e2) == 0 has the
 * same boolean value as e1.equals(e2) for every e1 and e2 of class C.
 *
 *
 * All general-purpose sorted set implementation classes should
 * 1) A void (no arguments) constructor, which creates an empty sorted set sorted according to the natural ordering of
 * its elements.
 *  TreeSet();
 * 2) A constructor with a single argument of type <tt>Comparator</tt>, which creates an empty
 * sorted set sorted according to the specified comparator.
 * TreeSet(Comparator<E>);
 * 3) A constructor with a single argument of type <tt>Collection</tt>,
 * which creates a new sorted set with the same elements as its
 * argument, sorted according to the natural ordering of the elements.
 * TreeSet(Collection<E>);
 * 4) A constructor with a single argument of type <tt>SortedSet</tt>,
 * which creates a new sorted set with the same elements and the same
 * ordering as the input sorted set.  There is no way to enforce this
 * recommendation, as interfaces cannot contain constructors.
 * TreeSet(SortedSet<E>);
 *
 */
public class TreeSet<E> extends AbstractSet
        implements Serializable, Cloneable, NavigableSet {

    private transient NavigableMap<E,Object> map;
    private static final Object PRESENT= new Object();

    public TreeSet(){
            this(new TreeMap<E,Object>());
    }

    public TreeSet(NavigableMap<E,Object> map){
        this.map=map;
    }

    public TreeSet(Comparator<E> comparator){
        this(new TreeMap<E, Object>(comparator));
    }

    public TreeSet(SortedSet<E> sortedSet){
        this((Comparator<E>) sortedSet.comparator());
        addAll(sortedSet);
    }

    public TreeSet(Collection<E> collection){
        this();
        addAll(collection);
    }

    @Override
    public Object lower(Object o) {
        return  map.navigableKeySet().lower((E)o);
    }

    @Override
    public Object floor(Object o) {
        return  map.navigableKeySet().floor((E)o);
    }

    @Override
    public Object ceiling(Object o) {
        return  map.navigableKeySet().ceiling((E)o);
    }

    @Override
    public Object higher(Object o) {
            return map.higherKey((E) o);
    }

    @Override
    public Object pollFirst() {
        return map.pollFirstEntry().getKey();
    }

    @Override
    public Object pollLast() {
        return map.pollLastEntry().getKey();
    }

    @Override
    public Iterator iterator() {
        return map.navigableKeySet().iterator();
    }

    @Override
    public NavigableSet descendingSet() {
        return map.descendingKeySet().descendingSet();
    }

    @Override
    public Iterator descendingIterator() {
        return  map.descendingKeySet().iterator();
    }

    @Override
    public NavigableSet subSet(Object fromElement, boolean fromInclusive, Object toElement, boolean toInclusive) {
        return map.navigableKeySet().subSet((E)fromElement,fromInclusive,(E)toElement,toInclusive);
    }

    @Override
    public NavigableSet headSet(Object toElement, boolean inclusive) {
        return map.navigableKeySet().headSet((E)toElement,inclusive);
    }

    @Override
    public NavigableSet tailSet(Object fromElement, boolean inclusive) {
        return map.navigableKeySet().tailSet((E)fromElement,inclusive);
    }

    @Override
    public Comparator comparator() {
        return map.navigableKeySet().comparator();
    }

    @Override
    public SortedSet subSet(Object fromElement, Object toElement) {
        return map.navigableKeySet().subSet((E)fromElement,(E)toElement);
    }

    @Override
    public SortedSet headSet(Object toElement) {
        return map.navigableKeySet().headSet((E)toElement);
    }

    @Override
    public SortedSet tailSet(Object fromElement) {
        return  map.navigableKeySet().tailSet((E)fromElement);
    }

    @Override
    public Object first() {
        return  map.navigableKeySet().first();
    }

    @Override
    public Object last() {
        return  map.navigableKeySet().last();
    }

    @Override
    public int size() {
        return map.navigableKeySet().size();
    }
}
