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

	private ArrayList<Entry<K, V>> heap = new ArrayList<>();
	DefaultComparator C = new DefaultComparator();
	private int index;

	private class AdaptablePQEntry<K, V> extends PQEntry<K, V> {
		private int index;
		public AdaptablePQEntry(K key, V value, int j) {
			super(key, value);
			index = j;
		}
		public int getIndex() {return index;}
		public void setIndex(int j) {index = j;}
	}
	
	public HeapPQ(Comparator<K> c) {
		super(c);
	}

	/**
	 * The entry should be bubbled up to its appropriate position 
	 * @param int move the entry at index j higher if necessary, to restore the heap property
	 */
	public void upheap(int j){
		while(j > 0) {
			int p = parent(j);
			if(C.compare(heap.get(j), heap.get(p)) > 0) break;
			swap(j,p);
			j = p;
		}
	}
	
	/**
	 * The entry should be bubbled down to its appropriate position 
	 * @param int move the entry at index j lower if necessary, to restore the heap property
	 */
	
	public void downheap(int j){
		while(hasLeft(j)) {
			int leftIndex = left(j);
			int smallChildIndex = leftIndex;
			if (hasRight(j)) {
				int rightIndex = right(j);
				if (C.compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
					smallChildIndex = rightIndex;
				}
			}
			if(C.compare(heap.get(smallChildIndex), heap.get(j)) >= 0) break;
			swap(j, smallChildIndex);
			j = smallChildIndex;
		}
	}

	@Override
	public int size() { return heap.size(); }

	@Override
	public boolean isEmpty() { return heap.isEmpty(); }

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		//checkKey(key);
		Entry<K, V> newest = new PQEntry<>(key, value);
		heap.add(size() - 1, newest);
		upheap(heap.size() - 1);
		return newest;
	}

	@Override
	public Entry<K, V> min() {
		if(heap.isEmpty()) return null;
		return heap.get(0);
	}

	@Override
	public Entry<K, V> removeMin() {
		if(heap.isEmpty()) return null;
		Entry<K, V> answer = heap.get(0);
		swap(0, heap.size() - 1);
		downheap(0);
		return answer;
	}
	public void swap(int i, int j) {
		Entry tempi = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, tempi);
	}

	public int parent(int i) {
		return (i - 1) / 2;
	}
	public int left (int i) {
		return 2 * i + 1;
	}
	public int right(int i) {
		return 2 * i + 2;
	}
	public boolean hasLeft(int i) {
		try {
			left(i);
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}
	public boolean hasRight(int i) {
		try {
			right(i);
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}


	public PQEntry<K, V> validate(Entry<K, V> entry) {
		if(!(entry instanceof PQEntry)) throw new IllegalArgumentException("Invalid Entry");
		PQEntry<K, V> locator =(PQEntry<K, V>) entry;
		//int j = locator.getIndex();
	}

	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		PQEntry locator = new
		
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
