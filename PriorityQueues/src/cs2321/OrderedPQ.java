package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A PriorityQueue based on an ordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Samuel Milner
 */

public class OrderedPQ<K,V> implements PriorityQueue<K,V> {

	private DoublyLinkedList<Entry<K, V>> Heap = new DoublyLinkedList();
	Comparator<K> C;


	public OrderedPQ() {
		super();
		C = new DefaultComparator<K>();
	}
	
	public OrderedPQ(Comparator<K> c) {
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
		Entry<K, V> newest = new PQEntry<>(key, value);
		Position<Entry<K, V>> walk = Heap.last();
		while(walk != null && C.compare(newest.getKey(), walk.getElement().getKey()) < 0)
			walk = Heap.before(walk);
		if(walk == null)
			Heap.addFirst(newest);
		else
			Heap.addAfter(walk, newest);
		return newest;
	}

	@Override
	public Entry<K, V> min() {
		if(Heap.isEmpty()) return null;
		return Heap.first().getElement();
	}

	@Override
	public Entry<K, V> removeMin() {
		if(Heap.isEmpty()) return null;
		return Heap.remove(Heap.first());
	}

//	public void swap(int i, int j) {
//		Entry tempi = Heap.getPositon(i).getElement();
//		Heap.set(Heap.getPositon(i), Heap.getPositon(i).getElement());
//		Heap.set(Heap.getPositon(j), tempi);
//	}

//	public void upheap(int i) {
//		if(i == 0){ }
//		else {
//			int p = getParent(i);if(C.compare(Heap.getPositon(i).getElement(), Heap.getPositon(p).getElement()) < 0) {
//				swap(i, p);
//				upheap(p);
//			}
//		}
//	}

//	public void downheap(int i) {
//		int s = i;
//		if(!hasLeftChild(i)) {}
//		else {
//			int c1 = getLeftChild(i);
//			int c2 = getRightChild(i);
//			if(hasLeftChild(i) && C.compare(Heap.getPositon(i).getElement(), Heap.getPositon(c1).getElement()) > 0){
//				s = getLeftChild(i);
//			}
//			if(hasRightChild(i) && C.compare(Heap.getPositon(s).getElement(), Heap.getPositon(c2).getElement()) > 0){
//				s = getRightChild(i);
//			}
//			if(i != s) {
//					swap(i, s);
//					downheap(s);
//			}
//		}
//	}

	public int getParent(int i) {
		return (i - 1) / 2;
	}
	public int getLeftChild (int i) {
		return 2 * i + 1;
	}
	public int getRightChild(int i) {
		return 2 * i + 2;
	}
	public boolean hasLeftChild(int i) {
		try {
			getLeftChild(i);
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}
	public boolean hasRightChild(int i) {
		try {
			getRightChild(i);
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

}
