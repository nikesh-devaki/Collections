package com.ndevaki.collections.utils.list;

import com.ndevaki.collections.Collections;
import com.ndevaki.collections.utils.Iterator;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.RandomAccess;

/*
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access an <tt>ArrayList</tt> instance concurrently,
 * and at least one of the threads modifies the list structurally, it
 * <i>must</i> be synchronized externally.  (A structural modification is
 * any operation that adds or deletes one or more elements, or explicitly
 * resizes the backing array; merely setting the value of an element is not
 * a structural modification.)  This is typically accomplished by
 * synchronizing on some object that naturally encapsulates the list.
 *
 *  *
 * If no such object exists, the list should be "wrapped" using the
 * {@link Collections#synchronizedList Collections.synchronizedList}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the list:<pre>
 *   List list = Collections.synchronizedList(new ArrayList(...));</pre>
 *
 *
 * ---Iterator works on fail-fast principle
 *  *
 * <p><a name="fail-fast"/>
 * The iterators returned by this class's {@link #iterator() iterator} and
 * {@link #listIterator(int) listIterator} methods are <em>fail-fast</em>:
 * if the list is structurally modified at any time after the iterator is
 * created, in any way except through the iterator's own
 * {@link ListIterator#remove() remove} or
 * {@link ListIterator#add(Object) add} methods, the iterator will throw a
 * {@link ConcurrentModificationException}.  Thus, in the face of
 * concurrent modification, the iterator fails quickly and cleanly, rather
 * than risking arbitrary, non-deterministic behavior at an undetermined
 * time in the future.
 */
//TODO:Work on Co-Modifications
public class ArrayList<T> extends AbstractList<T>
                        implements List, RandomAccess,Cloneable,Serializable {

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
        return null;
    }
    public T[] toArray(){
        return (T[])Arrays.copyOf(data,size);
    }

    @Override
    public Object[] toArray(Object[] array) {
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
        System.arraycopy(data,index,data,index+1,size-index);
        data[index]=value;
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

    public Object remove(int index) {
        validateIndex(index);
        Object oldValue=data[index];
        System.arraycopy(data,index+1,data,index,size-index+1);
        data[size--]=null;
        return oldValue;
    }

    public void remove(int startIndex, int endIndex) {
        validateIndex(startIndex);
        validateIndex(endIndex);
        if(startIndex<endIndex){
            throw new IllegalArgumentException("Start index should be lower than endIndex");
        }
        System.arraycopy(data,endIndex+1,data,startIndex,size-endIndex);
        for(int i=endIndex+1;i<data.length;i++){
            data[i]=null;
        }
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
       for(int i=0;i<data.length;i++){
           data[i]=null;
       }
       size=0;
    }

    /*
     * Ignore static,transient variables
     * serilaize modification count, size, array data
     */
    public void writeObject(ObjectOutputStream output) throws IOException {
        //caputure count of data array
        output.defaultWriteObject();
        output.writeInt(size);
        for(int i=0;i<size;i++){
            output.writeObject(data[i]);
        }
        //if count changes inbetween throw ConcurrentModificationException
    }

    public ArrayList<T> readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        ArrayList<T> result=new ArrayList<T>();
        result.size=inputStream.re

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

    //Implement Iterator
    // Implement ListIterator
    //Implement Sublist
}
