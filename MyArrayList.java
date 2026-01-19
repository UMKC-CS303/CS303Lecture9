import java.util.*;
@SuppressWarnings("unchecked")

public class MyArrayList<E> implements MyList<E> {
    
    public static final int INITIAL_CAPACITY = 5;
    private E[] data = (E[]) new Object[INITIAL_CAPACITY];
    private int size = 0;

    /** Create a default list */
    public MyArrayList() { }

    /** Create a list from an array of objects */
    public MyArrayList(E[] objects) {
       for (int i = 0; i < objects.length; i++){
           if (objects[i] != null)
               add(objects[i]); // Warning: don't use super(objects)!
       }
    }

    @Override 
    //adding a definition for add method
    //overrides abstract method in the interface MyList
    /** Add a new element at the specified index */
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + " < size: " + size);

        ensureCapacity();

        // Move the elements to the right after the specified index
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        // Insert new element to data[index]
        data[index] = e;
        // Increase size by 1
        size++;
    }

    /** Create a new larger array, double the current size + 1 */
 
    private void ensureCapacity() {
        if (size >= data.length) {
            E[] newData = (E[])(new Object[size * 2 + 1]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    @Override /** Clear the list */
    //adding a definition for clear method
    //overrides abstract method in the interface MyList
    public void clear() {
        data = (E[])new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override /** Return true if this list contains the element */
    //adding a definition for add method
    //overrides abstract method in the Collection interface
    public boolean contains(Object e) {
        for (int i = 0; i < size; i++)
            if (e.equals(data[i])) return true;

        return false;
    }

    @Override /** Return the element at the specified index */
    //adding a definition for get method
    //overrides abstract method in MyList interface 
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }


    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException
                ("index " + index + " out of bounds");
    }

    @Override
    //adding a definition for size method
    //overrides abstract method in Collection interface 
    public int size(){
        return size;
    }

    @Override /** Return the index of the first matching element
    * in this list. Return -1 if no match. */
    //adding a definition for indexOf method
    //overrides abstract method in MyList interface 
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++)
           if (e.equals(data[i])) return i;

        return -1;
    }

    @Override  
    //adding a definition for lastIndexOf method
    //overrides abstract method in MyList interface 
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--)
        if (e.equals(data[i])) return i;

        return -1;
    }

    @Override /** Remove the element at the specified position
    * in this list. Shift any subsequent elements to the left.
    * Return the element that was removed from the list. */
    public E remove(int index) {
        checkIndex(index);

        E e = data[index];

        // Shift data to the left
        for (int j = index; j < size - 1; j++)
        data[j] = data[j + 1];

        data[size - 1] = null; // This element is now null

        // Decrement size
        size--;
        return e;
    }

    @Override /** Replace the element at the specified position
    * in this list with the specified element. */
    //adding a definition for set method
    //overrides abstract method in MyList interface 
    public E set(int index, E e) {
        checkIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) result.append(", ");
        }

        return result.toString() + "]";
    }

    /** Trims the capacity to current size */
    public void trimToSize() {
        if (size != data.length) {
            E[] newData = (E[])(new Object[size]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        } // If size == capacity, no need to trim
    }

    @Override
    //Returns an array of Object for the elements in this collection.

    public Object[] toArray() {
        Object[] newList = new Object[size()];
        // Left as an exercise
        for (int i = 0; i < size(); i++)
            newList[i] = data[i];
        return newList;
    }
    
    
    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size())
            return Arrays.copyOf(data, size(), (Class <? extends T[]>) array.getClass());
            
        System.arraycopy(data, 0, array, 0, size());
        if (array.length > size())
            array[size()] = null;
        return array;    
    }


    @Override
    //Returns true if the collection contains all the elements in c.
    public boolean containsAll(Collection<?> c) {
        // Left as an exercise
        for (Object item : c){
            boolean currentItem = false;
            for (int i = 0; i < size; i++){
                if (item.equals(data[i])){ 
                    currentItem = true;
                    break;
                }
            }
            if (!currentItem)
                return false;
        }
        return true;

        /* chat 
        for (Object element : c) {
            if (!this.contains(element)) {
                return false;
            }
        }
        return true;
        */
    }

    
    @Override
    //PRE: Accepts a list of items 'c'
    //POST Adds all the elements in the collection c 
    //     returns true if data was updated 

    public boolean addAll(Collection<? extends E> c) {
        // Left as an exercise
        boolean updated = false;
        for (E e : c){
            this.add(size(), e);
            updated = true;
        }
        return updated;
    }

    @Override
    //Removes all the elements in c from this collection.
    public boolean removeAll(Collection<?> c) {
        boolean updated = false;
        int index;
        for (Object e : c){
            index = indexOf(e);
            while (index >= 0){
                remove(index);
                updated = true;
                index = indexOf(e);
            }
        }
        return updated;
    }


    @Override
    //keep only the elements in the other collection
    public boolean retainAll(Collection<?> c) {
        boolean updated = false;
        int index = 0;
        for (int i = 0 ; i < size() ; i++){
            if (c.contains(data[i]))
                data[index++] = data[i];
            else
                updated = true;
        }

        for (int i = index; i < size(); i++)
            data[i] = null;
        
        size = index;
        return updated;
    }

    @Override /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements java.util.Iterator<E> {
        private int current = 0; // Current index

        @Override
        public boolean hasNext() {
            return (current < size);
        }

        @Override
        public E next() {
            return data[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(current);
        }
    }

}