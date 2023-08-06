package in.rgukt.r081247.java.datastructure.graph;

import in.rgukt.r081247.java.datastructure.list.LinkedPositionalList;
import in.rgukt.r081247.java.datastructure.list.PositionalList;
import in.rgukt.r081247.java.datastructure.priorityqueue.AdaptablePriorityQueue;
import in.rgukt.r081247.java.datastructure.priorityqueue.Entry;
import in.rgukt.r081247.java.datastructure.priorityqueue.HeapAdaptablePriorityQueue;

import java.util.*;


public class Algos {
    public static void main(String[] args) {
        /*
        Graph<String, String> ug = getUndirectGraph();
        System.out.println(ug);
        PositionalList<Vertex<String>> vertices = (PositionalList<Vertex<String>>) ug.vertices();

        Set<Vertex<String>> known = new LinkedHashSet<>();
        Map<Vertex<String>, Edge<String>> forest = new LinkedHashMap<>();
        dfs(ug, (Vertex<String>) vertices.first().getElement(), known, forest);
        System.out.println(known);
        System.out.println(forest);
        */

        /*
        Graph<String, String> ug = getDirectGraph();
        System.out.println(ug);
        PositionalList<Vertex<String>> vertices = (PositionalList<Vertex<String>>) ug.vertices();

        Set<Vertex<String>> known = new LinkedHashSet<>();
        Map<Vertex<String>, Edge<String>> forest = new LinkedHashMap<>();
        dfs(ug, (Vertex<String>) vertices.first().getElement(), known, forest);
        System.out.println(known);
        System.out.println(forest);

        PositionalList<Edge<String>> list = constructPath(ug, (Vertex<String>) vertices.first().getElement(), (Vertex<String>) vertices.last().getElement(), forest);
        System.out.println(list);
         */

        /**
        Graph<String, String> ug = getUndirectGraph();
        System.out.println(ug);
        PositionalList<Vertex<String>> vertices = (PositionalList<Vertex<String>>) ug.vertices();

        Set<Vertex<String>> known = new LinkedHashSet<>();
        Map<Vertex<String>, Edge<String>> forest = new LinkedHashMap<>();
        bfs(ug, (Vertex<String>) vertices.first().getElement(), known, forest);
        System.out.println(known);
        System.out.println(forest);

        PositionalList<Edge<String>> list = constructPath(ug, (Vertex<String>) vertices.first().getElement(), (Vertex<String>) vertices.last().getElement(), forest);
        System.out.println(list);
         */

        /*
        Graph<String, String> dg = getTopologicalSortDirectGraph();
        System.out.println(dg);
        PositionalList<Vertex<String>> vertices = topologicalSort(dg);
        System.out.println(vertices);

         */

        Graph<String,Integer> g = getDijkstraUndirectGraph();
        System.out.println(g);
        PositionalList<Vertex<String>> vertices = (PositionalList<Vertex<String>>) g.vertices();
        Map<Vertex<String>, Integer> map = shortestPathLengths(g, vertices.first().getElement());
        System.out.println(map);
        Map<Vertex<String>, Edge<Integer>> dist = spTree(g, vertices.first().getElement(), map);
        System.out.println(dist);

        /*
        Graph<String,Integer> g = getDijkstraUndirectGraph();
        System.out.println(g);
        PositionalList<Vertex<String>> vertices = (PositionalList<Vertex<String>>) g.vertices();
        Map<Vertex<String>, Integer> map = primJarnikMST(g, vertices.first().getElement());
        System.out.println(map);
         */

    }

    /** Performs depth-first search of Graph g starting at Vertex u. */
    public static <V, E> void dfs(Graph<V, E> g, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
        known.add(u); // u has been discovered
        for (Edge<E> e: g.outgoingEdges(u)) { // for every outgoing edge from u
            Vertex<V> v = g.opposite(u, e);
            if (!known.contains(v)) {
                forest.put(v, e);           // e is the tree edge that discovered v
                dfs(g, v, known, forest);   // recursively explore from v
            }
        }
    }

