package cs2321;

import java.util.Arrays;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {

	DefaultComparator<E> C = new DefaultComparator<>();

	public void sort(E[] array) {
		mergeSort(array);
	}
	@TimeComplexity("O(n lg n)")
	public void mergeSort(E[] a) {
		int n = a.length;
		if (n < 2) return;
		int mid = n/2;
		E[] a1 = Arrays.copyOfRange(a, 0, mid);
		E[] a2 = Arrays.copyOfRange(a, mid, n);
//		E[] a1 = copyOfRange(a, 0, mid);
//		E[] a2 = copyOfRange(a, mid, n);
		mergeSort(a1);
		mergeSort(a2);
		merge(a1, a2, a);
	}

	public void merge(E[ ] a1, E[ ] a2, E[ ] a) {
		int i = 0, j = 0;
		while (i + j < a.length) {
			if (j == a2.length || (i < a1.length && C.compare(a1[i], a2[j]) < 0)) {
				a[i+j] = a1[i++];
			}
			else
				a[i+j] = a2[j++];
		}
	}


}

