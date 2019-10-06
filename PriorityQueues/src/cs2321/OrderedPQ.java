package cs2321;

import java.util.Comparator;

import javafx.scene.layout.HBox;
import net.datastructures.*;
/**
 * A PriorityQueue based on an ordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Samuel Milner
 */

public class OrderedPQ<K,V> implements PriorityQueue<K,V> {

	private DoublyLinkedList<PQEntry> Heap = new DoublyLinkedList();
	DefaultComparator C = new DefaultComparator();

	public OrderedPQ() {
		super();
	}
	
	public OrderedPQ(Comparator<K> c) {
		//TODO super(c);
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
		PQEntry E = new PQEntry<>(key, value);
		Heap.addLast(E);
		upheap(size() - 1);
		return E;
	}

	@Override
	public Entry<K, V> min() {
		return Heap.first().getElement();
	}

	@Override
	public Entry<K, V> removeMin() {
		PQEntry E = Heap.first().getElement();
		Heap.set(Heap.first(), Heap.last().getElement());
		Heap.removeLast();
		downheap(0);
		return E;
	}

	public void swap(int i, int j) {
		PQEntry tempi = Heap.getPositon(i).getElement();
		Heap.set(Heap.getPositon(i), Heap.getPositon(i).getElement());
		Heap.set(Heap.getPositon(j), tempi);
	}

	public void upheap(int i) {
		if(i == 0){ }
		else {
			int p = getParent(i);if(C.compare(Heap.getPositon(i).getElement(), Heap.getPositon(p).getElement()) < 0) {
				swap(i, p);
				upheap(p);
			}
		}
	}

	public void downheap(int i) {
		int s = i;
		if(!hasLeftChild(i)) {}
		else {
			int c1 = getLeftChild(i);
			int c2 = getRightChild(i);
			if(hasLeftChild(i) && C.compare(Heap.getPositon(i).getElement(), Heap.getPositon(c1).getElement()) > 0){
				s = getLeftChild(i);
			}
			if(hasRightChild(i) && C.compare(Heap.getPositon(s).getElement(), Heap.getPositon(c2).getElement()) > 0){
				s = getRightChild(i);
			}
			if(i != s) {
					swap(i, s);
					downheap(s);
			}
		}
	}

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
