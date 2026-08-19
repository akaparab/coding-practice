package com.example.demo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxSubTreeColor {
    static class Result {
        boolean color;
        int size;

        Result(boolean color, int size) {
            this.color = color;
            this.size = size;
        }
    }

    private Map<Integer, List<Integer>> graph;
    private int[] colors;
    private int answer = 1;

    public int maximumSubtreeSize(int[][] edges, int[] colors) {

        int n = colors.length;
        this.colors = colors;
        // Build graph
        graph = new HashMap<>();

        for (int i = 0; i < colors.length; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(0, -1);
        return answer;
    }

    private Result dfs(int node, int parent) {
        boolean sameColor = true;
        int size = 1;

        for (int child : graph.get(node)) {
            System.out.println("Loop start child: " + child);

            if (child == parent)
                continue;

            Result r = dfs(child, node);
            System.out.println("Right after calling dfs: " + child);
            System.out.println("Result size : " + r.size + "  color : " + r.color);

            size += r.size;

            if (!r.color || colors[child] != colors[node])
                sameColor = false;
            System.out.println("end of iteration: " + child);
        }

        if (sameColor)
            answer = Math.max(answer, size);

        return new Result(sameColor, size);
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}};
        int[] colors = {1, 1, 2, 3};

        MaxSubTreeColor tree = new MaxSubTreeColor();
        System.out.println(tree.maximumSubtreeSize(edges, colors));
    }
}
