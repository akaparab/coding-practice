package com.example.demo.graph;

public class NodesConnected {

    public int isConnected(int[][] grid) {

        int n = grid.length;

        boolean[] visited = new boolean[n];

        int components = 0;

        // Try starting DFS from every node
        for (int i = 0; i < n; i++) {

            // If this node has not been visited,
            // it represents a new connected component.
            if (!visited[i]) {

                components++;

                dfs(i, grid, visited);
            }
        }

        return components;
    }

    private static void dfs(
            int node,
            int[][] isConnected,
            boolean[] visited) {

        // Mark current node as visited
        visited[node] = true;

        // Check all possible neighbors
        for (int neighbor = 0;
             neighbor < isConnected.length;
             neighbor++) {

            // If directly connected
            // and not visited yet
            if (isConnected[node][neighbor] == 1
                    && !visited[neighbor]) {

                dfs(
                        neighbor,
                        isConnected,
                        visited
                );
            }
        }
    }


    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        NodesConnected obj = new NodesConnected();
        System.out.println(obj.isConnected(grid));

    }

}
