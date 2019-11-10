package cs2321;

import javafx.util.Builder;
import net.datastructures.*;

public class HashMap<K, V> extends AbstractMap<K,V> implements Map<K, V> {
	
	/* Use Array of UnorderedMap<K,V> for the Underlying storage for the map of entries.
	 * 
	 */
	private UnorderedMap<K,V>[]  table;
	int 	size;  // number of mappings(entries) 
	int 	capacity; // The size of the hash table. 
	int     DefaultCapacity = 17; //The default hash table size
	DefaultComparator C;
	
	/* Maintain the load factor <= 0.75.
	 * If the load factor is greater than 0.75, 
	 * then double the table, rehash the entries, and put then into new places. 
	 */
	double  loadfactor= 0.75;  
	
	/**
	 * Constructor that takes a hash size
	 * @param hashtablesize size: the number of buckets to initialize
	 */
	public HashMap(int hashtablesize) {
		size = hashtablesize;
		C = new DefaultComparator();
	}
	
	/**
	 * Constructor that takes no argument
	 * Initialize the hash table with default hash table size: 17
	 */
	public HashMap() { }
	
	/* This method should be called by map an integer to the index range of the hash table 
	 */
	private int hashValue(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
	/*
	 * The purpose of this method is for testing if the table was doubled when rehashing is needed. 
	 * Return the the size of the hash table. 
	 * It should be 17 initially, after the load factor is more than 0.75, it should be doubled.
	 */
	public int tableSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public int size() {
		return table.length;
	}

	@Override
	public boolean isEmpty() {
		return table.length == 0;
	}

//	public boolean isAvailable(int j) {
//		return (table[j] == null || table[j].equals(DEFUNCT));
//	}
//
//	public int findSlot(int h, K k) {
//		int available = -1;
//		int j = h;
//		do {
//			if(isAvailable(j)) {
//				if(available == -1) available = j;
//				if(table[j] == null) break;
//			} else if(table[j].equals(k)) return j;
//			j = (j + 1) % capacity;
//		} while (j!= h);
//		return -(available + 1);
//	}

//	public V search(K key) {
//		int i = hashValue(key);
//		int p = 0;
//		while (p == ) {
//			Map c = table[i];
//			if(c == null) return null;
//			else if(c.)
//		}
//	}

	public int binarySearch(K key, int mode) {
		int l = 0;
		int h = table.length - 1;
		while(l <= h) {
			int mid = (l + h) / 2;
			UnorderedMap<K, V> cur = table[mid];
			if(C.compare(cur, key) == 0) return mid;
			if(C.compare(cur, key) > 0) h = mid - 1;
			else l = mid +  1;
		}
		if(mode == 0) return -1;
		else return l;
	}

	@Override
	public V get(K key) {
//		for(UnorderedMap<K, V> t : table) {
//			for(Entry<K, V> r : t.entrySet()) {
//				if(r.getKey().equals(key)) return r.getValue();
//			}
//		}
//		return null;

		int index = binarySearch(key, 0);
		if(index == -1) return null;
		return table[index].get(key);
	}

	@Override
	public V put(K key, V value) {
//		for(UnorderedMap<K, V> t : table) {
//			for(Entry<K, V> e : t.entrySet()) {
//				if(e.getKey().equals(key)) {
//					V oldV = e.getValue();
//					e.setValue(value);
//					return oldV;
//				}
//			}
//		}
//		return null;
		int index = binarySearch(key, 0);
		if(index <= table.length - 1 && C.compare(table[index].getEntry(key).getKey(), key) == 0) { //problem getting key from table[index]
			V oldV = table[index].get(key);
			K tempK = table[index].getEntry(key).getKey();
			table[index].remove(key);
			table[index].put(tempK, value);
			return oldV;
		} else {
			table[index].put(key, value);
			return null;
		}
	}

	@Override
	public V remove(K key) {
		int index = binarySearch(key, 0);

		if(index == -1) return null;
		UnorderedMap<K, V> e = table[index];
		return e.get(key);
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
