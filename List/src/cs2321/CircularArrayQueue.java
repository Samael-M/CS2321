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
	private int index = 0;

	public CircularArrayQueue(int queueSize) {
		data = (E[]) new Object[queueSize];
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
	public void enqueue(E e) throws IndexOutOfBoundsException {
		if (size == QueueSize)
			throw new IndexOutOfBoundsException("Illegal index: " + size + 1);
	    data[size] = e;
	    size ++;
	}

	@Override
	public E first() {
		return data[index];
	}

	@Override
	public E dequeue() throws IndexOutOfBoundsException {
		E temp = data[index];
		index = index + 1 % data.length;
		size--;
		return temp;
	}
    
}
