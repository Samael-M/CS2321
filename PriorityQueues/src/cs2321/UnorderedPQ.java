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

	public UnorderedPQ() {
		super();
	}
	
	public UnorderedPQ(Comparator<K> c) {
			//TODO super(c);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> removeMin() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
