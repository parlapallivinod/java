package in.rgukt.r081247.java.datastructure.graph;

import in.rgukt.r081247.java.datastructure.list.LinkedPositionalList;
import in.rgukt.r081247.java.datastructure.list.Position;
import in.rgukt.r081247.java.datastructure.list.PositionalList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An implementation for a graph structure using an adjacency map for each vertex.
 *
 * Every vertex stores an element of type V.
 * Every edge stores an element of type E.
 */
public class AdjacencyMapGraph<V, E> implements Graph<V, E> {
    private boolean isDirected;
    private PositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
    private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();

    /**
     * Constructs an empty graph.
     * The parameter determines whether this is an undirected or directed graph.
     */
    public AdjacencyMapGraph(boolean directed) {
        isDirected = directed;
    }

    /** Returns the number of vertices of the graph */
    @Override
    public int numVertices() {
        return vertices.size();
    }

    /** Returns the number of edges of the graph */
    @Override
    public int numEdges() {
        return edges.size();
    }

    /** Returns the vertices of the graph as an iterable collection */
    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    /** Returns the edges of the graph as an iterable collection */
    @Override
    public Iterable<Edge<E>> edges() {
        return edges;
    }

    /**
     * Returns the number of edges for which vertex v is the origin.
     * Note that for an undirected graph, this is the same result
     * returned by inDegree(v).
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    @Override
    public int outDegree(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().size();
    }

    /**
     * Returns the number of edges for which vertex v is the destination.
     * Note that for an undirected graph, this is the same result
     * returned by outDegree(v).
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    @Override
    public int inDegree(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().size();
    }

    /**
     * Returns an iterable collection of edges for which vertex v is the origin.
     * Note that for an undirected graph, this is the same result
     * returned by incomingEdges(v).
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().values();
    }

    /**
     * Returns an iterable collection of edges for which vertex v is the destination.
     * Note that for an undirected graph, this is the same result
     * returned by outgoingEdges(v).
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().values();
    }

    /** Returns the edge from u to v, or null if they are not adjacent. */
    @Override
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException {
       InnerVertex<V> origin = validate(u);
       return origin.getOutgoing().get(v); // will be null if no edge from u to v
    }

