package cs2321;

import net.datastructures.*;

/**
 * @author Ruihong Zhang
 * Reference textbook R14.16 P14.81 
 *
 */
public class Travel {

	Graph<String, Integer> city;
	
	/**
	 * @param routes: Array of routes between cities. 
	 *                routes[i][0] and routes[i][1] represent the city names on both ends of the route. 
	 *                routes[i][2] represents the cost in string type. 
	 *                Hint: In Java, use Integer.valueOf to convert string to integer. 
	 */
	public Travel(String [][] routes) {
		//TODO: complete the constructor


		city = new AdjListGraph<>(true);
		for(int i = 0; i <= routes.length - 1; i++) {
			Vertex<String> v = null;
			Vertex<String> u = null;
			Boolean vv = false;
			Boolean uu = false;
			for(Vertex<String> x : city.vertices()) {
				if(x.getElement().equals(routes[i][0])) {
					v = x;
					vv = true;
				}
				if(x.getElement().equals(routes[i][1])) {
					u = x;
					uu = true;
				}
			}
			if (!vv) v = city.insertVertex(routes[i][0]);
			if(!uu) u = city.insertVertex(routes[i][1]);
			city.insertEdge(v, u, Integer.valueOf(routes[i][2]));
		}

//		for(int i = 0; i <= routes.length - 1; i++) {
//			city.insertEdge(city.insertVertex(routes[i][0]), city.insertVertex(routes[i][1]), Integer.valueOf(routes[i][2]));
//		}

		
	}
	
	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @return Return the path from departure city to destination using Depth First Search algorithm. 
	 *         The path should be represented as ArrayList or DoublylinkedList of city names. 
	 *         The order of city names in the list should match order of the city names in the path.  
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              	              
	 *              See the method sortedOutgoingEdges below. 
	 */
	public Iterable<String> DFSRoute(String departure, String destination ) {
		//TODO: find the path based Depth First Search and return it
		Vertex<String> from = null;
		Vertex<String> to = null;

		for(Vertex<String> v : city.vertices()) {
			if (v.getElement().equals(departure)) {
				from = v;
			}
			if (v.getElement().equals(destination)) {
				to = v;
			}
		}
		DoublyLinkedList<Vertex<String>> known = new DoublyLinkedList<>();
		HashMap<Vertex<String>, Edge<Integer>> forest = new HashMap<>();
		DFS(city, from, known, forest);
		DoublyLinkedList<Edge<Integer>> dfs = constructPath(city, from, to, forest);

		ArrayList<String> path = new ArrayList<>();
		for(Position<Edge<Integer>> p : dfs.positions()) {
			path.addLast(p.getElement().getElement().toString());
		}
		Iterable<String> it = () -> path.iterator();
		return it;
	}

	public void DFS(Graph<String, Integer> g,
					 Vertex<String> u, DoublyLinkedList<Vertex<String>> known, HashMap<Vertex<String>, Edge<Integer>> forest) {
		for(Edge<Integer> e : sortedOutgoingEdges(u)) {
			Vertex<String> v = g.opposite(u, e);
			for(Vertex<String> x : known) {
				if(x.equals(v)) {
					forest.put(v, e);
					DFS(g, v, known, forest);
				}
			}
		}
	}

	public DoublyLinkedList<Edge<Integer>> constructPath(Graph<String, Integer> g, Vertex<String> u,
														 Vertex<String> v, Map<Vertex<String>, Edge<Integer>> forest) {
		DoublyLinkedList<Edge<Integer>> path = new DoublyLinkedList<>();
		if(forest.get(v) != null) {
			Vertex<String> walk = v;
			while(walk != u) {
				Edge<Integer> edge = forest.get(walk);
				path.addFirst(edge);
				walk = g.opposite(walk, edge);
			}
		}
		return path;
	}


	
	
	
	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
     * @return Return the path from departure city to destination using Breadth First Search algorithm. 
	 *         The path should be represented as ArrayList or DoublylinkedList of city names. 
	 *         The order of city names in the list should match order of the city names in the path.  
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              	             
	 *              See the method sortedOutgoingEdges below. 
	 */
	
	public Iterable<String> BFSRoute(String departure, String destination ) {
		
		//TODO: find the path based Breadth First Search and return it
		Vertex<String> from = null;
		Vertex<String> to = null;

		for(Vertex<String> v : city.vertices()) {
			if (v.getElement().equals(departure)) {
				from = v;
			}
			if (v.getElement().equals(destination)) {
				to = v;
			}
		}
		DoublyLinkedList<Vertex<String>> known = new DoublyLinkedList<>();
		HashMap<Vertex<String>, Edge<Integer>> forest = new HashMap<>();
		HashMap<Vertex<String>, Edge<Integer>> bfs = BFS(city, from, known, forest);
		ArrayList<String> route = new ArrayList<>();
		for(Edge<Integer> e : bfs.values()) {
			route.addLast(e.getElement().toString());
		}
		Iterable<String> it = () -> route.iterator();
		return it;
	}

