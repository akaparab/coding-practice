package com.example.demo.graph;

import java.util.ArrayList;
import java.util.List;

public class MaxSubTreeColor {
    static class Result {
        boolean uniform;
        int color;
        int size;

        Result(boolean uniform, int color, int size) {
            this.uniform = uniform;
            this.color = color;
            this.size = size;
        }
    }

    private List<Integer>[] graph;
    private int[] colors;
    private int answer = 1;

    public int maximumSubtreeSize(int[][] edges, int[] colors) {

        int n = colors.length;
        this.colors = colors;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        // Build tree
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        dfs(0, -1);
        return answer;
    }

    private Result dfs(int node, int parent) {
        boolean uniform = true;
        int size = 1;

        for (int child : graph[node]) {

            if (child == parent)
                continue;

            Result r = dfs(child, node);

            size += r.size;

            if (!r.uniform || r.color != colors[node])
                uniform = false;
        }

        if (uniform)
            answer = Math.max(answer, size);

        return new Result(uniform, colors[node], size);
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}};
        int[] colors = {1, 1, 2, 3};

        MaxSubTreeColor tree = new MaxSubTreeColor();
        System.out.println(tree.maximumSubtreeSize(edges, colors));
    }
}
