package cs2321;

import net.datastructures.*;

public class LookupTable<K extends Comparable<K>, V> extends AbstractMap<K,V> implements SortedMap<K, V> {
	
	/* Use Sorted ArrayList for the Underlying storage for the map of entries.
	 * 
	 */
	private ArrayList<mapEntry<K,V>> table;

	
	public LookupTable(){
		// TODO Add necessary initialization
	}

	public int findIndex(K key, int low, int high) {
		if(high < low) return high + 1;
		int mid = (low + high) / 2;
		int comp = key.compareTo(table.get(mid).getKey());
		if (comp == 0) return mid;
		else if (comp < 0) return findIndex(key, low, mid -1);
		else return findIndex(key, mid+ 1, high);
	}

	public int findIndex(K key) {
		return findIndex(key, 0, size() - 1);
	}

	@Override
	public int size() {
		return table.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V get(K key) {
		int j = findIndex(key);
		if(j == size() || key.compareTo(table.get(j).getKey()) != 0) return null;
		return table.get(j).getValue();
	}

	@Override
	public V put(K key, V value) {
		int j = findIndex(key);
		if(j < size() && key.compareTo(table.get(j).getKey()) == 0) return null; //table.get(j).setValue(value);
		table.add(j, new mapEntry<K, V>(key, value));
		return null;
	}

	@Override
	public V remove(K key) {
		int j = findIndex(key);
		if(j == size() || key.compareTo(table.get(j).getKey()) != 0) return null;
		return table.remove(j).getValue();
	}

	public Entry<K, V> safeEntry(int j) {
		if(j < 0 || j >= table.size()) return null;
		return table .get(j);
	}
	@Override
	public Entry<K, V> firstEntry() { return safeEntry(0); }
	@Override
	public Entry<K, V> lastEntry() { return safeEntry(table.size() - 1); }
	@Override
	public Entry<K, V> ceilingEntry(K key)  { return safeEntry(findIndex(key)); }

	@Override
	public Entry<K, V> floorEntry(K key)  {
		int j = findIndex(key);
		if(j == size() || key.equals(table.get(j))) j--;
		return safeEntry(j);
	}

	@Override
	public Entry<K, V> lowerEntry(K key) { return safeEntry(findIndex(key) - 1); }
	@Override
	public Entry<K, V> higherEntry(K key) {
		int j = findIndex(key);
		if (j < size() && key.equals(table.get(j).getKey())) j++;
		return safeEntry(j);
	}

	public Iterable<Entry<K, V>> snapshot(int startIndex, K stop) {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		int j = startIndex;
		while(j < table.size() && (stop == null || stop.compareTo(table.get(j).getKey()) > 0)) {
			buffer.addLast(table.get(j++));
		}
		return buffer;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() { return snapshot(0, null); }
	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) { return snapshot(findIndex(fromKey), toKey); }


}
