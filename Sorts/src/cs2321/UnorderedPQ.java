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

@TimeComplexity("O(n)")
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

	@TimeComplexity("O(1)")
	@Override
	public int size() {
		return Heap.size();
	}

	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		return Heap.isEmpty();
	}

	@TimeComplexity("O(1)")
	public boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return(C.compare(key, key) == 0);
		} catch(ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}

	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K,V> newest = new PQEntry<>(key, value);
		Heap.addLast(newest);
		return newest;
	}

	@TimeComplexity("O(n)")
	/**
	 * uses for loop for each position in heap, everything in loop will happen for every n things in heap
	 */
	public Position<Entry<K, V>> findMin() {
		Position<Entry<K, V>> small = Heap.first();
		for(Position<Entry<K, V>> walk: Heap.positions()) {
			if(C.compare(walk.getElement().getKey(), small.getElement().getKey()) < 0) {
				small = walk;
			}
		}
		return small;
	}

	@TimeComplexity("O(n)")
	//calls findMin which is O(n)
	@Override
	public Entry<K, V> min() {
		if(Heap.isEmpty()) return null;
		return findMin().getElement();
	}

	@TimeComplexity("O(n)")
	//calls findMin which is O(n)
	@Override
	public Entry<K, V> removeMin() {
		if(Heap.isEmpty()) return null;
		return Heap.remove(findMin());
	}
	
	

}
