package com.example.demo.graph;

import java.util.*;

public class CloneGraph {

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        Map<Node, Node> clonedMap = new HashMap<>();

        clonedMap.put(node, new Node(node.val));

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            for (Node n : curr.neighbors) {

                if (!clonedMap.containsKey(n)) {
                    clonedMap.put(n, new Node(n.val));
                    queue.add(n);
                }

                clonedMap.get(curr).neighbors.add(clonedMap.get(n));
            }
        }

        return clonedMap.get(node);
    }

    public static void main(String[] args) {

        CloneGraph solution = new CloneGraph();

        // Create graph:
        //
        //       1
        //      / \
        //     2---3
        //      \ /
        //       4
        //
        // Actually we'll create:
        //
        // 1 -- 2
        // |    |
        // 4 -- 3

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // Add edges
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        // Clone graph
        Node clonedNode = solution.cloneGraph(node1);

        // Print original graph
        System.out.println("Original Graph:");
        printGraph(node1);

        // Print cloned graph
        System.out.println("\nCloned Graph:");
        printGraph(clonedNode);

        // Verify clone is a different object
        System.out.println(
                "\nOriginal node == Cloned node: " + (node1 == clonedNode)
        );

        System.out.println(
                "Original node value: " + node1.val
        );

        System.out.println(
                "Cloned node value: " + clonedNode.val
        );
    }

    private static void printGraph(Node node) {

        if (node == null) {
            return;
        }

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(node);
        visited.add(node);

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            System.out.print(curr.val + " -> ");

            for (Node neighbor : curr.neighbors) {

                System.out.print(neighbor.val + " ");

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }

            System.out.println();
        }
    }
}