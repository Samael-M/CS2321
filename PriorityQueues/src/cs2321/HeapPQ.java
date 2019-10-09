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
	Comparator<K> C;

	private class AdaptablePQEntry<K, V> extends PQEntry<K, V> {
		private int index;
		public AdaptablePQEntry(K key, V value, int j) {
			super(key, value);
			index = j;
		}
		public int getIndex() {return index;}
		public void setIndex(int j) {index = j;}
	}

	public HeapPQ() {
		super();
		C = new DefaultComparator<K>();
	}
	public HeapPQ(Comparator<K> c) {
		this.C = c;
	}

	/**
	 * The entry should be bubbled up to its appropriate position 
	 * @param j move the entry at index j higher if necessary, to restore the heap property
	 */
	public void upheap(int j){
		while(j > 0) {
			int p = parent(j);
			if(C.compare(heap.get(j).getKey(), heap.get(p).getKey()) > 0) break;
			swap(j,p);
			j = p;
		}
	}
	
	/**
	 * The entry should be bubbled down to its appropriate position 
	 * @param j move the entry at index j lower if necessary, to restore the heap property
	 */
	
	public void downheap(int j){
		while(hasLeft(j)) {
			int leftIndex = left(j);
			int smallChildIndex = leftIndex;
			if (hasRight(j)) {
				int rightIndex = right(j);
				if (C.compare(heap.get(leftIndex).getKey(), heap.get(rightIndex).getKey()) > 0) {
					smallChildIndex = rightIndex;
				}
			}
			if(C.compare(heap.get(smallChildIndex).getKey(), heap.get(j).getKey()) >= 0) break;
			swap(j, smallChildIndex);
			j = smallChildIndex;
		}
	}

	@Override
	public int size() { return heap.size(); }

	@Override
	public boolean isEmpty() { return heap.isEmpty(); }

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
		Entry<K, V> newest = new AdaptablePQEntry<>(key, value, heap.size());
		heap.add(size(), newest);
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
		swap(0, size() - 1);
		heap.remove(size() - 1);
		downheap(0);
		return answer;
	}

	public void swap(int i, int j) {
		Entry<K, V> tempi = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, tempi);
		((AdaptablePQEntry<K, V>) heap.get(i)).setIndex(i);
		((AdaptablePQEntry<K, V>) heap.get(j)).setIndex(j);
//		super.swap(i, j);
//		((AdaptablePQEntry<K, V>) heap.get(i)).setIndex(i);
//		((AdaptablePQEntry<K, V>) heap.get(j)).setIndex(j);
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
	private boolean hasLeft(int j) { return left(j) < heap.size(); }
	private boolean hasRight(int j) { return right(j) < heap.size(); }

	public AdaptablePQEntry<K, V> validate(Entry<K, V> entry) {
		if(!(entry instanceof PQEntry)) throw new IllegalArgumentException("Invalid Entry");
		AdaptablePQEntry<K, V> locator =( AdaptablePQEntry<K, V>) entry;
		int j = locator.getIndex();
		if(j >= heap.size() || heap.get(j) != locator) {
			throw new IllegalArgumentException("invalid entry");
		}
		return locator;
	}

	public void bubble(int j) {
		if (j > 0 && C.compare(heap.get(j).getKey(), heap.get(parent(j)).getKey()) > 0) {
			upheap(j);
		} else downheap(j);
	}

	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(entry);
		int j = locator.getIndex();
		if (j == heap.size() -1) {
			heap.remove(heap.size() - 1);
		} else {
			swap(j, heap.size() - 1);
			heap.remove(heap.size() - 1);
			bubble(j);
		}
	}

	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(entry);
		checkKey(key);
		locator.setKey(key);
		bubble(locator.getIndex());
	}

	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(entry);
		locator.setValue(value);
	}
}
