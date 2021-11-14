package in.rgukt.r081247.java.datastructure.graph;

import in.rgukt.r081247.java.datastructure.list.LinkedPositionalList;
import in.rgukt.r081247.java.datastructure.list.PositionalList;

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
}
