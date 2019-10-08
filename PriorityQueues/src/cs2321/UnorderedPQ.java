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
	Comparator<K> C;

	public UnorderedPQ() {
		super();
		C = new DefaultComparator<K>();
	}
	
	public UnorderedPQ(Comparator<K> c) {
		this.C = c;
	}

	@Override
	public int size() {
		return Heap.size();
	}

	@Override
	public boolean isEmpty() {
		return Heap.isEmpty();
	}

	public boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return(C.compare(key, key) == 0);
		} catch(ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K,V> newest = new PQEntry<>(key, value);
		Heap.addLast(newest);
		return newest;
	}

	public Position<Entry<K, V>> findMin() {
		Position<Entry<K, V>> small = Heap.first();
		for(Position<Entry<K, V>> walk: Heap.positions()) {
			if(C.compare(walk.getElement().getKey(), small.getElement().getKey()) < 0) {
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
		return Heap.remove(findMin());
	}
	
	

}
