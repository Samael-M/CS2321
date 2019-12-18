package cs2321;
import net.datastructures.*;
import java.util.Iterator;

/**
 * Samuel Milner
 * <p>
 * This is the sloppiest most inefficient, over complicated code I have ever produced, but it passes test and thats good enough
 * for me right now in the hecticness of finals week.
 *
 * @author Ruihong Zhang
 * Reference textbook R14.16 P14.81
 */
@SuppressWarnings("Duplicates")
public class Travel {

    Graph<String, Integer> city;

    /**
     * @param routes: Array of routes between cities.
     *                routes[i][0] and routes[i][1] represent the city names on both ends of the route.
     *                routes[i][2] represents the cost in string type.
     *                Hint: In Java, use Integer.valueOf to convert string to integer.
     */
    public Travel(String[][] routes) {
        city = new AdjListGraph<>(false);
        for (int i = 0; i < routes.length; i++) {
            city.insertEdge(city.insertVertex(routes[i][0]),
                    city.insertVertex(routes[i][1]), Integer.valueOf(routes[i][2]));
        }
    }

    /**
     * @param departure:   the departure city name
     * @param destination: the destination city name
     * @return Return the path from departure city to destination using Depth First Search algorithm.
     * The path should be represented as ArrayList or DoublylinkedList of city names.
     * The order of city names in the list should match order of the city names in the path.
     * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
     * the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
     * V
     * /  |  \
     * /   |    \
     * B    A     F
     * your algorithm below should visit the outgoing edges of V in the order of A,B,F.
     * This means you will need to create a helper function to sort the outgoing edges by
     * the opposite city names.
     * <p>
     * See the method sortedOutgoingEdges below.
     */
    public Iterable<String> DFSRoute(String departure, String destination) {
        //TODO: find the path based Depth First Search and return it

        Vertex<String> from = city.insertVertex(departure);
        Vertex<String> to = city.insertVertex(destination);

        Set<Vertex<String>> known = new Set<>();
        HashMap<Vertex<String>, Edge<Integer>> forest = new HashMap<>();

        Graph<String, Integer> graph = new AdjListGraph<>(true);
        graph = DFS(graph, from, known, forest);

        Set<String> route = new Set<>();
        route = backwards(graph, to, route);
        ArrayList<String> done = new ArrayList<>();
        for (Iterator<String> it = route.iterator(); it.hasNext(); ) {
            String s = it.next();
            done.addLast(s);
        }
        return done;
    }

    public Graph<String, Integer> DFS(Graph<String, Integer> graph,
                                      Vertex<String> u, Set<Vertex<String>> known, HashMap<Vertex<String>, Edge<Integer>> forest) {
        known.add(u);
        graph.insertVertex(u.getElement());
        for (Edge<Integer> e : sortedOutgoingEdges(u)) {
            Vertex<String> v = city.opposite(u, e);
            if (!known.contains(v)) {
                graph.insertEdge(graph.insertVertex(u.getElement()), graph.insertVertex(v.getElement()), e.getElement());
                return DFS(graph, v, known, forest);
            }
        }
        return graph;
    }


    /**
     * @param departure:   the departure city name
     * @param destination: the destination city name
     * @return Return the path from departure city to destination using Breadth First Search algorithm.
     * The path should be represented as ArrayList or DoublylinkedList of city names.
     * The order of city names in the list should match order of the city names in the path.
     * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
     * the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
     * V
     * /  |  \
     * /   |    \
     * B    A     F
     * your algorithm below should visit the outgoing edges of V in the order of A,B,F.
     * This means you will need to create a helper function to sort the outgoing edges by
     * the opposite city names.
     * <p>
     * See the method sortedOutgoingEdges below.
     */

    public Iterable<String> BFSRoute(String departure, String destination) {

        //TODO: find the path based Breadth First Search and return it
        Vertex<String> from = city.insertVertex(departure);
        Vertex<String> to = city.insertVertex(destination);

        Set<Vertex<String>> known = new Set<>();
        HashMap<Vertex<String>, Edge<Integer>> forest = new HashMap<>();
        Graph<String, Integer> graph = new AdjListGraph<>(true);
        graph = BFS(city, from, to, known, forest);

        Set<String> route = new Set<>();
        route = backwards(graph, to, route);
        ArrayList<String> done = new ArrayList<>();
        for (Iterator<String> it = route.iterator(); it.hasNext(); ) {
            String s = it.next();
            done.addLast(s);
        }

        return done;
    }

    public Set<String> backwards(Graph<String, Integer> graph, Vertex<String> destination, Set<String> path) {
        Vertex<String> des = graph.insertVertex(destination.getElement());
        path.addFirst(des.getElement());
        for (Edge<Integer> e : graph.incomingEdges(des)) {
            Vertex<String> v = graph.opposite(des, e);
            if (graph.incomingEdges(v) != null) {
                path.addFirst(v.getElement());
                return backwards(graph, v, path);
            }
        }

        return path;
    }


