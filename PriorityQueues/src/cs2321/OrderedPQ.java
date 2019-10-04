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

	DoublyLinkedList<PQEntry> Heap = new DoublyLinkedList();
	DefaultComparator C = new DefaultComparator();

	public OrderedPQ() {
		//TODO implement this method
	}
	
	public OrderedPQ(Comparator<K> c) {
		//TODO implement this method
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
		upheap(Heap.size() - 1);
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
		if(i == 0){ }
		else {
			int c1 = getRightChild(i);
			int c2 = getLeftChild(i);
			if( (C.compare(Heap.getPositon(i).getElement(), Heap.getPositon(c1).getElement()) > 0) || (C.compare(Heap.getPositon(i).getElement(), Heap.getPositon(c1).getElement()) > 0) ) {
				if(C.compare(Heap.getPositon(c1).getElement(), Heap.getPositon(c2).getElement()) > 0) {
					swap(i, c2);
					downheap(c2);
				}
				else{
					swap(i, c1);
					downheap(c1);
				}
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

}
