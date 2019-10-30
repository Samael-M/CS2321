package cs2321;

public class InPlaceInsertionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 */
	DefaultComparator C = new DefaultComparator();

	public void sort(K[] array) {
		K cur;
		int j;
		for(int i = 0; i <= array.length - 1; i++) {
			cur = array[i];
			j = i - 1;
			while(j >= 0 && C.compare(array[j], cur) > 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = cur;
		}
	}
}
