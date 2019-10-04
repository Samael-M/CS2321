package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A Adaptable PriorityQueue based on an heap. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author
 */

public class HeapPQ<K,V> implements AdaptablePriorityQueue<K,V> {
	
	
	public HeapPQ() {
		//TODO: implement this method
	}
	
	public HeapPQ(Comparator<K> c) {
		//TODO: implement this method
	}
	
	/**
	 * The entry should be bubbled up to its appropriate position 
	 * @param int move the entry at index j higher if necessary, to restore the heap property
	 */
	public void upheap(int j){
		//TODO: implement this method
	}
	
	/**
	 * The entry should be bubbled down to its appropriate position 
	 * @param int move the entry at index j lower if necessary, to restore the heap property
	 */
	
	public void downheap(int j){
		//TODO: implement this method
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
	


}
