package cs2321;
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
		capacity = hashtablesize;
		C = new DefaultComparator();
		size = 0;
//		if(hashtablesize == 0) { table = new UnorderedMap[DefaultCapacity]; }
//		else table = new UnorderedMap[capacity];
		table = new UnorderedMap[capacity];
	}
	
	/**
	 * Constructor that takes no argument
	 * Initialize the hash table with default hash table size: 17
	 */
	public HashMap() {
		C = new DefaultComparator();
		size = 0;
		table = new UnorderedMap[DefaultCapacity];
	}
	
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
		if(size > capacity * loadfactor) {
			resize(2 * capacity - 1);
			return size;
		} else return size;
	}

	public void resize(int newCap) {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>(size);
		for (Entry<K, V> e : entrySet()) buffer.addLast(e);
		capacity = newCap;
		table = (UnorderedMap<K, V>[]) new UnorderedMap[capacity];
		size = 0;
		for(Entry<K, V> e : buffer) {
			put(e.getKey(), e.getValue());
		}
	}
	
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V get(K key) {
		return bucketGet(hashValue(key), key);
	}

	public V bucketGet(int h, K key) {
		UnorderedMap<K, V> bucket = table[h];
		if(bucket == null) return null;
		return bucket.get(key);
	}

	@Override
	public V put(K key, V value) {
		return bucketPut(hashValue(key), key, value);
	}

	public V bucketPut(int h, K key, V value) {
		UnorderedMap<K, V> bucket = table[h];
		if(bucket == null) bucket = table[h] = new UnorderedMap<>();
		int oldSize = bucket.size();
		V answer = bucket.put(key, value);
		size += (bucket.size() - oldSize);
		//tableSize();
		return answer;
	}

	@Override
	public V remove(K key) {
		return bucketRemove(hashValue(key), key);
	}

	public V bucketRemove(int h, K key) {
		UnorderedMap<K, V> bucket = table[h];
		if(bucket == null) return null;
		int oldSize = bucket.size();
		V answer = bucket.remove(key);
		size -= (oldSize - bucket.size());
		return answer;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		for (int h = 0; h < capacity; h++) {
			if(table[h] != null) {
				for(Entry<K, V> entry : table[h].entrySet()) {
					buffer.addLast(entry);
				}
			}
		} return buffer;
	}
	

}
