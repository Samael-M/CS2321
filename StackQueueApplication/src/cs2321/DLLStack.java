package cs2321;

import net.datastructures.Stack;

public class DLLStack<E> implements Stack<E> {

	DoublyLinkedList<E> Stack = new DoublyLinkedList();

	@Override
	public int size() {
		Stack.size();
		return 0;
	}

	@Override
	public boolean isEmpty() {
		Stack.isEmpty();
		return false;
	}

	@Override
	public void push(E e) {
		Stack.addFirst(e);
	}

	@Override
	public E top() {
		Stack.first();
		return null;
	}

	@Override
	public E pop() {
		Stack.removeFirst();
		return null;
	}

}
