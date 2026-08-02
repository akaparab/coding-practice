package com.example.demo.graph;

import java.util.*;

/**
 * Use BFS for all edges have the same weight
 */
public class BFSShortestPath {

    // Method to find the shortest path from start to goal
    public static List<Integer> findShortestPath(Map<Integer, List<Integer>> graph, int start, int goal) {
        // Edge case: start and goal are the same
        if (start == goal) {
            return Collections.singletonList(start);
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        // Maps a child node to its parent node to reconstruct the path later
        Map<Integer, Integer> parentMap = new HashMap<>();

        // Initialize the search
        queue.add(start);
        visited.add(start);
        parentMap.put(start, null); // Start node has no parent

        boolean pathFound = false;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            // If we reached the goal, we can stop searching
            if (currentNode == goal) {
                pathFound = true;
                break;
            }

            // Get neighbors of the current node
            List<Integer> neighbors = graph.getOrDefault(currentNode, new ArrayList<>());
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, currentNode); // Record where we came from
                    queue.add(neighbor);
                }
            }
        }

        // Reconstruct the path if one exists
        if (!pathFound) {
            return null; // Return null if goal is unreachable
        }

        List<Integer> path = new ArrayList<>();
        Integer current = goal;
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }

        // The path was built backwards (goal -> start), so reverse it
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        // Define a sample unweighted graph using an Adjacency List
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3, 4));
        graph.put(2, Arrays.asList(0, 5));
        graph.put(3, Arrays.asList(1, 6));
        graph.put(4, Arrays.asList(1, 6));
        graph.put(5, Arrays.asList(2, 6));
        graph.put(6, Arrays.asList(3, 4, 5));

        int start = 0;
        int goal = 6;

        List<Integer> shortestPath = findShortestPath(graph, start, goal);

        if (shortestPath != null) {
            System.out.println("Shortest path from " + start + " to " + goal + ": " + shortestPath);
        } else {
            System.out.println("No path exists between " + start + " and " + goal);
        }
    }

    // time complexity O(V+E)
    // space complexity O(V)
}