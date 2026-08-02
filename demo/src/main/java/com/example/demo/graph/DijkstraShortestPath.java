package com.example.demo.graph;

import java.util.*;

public class DijkstraShortestPath {

    // Helper class to represent a weighted edge
    public static class Edge {
        int target;
        int weight;

        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Helper class to store nodes in the PriorityQueue
    public static class NodePair implements Comparable<NodePair> {
        int node;
        int distance;

        public NodePair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        // Min-Heap sorting logic: lowest distance prioritized
        @Override
        public int compareTo(NodePair other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static List<Integer> findShortestPath(Map<Integer, List<Edge>> graph, int start, int goal) {
        // Track the shortest distance from start to every node
        Map<Integer, Integer> distances = new HashMap<>();
        // Track parents to reconstruct the path
        Map<Integer, Integer> parentMap = new HashMap<>();
        // Priority Queue elements sorted by distance
        PriorityQueue<NodePair> minHeap = new PriorityQueue<>();

        // Initialize distances to infinity
        for (int node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        // Setup start node
        distances.put(start, 0);
        parentMap.put(start, null);
        minHeap.add(new NodePair(start, 0));

        while (!minHeap.isEmpty()) {
            NodePair current = minHeap.poll();
            int u = current.node;
            int distU = current.distance;

            // Early exit if we reached our destination
            if (u == goal) break;

            // If we found a shorter path to u already, skip this stale entry
            if (distU > distances.getOrDefault(u, Integer.MAX_VALUE)) {
                continue;
            }

            // Process neighbors
            List<Edge> neighbors = graph.getOrDefault(u, new ArrayList<>());
            for (Edge edge : neighbors) {
                int v = edge.target;
                int weight = edge.weight;
                int newDist = distU + weight;

                // Relaxation step
                if (newDist < distances.getOrDefault(v, Integer.MAX_VALUE)) {
                    distances.put(v, newDist);
                    parentMap.put(v, u);
                    minHeap.add(new NodePair(v, newDist));
                }
            }
        }

        // Reconstruct the path if reachable
        if (!distances.containsKey(goal) || distances.get(goal) == Integer.MAX_VALUE) {
            return null;
        }

        List<Integer> path = new ArrayList<>();
        Integer curr = goal;
        while (curr != null) {
            path.add(curr);
            curr = parentMap.get(curr);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        // Graph Representation
        Map<Integer, List<Edge>> graph = new HashMap<>();

        // Initialize nodes
        for (int i = 0; i <= 4; i++) graph.put(i, new ArrayList<>());

        // Add weighted edges: graph.get(source).add(new Edge(target, weight))
        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 1));
        graph.get(2).add(new Edge(1, 2));
        graph.get(1).add(new Edge(3, 1));
        graph.get(2).add(new Edge(3, 5));
        graph.get(3).add(new Edge(4, 3));

        int start = 0;
        int goal = 4;

        List<Integer> path = findShortestPath(graph, start, goal);
        System.out.println("Shortest path from " + start + " to " + goal + ": " + path);
    }
}

