package cs2321;

public class HeapPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K> {

	DefaultComparator C = new DefaultComparator();

	public void sort(K[] array) {
		HeapPQ heap = new HeapPQ();
		sort(array, heap);
	}

	protected void sort(K[] kArray, HeapPQ<K,K> heap) {
		for(int i = 0; i <= kArray.length - 1; i++) {
			heap.insert(kArray[i], null);
		}
		for(int i = 0; i <= kArray.length - 1; i++) {
			kArray[i] =  heap.removeMin().getKey();
		}
	}

}
