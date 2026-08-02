package com.example.demo.graph;

import java.util.*;

public class CloneGraph {
    static class Node {
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

    public Node cloneGraph(Node node) {
        Map<Node, Node> clonedMap = new HashMap<>();

        clonedMap.put(node, new Node(node.val));

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            List<Node> neighbors = curr.neighbors;
            for (Node n : neighbors) {
                if (clonedMap.get(n) != null) {
                    clonedMap.put(n, new Node(n.val));
                }
                clonedMap.get(curr).neighbors.add(clonedMap.get(n));
                queue.add(n);
            }
        }
        return clonedMap.get(node);
    }

    public static void main(String[] args) {
        int[][] adjList = {{2, 4}, {1, 3}, {2, 4}, {1, 3}};
    }
}
