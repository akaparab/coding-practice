package com.example.demo.graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPath {
    public int shortestPathLength(int[][] graph) {

        int n = graph.length;

        // Bitmask where all n nodes are visited.
        // Example: n = 4
        // targetMask = 1111
        int targetMask = (1 << n) - 1;

        // Queue stores:
        // [currentNode, visitedMask]
        Queue<int[]> q = new LinkedList<>();

        // visited[node][mask]
        // Tracks whether we have already reached 'node'
        // with this exact set of visited nodes.
        boolean[][] visited = new boolean[n][1 << n];

        // We can start from ANY node.
        for (int node = 0; node < n; node++) {

            // Only this node has been visited.
            int mask = 1 << node;

            q.offer(new int[]{node, mask});

            visited[node][mask] = true;
        }

        int distance = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            // Process one BFS level at a time.
            for (int i = 0; i < size; i++) {

                int[] current = q.poll();

                int node = current[0];
                int mask = current[1];

                // All nodes have been visited.
                if (mask == targetMask) {
                    return distance;
                }

                // Visit all neighbors.
                for (int neighbor : graph[node]) {

                    // Add neighbor to the visited set.
                    int newMask =
                            mask | (1 << neighbor);

                    // Have we already seen this exact state?
                    if (!visited[neighbor][newMask]) {

                        visited[neighbor][newMask] = true;

                        q.offer(
                                new int[]{
                                        neighbor,
                                        newMask
                                }
                        );
                    }
                }
            }

            // Move to the next BFS level.
            distance++;
        }
        return distance;
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 2, 3}, {0}, {0}, {0}};
        ShortestPath obj = new ShortestPath();
        System.out.println(obj.shortestPathLength(graph));

    }
}
