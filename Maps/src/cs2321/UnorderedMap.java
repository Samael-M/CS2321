package cs2321;


import net.datastructures.Entry;
import net.datastructures.Map;
import net.datastructures.Position;

public class UnorderedMap<K,V> extends AbstractMap<K,V> {
	
	/* Use ArrayList or DoublyLinked list for the Underlying storage for the map of entries.
	 * Uncomment one of these two lines;
	 * private ArrayList<Entry<K,V>> table; 
	 * private DoublyLinkedList<Entry<K,V>> table;
	 */
	private DoublyLinkedList<Entry<K,V>> table;

	public UnorderedMap() {
		// TODO Auto-generated constructor stub
	}
		

	@Override
	public int size() {
		return table.size();
	}

	@Override
	public boolean isEmpty() {
		return table.isEmpty();
	}

	@Override
	public V get(K key) {
		for (Entry<K, V> e: table) {
			if (e.getKey().equals(key)) {
				return e.getValue();
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		for (Entry e: table) {
			if (e.getKey() ==  key) {
				V oldV = (V) e.getValue();
				//e.setValue(value);
				return oldV;
			}
			table.addLast(e);
		}
		return null;
	}

	@Override
	public V remove(K key) {
		for (Position p: table.positions()) {
			Entry<K, V> e = (Entry<K, V>) p.getElement();
			if (e.getKey().equals(key)) {
				V oldV = e.getValue();
				table.remove(p);
				return oldV;
			}
		}
		return null;
	}


	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
