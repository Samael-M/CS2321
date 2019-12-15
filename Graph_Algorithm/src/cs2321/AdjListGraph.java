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

	public class InnerVertex<V> implements Vertex<V> {
		private V element;
		private Position<Vertex<V>> pos;
		private Map<Vertex<V>, Edge<E>> outgoing, incoming;
		public  InnerVertex(V elem, boolean graphIsDirected) {
			element = elem;
			outgoing = new HashMap<>();
			if(graphIsDirected) incoming = new HashMap<>();
			else incoming = outgoing;
		}
		@Override
		public V getElement() {
			return element;
		}
		public V setElement(V v) { V old = element; element = v; return  old; }
		public void setPostion(Position<Vertex<V>> p) {pos = p; }
		public Position<Vertex<V>> getPosition() { return pos; }
		public Map<Vertex<V>, Edge<E>> getOuting() { return outgoing; }
		public Map<Vertex<V>, Edge<E>> getIncoming() { return incoming; }
	}

	public class InnerEdge<E> implements Edge<E> {
		private E element;
		private Position<Edge<E>> pos;
		private Vertex<V>[] endpoints;

		public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
			element = elem;
			endpoints = (Vertex<V>[]) new Vertex[]{u, v};
		}
		/** Returns the element associated with the edge. */
		@Override
		public E getElement() { return element; }
		public E setElement(E e) { E old = element; element = e; return  old; }
		public Vertex<V>[] getEndpoints() {return endpoints; }
		public void setPosition(Position<Edge<E>> p ) { pos = p; }
		public Position<Edge<E>> getPosition() { return pos; }

	}

	private boolean isDirected;
	private PositionalList<Vertex<V>> vertices = new DoublyLinkedList<>();
	private PositionalList<Edge<E>> edges = new DoublyLinkedList<>();

	public AdjListGraph(boolean directed) {
		isDirected = directed;
	}

	public AdjListGraph() {
		isDirected = false;
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#edges()
	 */
	public Iterable<Edge<E>> edges() {
		return edges;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#vertices()
	 */
	public Iterable<Vertex<V>> vertices() {
		return vertices;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#endVertices(net.datastructures.Edge)
	 */
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		return edge.getEndpoints();
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertEdge(net.datastructures.Vertex, net.datastructures.Vertex, java.lang.Object)
	 */
	@TimeComplexityExpected("O(1)")
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o)
			throws IllegalArgumentException {
		if(getEdge(u, v) == null) {
			InnerEdge<E> e = new InnerEdge<>(u, v, o);
			e.setPosition(edges.addLast(e));
			InnerVertex<V> origin = validate(u);
			InnerVertex<V> dest = validate(v);
			origin.getOuting().put(v, e);
			dest.getIncoming().put(u, e);
			return e;
		} else throw  new IllegalArgumentException("Edge from u to v exist");
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertVertex(java.lang.Object)
	 */
	@TimeComplexityExpected("O(1)")
	public Vertex<V> insertVertex(V o) {
		InnerVertex<V> v = new InnerVertex<>(o, isDirected);
		v.setPostion((vertices.addLast(v)));
		return v;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numEdges()
	 */
	@TimeComplexityExpected("O(1)")
	public int numEdges() {
		return edges.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numVertices()
	 */
	@TimeComplexityExpected("O(1)")
	public int numVertices() {
		return vertices.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#opposite(net.datastructures.Vertex, net.datastructures.Edge)
	 */
	@TimeComplexityExpected("O(1)")
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
			throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		Vertex<V>[] endpoints = edge.getEndpoints();
		if(endpoints[0] == v) return endpoints[1];
		else if(endpoints[1] == v) return endpoints[0];
		else throw new IllegalArgumentException("V is not incident to this edge");
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeEdge(net.datastructures.Edge)
	 */
	@TimeComplexityExpected("O(1)")
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		InnerVertex<V> v = validate(edge.getEndpoints()[0]);
		InnerVertex<V> u = validate(edge.getEndpoints()[1]);

		u.getOuting().remove(v);
		v.getOuting().remove(u);

		edges.remove(edge.getPosition());
		edge.setPosition(null);
		edge.setElement(null);
		edge.endpoints = null;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeVertex(net.datastructures.Vertex)
	 */
	@TimeComplexityExpected("O(1)")
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		for (Edge<E> e : vert.getOuting().values())
			removeEdge(e);
		for (Edge<E> e : vert.getIncoming().values())
			removeEdge(e);
		vertices.remove(vert.getPosition());
	}

	/* 
     * replace the element in edge object, return the old element
     */
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		return edge.setElement(o);
	}

    /* 
     * replace the element in vertex object, return the old element
     */
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
		InnerVertex<V> edge = validate(v);
		return edge.setElement(o);
	}

	@Override
	@TimeComplexityExpected("O(1)")
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getOuting().size();
	}

	@Override
	@TimeComplexityExpected("O(1)")
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getIncoming().size();
	}

	@Override
	@TimeComplexityExpected("O(n)")
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getOuting().values();
	}

	@Override
	@TimeComplexityExpected("O(1)")
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getIncoming().values();
	}

	@Override
	@TimeComplexityExpected("O(1)")
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
			throws IllegalArgumentException {
		InnerVertex<V> origin = validate(v);
		return origin.getOuting().get(v);
	}


	public InnerVertex<V> validate(Vertex<V> v) {
		if (!(v instanceof InnerVertex)) throw new IllegalArgumentException("v is not valid vertex");
		InnerVertex<V> vert = (InnerVertex<V>) v;
		return (InnerVertex<V>) vert;
	}


	public InnerEdge<E> validate(Edge<E> e) {
		if(!(e instanceof InnerEdge)) throw new IllegalArgumentException("e is not valid Edge");
		InnerEdge<E> edge = (InnerEdge<E>) e;
		return (InnerEdge<E>) edge;
	}
	
}
