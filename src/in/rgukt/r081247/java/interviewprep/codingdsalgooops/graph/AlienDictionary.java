package in.rgukt.r081247.java.interviewprep.codingdsalgooops.graph;

import java.util.*;
import java.util.stream.Collectors;

public class AlienDictionary {

    public static void main(String[] args) {
        List<String> words = List.of("wrt", "wrf", "er", "ett", "rftt");
        long start = System.currentTimeMillis();
        String letters = Solution1.alienDictionary(words);
        System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println(letters);
    }

    /*
     * Approach   : chars of a word not in order, the words are in order,
     * find adjacency list of each unique char by iterating through adjacent words and finding first chars that are different,
     * run topologicalsort on graph and do loop detection;
     *
     * Complexity : Time: O(nodes + edges) ; Space: O(nodes + edges)
     */
    public static class Solution1 {
        public static String alienDictionary(List<String> words) {
            if(words.size() <= 1)
                throw new IllegalArgumentException("words must be more than 1");

            Map<Character, List<Set<Character>>> adjacencyMap = new HashMap<>();
            for(int i = 0; i < words.size() - 1; i++)
                addEdgetoAdjacentyMap(words.get(i), words.get(i+1), adjacencyMap);

            Map<Character, Integer> indegreeCount = new HashMap<>();
            Deque<Character> zeroIndegreeVertices = new LinkedList<>();
            Deque<Character> topologicalOrder = new LinkedList<>();
            for(Map.Entry<Character, List<Set<Character>>> entry: adjacencyMap.entrySet()) {
                indegreeCount.put(entry.getKey(), entry.getValue().get(0).size());
                if(indegreeCount.get(entry.getKey()) == 0)
                    zeroIndegreeVertices.offer(entry.getKey());
            }
            while(!zeroIndegreeVertices.isEmpty()) {
                Character c = zeroIndegreeVertices.poll();
                topologicalOrder.offer(c);
                for(Character outgoing: adjacencyMap.get(c).get(1)) {
                    indegreeCount.put(outgoing, indegreeCount.get(outgoing)-1);
                    if(indegreeCount.get(outgoing) == 0)
                        zeroIndegreeVertices.offer(outgoing);
                }
            }
            if(topologicalOrder.size() != indegreeCount.size())
                return "";
            StringBuilder sb = new StringBuilder();
            topologicalOrder.forEach(c -> sb.append(c));
            return sb.toString();
        }

        private static void addEdgetoAdjacentyMap(String s1, String s2, Map<Character, List<Set<Character>>> adjacentyMap) {
            for(int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
                if(s1.charAt(i) != s2.charAt(i)) {
                    Character c1 = s1.charAt(i);
                    Character c2 = s2.charAt(i);
                    if(!adjacentyMap.containsKey(c1)) {
                        adjacentyMap.put(c1, new ArrayList<>(2));
                        adjacentyMap.get(c1).add(new HashSet<>());
                        adjacentyMap.get(c1).add(new HashSet<>());
                    }
                    if(!adjacentyMap.containsKey(c2)) {
                        adjacentyMap.put(c2, new ArrayList<>(2));
                        adjacentyMap.get(c2).add(new HashSet<>());
                        adjacentyMap.get(c2).add(new HashSet<>());
                    }
                    adjacentyMap.get(c1).get(1).add(c2);
                    adjacentyMap.get(c2).get(0).add(c1);
                    break;
                }
            }
        }
    }
}
