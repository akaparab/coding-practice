package com.example.demo.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class NumIslands {
    /**
     * DFS finding number islands
     * Dirs[][] = {1,0}, (-1, 0}, {0,1}, {0, -1}
     */

    public int numIslandsDFS(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    dfs(grid, r, c);
                }
            }
        }
        return count;
    }

    public int numIslandsDFS1(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        boolean[][] visited =
                new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    count++;
                    dfs1(grid, r, c, visited);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (r < 0 || c < 0 || r >= rows || c >= cols ||
                grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);

    }

    private void dfs1(char[][] grid, int r, int c, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (r < 0 || c < 0 || r >= rows || c >= cols ||
                grid[r][c] == '0' || visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        dfs1(grid, r + 1, c, visited);
        dfs1(grid, r - 1, c, visited);
        dfs1(grid, r, c + 1, visited);
        dfs1(grid, r, c - 1, visited);

    }

    public int numIslandsBFS(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        boolean[][] visited =
                new boolean[rows][cols];
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    count++;
                    visited[r][c] = true;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{r, c});
                    while (!queue.isEmpty()) {
                        int[] vals = queue.poll();
                        for (int[] dir : dirs) {
                            int rn = vals[0] + dir[0];
                            int cn = vals[1] + dir[1];
                            if (rn < 0 || rn >= rows || cn < 0 || cn >= cols ||
                                    grid[rn][cn] == '0' || visited[rn][cn]) {
                                continue;
                            }
                            if (grid[rn][cn] == '1') {
                                visited[rn][cn] = true;
                                queue.offer(new int[]{rn, cn});
                            }

                        }

                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        NumIslands ni = new NumIslands();

        System.out.println(ni.numIslandsDFS(grid));
        char[][] grid1 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '1', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(ni.numIslandsDFS1(grid1));
        System.out.println(ni.numIslandsBFS(grid1));

    }

}
