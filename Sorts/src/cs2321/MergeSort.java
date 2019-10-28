package cs2321;

import java.util.Arrays;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {

	DefaultComparator C = new DefaultComparator();

	public void sort(E[] array) {
		int n = array.length;
		if(n < 2) return;

		int mid = n / 2;
		E[] a1 = Arrays.copyOfRange(array, 0, mid);
		E[] a2 = Arrays.copyOfRange(array, mid, n);

		sort(a1);
		sort(a2);
		merge(a1, a2, array);

	}

	public void merge(E[] a1, E[] a2, E[] a) {
		int n1 = a1.length;
		int n2 = a2.length;
		int i = 0;
		int j = 0;
		int k = 0;
		while(i < n1 && j < n2) {
			if(C.compare(a[i], a2[j]) < 0) {
				a[k] = a1[i];
				i++;
				k++;
			}
			else {
				a[k] = a2[j];
				j++;
				k++;
			}
		}
		while(i <n1) {
			a[k] = a1[i];
			i++;
			k++;
		}
		while(j < n2) {
			a[k] = a2[j];
			j++;
			k++;
		}
	}
}

