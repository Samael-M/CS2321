package cs2321;
import java.util.Iterator;
import java.util.NoSuchElementException;


import net.datastructures.Position;
import net.datastructures.PositionalList;


public class DoublyLinkedList<E> implements PositionalList<E> {

	private static class Node<E> implements Position<E> {
		private E element;
		private Node<E> prev;
		private Node<E> next;
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		public E getElement() throws IllegalStateException {
			if (next == null)
				throw  new IllegalStateException("Position no longert valid");
			return element;
		}
		public Node<E> getPrev() {
			return prev;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setElement(E e) {
			element = e;
		}
		public void setPrev(Node<E> p) {
			prev = p;
		}
		public void setNext(Node<E> n) {
			next = n;
		}
	}

	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;

	public DoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}

	/**
	 * Checks to see if Position<E> p is valid, by checking to see if its a instance of the Node being used and checking to see if it is in the list
	 * @param Position<E> p, position to check
	 * @return Node<E> node, p recasted as a Node<E>
	 * @throws IllegalArgumentException if p is invalid or not in lust
	 */
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>) p;
		if (node.getNext() == null)
			throw new IllegalArgumentException("p is no longer in the list");
		return node;
	}
	/**
	 *
	 * @param
	 * @return
	 * @throws
	 */
	private Position<E> position(Node<E> node) {
		if(node == header || node == trailer)
			return null;
		return node;
	}
	/**
	 *
	 * @return size of List
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 *@return boolean size
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * @return position(header.getNext(), the data which is in the first node after header
	 */
	@Override
	public Position<E> first() {
		return position(header.getNext());
	}
	/**
	 * @return position(trailder.getPrev), the data which is the the previous node to the trailer
	 */
	@Override
	public Position<E> last() {
		return position(trailer.getPrev());
	}
	/**
	 * Check data thats in previous node of node given
	 * @param Position<E> p, node to check data of previous node
	 * @return postion(node.getPrev) data that is in node before p
	 * @throws IllegalArgumentException if p is not valid
	 */
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}
	/**
	 * Check data thats in next node of node given
	 * @param Position<E> p, node to check data of next node
	 * @return postion(node.getNext) data that is in node after p
	 * @throws IllegalArgumentException if p is not valid
	 */
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}
	/**
	 * Adds new node between two existing nodes, and changes where prev and next node point to
	 * @param E e, data to add in node & Node<E> prev, node that will be before new node & Node<E> succ, node that will be after new node
	 * @return Node<E> newest, node that was insterted
	 */
	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e, pred, succ);
		pred.setNext(newest);
		succ.setPrev(newest);
		size++;
		return newest;
	}
	/**
	 * adds new node after header and changes where header and previously first node point to
	 * @param E e, data to add in new node
	 */
	@Override
	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext());
	}
	/**
	 *Adds new node before trailer and cahgnes were previously last node and trailer point to
	 * @param E e, data to be added to new node
	 */
	@Override
	public Position<E> addLast(E e) {
		return addBetween(e, trailer.getPrev(), trailer);
	}
	/**
	 * Adds new node with data E e before node p thats given
	 * @param Position<E> p, node to add new node before
	 * @return addBetween(e, node.getPrev(), node), returns call of addbetween
	 * @throws IllegalArgumentException if p is not valid
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}
	/**
	 * Adds new node with data E e after node p thats given
	 * @param Position<E> p, node to add new node after
	 * @return addBetween(e, node, node.getNext()), node), returns call of addbetween
	 * @throws IllegalArgumentException if p is not valid
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}
	/**
	 * Sets E e as the new data within a node already in List
	 * @param Position<E> p, node to put data in, E e, data to add in
	 * @return E answer, data that was previosuyl in node
	 * @throws IllegalArgumentException if p is not valid
	 */
	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}
	/**
	 * Removes p and resents where prev and next ndoes point to, and decreses size of list.
	 * @param Position<E> p, node to remove
	 * @return E answer, data that was in removed node
	 * @throws IllegalArgumentException if node is not valid
	 */
	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		E answer = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return  answer;
	}

	/**
	 * I don't quite understand the Iterator for this yet so I included the comments that the book uses
	 */

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionalIterable();
	}

	private class PositionalIterator implements Iterator<Position<E>> {

		private Position<E> cursor = first();
		private Position<E> recent = null;
 		// Tests whether the iterator has a next object.
		public boolean hasNext() {
			return (cursor != null);
		}
		// Returns the next position in the iterator.
		public Position<E> next() throws NoSuchElementException {
			if (cursor == null) throw new NoSuchElementException("nothin left");
			recent = cursor;
			cursor = after(cursor);
			return recent;
		}
		// Removes the element returned by most recent call to next
		public void remove() throws IllegalArgumentException {
			if (recent == null) throw new IllegalArgumentException("nothing to remove");
			DoublyLinkedList.this.remove(recent);
			recent = null;
		}
	}

	private class PositionalIterable implements Iterable<Position<E>> {
		//Returns an iterable representation of the list's positions
		public Iterator<Position<E>> iterator() { return new PositionalIterator(); }
	}

	//This class adapts the iteration produced by positions() to return elements
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posIterator = new PositionalIterator();
		public boolean hasNext() {return posIterator.hasNext();}
		public E next() {return posIterator.next().getElement();}
		public void remove() { posIterator.remove();}
	}
	/**
	 *removes first node after header
	 * @return remove(header.next), call of remove
	 * @throws IllegalArgumentException
	 */
	public E removeFirst() throws IllegalArgumentException {
		return remove(header.next);
	}
	/**
	 *removes node before trailer
	 * @return remove(trailer.prev), call of remove
	 * @throws IllegalArgumentException
	 */
	public E removeLast() throws IllegalArgumentException {
		return remove(trailer.prev);
	}

	public Position<E> getPositon(int i) {
		Position pos = this.first();
		for(int j = 0; j <= i - 1; j++) {
			pos = this.after(pos);
		}
		return pos;
	}

//	public static void main(String[] args) {
//		DoublyLinkedList list = new DoublyLinkedList();
//		list.addFirst(1);
//		list.addFirst(2);
//		list.addFirst(3);
//		list.addFirst(4);
//		for(int i = 0; i<= list.size() - 1; i++){
//			System.out.println(list.getPositon(i).getElement());
//		}
//
//	}

}
