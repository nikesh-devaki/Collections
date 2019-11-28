package com.ndevaki.collections.utils.map;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
 *
Steps to create unmodifiable map:
1)	Extend Abstarct Map
2)	Implement EntrySet which gives Set.
3)	For returened set, do not support add, remove or iterator.remove() actions
Steps to create modifiable map:
1)	Extends AbstarctMap and implement put,remove(key),iterator.remove()

* Constructors shold be provided by implemntations
*  > Empty args
*  >args wth map
*
 */
public abstract class AbstractMap<K,V> implements Map {

    @Override
    public int size() {
        return entrySet().size();
    }

    @Override
    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        Iterator iterator=entrySet().iterator();
        while(iterator.hasNext()){
            Entry entry=(Entry)iterator.next();
            if(key==null){
                if(entry.getKey()==null){
                    return true;
                }
            }else{
                if(key.equals(entry.getKey())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Iterator iterator=entrySet().iterator();
        while(iterator.hasNext()){
            Entry entry=(Entry)iterator.next();
            if(value==null){
                if(entry.getValue()==null){
                    return true;
                }
            }else{
                if(entry.equals(entry.getValue())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        Iterator iterator=entrySet().iterator();
        while(iterator.hasNext()){
            Entry entry=(Entry)iterator.next();
            if(key==null){
                if(entry.getKey()==null){
                    return entry.getValue();
                }
            }else{
                if(key.equals(entry.getKey())){
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        throw new UnsupportedOperationException("Put not supperoted by abstarct map");
    }

    public Object remove(Object key){
      Iterator<Entry> iterator=(Iterator<Entry>)entrySet().iterator();
        V oldValue=null;
      while(iterator.hasNext()){
          if(key==null){
              Entry obj=iterator.next();
              oldValue=(V)obj.getValue();
             if(obj.getKey()==null){
                 iterator.remove();
             }
          }else{
              Entry obj=iterator.next();
              oldValue=(V)obj.getValue();
              if(obj.getKey().equals(key)){
                  iterator.remove();
              }
          }
      }
      return oldValue;
    }

    @Override
    public void putAll(Map m) {
        for(java.util.Map.Entry entry:m.entrySet()){
            put(entry.getKey(),entry.getValue());
        }
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Put not supperoted by abstarct map");
    }

    transient volatile Set<K>        keySet = null;
    transient volatile Collection<V> values = null;

    @Override
    public Set keySet() {
        return entrySet();
    }

    @Override
    public Collection values() {
        return null;
    }

    public abstract Set<Entry> entrySet();

    //Key is always final. No setters for key
    //It's value is mutable
    public static class SimpleEntry<K,V> implements Serializable{
        private final K key;
        private V value;

        public SimpleEntry(K key,V value){
            this.key=key;
            this.value=value;
        }

        public SimpleEntry(Entry<K, V> entrySet) {
            this.key = entrySet.getKey();
            this.value=entrySet.getValue();
        }

        public K getKey(){
            return key;
        }
        public V getValue(){
            return value;
        }

        public V setValue(V value){
           V oldValue=value;
           this.value=value;
           return oldValue;
        }

        public int hashCode(){
            return (key==null?0:key.hashCode())^
                    (value==null?0:value.hashCode());
        }

        public boolean equals(Object o){
            if(!(o instanceof Entry) ){
                return false;
            }
            Entry entry=(Entry)o;
            return o.equals(this);
        }

        public String toString(){
            return key+"="+value;
        }
    }

    public static class SimpleImmutableEntry<K,V> implements Serializable{
        private final K key;
        private final V value;

        public SimpleImmutableEntry(K key,V value){
            this.key=key;
            this.value=value;
        }

        public SimpleImmutableEntry(Entry<K, V> entrySet) {
            this.key = entrySet.getKey();
            this.value=entrySet.getValue();
        }

        public K getKey(){
            return key;
        }
        public V getValue(){
            return value;
        }

        public void setValue(V value){
            throw new UnsupportedOperationException();
        }

        public int hashCode(){
            return (key==null?0:key.hashCode())^
                    (value==null?0:value.hashCode());
        }

        public boolean equals(Object o){
            if(!(o instanceof Entry) ){
                return false;
            }
            Entry entry=(Entry)o;
            return o.equals(this);
        }

        public String toString(){
            return key+"="+value;
        }
    }
}
