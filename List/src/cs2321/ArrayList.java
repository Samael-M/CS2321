package cs2321;

import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {

	private int CAPACITY = 16;
	private E[] data;
	private int size = 0;

	public ArrayList() {
		this(16);
	}

	public ArrayList(int capacity) {
		data = (E[]) new Object[capacity];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return data[i];
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		data[i] = e;
		return temp;
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size + 1);
		if(size == data.length) {
			increaseSize();
		}
		for(int k = size - 1; k>= i; k--)
			data[k+1] = data[k];
		data[i] =e;
		size++;
	}
	
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		for(int k = i; k < size - 1; k++)
			data[k] = data[k+ 1];
		data[size -1] = null;
		size--;
		return temp;
	}

	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n)
			throw  new IndexOutOfBoundsException("Illegal index: " + i);
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}

	public void addFirst(E e)  {
		if(size == data.length)
			increaseSize();
		add(0, e);
	}
	
	public void addLast(E e)  {
		if(size == data.length)
			increaseSize();
		add(size, e);
	}
	
	public E removeFirst() throws IndexOutOfBoundsException {
		return this.remove(0);
	}
	
	public E removeLast() throws IndexOutOfBoundsException {
		return remove(size - 1);
	}
	
	// Return the capacity of array, not the number of elements.
	// Notes: The initial capacity is 16. When the array is full, the array should be doubled. 
	public int capacity() {
		return CAPACITY;
	}

	public void increaseSize() {
		CAPACITY = CAPACITY * 2;
		E[] newArray = (E[]) new Object[CAPACITY];

		for(int j = 0; j < data.length; j++) {
			newArray[j] = data[j];
		}
		data = newArray;
	}

	private class ArrayListIterator implements Iterator {
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
