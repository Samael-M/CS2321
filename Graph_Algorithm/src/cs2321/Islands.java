package cs2321;

import net.datastructures.*;

/**
 * @author Ruihong Zhang
 * Reference: Textbook R-14.27 on page 679
 *
 */
public class Islands  {

	Graph<Integer, Integer> city;

	/**
	 * @param numOfIslands: total number of islands. It will be numbered as 0,1,2,...
	 * @param distance: distance[i][j] represents the distance between island[i] and island[j]. 
	 * 					-1 means there is no edge between island[i] and island[j]. 
	 */
	public Islands(int numOfIslands, int distance[][]) {
		
		//TODO: complete the constructor
		city = new AdjListGraph<>(true);

		int num = numOfIslands;
		int i = 0;
		while(num != 0) {
			city.insertVertex(i);
			num--;
		}
		i = 0;
		for(Vertex<Integer> v : city.vertices()) {
			int k = 0;
			for(Vertex<Integer> u : city.vertices()) {
				if(!v.equals(u) && k < distance[i].length && i < distance.length) {
					city.insertEdge(v, u, distance[i][k]);
				}
				k++;
			}
			i++;
		}


		
	}


	/**
	 * @return the cost of minimum spanning tree using Kruskal's algorithm. 
	 */
	public int Kruskal() {
		//TODO: implement the Kruskal's algorithm and find the MST among all islands.

		PositionalList<Edge<Integer>> sum = MST(city);
		int total = 0;
		for(Position<Edge<Integer>> s : sum.positions()) {
			total += s.getElement().getElement();
		}
		return total;
	}

	public PositionalList<Edge<Integer>> MST(Graph<Integer, Integer> g) {
		PositionalList<Edge<Integer>> tree = new DoublyLinkedList<>();
		PriorityQueue<Integer, Edge<Integer>>pq = new HeapPQ<>();
		Partition<Vertex<Integer>> forest = new Partition<>();

		Map<Vertex<Integer>,Position<Vertex<Integer>>>positions = new HashMap<>();

		for(Vertex<Integer> v : city.vertices())
			positions.put(v, forest.makeCluster(v));

		for(Edge<Integer>e : city.edges())
			pq.insert(e.getElement(), e);

		int size = city.numVertices();
		while(tree.size() != size - 1 && !pq.isEmpty()) {
			Entry<Integer, Edge<Integer>>entry = pq.removeMin();
			Edge<Integer>edge = entry.getValue();
			Vertex<Integer>[ ] endpoints = city.endVertices(edge);
			Position<Vertex<Integer>>a = forest.find(positions.get(endpoints[0]));
			Position<Vertex<Integer>>b = forest.find(positions.get(endpoints[1]));
			if(a != b) {
				tree.addLast(edge);
				forest.union(a, b);
			}
		}
		return tree;
	}

}
