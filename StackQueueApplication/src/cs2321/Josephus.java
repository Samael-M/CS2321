package cs2321;

import net.datastructures.Position;

public class Josephus {
	/**
	 * All persons sit in a circle. When we go around the circle, initially starting
	 * from the first person, then the second person, then the third... 
	 * we count 1,2,3,.., k-1. The next person, that is the k-th person is out. 
	 * Then we restart the counting from the next person, go around, the k-th person 
	 * is out. Keep going the same way, when there is only one person left, she/he 
	 * is the winner. 
	 *  
	 * @parameter persons  an array of string which contains all player names.
	 * @parameter k  an integer specifying the k-th person will be kicked out of the game
	 * @return return a doubly linked list in the order when the players were out of the game. 
	 *         the last one in the list is the winner.  
	 */
	public DoublyLinkedList<String> order(String[] persons, int k ) {
		CircularArrayQueue People = new CircularArrayQueue(persons.length);
		DoublyLinkedList result = new DoublyLinkedList();
		for(int i = 0; i < persons.length; i++) {
			People.enqueue(persons[i]);
		}

		while (result.size() < persons.length) {
			for (int i = 0; i < k - 1; i++){
				People.enqueue(People.dequeue());
			}
			result.addLast(People.dequeue());
		}
		return result;
	}
	public static void main(String[] args) {
		Josephus test = new Josephus();
		String[] testt = new String[] {"A", "B", "C", "D", "E", "F"};
		DoublyLinkedList temp = test.order(testt, 3);
		for (int i = 0; i < 6; i++){
			System.out.println(temp.removeFirst());
		}
	}
}