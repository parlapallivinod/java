package in.rgukt.r081247.java.interviewprep.codingdsalgooops.graph;

import java.util.*;

public class CloneGraph {
    public static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /*
     * Approach   : Depth First Search
     * Complexity : Time: O(nodes + edges) ; Space: O(nodes)
     */
    public static class Solution1 {
        public Node cloneGraph(Node node) {
            Map<Integer, Node> clonedNodes = new HashMap<>();
            if (node == null)
                return null;
            return cloneGraphNode(node, clonedNodes);
        }

        public Node cloneGraphNode(Node node, Map<Integer, Node> clonedNodes) {
            if (clonedNodes.containsKey(node.val))
                return clonedNodes.get(node.val);

            Node clonedNode = new Node(node.val);
            clonedNodes.put(clonedNode.val, clonedNode);

            for(Node neighbor: node.neighbors) {
                Node clonedNeighbor = cloneGraphNode(neighbor, clonedNodes);
                clonedNode.neighbors.add(clonedNeighbor);
            }

            return clonedNode;
        }

    }

    /*
     * Approach   : BFS
     * Complexity : Time: O(nodes + edges) ; Space: O(n)
     */
    public static class Solution2 {
        public Node cloneGraph(Node node) {

            if (node == null) return null;
            Map<Node, Node> map = new HashMap<>();
            Queue<Node> queue = new LinkedList();

            map.put(node, new Node(node.val));
            queue.offer(node);


            while (!queue.isEmpty()) { //N
                Node head = queue.poll();
                for (Node neighbor : head.neighbors) {//E
                    if (!map.containsKey(neighbor)) {
                        map.put(neighbor, new Node(neighbor.val));
                        queue.offer(neighbor);
                    }
                    map.get(head).neighbors.add(map.get(neighbor));
                }
            }
            return map.get(node);
        }
    }
}
