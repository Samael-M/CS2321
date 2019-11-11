package cs2321;
import java.util.Iterator;
import net.datastructures.*;

import javax.management.openmbean.CompositeDataSupport;


public class LinkedBinaryTree<E> implements BinaryTree<E>{

	protected static class Node<E> implements Position<E> {
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;
		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
		}
		@Override
		public E getElement() throws IllegalStateException { return element; }
		public Node<E> getParent() { return parent; }
		public Node<E> getLeft() { return left; }
		public Node<E> getRight() { return right; }

		public void setElement(E e) { element = e; }
		public void setParent(Node<E> p) { parent = p; }
		public void setLeft(Node<E> l) { left = l; }
		public void setRight(Node<E> r) { right = r; }
	}

	public Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) { return new Node<E>(e, parent, left, right); }

	Node<E> root = null;
	int size = 0;

	public E set(Position<E> p, E e) {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}

	public Node<E> validate(Position<E> p ) throws IllegalArgumentException {
		if(!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) p;
		if(node.getParent() == node)
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}

	@Override
	public Position<E> root() {
		return root;
	}
	
	public  LinkedBinaryTree( ) { }
	
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		List<Position<E>> snapshot = new ArrayList<>(2);
		if(left(p) != null) snapshot.add(snapshot.size() - 1, left(p));
		if(right(p) != null) snapshot.add(snapshot.size() - 1, right(p));
		return snapshot;
	}

	@Override
	/* count only direct child of the node, not further descendant. */
	public int numChildren(Position<E> p) throws IllegalArgumentException {
		int count = 0;
		if(left(p) != null) count ++;
		if (right(p) != null) count ++;
		return count;
	}

	@Override
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return !(node.getLeft() == null && node.getRight() == null);
	}

	@Override
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft() == null && node.getRight() == null;
	}

	@Override
	public boolean isRoot(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return p == root; }

	@Override
	public int size() { return size; }

	@Override
	public boolean isEmpty() { return size == 0; }

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}

	@Override
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		Position<E> parent = parent(p);
		if(parent == null) return null;
		if(p == left(parent)) return right(parent);
		return left(parent);
	}

	public Iterable<Position<E>> inorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty()) inorderSubtree(root(), snapshot);
		return snapshot;
	}
	public void inorderSubtree(Position<E> p , List<Position<E>> snapshot) {
		if(left(p) !=null) inorderSubtree(left(p), snapshot);
		snapshot.add(snapshot.size() - 1, p);
		if(right(p) !=null) inorderSubtree(right(p), snapshot);
	}
	
	/* creates a root for an empty tree, storing e as element, and returns the 
	 * position of that root. An error occurs if tree is not empty. 
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty()) throw new IllegalStateException("tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}
	
	/* creates a new left child of Position p storing element e, return the left child's position.
	 * If p has a left child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if(parent.getLeft() != null) throw new IllegalArgumentException("p already has a left child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}

	/* creates a new right child of Position p storing element e, return the right child's position.
	 * If p has a right child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if(parent.getRight() != null) throw new IllegalArgumentException("p already has a left child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}
	
	/* Attach trees t1 and t2 as left and right subtrees of external Position. 
	 * if p is not external, throw IllegalArgumentExeption.
	 */
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (isInternal(p)) throw  new IllegalArgumentException("p must be a leaf");
		size += t1.size() + t2.size();
		if(!t1.isEmpty()) {
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t1.root = null;
			t1.size = 0;
		}
		if(!t2.isEmpty()) {
			t2.root.setParent(node);
			node.setLeft(t1.root);
			t2.root = null;
			t2.size = 0;
		}
	}

	/**
	 * If p is an external node ( that is it has no child), remove it from the tree.
	 * If p has one child, replace it with its child.
	 * If p has two children, throw IllegalAugumentException.
	 * @param p who has at most one child.
	 * @return the element stored at position p if p was removed.
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (numChildren(p) == 2) throw  new IllegalArgumentException("P has two children");
		Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight() );
		if (child != null) child.setParent(node.getParent());
		if(node == root) root = child;
		else {
			Node<E> parent = node.getParent();
			if(node == parent.getLeft()) parent.setLeft(child);
			else parent.setRight(child);
		}
		size--;
		E temp = node.getElement();
		node.setElement(null);
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node);
		return temp;
	}
}
