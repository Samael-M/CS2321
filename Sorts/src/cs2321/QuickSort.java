package cs2321;
public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

	DefaultComparator C = new DefaultComparator();
	@TimeComplexity("O(n lg n)")
	public void sort(E[] array) {
		sort(array, 0, array.length - 1);
	}

	public void sort(E[] array, int p, int r) {
		if(p < r) {
			int q = partition(array, p, r);
			sort(array, p, q - 1);
			sort(array, q + 1, r);
		}
	}

	public int partition(E[] array, int p, int r){
		int i = p - 1;
		E pivot = array[r];
		for(int j = p; j <= r - 1; j++) {
			if(C.compare(array[j], pivot) < 0) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, r, i + 1);
		return i + 1;
	}

	public void swap(E[] array, int i, int j) {
		E e = array[i];
		array[i] = array[j];
		array[j] = e;
	}
}
