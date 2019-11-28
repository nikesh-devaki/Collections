package com.ndevaki.collections.utils.set;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class AbstractSet extends AbstractCollection implements Set {

    // unqiueness
    public boolean isEqual(Object obj){
        //Checks if argumanter pointer points to this.
        if(this==obj){
            return true;
        }
        if(!(obj instanceof Set)){
            return false;
        }
        if(!(((Set) obj).size()==this.size())){
            return false;
        }
        if(this.containsAll((Set)obj)){
            return true;
        }
        return false;
    }

    // set1.equals(set2) then hash(set1)==hash(set2)
    public int hashCode(){
        Iterator iterator=this.iterator();
        int sum=0;
        while(iterator.hasNext()){
            Object obj=(Object)iterator.next();
            if(obj!=null) {
                sum += obj.hashCode();
            }
        }
        return sum;
    }

    //final set gives the assymentrci difference between 2 sets
    public boolean removeAll(Collection c){
        boolean modified=false;
        Iterator iterator=c.iterator();
        while(iterator.hasNext()){
            if(this.contains(iterator.hasNext())){
                iterator.remove();
                modified=true;
            }
        }
        return modified;
    }

}