    /** Performs breadth-first search of Graph g starting at Vertex u. */
    public static <V,E> void bfs(Graph<V,E> g, Vertex<V> s, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
        PositionalList<Vertex<V>> level = new LinkedPositionalList<>();
        known.add(s);
        level.addLast(s);   // first level includes only s

        while (!level.isEmpty()) {
            PositionalList<Vertex<V>> nextLevel = new LinkedPositionalList<>();
            for (Vertex<V> u: level) {
                for (Edge<E> e: g.outgoingEdges(u)) {
                    Vertex<V> v = g.opposite(u, e);
                    if (!known.contains(v)) {
                        known.add(v);
                        nextLevel.addLast(v);
                        forest.put(v, e);       // e is the tree edge that discovered v
                    }
                }
            }
            level = nextLevel;
        }
    }

    /** Returns an ordered list of edges comprising the directed path from u to v. */
    public static <V, E> PositionalList<Edge<E>> constructPath(Graph<V, E> g, Vertex<V> u, Vertex<V> v, Map<Vertex<V>, Edge<E>> forest) {
        PositionalList<Edge<E>> path = new LinkedPositionalList<>();
        if (forest.get(v) != null) { // v was discovered during the search
            Vertex<V> walk = v;     // we construct path from back to front
            while (walk != u) {
                Edge<E> edge = forest.get(walk);
                path.addFirst(edge);            // add edge to *front* of path
                System.out.println(edge);
                walk = g.opposite(walk, edge);  // repeat with opposite endpoint
            }
        }
        return path;
    }

    /** Performs DFS for the entire graph and returns the DFS forest as a map. */
    public static <V, E> Map<Vertex<V>, Edge<E>> dfsComplete(Graph<V, E> g) {
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Edge<E>> forest = new HashMap<>();
        for (Vertex<V> u: g.vertices()) {
            if (!known.contains(u)) {
                dfs(g, u, known, forest);
            }
        }
        return forest;
    }

    /** Converts graph g into its transitive closure. */
    public static <V,E> void transitiveClosure(Graph<V,E> g) {
        for (Vertex<V> k: g.vertices()) {
            for (Vertex<V> i: g.vertices()) {
                // verify that edge (i,k) exists in the partial closure
                if (i != k && g.getEdge(i,k) != null) {
                    for (Vertex<V> j: g.vertices()) {
                        // verify that edge (k,j) exists in the partial closure
                        if (i != j && j != k && g.getEdge(k, j) != null) {
                            // if(i,j) not yet included, add it to the closure
                            if (g.getEdge(i,j) == null) {
                                g.insertEdge(i, j, null);
                            }
                        }
                    }
                }
            }
        }
    }

    /** Returns a list of vertices of directed acyclic graph g in topological order. */
    public static <V,E> PositionalList<Vertex<V>> topologicalSort(Graph<V,E> g) {
        // list of vertices placed in topological order
        PositionalList<Vertex<V>> topo = new LinkedPositionalList<>();
        // container of vertices that have no remaining constraints
        Deque<Vertex<V>> ready = new LinkedList<>();
        // map keeping track of remaining in-degree for each vertex
        Map<Vertex<V>, Integer> inCount = new HashMap<>();
        for (Vertex<V> u: g.vertices()) {
            inCount.put(u, g.inDegree(u));      // initialize with actual in-degree
            if (inCount.get(u) == 0)            // if u has no incoming edges,
                ready.push(u);                  // it is free of constraints
        }

        while (!ready.isEmpty()) {
            Vertex<V> u = ready.pop();
            topo.addLast(u);
            for (Edge<E> e: g.outgoingEdges(u)) { // consider all outgoing neighbors of u
                Vertex<V> v = g.opposite(u, e);
                inCount.put(v, inCount.get(v) - 1);
                if (inCount.get(v) == 0)
                    ready.push(v);
            }
        }
        return topo;
    }

    /**
     * Reconstructs a shortest-path tree rooted at vertex s, given distance map d.
     * The tree is represented as a map from each reachable vertex v (other than s)
     * to the edge e = (u,v) that is used to reach v from its parent u in the tree.
     */
    public static <V> Map<Vertex<V>,Edge<Integer>> spTree(Graph<V,Integer> g, Vertex<V> s, Map<Vertex<V>,Integer> d) {
        Map<Vertex<V>, Edge<Integer>> tree = new HashMap<>();
        for (Vertex<V> v: d.keySet()) {
            if (v != s) {
                for (Edge<Integer> e: g.incomingEdges(v)) { // consider INCOMING edges
                    Vertex<V> u = g.opposite(v, e);
                    int wgt = e.getElement();
                    if (d.get(v) == d.get(u) + wgt)
                        tree.put(v, e);                     // edge is used to reach v
                }
            }
        }
        return tree;
    }

