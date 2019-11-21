package com.ndevaki.collections.utils;

import com.ndevaki.collections.Collections;
import com.ndevaki.collections.List;

import java.io.InvalidClassException;
import java.security.InvalidParameterException;

public class ArrayList<T> implements List {

    private static float threshold_limit=0.75f;
    private Object[] data;
    private int lastIndex=-1;

    public ArrayList() {
        this.data = new Object[1];
    }

    public ArrayList(int startSize){
        this.data=new Object[startSize];
    }

    public int getIndexOf(Object obj) throws InvalidClassException {
        if(obj==null){
            throw new NullPointerException("Input cannot be null");
        }
        if(!(obj instanceof T)){
            throw new InvalidClassException("Invalid type passed");
        }
        for(int i=0;i<this.size();i++){
            if(obj.equals(data[i])){
                return i;
            }
        }
        return -1;
    }

    public Object getValueAtIndex(int index) {
        return data[index];
    }

    public List subList(int startIndex, int endIndex) {
        if(endIndex<startIndex){
            throw new InvalidParameterException("End index cannot be less than Start Index");
        }
        if(endIndex<0||startIndex<0||endIndex>=data.length||startIndex>=data.length){
            throw new IndexOutOfBoundsException("Invalid index passed");
        }
        int size=endIndex-startIndex+1;
        List<T> result=new ArrayList<T>();
        for(int i=startIndex;i<=endIndex;i++){
            result.add((T)data[i]);
        }
        return result;
    }

    //validate exception types and method of throwing exceptions
    public void replace(int index, Object value) throws InvalidClassException {
        if(index<0||index>=data.length||index>=data.length){
            throw new IndexOutOfBoundsException("Invalid index passed");
        }
        if(!(value instanceof T)){
            throw new InvalidClassException("Invalid type passed");
        }
        data[index]=value;
    }

    public void add(int index, Object value) {

    }

    public void add(Object obj) {
        int size=this.size();
        int length=data.length;
        //good palce to validate? inside resize or here?
        if(size*threshold_limit>length){
           data=resize(data);
        }
        //How to maintain last index? lastindex-1 or lkast index?
        lastIndex++;
        data[lastIndex]=obj;
    }

    Object[] resize(Object[] obj){
        int newLength=2*obj.length;
        Object[] newArray=new Object[newLength];
        for(int i=0;i<obj.length;i++){
            newArray[i]=obj[i];
        }
        return newArray;
    }

    //How much to resize? to size of new colelction or maintain 0.75 after resisze?
    //What are best sequence of steps to be fdollowed
    public void addAll(Collections collection) {

    }

    public boolean remove(Object obj) {
        return false;
    }

    //does array accepts 0 size?
    // what shoudl be starting size?
    public boolean removeAll() {
        data=new T[0];
        return true;
    }

    // is it good to rely on lastIndex? or array length?
    public int size() {
        return data.length;
    }

    public boolean containsValue(Object obj) throws InvalidClassException {
        if(!(obj instanceof T)){
            throw new InvalidClassException("Invalid type passed");
        }
        for(int i=0;i<data.length;i++){
            if(data[i].equals(obj)){
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return this.size()!=0?false:true;
    }

    public void clear() {
        this.data=new Object[0];
        lastIndex--;
    }

    public Iterator getIterator() {
        return null;
    }

    public boolean hasNext() {
        return false;
    }

    public T next() {
        return null;
    }
}
