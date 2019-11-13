package cs2321;
import net.datastructures.*;


public class BinarySearchTree<K extends Comparable<K>,V> extends AbstractMap<K,V> implements SortedMap<K,V> {
	
	/* all the data will be stored in tree*/
	LinkedBinaryTree<Entry<K,V>> tree; 
	int size;  //the number of entries (mappings)
	DefaultComparator<K> C;
	
	/* 
	 * default constructor
	 */
	public BinarySearchTree() {
		tree = new LinkedBinaryTree<>();
		tree.addRoot(null);
		C = new DefaultComparator();
	}

	public boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return (C.compare(key, key) == 0);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatiable key");
		}
	}
	
	/* 
	 * Return the tree. The purpose of this method is purely for testing. 
	 * You don't need to make any change. Just make sure to use object tree to store your entries. 
	 */
	public LinkedBinaryTree<Entry<K,V>> getTree() {
		return tree;
	}
	Position<Entry<K,V>> root() { return tree.root(); }

	/** Helper function to put method */
	public void expandExternal(Position<Entry<K, V>> p, Entry<K, V> entry) {
		tree.set(p, entry);
		tree.addLeft(p, null);
		tree.addRight(p, null);
	}

	@Override
	public int size(){
		return (tree.size() - 1) / 2;
	}

	@Override
	public V get(K key) {
		checkKey(key);
		Position<Entry<K, V>> p = treeSearch(root(), key);
		if (tree.isExternal(p)) return null;
		return p.getElement().getValue();
	}

	/** returns Position p of Entry with given K key */
	public Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> p , K key) {
		if (tree.isExternal(p))  return p;
		int comp = C.compare(key, p.getElement().getKey());
		if (comp == 0) return p ;
		else if (comp < 0) return treeSearch(tree.left(p), key);
		else return treeSearch(tree.right(p), key);
	}
	@Override
	public V put(K key, V value) {
		checkKey(key);
		Entry<K, V> newEntry = new mapEntry<>(key, value);
		Position<Entry<K, V>> p = treeSearch(root(), key);
		if(tree.isExternal(p)) {
			expandExternal(p, newEntry);
			return null;
		} else {
			V old = p.getElement().getValue();
			tree.set(p, newEntry);
			return old;
		}
	}


	@Override
	public V remove(K key) {
		checkKey(key);
		Position<Entry<K, V>> p = treeSearch(root(), key);
		if(tree.isExternal(p)) {
			return null;
		} else {
			V old = p.getElement().getValue();
			if(tree.isInternal(tree.left(p)) && tree.isInternal(tree.right(p))) {
				Position<Entry<K, V>> replacement = treeMax(tree.left(p));
				tree.set(p, replacement.getElement());
				p = replacement;
			}
			Position<Entry<K, V>> leaf = (tree.isExternal(tree.left(p)) ? tree.left(p) : tree.right(p));
			Position<Entry<K, V>> sib = tree.sibling(leaf);
			tree.remove(leaf);
			tree.remove(p);
			return old;
		}
	}
	/** returns postiion in subtree p with maximun key */
	public Position<Entry<K, V>> treeMax(Position<Entry<K, V>> p) {
		Position<Entry<K, V>> walk = p;
		while(tree.isInternal(walk))
			walk = tree.right(walk);
		return tree.parent(walk);
	}

	/** Same as Treemax, but with mins */
	public Position<Entry<K, V>> treeMin(Position<Entry<K, V>> p) {
		Position<Entry<K, V>> walk = p;
		while(tree.isInternal(walk))
			walk = tree.left(walk);
		return tree.parent(walk);
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>(size());
		return buffer;
	}

	@Override
	public Entry<K, V> firstEntry() {
		if(isEmpty()) return null;
		return treeMin(root()).getElement();
	}

	@Override
	public Entry<K, V> lastEntry() {
		if(isEmpty()) return null;
		return treeMax(root()).getElement();
	}

	@Override
	public Entry<K, V> ceilingEntry(K key) {
		checkKey(key);
		Position<Entry<K, V>> p = treeSearch(root(), key);
		if(tree.isInternal(p)) return p.getElement();
		while (!tree.isRoot(p)) {
			if (p == tree.left(tree.parent(p))) return tree.parent(p).getElement();
			else p = tree.parent(p);
		}
		return null;
	}

	@Override
	public Entry<K, V> floorEntry(K key)  {
		checkKey(key);
		Position<Entry<K, V>> p = treeSearch(root(), key);
		if(tree.isInternal(p)) return p.getElement();
		while (!tree.isRoot(p)) {
			if (p == tree.right(tree.parent(p))) return tree.parent(p).getElement();
			else p = tree.parent(p);
		}
		return null;
	}

	@Override
	public Entry<K, V> lowerEntry(K key) {
		checkKey(key);
		Position<Entry<K, V>> p = treeSearch(root(), key);
		if(tree.isInternal(p) && tree.isInternal(tree.right(p))) return treeMax(tree.left(p)).getElement();
		while (!tree.isRoot(p)) {
			if (p == tree.right(tree.parent(p))) return tree.parent(p).getElement();
			else p = tree.parent(p);
		}
		return null;
	}

	@Override
	public Entry<K, V> higherEntry(K key){
		checkKey(key);
		Position<Entry<K, V>> p = treeSearch(root(), key);
		if(tree.isInternal(p) && tree.isInternal(tree.right(p))) return treeMin(tree.right(p)).getElement();
		while (!tree.isRoot(p)) {
			if (p == tree.left(tree.parent(p))) return tree.parent(p).getElement();
			else p = tree.parent(p);
		}
		return null;
	}


	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey)
			throws IllegalArgumentException {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>(size());
		if (C.compare(fromKey, toKey) < 0) subMapRecurse(fromKey, toKey, root(), buffer);
		return buffer;
	}

	public void subMapRecurse(K fromKey, K toKey, Position<Entry<K, V>> p, ArrayList<Entry<K, V>> buffer) {
		if (tree.isInternal(p)) {
			if(C.compare(p.getElement().getKey(), fromKey) < 0) subMapRecurse(fromKey, toKey, tree.right(p), buffer);
			else {
				subMapRecurse(fromKey, toKey, tree.left(p), buffer);
				if (C.compare(p.getElement().getKey(), toKey) < 0) {
					buffer.addLast(p.getElement());
					subMapRecurse(fromKey, toKey, tree.right(p), buffer);
				}
			}
		}
	}
	//I wonder if its empty
	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}


	

}
