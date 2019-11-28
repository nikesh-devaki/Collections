package com.ndevaki.collections.utils.map;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public class HashMap<K,V> implements Map<K,V>,Cloneable, Serializable {

    private static final int DEFAULT_INITIAL_CAPACITY=16;
    private static final int MAXIMUM_CAPACITY=1<<30; //i.e., 2^30
    private static final float loadfactor=0.75f;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> getValues() {
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean conatainsValue(V value) {
        return false;
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public boolean add(V value) {
        return false;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public boolean removeValue(V value) {
        return false;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Map map) {
        return false;
    }
}