	public HashMap<Vertex<String>, Edge<Integer>> BFS(Graph<String, Integer> g, Vertex<String> s, DoublyLinkedList<Vertex<String>> known,
					HashMap<Vertex<String>, Edge<Integer>> forest) {
		DoublyLinkedList<Vertex<String>> level = new DoublyLinkedList<>();
		known.addLast(s);
		level.addLast(s);
		while(!level.isEmpty()) {
			DoublyLinkedList<Vertex<String>> nextLevel = new DoublyLinkedList<>();
			for(Vertex<String> u : level)
				for(Edge<Integer> e : sortedOutgoingEdges(u)) {
					Vertex<String> v = g.opposite(u, e);
					for(Vertex<String> x : known) {
						if(x.equals(v)) {
							known.addLast(v);
							forest.put(v, e);
							nextLevel.addLast(v);
						}
					}
				}
			level = nextLevel;
		}
		return forest;
	}
	
	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @param itinerary: an empty DoublylinkedList object will be passed in to the method. 
	 * 	       When a shorted path is found, the city names in the path should be added to the list in the order. 
	 * @return return the cost of the shortest path from departure to destination. 
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              
	 *              See the method sortedOutgoingEdges below. 
	 */

	public int DijkstraRoute(String departure, String destination, DoublyLinkedList<String> itinerary ) {
		
		// TODO: find the path based Dijkstra Search, update itinerary and return the cost

		Vertex<String> from = null;
		Vertex<String> to = null;

		for(Vertex<String> v : city.vertices()) {
			if (v.getElement().equals(departure)) {
				from = v;
			}
			if (v.getElement().equals(destination)) {
				to = v;
			}
		}
		Map<Vertex<String>, Integer> shortest_path = dijk(city, from);
		return shortest_path.get(to);
	}

	public Map<Vertex<String>, Integer> dijk(Graph<String,Integer> g, Vertex<String> src) {

		Map<Vertex<String>, Integer> distance = new HashMap<>();
		Map<Vertex<String>, Integer> cloud = new HashMap<>();
		AdaptablePriorityQueue<Integer, Vertex<String>> pq;
		pq = new HeapPQ<>();
		Map<Vertex<String>, Entry<Integer,Vertex<String>>> pqTokens;
		pqTokens = new HashMap<>();

		for (Vertex<String> v : g.vertices()) {
			if(v== src) {
				distance.put(v, 0);
			}
			else
				distance.put(v, Integer.MAX_VALUE);
			pqTokens.put(v, pq.insert(distance.get(v), v));
		}

		while(!pq.isEmpty()) {
			Entry<Integer, Vertex<String>> entry = pq.removeMin();
			int key = entry.getKey();
			Vertex<String> u = entry.getValue();
			cloud.put(u, key);
			pqTokens.remove(u);

			for(Edge<Integer> e : sortedOutgoingEdges(u)) {
				Vertex<String> v = g.opposite(u, e);
				if(cloud.get(v) == null) {
					int wgt = e.getElement();
					if(distance.get(u) + wgt < distance.get(v)) {
						distance.put(v, distance.get(u) + wgt);
						pq.replaceKey(pqTokens.get(v), distance.get(v));
					}
				}
			}
		}
		return cloud;
	}
	
	

	/**
	 * I strongly recommend you to implement this method to return sorted outgoing edges for vertex V
	 * You may use any sorting algorithms, such as insert sort, selection sort, etc.
	 * 
	 * @param v: vertex v
	 * @return a list of edges ordered by edge's name
	 */
	public Iterable<Edge<Integer>> sortedOutgoingEdges(Vertex<String> v)  {

		//TODO: sort the outgoing edges and return the sorted list
		QuickSort<String> sort = new QuickSort<>();
		int j = 0;
		for (Edge<Integer> e : city.outgoingEdges(v)) { j++; }

		String[] vertices = new String[j];
		int i = 0;
		for(Edge<Integer> e : city.outgoingEdges(v)) {
			vertices[i] = city.opposite(v, e).getElement();
		}
		sort.sort(vertices);
		ArrayList<Edge<Integer>> Edge = new ArrayList<>();
		int k = 0;
		for(Edge<Integer> e : city.outgoingEdges(v)) {
			if(city.opposite(v, e).getElement().equals(vertices[k])) {
				Edge.addLast(e);
			}
		}
		Iterable<Edge<Integer>> it = () -> Edge.iterator();
		return it;

	}

	public static void main(String[] args) {

		String routes[][] = {  {"A","B","8"},
				{"A","D","1"},
				{"B","C","11"},
				{"C","D","1"}};

		Travel T = new Travel(routes);
		DoublyLinkedList<String> thing = new DoublyLinkedList<>();
		int a = T.DijkstraRoute(routes[0][0], routes[3][0], thing);
		System.out.println(a);

	}
}