    /** Computes minimum spanning tree of g using prims-jarnik algorithm. */
    public static <V> Map<Vertex<V>, Integer> primJarnikMST(Graph<V, Integer> g, Vertex<V> src) {
        // d.get(v) is upper bound on distance from src to v
        Map<Vertex<V>, Integer> d = new HashMap<>();
        // map reachable v to its d value
        Map<Vertex<V>, Integer> cloud = new LinkedHashMap<>();
        // pq will have vertices as elements, with d.get(v) as key
        AdaptablePriorityQueue<Integer, Vertex<V>> pq = new HeapAdaptablePriorityQueue<>( );
        // maps from vertex to its pq locator
        Map<Vertex<V>, Entry<Integer,Vertex<V>>> pqTokens = new HashMap<>();

        // for each vertex v of the graph, add an entry to the priority queue, with
        // the source having distance 0 and all others having infinite distance
        for (Vertex<V> v: g.vertices()) {
            if (v == src)
                d.put(v, 0);
            else
                d.put(v, Integer.MAX_VALUE);
            pqTokens.put(v, pq.insert(d.get(v), v)); // save entry for future updates
        }

        // now begin adding reachable vertices to the cloud
        while (!pq.isEmpty()) {
            Entry<Integer, Vertex<V>> entry = pq.removeMin();
            int key = entry.getKey();
            Vertex<V> u = entry.getValue();
            cloud.put(u, key); // this is actual distance to u
            pqTokens.remove(u); // u is no longer in pq
            for (Edge<Integer> e: g.outgoingEdges(u)) {
                Vertex<V> v = g.opposite(u, e);
                if (cloud.get(v) == null) {
                    // perform relaxation step on edge (u,v)
                    int wgt = e.getElement();
                    if (wgt < d.get(v)) { // better path to v
                        d.put(v, wgt); // update the distance
                        pq.replaceKey(pqTokens.get(v), d.get(v)); // update the pq entry
                    }
                }
            }
        }
        return cloud; // this only includes reachable vertices
    }

    /** Computes shortest-path distances from src vertex to all reachable vertices of g. */
    public static <V> Map<Vertex<V>, Integer> shortestPathLengths(Graph<V, Integer> g, Vertex<V> src) {
        // d.get(v) is upper bound on distance from src to v
        Map<Vertex<V>, Integer> d = new HashMap<>();
        // map reachable v to its d value
        Map<Vertex<V>, Integer> cloud = new LinkedHashMap<>();
        // pq will have vertices as elements, with d.get(v) as key
        AdaptablePriorityQueue<Integer, Vertex<V>> pq = new HeapAdaptablePriorityQueue<>( );
        // maps from vertex to its pq locator
        Map<Vertex<V>, Entry<Integer,Vertex<V>>> pqTokens = new HashMap<>();

        // for each vertex v of the graph, add an entry to the priority queue, with
        // the source having distance 0 and all others having infinite distance
        for (Vertex<V> v: g.vertices()) {
            if (v == src)
                d.put(v, 0);
            else
                d.put(v, Integer.MAX_VALUE);
            pqTokens.put(v, pq.insert(d.get(v), v)); // save entry for future updates
        }

        // now begin adding reachable vertices to the cloud
        while (!pq.isEmpty()) {
            Entry<Integer, Vertex<V>> entry = pq.removeMin();
            int key = entry.getKey();
            Vertex<V> u = entry.getValue();
            cloud.put(u, key); // this is actual distance to u
            pqTokens.remove(u); // u is no longer in pq
            for (Edge<Integer> e: g.outgoingEdges(u)) {
                Vertex<V> v = g.opposite(u, e);
                if (cloud.get(v) == null) {
                    // perform relaxation step on edge (u,v)
                    int wgt = e.getElement();
                    if (d.get(u) + wgt < d.get(v)) { // better path to v
                        d.put(v, d.get(u) + wgt); // update the distance
                        pq.replaceKey(pqTokens.get(v), d.get(v)); // update the pq entry
                    }
                }
            }
        }
        return cloud; // this only includes reachable vertices
    }

