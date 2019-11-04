package cs2321;

public class InPlaceSelectionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place selection sort
	 * @param array - Array to sort
	 */
	DefaultComparator C = new DefaultComparator();
	@TimeComplexity("O(n^2)")
	/**
	 * both the first for loop and the nested one run a O(n) time, which makes n^2. first loop runs from 0 to n and second runs from j = 1 to n -1
	 */
	public void sort(K[] array) {
		int minIndex;
		for(int i = 0; i <= array.length - 1; i++) {
			minIndex = i;
			for(int j = i + 1; j <= array.length - 1; j++) {
				if (C.compare(array[j], array[minIndex]) < 0) {
					minIndex = j;
				}
			}
			if(minIndex != i) {
				swap(array, i, minIndex);
			}
		}
	}

	public void swap(K[] array, int i, int j) {
		K e = array[i];
		array[i] = array[j];
		array[j] = e;
	}

}
