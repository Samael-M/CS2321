package cs2321;

import net.datastructures.Stack;

public class DLLStack<E> implements Stack<E> {

	DoublyLinkedList<E> Stack = new DoublyLinkedList();

	@Override
	public int size() {
		return Stack.size();
	}

	@Override
	public boolean isEmpty() {
		return Stack.isEmpty();
}

	@Override
	public void push(E e) {
		Stack.addLast(e);
	}

	@Override
	public E top() {
		return Stack.last().getElement();
	}

	@Override
	public E pop() {
		return Stack.removeLast();
	}

}
