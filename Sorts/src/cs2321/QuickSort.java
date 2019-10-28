package cs2321;
public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

	DefaultComparator C = new DefaultComparator();

	public void sort(E[] array) {
		sort(array, 0, array.length);
	}

	public void sort(E[] array, int p, int q) {
		int r;
		if(p < q) {
			r = partition(array, p, q);
			sort(array, p, r -1);
			sort(array, r +1, q);
		}
	}

	public int partition(E[] array, int p, int q){
		int i = p;
		int j = q - 1;
		E pivot = array[q];
		while (i <= j) {
			while(i <= j && C.compare(array[i], pivot) < 0) {
	 			i++;
			}
			while(i<= j && C.compare(array[j], pivot) > 0) {
				j--;
			}
			if(i < j) {
				swap(array, i, j);
				i++;
				j--;
			}
		}
		swap(array, i, q);
		return i;
	}

	public void swap(E[] array, int i, int j) {
		E e = array[i];
		array[i] = array[j];
		array[j] = e;
	}
}
