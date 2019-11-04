package cs2321;

public class OrderedPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K>  {


	@Override
	@TimeComplexity("O(n^2)")
	/**
	 * uses pqsort which is O(n)
	 */
	public void sort(K[] array) {
		PQSort sort = new PQSort();
		OrderedPQ pq = new OrderedPQ();
		sort.sort(array, pq);
		
	}
}