    /**
     * Returns the vertices of edge e as an array of length two.
     * If the graph is directed, the first vertex is the origin, and
     * the second is the destination.  If the graph is undirected, the
     * order is arbitrary.
     */
    @Override
    public Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        return edge.getEndpoints();
    }

    /** Returns the vertex that is opposite vertex v on edge e. */
    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoints = edge.getEndpoints();
        if (endpoints[0] == v) {
            return endpoints[1];
        } else if (endpoints[1] == v) {
            return endpoints[0];
        } else {
            throw new IllegalArgumentException("v is not incident to this edge");
        }
    }

    /** Inserts and returns a new vertex with the given element. */
    @Override
    public Vertex<V> insertVertex(V element) {
        InnerVertex<V> v = new InnerVertex<>(element, isDirected);
        v.setPosition(vertices.addLast(v));
        return v;
    }

    /**
     * Inserts and returns a new edge between vertices u and v, storing given element.
     *
     * @throws IllegalArgumentException if u or v are invalid vertices, or if an edge already exists between u and v.
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
        if (getEdge(u, v) == null) {
            InnerEdge<E> e = new InnerEdge<>(u, v, element);
            e.setPosition(edges.addLast(e));
            InnerVertex<V> origin = validate(u);
            InnerVertex<V> dest = validate(v);
            origin.getOutgoing().put(v, e);
            dest.getIncoming().put(u, e);
            return e;
        } else {
            throw new IllegalArgumentException("Edge from u to v exists");
        }
    }

    /** Removes a vertex and all its incident edges from the graph. */
    @Override
    public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        for (Edge<E> e: vert.getOutgoing().values())
            removeEdge(e);
        for (Edge<E> e: vert.getIncoming().values())
            removeEdge(e);
        vertices.remove(vert.getPosition());
        vert.setPosition(null);
    }

    /** Removes an edge from the graph. */
    @Override
    public void removeEdge(Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        InnerVertex<V>[] verts = (InnerVertex<V>[]) edge.getEndpoints();
        verts[0].getOutgoing().remove(verts[1]);
        verts[1].getIncoming().remove(verts[0]);
        edges.remove(edge.getPosition());
        edge.setPosition(null);
    }

    private InnerVertex<V> validate(Vertex<V> v) {
        if (!(v instanceof InnerVertex))
            throw new IllegalArgumentException("Invalid vertex");
        InnerVertex<V> vert = (InnerVertex<V>) v;
        if (!vert.validate(this))
            throw new IllegalArgumentException("Invalid vertex");
        return vert;
    }

    public InnerEdge<E> validate(Edge<E> e) {
        if (!(e instanceof InnerEdge))
            throw new IllegalArgumentException("Invalid edge");
        InnerEdge<E> edge = (InnerEdge<E>) e;
        if (!edge.validate(this))
            throw new IllegalArgumentException("Invalid edge");
        return edge;
    }

    //------------------------ nested Vertex class -----------------------------
    /** A vertex of an adjacency map graph representation. */
    private class InnerVertex<V> implements Vertex<V> {
        private V element;
        private Position<Vertex<V>> pos;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;

        /** Constructs a new InnerVertex instance storing the given element. */
        public InnerVertex(V elem, boolean graphIsDirected) {
            element = elem;
            outgoing = new LinkedHashMap<>();
            if (graphIsDirected)
                incoming = new LinkedHashMap<>();
            else
                incoming = outgoing;
        }

        /** Validates that this vertex instance belongs to the given graph. */
        public boolean validate(Graph<V, E> graph) {
            return (AdjacencyMapGraph.this == graph && pos != null);
        }

        /** Returns the element associated with the vertex. */
        @Override
        public V getElement() {
            return element;
        }

        /** Stores the position of this vertex within the graph's vertex list. */
        public void setPosition(Position<Vertex<V>> p) {
            pos = p;
        }

        /** Returns the position of this vertex within the graph's vertex list. */
        public Position<Vertex<V>> getPosition() {
            return pos;
        }

        /** Returns reference to the underlying map of outgoing edges. */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        /** Returns reference to the underlying map of incoming edges. */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }

        @Override
        public String toString() {
            return "InnerVertex{" +
                    "element=" + element +
                    '}';
        }
    }

    //---------------- nested InnerEdge class ----------------
    /** An edge between two vertices. */
    private class InnerEdge<E> implements Edge<E> {
        private E element;
        private Position<Edge<E>> pos;
        private Vertex<V>[] endPoints;

        /** Constructs InnerEdge instance from u to v, storing the given element. */
        public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
            element = elem;
            endPoints = (Vertex<V>[] ) new Vertex[] {u, v};
        }

        /** Returns the element associated with the edge. */
        @Override
        public E getElement() {
            return element;
        }

        /** Returns reference to the endpoint array. */
        public Vertex<V>[] getEndpoints() {
            return endPoints;
        }

        /** Validates that this edge instance belongs to the given graph. */
        public boolean validate(Graph<V, E> graph) {
            return AdjacencyMapGraph.this == graph && pos != null;
        }

        /** Stores the position of this edge within the graph's vertex list. */
        public void setPosition(Position<Edge<E>> p) {
            pos = p;
        }

        /** Returns the position of this edge within the graph's vertex list. */
        public Position<Edge<E>> getPosition() {
            return pos;
        }

        @Override
        public String toString() {
            return "InnerEdge{" +
                    "element=" + element +
                    '}';
        }
    }

    /**
     * Returns a string representation of the graph.
     * This is used only for debugging; do not rely on the string representation.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<V> v : vertices) {
            sb.append("Vertex " + v.getElement() + "\n");
            if (isDirected)
                sb.append(" [outgoing]");
            sb.append(" " + outDegree(v) + " adjacencies:");
            for (Edge<E> e: outgoingEdges(v))
                sb.append(String.format(" (%s, %s)", opposite(v,e).getElement(), e.getElement()));
            sb.append("\n");
            if (isDirected) {
                sb.append(" [incoming]");
                sb.append(" " + inDegree(v) + " adjacencies:");
                for (Edge<E> e: incomingEdges(v))
                    sb.append(String.format(" (%s, %s)", opposite(v,e).getElement(), e.getElement()));
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
