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

	public Entry<K, V> getEntry(int index) {
		return table.get(index);
	}

	public Entry<K, V> getEntry(K key) {
		int j = findIndex(key);
		if(j == size() || C.compare(key, table.get(j).getKey()) != 0) return null;
		return table.get(j);
	}
	@Override
	public boolean isEmpty() {
		return table.isEmpty();
	}

	public int findIndex(K key, int low, int high) {
		if(high < low) return high + 1;
		int mid = (low + high) / 2;
		int comp = C.compare(key, table.get(mid).getKey());
		if (comp == 0) return mid;
		else if (comp < 0) return findIndex(key, low, mid -1);
		else return findIndex(key, mid+ 1, high);
	}

	public int findIndex(K key) {
		return findIndex(key, 0, size() - 1);
	}

	@Override
	public V get(K key) {
		int j = findIndex(key);
		if(j == size() || C.compare(key, table.get(j).getKey()) != 0) return null;
		return table.get(j).getValue();
	}

	@Override
	public V put(K key, V value) {
		int j = findIndex(key);
		if(j < size() && C.compare(key, table.get(j).getKey()) == 0) return null; //table.get(j).setValue(value);
		table.add(j, new mapEntry<K, V>(key, value));
		return null;

//		int j = findIndex(key);
//		if(j == -1) {
//			table.addLast(new mapEntry<>(key, value));
//			return null;
//		} else {
//			table.get(j).setValue(value);
//			return value;
//		}
	}

	@Override
	public V remove(K key) {
		int j = findIndex(key);
		if(j == size() || C.compare(key, table.get(j).getKey()) != 0) return null;
		return table.remove(j).getValue();
	}

	public class EntryIterator implements Iterator<Entry<K, V>> {
		private int j = 0;
		public boolean hasNext() { return j < table.size(); }
		public Entry<K, V> next() {
			if (j == table.size()) throw new NoSuchElementException();
			return table.get(j++);
		}
		public void remove() { throw new UnsupportedOperationException(); }
	}

	public class EntryIterable implements Iterable<Entry<K, V>> {
		@Override
		public Iterator<Entry<K, V>> iterator() { return new EntryIterator(); }
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() { return new EntryIterable(); }

}
