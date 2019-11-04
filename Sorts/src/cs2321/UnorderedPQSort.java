package cs2321;

public class UnorderedPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K>  {

	@Override
	@TimeComplexity("O(n^2)")
	/**
	 * uses pqsort which is O(n)
	 */
	public void sort(K[] array) {
		PQSort sort = new PQSort();
		UnorderedPQ pq = new UnorderedPQ();
		sort.sort(array, pq);
	}

}
