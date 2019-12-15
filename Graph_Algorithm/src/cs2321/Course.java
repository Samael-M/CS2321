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
		courseMap = new AdjListGraph<>();
		for(int i = 0; i < courses.length - 1; i++) {
			courseMap.insertVertex(courses[i][0]);
		}
		int i = 0;
		for(Vertex<String> v : courseMap.vertices()) {
			int k = 0;
			for(Vertex<String> u : courseMap.vertices()) {
				if(courses[i][k].equals(u.getElement()) && k < courses[i].length - 1 && i < courses.length) {
					courseMap.insertEdge(v, u, courses[i][k]);
				} else break;
				k++;
			}
			i++;
		}
	}
	
	/**
	 * @param course
	 * @return find the earliest semester that the given course could be taken by a students after taking all the prerequisites. 
	 */
	public int whichSemester(String course) {
		
		DoublyLinkedList<Vertex<String>> topo = topologicalSort(courseMap);
		int count = 0;
		for(Position<Vertex<String>> p : topo.positions()) {
			count ++;
		}
		return count;
	}

	public DoublyLinkedList<Vertex<String>> topologicalSort(Graph<String, String> g) {
		DoublyLinkedList<Vertex<String>> topo = new DoublyLinkedList<>();

		Stack<Vertex<String>> ready = new DLLStack<>();
		Map<Vertex<String>, Integer> inCount = new HashMap<>();
		for(Vertex<String> u : courseMap.vertices()) {
			inCount.put(u, courseMap.inDegree(u));
			if(inCount.get(u) == 0)
				ready.push(u);
		}
		while(!ready.isEmpty()) {
			Vertex<String> u = ready.pop();
			topo.addLast(u);
			for(Edge<String> e:courseMap.outgoingEdges(u)) {
				Vertex<String> v = courseMap.opposite(u, e);
				inCount.put(v, inCount.get(v) - 1);
				if(inCount.get(v) == 0)
					ready.push(v);
			}
		}
		return topo;
	}
			
}
