package cs2321;

import net.datastructures.*;

/**
 * @author Ruihong Zhang
 * Reference: Textbook R-14.27 on page 679
 */
public class Islands {

    Graph<Integer, Integer> islands;

    /**
     * @param numOfIslands: total number of islands. It will be numbered as 0,1,2,...
     * @param distance:     distance[i][j] represents the distance between island[i] and island[j].
     *                      -1 means there is no edge between island[i] and island[j].
     */
    public Islands(int numOfIslands, int distance[][]) {

        //TODO: complete the constructor
        islands = new AdjListGraph<>(true);
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[i].length; j++) {
                if (distance[i][j] != -1) {
                    islands.insertEdge(islands.insertVertex(i), islands.insertVertex(j), distance[i][j]);
                }
            }
        }
        for (Vertex<Integer> v : islands.vertices()) {
            System.out.println(v.getElement());
        }
    }


    /**
     * @return the cost of minimum spanning tree using Kruskal's algorithm.
     */
    public int Kruskal() {
        //TODO: implement the Kruskal's algorithm and find the MST among all islands.

        PositionalList<Edge<Integer>> sum = MST();
        int total = 0;
        for (Position<Edge<Integer>> s : sum.positions()) {
            total += s.getElement().getElement();
        }
        return total;
    }

    public PositionalList<Edge<Integer>> MST() {
        PositionalList<Edge<Integer>> tree = new DoublyLinkedList<>();
        PriorityQueue<Integer, Edge<Integer>> pq = new HeapPQ<>();
        Partition<Vertex<Integer>> forest = new Partition<>();

        Map<Vertex<Integer>, Position<Vertex<Integer>>> positions = new HashMap<>();

        for (Vertex<Integer> v : islands.vertices())
            positions.put(v, forest.makeCluster(v));

        for (Edge<Integer> e : islands.edges())
            pq.insert(e.getElement(), e);

        int size = islands.numVertices();
        while (tree.size() != size - 1 && !pq.isEmpty()) {
            Entry<Integer, Edge<Integer>> entry = pq.removeMin();
            Edge<Integer> edge = entry.getValue();
            Vertex<Integer>[] endpoints = islands.endVertices(edge);
            Position<Vertex<Integer>> a = forest.find(positions.get(endpoints[0]));
            Position<Vertex<Integer>> b = forest.find(positions.get(endpoints[1]));
            if (a != b) {
                tree.addLast(edge);
                forest.union(a, b);
            }
        }
        return tree;
    }

}
