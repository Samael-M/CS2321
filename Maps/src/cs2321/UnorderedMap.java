package cs2321;


import net.datastructures.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedMap<K,V> extends AbstractMap<K,V> {
	
	/* Use ArrayList or DoublyLinked list for the Underlying storage for the map of entries.
	 * Uncomment one of these two lines;
	 * private ArrayList<Entry<K,V>> table; 
	 * private DoublyLinkedList<Entry<K,V>> table;
	 */
	private ArrayList<mapEntry<K,V>> table;
	DefaultComparator C = new DefaultComparator();

	public UnorderedMap() {
		table = new ArrayList<>();
	}
		

	@Override
	public int size() {
		return table.size();
	}

	@Override
	public boolean isEmpty() {
		return table.isEmpty();
	}

	public int findIndex(K key) {
		int n = table.size();
		for (int j = 0; j < n; j++) {
			if(table.get(j).getKey().equals(key)) return j;
		} return -1;
	}
	@Override
	public V get(K key) {
		int j = findIndex(key);
		if(j == -1) return null;
		return table.get(j).getValue();
	}

	@Override
	public V put(K key, V value) {
		int j = findIndex(key);
		if(j == -1) {
			table.addLast(new mapEntry<>(key, value));
			return null;
		} else {
			//return table.get(j).setValue(value);
			return table.set(j, new mapEntry<K, V>(key, value)).getValue();
		}
	}

	@Override
	public V remove(K key) {
		int j = findIndex(key);
		int n = size();
		if(j == -1) return null;
		V answer = table.get(j).getValue();
		if(j != n - 1) table.set(j, table.get(n - 1));
		table.remove(n - 1);
		return answer;

	}

	/** Iterator from the book */
	public class EntryIterator implements Iterator<Entry<K, V>> {
		private int j = 0;
		public boolean hasNext() { return j < table.size(); }
		public Entry<K, V> next() {
			if (j == table.size()) throw new NoSuchElementException();
			return table.get(j++);
		}
		public void remove() { throw new UnsupportedOperationException(); }
	}

	/** Iterator from the book */
	public class EntryIterable implements Iterable<Entry<K, V>> {
		@Override
		public Iterator<Entry<K, V>> iterator() { return new EntryIterator(); }
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() { return new EntryIterable(); }

}
