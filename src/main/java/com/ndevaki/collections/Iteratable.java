package com.ndevaki.collections;

public interface Iteratable<T> {
    // The hasNext method returns true if the iteration has more elements, and the next method returns the next element in the iteration.
    boolean hasNext();
    Object next();
    //The remove method removes the last element that was returned by next from the underlying Collection.
    // The remove method may be called only once per call to next and throws an exception if this rule is violated.
    //Note that Iterator.remove is the only safe way to modify a collection during iteration;
    // the behavior is unspecified if the underlying collection is modified in any other way while the iteration is in progress.
    //Such as concurrent iteration modification error
    boolean remove();

    //Note:Use Iterator instead of the for-each construct when you need to:
    //1.Remove the current element. The for-each construct hides the iterator, so you cannot call remove. Therefore, the for-each construct is not usable for filtering.
    //2.Iterate over multiple collections in parallel.
}
