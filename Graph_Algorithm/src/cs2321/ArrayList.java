package cs2321;

import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {

	//instance varaibles
	private int CAPACITY;
	private E[] data;
	private int size = 0;

	public ArrayList() {
		this(16);
	}

	public ArrayList(int capacity) {
		CAPACITY = capacity;
		data = (E[]) new Object[capacity];
	}

	/**
	 * @return size of ArrayList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 *@return returns boolean value of whether array is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 *gets data that is stored in position i
	 * @param i index of array
	 * @return data located at index i
	 * @throws IndexOutOfBoundsException if index is negative or greater than array size
	 */
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return data[i];
	}

	/**
	 * Sets data at index i to data given
	 * @param int i for index to insert data to & E e, data to be inserted
	 * @return E temp which is set to data which used to be at index i
	 * @throws IndexOutOfBoundsException if index i is negative or greater than array size
	 */
	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		data[i] = e;
		return temp;
	}
	/**
	 * Adds new data into array at point i. Shifts all data in array to the right of i to the right.
	 * Calls increaseSize() if array is full
	 * @param int i, index to insert data & E e, data to be inserted
	 * @throws IndexOutOfBoundsException if index i is negative or greater than array size
	 */
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size + 1);
		if(size == data.length) {
			increaseSize();
		}
		for(int k = size - 1; k>= i; k--)
			data[k+1] = data[k];
		data[i] = e;
		size++;
	}

	/**
	 * Removes data from index i and shifts all data to the right of i to the left
	 * @param int i, index to remove data from
	 * @return temp, data which was removed
	 * @throws IndexOutOfBoundsException if index i is negative or greater than size of array
	 */
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
	/**
	 * Checks whether the given index is in the range(0, n - 1)
	 * @param int i, index to start from & int n index to check to
	 * @throws IndexOutOfBoundsExceptionif index i is negative or greater than size of array
	 */
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n)
			throw  new IndexOutOfBoundsException("Illegal index: " + i);
	}
	/**
	 *@return returns new instance of ArrayListIterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}

	/**
	 * checks if there is room for new data and calls increaseSize() if needed, then calls add to insert data into 0 position
	 * @param E e, data to be insterted
	 */
	public void addFirst(E e)  {
		if(size == data.length)
			increaseSize();
		add(0, e);
	}

	/**
	 * checks if there is room for new data and calls increaseSize() if needed, then calls add to insert data into last position
	 * @param E e, data to be insterted
	 */
	public void addLast(E e)  {
		if(size == data.length)
			increaseSize();
		add(size, e);
	}
	/**
	 * removes data from 0 position
	 * @return this.remove(0), calling remove of data of array of this obejct from postion 0
	 */
	public E removeFirst() throws IndexOutOfBoundsException {
		return this.remove(0);
	}
	/**
	 * removes data from 0 position
	 * @return this.remove(size-1), calling remove of data of array of this obejct from last position
	 */
	public E removeLast() throws IndexOutOfBoundsException {
		return remove(size - 1);
	}

	// Return the capacity of array, not the number of elements.
	// Notes: The initial capacity is 16. When the array is full, the array should be doubled.
	/**
	 * checks capacity of current array
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

		for(int j = 0; j < data.length; j++) {
			newArray[j] = data[j];
		}
		data = newArray.clone();
	}

	private class ArrayListIterator implements Iterator {
		int cursor = 0;
		/**
		 *checkes to see if there is data in array after the cursors position
		 */
		public boolean hasNext() {
			if (cursor > size - 1)
				return false;
			return true;
		}
		/**
		 * Gets data from cursors curarent position in array and increments cursor
		 * @return E e, data at cursor position before encrementation
		 */
		public E next() {
			E e = get(cursor);
			cursor++;
			return e;
		}
	}
	
}
