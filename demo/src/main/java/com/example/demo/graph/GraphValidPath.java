package com.example.demo.graph;

import java.util.*;

/**
 * Graph is bi-directional
 */
public class GraphValidPath {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // build tree
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        int size = edges.length;

        for (int i = 0; i < size; i++) {
            int source1 = edges[i][0];
            int destination1 = edges[i][1];

            map.computeIfAbsent(source1, k -> new ArrayList<>()).add(destination1);
            map.computeIfAbsent(destination1, k -> new ArrayList<>()).add(source1);
        }


        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            Integer edge = queue.poll();
            if (destination == edge) {
                return true;
            }

            if (map.get(edge) != null) {
                for (int k : map.get(edge)) {
                    if (!visited.contains(k)) {

                        visited.add(k);
                        queue.offer(k);
                    }
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        GraphValidPath gvp = new GraphValidPath();
        int n = 3;
        int source = 0;
        int destination = 2;
        System.out.println(gvp.validPath(n, edges, source, destination));

        int[][] edges1 = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        System.out.println(gvp.validPath(6, edges1, source, 5));

    }
}
