package com.ndevaki.collections.utils.map;

import java.util.Collection;
import java.util.Set;

/*
 * Provides 3 view operations
 * > view all key set
 * > view all value set
 * > view all key-value pair
 *
 * Some implmentations may or may not support nullable keys, nullable values and nullable key/value paris
 *  Should be carefull with mutable objects. Mutated key object's will behave weirdly.
 * should use immutable map implementations.
 *
 * "A map can never be key of another map. while map can be as value of another map, but need to be cautious about
 *  it hashcode and equals values"
 *
 * 2 Types of constructs should be provided by implementation classes
 * > Empty constructor
 * > constructor with map as argument
 *   This lets to create new map type with pairs of argument map. Although it is diffiuclt to enforce this condition,
 *   all map implementations in jdk will comply to it.
 *
 *  *
 * <p>Some map implementations have restrictions on the keys and values they
 * may contain.  For example, some implementations prohibit null keys and
 * values, and some have restrictions on the types of their keys.  Attempting
 * to insert an ineligible key or value throws an unchecked exception,
 * typically <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.
 * Attempting to query the presence of an ineligible key or value may throw an
 * exception, or it may simply return false; some implementations will exhibit
 * the former behavior and some will exhibit the latter.  More generally,
 * attempting an operation on an ineligible key or value whose completion
 * would not result in the insertion of an ineligible element into the map may
 * throw an exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * <p>Many methods in Collections Framework interfaces are defined
 * in terms of the {@link Object#equals(Object) equals} method.  For
 * example, the specification for the {@link #containsKey(Object)
 * containsKey(Object key)} method says: "returns <tt>true</tt> if and
 * only if this map contains a mapping for a key <tt>k</tt> such that
 * <tt>(key==null ? k==null : key.equals(k))</tt>." This specification should
 * <i>not</i> be construed to imply that invoking <tt>Map.containsKey</tt>
 * with a non-null argument <tt>key</tt> will cause <tt>key.equals(k)</tt> to
 * be invoked for any key <tt>k</tt>.  Implementations are free to
 * implement optimizations whereby the <tt>equals</tt> invocation is avoided,
 * for example, by first comparing the hash codes of the two keys.  (The
 * {@link Object#hashCode()} specification guarantees that two objects with
 * unequal hash codes cannot be equal.)  More generally, implementations of
 * the various Collections Framework interfaces are free to take advantage of
 * the specified behavior of underlying {@link Object} methods wherever the
 * implementor deems it appropriate.
 */
public interface Map<K,V> {

    //query operations
    int size();
    boolean isEmpty();
    Set<K> keySet();
    Collection<V> getValues();
    V get(K key);
    //(key==null ? k==null : key.equals(k))
    boolean containsKey(K key);
    boolean conatainsValue(V value);
    Set<java.util.Map.Entry<K,V>> entrySet();

    //Modify operations
    boolean add(V value);
    boolean remove(K key);
    boolean removeValue(V value);
    V put(K key,V value);
    void clear();

    interface EntrySet<K,V>{
        K getKey();
        V getValue();
        void setKey(K Key);
        void setValue(V value);
        //if((e1.getKey()==null? e1.getKey()==e2.getKey():e1.getKey().equals(e2.getKey())&&
        //     (e1.getValue()==null? e2.getValue()==null:e1.getValue().equals(e2.getValue()))
        boolean equals(EntrySet<K,V> entry);
        // (e1.key()==null?0:e1.key().hashCode()^
        //      e1.value()==null?0:e1.value.hashCode()
        int hashCode();
    }

    //2 maps are equal if their map.entrySet().equlas(arg.entryset());
    boolean equals(Map map);
    //It is sum of hash codes of entryset
    int hashCode();
}