    public static Graph<String, String> getUndirectGraph() {
        Graph<String, String> ug = new AdjacencyMapGraph<>(false);

        Vertex<String> a = ug.insertVertex("a");
        Vertex<String> b = ug.insertVertex("b");
        Vertex<String> c = ug.insertVertex("c");
        Vertex<String> d = ug.insertVertex("d");
        Vertex<String> e = ug.insertVertex("e");
        Vertex<String> f = ug.insertVertex("f");
        Vertex<String> g = ug.insertVertex("g");
        Vertex<String> h = ug.insertVertex("h");
        Vertex<String> i = ug.insertVertex("i");
        Vertex<String> j = ug.insertVertex("j");
        Vertex<String> k = ug.insertVertex("k");
        Vertex<String> l = ug.insertVertex("l");
        Vertex<String> m = ug.insertVertex("m");
        Vertex<String> n = ug.insertVertex("n");
        Vertex<String> o = ug.insertVertex("o");
        Vertex<String> p = ug.insertVertex("p");

        Edge<String> ab = ug.insertEdge(a, b, "ab");
        Edge<String> ae = ug.insertEdge(a, e, "ae");
        Edge<String> af = ug.insertEdge(a, f, "af");
        Edge<String> bc = ug.insertEdge(b, c, "bc");
        Edge<String> bf = ug.insertEdge(b, f, "bf");
        Edge<String> cd = ug.insertEdge(c, d, "cd");
        Edge<String> cg = ug.insertEdge(c, g, "cg");
        Edge<String> dg = ug.insertEdge(d, g, "dg");
        Edge<String> dh = ug.insertEdge(d, h, "dh");
        Edge<String> ef = ug.insertEdge(e, f, "ef");
        Edge<String> ei = ug.insertEdge(e, i, "ei");
        Edge<String> fi = ug.insertEdge(f, i, "fi");
        Edge<String> gj = ug.insertEdge(g, j, "gj");
        Edge<String> gk = ug.insertEdge(g, k, "gk");
        Edge<String> gl = ug.insertEdge(g, l, "gl");
        Edge<String> hl = ug.insertEdge(h, l, "hl");
        Edge<String> ij = ug.insertEdge(i, j, "ij");
        Edge<String> im = ug.insertEdge(i, m, "im");
        Edge<String> in = ug.insertEdge(i, n, "in");
        Edge<String> jk = ug.insertEdge(j, k, "jk");
        Edge<String> kn = ug.insertEdge(k, n, "kn");
        Edge<String> ko = ug.insertEdge(k, o, "ko");
        Edge<String> lp = ug.insertEdge(l, p, "lp");
        Edge<String> mn = ug.insertEdge(m, n, "mn");

        return ug;
    }

    public static Graph<String, String> getDirectGraph() {
        Graph<String, String> dg = new AdjacencyMapGraph<>(true);

        Vertex<String> bos = dg.insertVertex("bos");
        Vertex<String> jfk = dg.insertVertex("jfk");
        Vertex<String> dfw = dg.insertVertex("dfw");
        Vertex<String> lax = dg.insertVertex("lax");
        Vertex<String> ord = dg.insertVertex("ord");
        Vertex<String> mia = dg.insertVertex("mia");
        Vertex<String> sfo = dg.insertVertex("sfo");

        Edge<String> bosjfk = dg.insertEdge(bos, jfk, "bosjfk");
        Edge<String> bosmia = dg.insertEdge(bos, mia, "bosmia");
        Edge<String> bossfo = dg.insertEdge(bos, sfo, "bossfo");
        Edge<String> jfkbos = dg.insertEdge(jfk, bos, "jfkbos");
        Edge<String> jfkdfw = dg.insertEdge(jfk, dfw, "jfkdfw");
        Edge<String> jfkmia = dg.insertEdge(jfk, mia, "jfkmia");
        Edge<String> jfksfo = dg.insertEdge(jfk, sfo, "jkfsfo");
        Edge<String> dfwlax = dg.insertEdge(dfw, lax, "dfwlax");
        Edge<String> dfword = dg.insertEdge(dfw, ord, "dfword");
        Edge<String> dfwsfo = dg.insertEdge(dfw, sfo, "dfwsfo");
        Edge<String> laxord = dg.insertEdge(lax, ord, "laxord");
        Edge<String> orddfw = dg.insertEdge(ord, dfw, "orddfw");
        Edge<String> ordmia = dg.insertEdge(ord, mia, "ordmia");
        Edge<String> miadfw = dg.insertEdge(mia, dfw, "miadfw");
        Edge<String> mialax = dg.insertEdge(mia, lax, "mialax");
        Edge<String> sfolax = dg.insertEdge(sfo, lax, "sfolax");

        return dg;
    }

