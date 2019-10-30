package cs2321;

import java.util.Arrays;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {

	DefaultComparator C = new DefaultComparator();

	public void sort(E[] array) {
//		int n = array.length;
//		if(n < 2) return;
//
//		int mid = n / 2;
//		E[] a1 = Arrays.copyOfRange(array, 0, mid);
//		E[] a2 = Arrays.copyOfRange(array, mid, n);
//
//		sort(a1);
//		sort(a2);
//		merge(a1, a2, array);
		mergeSort(array);
	}

	public <K> void mergeSort(K[ ] a) {
		int n = a.length;
		if (n < 2) return;
		int mid = n/2;
		K[ ] a1 = Arrays.copyOfRange(a, 0, mid);
		K[ ] a2 = Arrays.copyOfRange(a, mid, n);
		mergeSort(a1);
		mergeSort(a2);
		merge(a1, a2, a);
	}

//	public void merge(E[] a1, E[] a2, E[] a) {
//		int n1 = a1.length;
//		int n2 = a2.length;
//		int i = 0;
//		int j = 0;
//		int k = 0;
//		while(i < n1 && j < n2) {
//			if(C.compare(a[i], a2[j]) < 0) {
//				a[k] = a1[i];
//				i++;
//				k++;
//			}
//			else {
//				a[k] = a2[j];
//				j++;
//				k++;
//			}
//		}
//		while(i < n1) {
//			a[k] = a1[i];
//			i++;
//			k++;
//		}
//		while(j < n2) {
//			a[k] = a2[j];
//			j++;
//			k++;
//		}
//	}
	public <K> void merge(K[ ] a1, K[ ] a2, K[ ] a) {
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

