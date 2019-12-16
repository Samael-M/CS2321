package cs2321;

import net.datastructures.*;

/**
 * @author Ruihong Zhang
 * Reference: Text book: R14.17 on page 678
 *
 */
public class Course {

	Graph<String, String> courseMap;

	/**
	 * @param courses: An array of course information. Each element in the array is also array:
	 * 				starts with the course name, followed by a list (0 or more) of prerequisite course names.
	 * 
	 */
	public Course(String courses[][]) {
		//TODO: complete the constructor
		courseMap = new AdjListGraph<>(true);

		for(String[] s : courses) {
			Vertex<String> v = courseMap.insertVertex(s[0]);
			if(s.length > 1) {
				for(int i = s.length - 1; i > 0; i--) {
					courseMap.insertEdge(courseMap.insertVertex(s[i]), v, "string");
				}
			}
		}
//		for(int i = 0; i < courses.length; i++) {
//			courseMap.insertVertex(courses[i][0]);
//		}
//		int i = 0;
//		for(Vertex<String> v : courseMap.vertices()) {
//			int k = 1;
//			int j =  2;
//			System.out.println("V----------------- is: " + v.getElement());
//			for(Vertex<String> u : courseMap.vertices()) {
//				System.out.println("U is: " + u.getElement());
//				if(k < courses[i].length) {
//					System.out.println("We are at: " + courses[i][k]);
//					if(courses[i][k].equals(u.getElement())) {
//						System.out.println("Inserting Edge");
//						courseMap.insertEdge(u, v, "Arrow");
//						k++;
//					}
//				}
//			}
//			i++;
//		}

//		for(Vertex<String> v : courseMap.vertices()) {
//			for(Edge<String> e: courseMap.outgoingEdges(v)) {
//				System.out.println(v.getElement() + " is pre to: " + courseMap.opposite(v, e).getElement());
//			}
//		}
	}
	
	/**
	 * @param course
	 * @return find the earliest semester that the given course could be taken by a students after taking all the prerequisites. 
	 */
	public int whichSemester(String course) {

		Vertex<String> u = null;
		for(Vertex<String> v : courseMap.vertices()) {
			if(v.getElement().equals(course)) u =v;
		}
		return topologicalSort(courseMap, u);
//		DoublyLinkedList<Vertex<String>> topo = topologicalSort(courseMap, u);
//		int count = 0;
//		for(Position<Vertex<String>> p : topo.positions()) {
//			System.out.println(p.getElement().getElement());
//			if(!p.getElement().getElement().equals(course)) {
//				count++;
//			} //else break;
//		}
//		count++;
//		return count;
	}

	public int topologicalSort(Graph<String, String> g, Vertex<String> x) {
		DoublyLinkedList<Vertex<String>> top = new DoublyLinkedList<>();
		DoublyLinkedList<DoublyLinkedList<Vertex<String>>> topo = new DoublyLinkedList<>();

		Stack<Vertex<String>> ready = new DLLStack<>();
		Map<Vertex<String>, Integer> inCount = new HashMap<>();
		int count = 0;

		for(Vertex<String> u : courseMap.vertices()) {
			inCount.put(u, courseMap.inDegree(u));
			if(inCount.get(u) == 0) {
				DoublyLinkedList<Vertex<String>> tp = new DoublyLinkedList<>();
				tp.addLast(u);
				topo.addFirst(tp);
				ready.push(u);
			}
		}
		count++;
		while(!ready.isEmpty()) {
			Vertex<String> u = ready.pop();
			top.addLast(u);
			DoublyLinkedList<Vertex<String>> tp = new DoublyLinkedList<>();
			for(Edge<String> e : courseMap.outgoingEdges(u)) {
				Vertex<String> v = courseMap.opposite(u, e);
				inCount.put(v, inCount.get(v) - 1);
				if(inCount.get(v) == 0) {
					tp.addLast(u);
					ready.push(v);
				}
			}
			topo.addFirst(tp);
		}

		return topo.size();
	}

//	public DoublyLinkedList<Vertex<String>> topologicalSort(Graph<String, String> g, Vertex<String> x) {
//		DoublyLinkedList<Vertex<String>> topo = new DoublyLinkedList<>();
//
//		Stack<Vertex<String>> ready = new DLLStack<>();
//		Map<Vertex<String>, Integer> inCount = new HashMap<>();
//		int count = 0;
//
//		for(Edge<String> e : courseMap.incomingEdges(x)) {
//			ready.push(courseMap.opposite(x, e));
//			topo.addFirst(courseMap.opposite(x, e));
//			count++;
//		}
//		while(!ready.isEmpty()) {
//			Vertex<String> v = ready.pop();
//			for(Edge<String> e : courseMap.incomingEdges(v)) {
////				for(Vertex<String> y : topo) {
////					if (courseMap.opposite(v, e).getElement().equals(y.getElement())) {
////						ready.push(courseMap.opposite(v, e));
////						topo.addFirst(courseMap.opposite(v, e));
////						count++;
////					}
////				}
//				ready.push(courseMap.opposite(v, e));
//				topo.addFirst(courseMap.opposite(v, e));
//
//			}
//		}
//		if(!topo.isEmpty()) {
//			topo.removeFirst();
//		}
//		return topo;
//	}
			
}
