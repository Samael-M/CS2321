package cs2321;

import java.util.Comparator;
import net.datastructures.*;
/**
 * A PriorityQueue based on an Unordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author
 */

//Page 367(385) in book
public class UnorderedPQ<K,V> implements PriorityQueue<K,V> {

	private DoublyLinkedList<Entry<K, V>> Heap = new DoublyLinkedList();
	DefaultComparator C = new DefaultComparator();

	public UnorderedPQ() {
		super();
	}
	
	public UnorderedPQ(Comparator<K> c) {
		c = new DefaultComparator<K>();
	}

	@Override
	public int size() {
		return Heap.size();
	}

	@Override
	public boolean isEmpty() {
		return Heap.isEmpty();
	}


	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		//checkKey(key); // auxiliary key-checking method (could throw exception)
		Entry<K,V> newest = new PQEntry<>(key, value);
		Heap.addLast(newest);
		return newest;
	}

	public Position<Entry<K, V>> findMin() {
		Position<Entry<K, V>> small = Heap.first();
		for(Position<Entry<K, V>> walk: Heap.positions()) {
			if(C.compare(walk.getElement(), small.getElement()) < 0) {
				small = walk;
			}
		}
		return small;
	}

	@Override
	public Entry<K, V> min() {
		if(Heap.isEmpty()) return null;
		return findMin().getElement();
	}

	@Override
	public Entry<K, V> removeMin() {
		if(Heap.isEmpty()) return null;
		return findMin().getElement();
	}
	
	

}
