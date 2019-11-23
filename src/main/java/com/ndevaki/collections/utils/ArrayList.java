package com.ndevaki.collections.utils;

import com.ndevaki.collections.AbstractList;
import com.ndevaki.collections.Collections;
import com.ndevaki.collections.List;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.ListIterator;

public class ArrayList<T> extends AbstractList<T>
                        implements Serializable {

    private static float threshold_limit=0.75f;
    //array data serialized through write and read() methods. not using default serialization mechanism
    private transient Object[] data;
    private int size;
    private int lastIndex=-1;

    public ArrayList() {
        this(10);
        //this.data = new Object[10];
    }

    public ArrayList(int startSize){
        super();
        if(startSize<0){
            throw new IllegalArgumentException("Initial size canotbe less than 0");
        }
        this.data=new Object[startSize];
    }

    public ArrayList(Collections<? extends T> collection){
        data=collection.toArray();
        size=data.length;
        //TODO: Need to handle when toArray() returns in different type
    }

    public void trimSize(){
        if(data.length>size) {
            data = Arrays.copyOf(data, size);
        }
    }

    public void ensureCapcity(int minLength){
        if(minLength>0 && data.length-size<minLength){
           int diff= data.length-size+minLength;
           int newLength=(int)(1+threshold_limit)*diff;
           data=Arrays.copyOf(data,newLength);
        }
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

    public ArrayList<T> clone(){
        //TODO: Need to implement
    }
    public T[] toArray(){
        return (T[])Arrays.copyOf(data,size);
    }


    public T[] toArray(T[] array){
        if(array.length<size){
            array=new T[size];
            array=(T[])Arrays.copyOf(data,size);
        }else{
            for(int i=0;i<size;i++){
                array[i]=(T)data[i];
            }
        }
        return array;
    }
    @Override
    public ListIterator listIterator() {
        return null;
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

    //TODO: System.arraycopy() should use. Pending
    public void add(int index, Object value) {
        ensureCapcity(1);
        int i=size-1;
        for(;i>=index;i--){
            data[i+1]=data[i];
        }
        data[i]=value;
        size++;
    }

    public void validateIndex(int index){
        if(index<0||index>data.length){
            throw new ArrayIndexOutOfBoundsException("incorrect value "+index);
        }
    }
    public Object get(int index) {
       validateIndex(index);
       return data[index];
    }

    public Object set(int index, Object value) {
        validateIndex(index);
        Object temp=data[index];
        data[index]=value;
        return temp;
    }
    //TODO: System.arraycopy() should use. Pending
    public Object remove(int index) {
        return null;
    }

    public void remove(int startIndex, int endIndex) {

    }

    public boolean add(Object obj) {
        int size=this.size();
        int length=data.length;
        boolean modified=false;
        //good palce to validate? inside resize or here?
        if(size*threshold_limit>length){
           data=resize(data);
        }
        //How to maintain last index? lastindex-1 or lkast index?
        lastIndex++;
        data[lastIndex]=obj;
        modified=true;
        return false;
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
        return size;
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
        return this.size()==0;
    }

    public void clear() {
        this.data=new Object[0];
        lastIndex--;
    }

    public Iterator getIterator() {
        return null;
    }

    @Override
    public ListIterator getListIterator() {
        return null;
    }

    @Override
    public boolean retainAll(Collections coll) {
        return false;
    }

    public boolean hasNext() {
        return false;
    }

    public T next() {
        return null;
    }

    public boolean remove() {
        return false;
    }
}
