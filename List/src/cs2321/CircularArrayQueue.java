/**
 * 
 */
package cs2321;

import net.datastructures.Queue;

/**
 * @author ruihong-adm
 * @param <E>
 *
 */

public class CircularArrayQueue<E> implements Queue<E> {

	private int size = 0;
	private E[] data;
	private int QueueSize = 16;
	private int index = 0; //to keep track of the front of the queue

	public CircularArrayQueue(int queueSize) {
		data = (E[]) new Object[queueSize];
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
	 * Adds new data to end or back of queue and increases size
	 * @param E e, data to be added
	 * @throws IndexOutOfBoundsException if queue is already full
	 */
	@Override
	public void enqueue(E e) throws IndexOutOfBoundsException {
		if (size == QueueSize)
			throw new IndexOutOfBoundsException("Illegal index: " + size + 1);
	    data[size] = e;
	    size ++;
	}
	/**
	 * @return returns data at front of queue
	 */
	@Override
	public E first() {
		return data[index];
	}
	/**
	 * removes data from front of queue, changes size and moves the index
	 * @return E temp, data being removed
	 * @throws IndexOutOfBoundsException if queue is empty
	 */
	@Override
	public E dequeue() throws IndexOutOfBoundsException {
		E temp = data[index];
		index = index + 1 % data.length;
		size--;
		return temp;
	}
    
}
