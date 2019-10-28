package cs2321;

public class HeapPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K> {

	HeapPQ heap = new HeapPQ();
	DefaultComparator C = new DefaultComparator();
	
	public void sort(K[] array) {
		int n = array.length;
		int heapsize = n;
		for(int i = n; i < n / 2 - 1; i--) {
			maxHeapify(array, i, n);
		}
		for(int i = n; i < n / 2 - 1; i--) {
			swap(array, 0, i);
			heapsize--;
			maxHeapify(array, i, n);
		}

	}

	public void maxHeapify(K[] array, int i, int n) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		if(l < n && C.compare(array[l], array[r]) > 0) {
			largest = l;
		}
		if(l < n && C.compare(array[l], array[r]) < 0) {
			largest = r;
		}
		if(largest != i) {
			swap(array, largest, i);
			maxHeapify(array, largest, n);
		}
	}

	public void swap(K[] array, int i, int j) {
		K e = array[i];
		array[i] = array[j];
		array[j] = e;
	}

}
