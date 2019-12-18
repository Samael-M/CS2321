package cs2321;

import net.datastructures.List;

import java.util.Iterator;

public class Set<E> {

    //Used ArrayList as tempalte for makeshift "Set" Data structure for DFS and BFS

    private int CAPACITY;
    private E[] data;
    private int size = 0;

    public Set() {
        this(16);
    }

    public Set(int capacity) {
        CAPACITY = capacity;
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    public Boolean contains(E e) {
        for (E ee : data) {
            if (e.equals(ee)) {
                return true;
            }
        }
        return false;
    }

    public void add(int i, E e) throws IndexOutOfBoundsException {
        if (valid(e)) {
            checkIndex(i, size + 1);
            if (size == data.length) {
                increaseSize();
            }
            for (int k = size - 1; k >= i; k--)
                data[k + 1] = data[k];
            data[i] = e;
            size++;
        }
    }

    public boolean valid(E e) {
        for (E ee : data) {
            if (e.equals(ee)) {
                return false;
            }
        }
        return true;
    }

    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        for (int k = i; k < size - 1; k++)
            data[k] = data[k + 1];
        data[size - 1] = null;
        size--;
        return temp;
    }

    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }

    public Iterator<E> iterator() {
        return new SetIterator();
    }

    public void add(E e) {
        if (valid(e)) {
            if (size == data.length)
                increaseSize();
            add(size, e);
        }
    }

    public void addFirst(E e)  {
        if (valid(e)) {
            if(size == data.length)
                increaseSize();
            add(0, e);
        }

    }

    // Return the capacity of array, not the number of elements.
    // Notes: The initial capacity is 16. When the array is full, the array should be doubled.

    /**
     * checks capacity of current array
     *
     * @return CAPSCITY, capacity of array
     */
    public int capacity() {
        return CAPACITY;
    }

    /**
     * Doubles the capacity variable, creates new array with new capacity and copies data from old array to new
     */
    public void increaseSize() {
        CAPACITY = CAPACITY * 2;
        E[] newArray = (E[]) new Object[CAPACITY];

        for (int j = 0; j < data.length; j++) {
            newArray[j] = data[j];
        }
        data = newArray.clone();
    }

    private class SetIterator implements Iterator {
        int cursor = 0;

        public boolean hasNext() {
            if (cursor > size - 1)
                return false;
            return true;
        }

        public E next() {
            E e = get(cursor);
            cursor++;
            return e;
        }
    }

}