    public static Graph<String, String> getTopologicalSortDirectGraph() {
        Graph<String, String> dg = new AdjacencyMapGraph<>(true);

        Vertex<String> a = dg.insertVertex("a");
        Vertex<String> b = dg.insertVertex("b");
        Vertex<String> c = dg.insertVertex("c");
        Vertex<String> d = dg.insertVertex("d");
        Vertex<String> e = dg.insertVertex("e");
        Vertex<String> f = dg.insertVertex("f");
        Vertex<String> g = dg.insertVertex("g");
        Vertex<String> h = dg.insertVertex("h");

        Edge<String> ac = dg.insertEdge(a, c, "ac");
        Edge<String> ad = dg.insertEdge(a, d, "ad");
        Edge<String> bd = dg.insertEdge(b, d, "bd");
        Edge<String> bf = dg.insertEdge(b, f, "bf");
        Edge<String> cd = dg.insertEdge(c, d, "cd");
        Edge<String> ce = dg.insertEdge(c, e, "ce");
        Edge<String> ch = dg.insertEdge(c, h, "ch");
        Edge<String> df = dg.insertEdge(d, f, "df");
        Edge<String> eg = dg.insertEdge(e, g, "eg");
        Edge<String> fg = dg.insertEdge(f, g, "fg");
        Edge<String> fh = dg.insertEdge(f, h, "fh");
        Edge<String> gh = dg.insertEdge(g, h, "gh");

        return dg;
    }

    public static Graph<String, Integer> getDijkstraUndirectGraph() {
        Graph<String, Integer> dg = new AdjacencyMapGraph<>(false);

        Vertex<String> bwi = dg.insertVertex("bwi");
        Vertex<String> bos = dg.insertVertex("bos");
        Vertex<String> jfk = dg.insertVertex("jfk");
        Vertex<String> dfw = dg.insertVertex("dfw");
        Vertex<String> lax = dg.insertVertex("lax");
        Vertex<String> ord = dg.insertVertex("ord");
        Vertex<String> pvd = dg.insertVertex("pvd");
        Vertex<String> mia = dg.insertVertex("mia");
        Vertex<String> sfo = dg.insertVertex("sfo");

        Edge<Integer> bosjfk = dg.insertEdge(bos, jfk, 187);
        Edge<Integer> bosmia = dg.insertEdge(bos, mia, 1258);
        Edge<Integer> bosord = dg.insertEdge(bos, ord, 867);
        Edge<Integer> bossfo = dg.insertEdge(bos, sfo, 2704);
        Edge<Integer> bwijkf = dg.insertEdge(bwi, jfk, 184);
        Edge<Integer> bwiimia = dg.insertEdge(bwi, mia, 946);
        Edge<Integer> bwiord = dg.insertEdge(bwi, ord, 621);
        Edge<Integer> jfkdfw = dg.insertEdge(jfk, dfw, 1391);
        Edge<Integer> jfkmia = dg.insertEdge(jfk, mia, 1090);
        Edge<Integer> jfkord = dg.insertEdge(jfk, ord, 740);
        Edge<Integer> jfkpvd = dg.insertEdge(jfk, pvd, 144);
        Edge<Integer> dfwlax = dg.insertEdge(dfw, lax, 1235);
        Edge<Integer> dfword = dg.insertEdge(dfw, ord, 802);
        Edge<Integer> dfwsfo = dg.insertEdge(dfw, sfo, 1464);
        Edge<Integer> dfwmia = dg.insertEdge(dfw, mia, 1211);
        Edge<Integer> laxmia = dg.insertEdge(lax, mia, 2342);
        Edge<Integer> laxsfo = dg.insertEdge(lax, sfo, 337);
        Edge<Integer> ordpvd = dg.insertEdge(ord, pvd, 849);
        Edge<Integer> ordsfo = dg.insertEdge(ord, sfo, 1846);

        return dg;
    }
}
