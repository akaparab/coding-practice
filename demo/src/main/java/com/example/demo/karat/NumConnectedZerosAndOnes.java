package com.example.demo.karat;

public class NumConnectedZerosAndOnes {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int getConnectedZerosAndOnes(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int count = 0;
        boolean[][] visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!visited[r][c]) {
                    count++;
                    dfs(grid, r, c, visited, grid[r][c]);
                }
            }
        }
        return count;

    }

    private void dfs(char[][] grid, int r, int c, boolean[][] visited, char input) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (r < 0 || r >= rows || c < 0 || c >= cols ||
                visited[r][c] || grid[r][c] != input) {
            return;
        }
        visited[r][c] = true;

        dfs(grid, r + 1, c, visited, input);
        dfs(grid, r - 1, c, visited, input);
        dfs(grid, r, c + 1, visited, input);
        dfs(grid, r, c - 1, visited, input);

    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        NumConnectedZerosAndOnes nc = new NumConnectedZerosAndOnes();
        System.out.println(nc.getConnectedZerosAndOnes(grid));
    }
}
