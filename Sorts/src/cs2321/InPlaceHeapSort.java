package cs2321;


public class InPlaceHeapSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place heap sort
	 * @param array - Array to sort
	 */
	DefaultComparator C = new DefaultComparator();
	public void sort(K[] array) {
		//buildMaxHeap(array, array.length);
		for (int i = array.length / 2 - 1; i >= 0; i--)
			maxHeapify(array, i, array.length);

		for(int i = array.length - 1; i >= 0; i--) {
			swap(array, 0, i);
			maxHeapify(array, 0, i);
		}
	}

	public void maxHeapify(K[] array, int i, int n) {
		int largest = i;
		int l = left(i);
		int r = right(i);

		if(l < n && C.compare(array[l], array[largest]) > 0) {
			largest = l;
		}
		if(r < n && C.compare(array[r], array[largest]) > 0) {
			largest = r;
		}
		if(largest != i) {
			swap(array, i, largest);
			maxHeapify(array, largest, n);
		}
	}

	public void swap(K[] array, int i, int j) {
		K e = array[i];
		array[i] = array[j];
		array[j] = e;
	}

	public int left(int i) {
		return 2 *i + 1;
	}

	public int right(int i) {
		return 2 *i + 2;
	}

}