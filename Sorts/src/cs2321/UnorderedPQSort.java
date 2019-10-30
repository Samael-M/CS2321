package cs2321;

public class UnorderedPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K>  {

	@Override
	public void sort(K[] array) {
		PQSort sort = new PQSort();
		UnorderedPQ pq = new UnorderedPQ();
		sort.sort(array, pq);
	}

}
