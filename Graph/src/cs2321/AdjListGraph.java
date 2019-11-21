package cs2321;

import net.datastructures.*;

/*
 * Implement Graph interface. A graph can be declared as either directed or undirected.
 * In the case of an undirected graph, methods outgoingEdges and incomingEdges return the same collection,
 * and outDegree and inDegree return the same value.
 * 
 * @author CS2321 Instructor
 */
public class AdjListGraph<V, E> implements Graph<V, E> {

	public AdjListGraph(boolean directed) {
		//TODO directed graph constructor 
	}

	public AdjListGraph() {
		//TODO non directed graph constructor 
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#edges()
	 */
	public Iterable<Edge<E>> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#endVertices(net.datastructures.Edge)
	 */
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertEdge(net.datastructures.Vertex, net.datastructures.Vertex, java.lang.Object)
	 */
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertVertex(java.lang.Object)
	 */
	public Vertex<V> insertVertex(V o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numEdges()
	 */
	public int numEdges() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numVertices()
	 */
	public int numVertices() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#opposite(net.datastructures.Vertex, net.datastructures.Edge)
	 */
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeEdge(net.datastructures.Edge)
	 */
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {

	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeVertex(net.datastructures.Vertex)
	 */
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
	
	}

	/* 
     * replace the element in edge object, return the old element
     */
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

    /* 
     * replace the element in vertex object, return the old element
     */
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#vertices()
	 */
	public Iterable<Vertex<V>> vertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