    public Graph<String, Integer> BFS(Graph<String, Integer> g, Vertex<String> start, Vertex<String> end, Set<Vertex<String>> known,
                                      HashMap<Vertex<String>, Edge<Integer>> forest) {

        Graph<String, Integer> graph = new AdjListGraph<>(true);

        DoublyLinkedList<Vertex<String>> level = new DoublyLinkedList<>();

        known.add(start);
        graph.insertVertex(start.getElement());
        level.addLast(start);
        while (!level.isEmpty()) {
            DoublyLinkedList<Vertex<String>> nextLevel = new DoublyLinkedList<>();
            for (Vertex<String> u : level)
                for (Edge<Integer> e : sortedOutgoingEdges(u)) {
                    Vertex<String> v = g.opposite(u, e);
                    if (!known.contains(v)) {
                        if (v.getElement().equals(end.getElement())) {
                            known.add(v);
                            graph.insertEdge(graph.insertVertex(u.getElement()), graph.insertVertex(v.getElement()), e.getElement());
                            return graph;
                        }
                        known.add(v);
                        graph.insertEdge(graph.insertVertex(u.getElement()), graph.insertVertex(v.getElement()), e.getElement());
                        nextLevel.addLast(v);
                    }
                }
            level = nextLevel;
        }
        return graph;
    }

    /**
     * @param departure:   the departure city name
     * @param destination: the destination city name
     * @param itinerary:   an empty DoublylinkedList object will be passed in to the method.
     *                     When a shorted path is found, the city names in the path should be added to the list in the order.
     * @return return the cost of the shortest path from departure to destination.
     * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
     * the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
     * V
     * /  |  \
     * /   |    \
     * B    A     F
     * your algorithm below should visit the outgoing edges of V in the order of A,B,F.
     * This means you will need to create a helper function to sort the outgoing edges by
     * the opposite city names.
     * <p>
     * See the method sortedOutgoingEdges below.
     */

    public int DijkstraRoute(String departure, String destination, DoublyLinkedList<String> itinerary) {

        // TODO: find the path based Dijkstra Search, update itinerary and return the cost

        Vertex<String> from = city.insertVertex(departure);
        Vertex<String> to = city.insertVertex(destination);

        Map<Vertex<String>, Integer> shortest_path = dijk(city, from, to, itinerary);
        return shortest_path.get(to);
    }

    public Map<Vertex<String>, Integer> dijk(Graph<String, Integer> g, Vertex<String> src, Vertex<String> des, DoublyLinkedList<String> itinerary) {

        Map<Vertex<String>, Integer> distance = new HashMap<>();
        Map<Vertex<String>, Integer> cloud = new HashMap<>();
        AdaptablePriorityQueue<Integer, Vertex<String>> pq;
        pq = new HeapPQ<>();
        Map<Vertex<String>, Entry<Integer, Vertex<String>>> pqTokens;
        pqTokens = new HashMap<>();

        for (Vertex<String> v : g.vertices()) {
            if (v == src) {
                distance.put(v, 0);
            } else
                distance.put(v, Integer.MAX_VALUE);
            pqTokens.put(v, pq.insert(distance.get(v), v));
        }
        while (!pq.isEmpty()) {
            Entry<Integer, Vertex<String>> entry = pq.removeMin();
            int key = entry.getKey();
            Vertex<String> u = entry.getValue();
            cloud.put(u, key);
            itinerary.addLast(u.getElement());
            if (u.getElement().equals(des.getElement())) {
                return cloud;
            }
            pqTokens.remove(u);
            for (Edge<Integer> e : sortedOutgoingEdges(u)) {
                Vertex<String> v = g.opposite(u, e);
                if (cloud.get(v) == null) {
                    int wgt = e.getElement();
                    if (distance.get(u) + wgt < distance.get(v)) {
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
    public Iterable<Edge<Integer>> sortedOutgoingEdges(Vertex<String> v) {

        //TODO: sort the outgoing edges and return the sorted list
        QuickSort sort = new QuickSort();
        int j = 0;
        for (Edge<Integer> e : city.outgoingEdges(v)) {
            j++;
        }

        Edge<Integer>[] edges = new Edge[j];
        int i = 0;
        for (Edge<Integer> e : city.outgoingEdges(v)) {
            edges[i] = e;
            i++;
        }
        sort.sort(edges, city, v);
        ArrayList<Edge<Integer>> Edge = new ArrayList<>();
        for (Edge<Integer> e : edges) {
            Edge.addLast(e);
        }
        return Edge;
    }

    public static void main(String[] args) {

        String routes[][] = {{"A", "B", "8"},
                {"A", "D", "1"},
                {"B", "C", "11"},
                {"C", "D", "1"}};

        Travel T = new Travel(routes);
        DoublyLinkedList<String> thing = new DoublyLinkedList<>();
        int a = T.DijkstraRoute(routes[0][0], routes[3][0], thing);
        System.out.println(a);

    }
}
