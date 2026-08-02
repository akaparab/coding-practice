package com.example.demo.graph;

import java.util.*;

public class NearestDistributionCenter {

    private static final int[][] DIRECTIONS = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public static Map<String, Integer> nearestWarehouse(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] distance = new int[rows][cols];

        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new LinkedList<>();

        // Multi-source BFS initialization
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    queue.offer(new int[]{r, c});
                    distance[r][c] = 0;
                }
            }
        }

        // BFS from all warehouses
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow < 0 || newRow >= rows ||
                        newCol < 0 || newCol >= cols ||
                        distance[newRow][newCol] != -1) {
                    continue;
                }

                distance[newRow][newCol] = distance[row][col] + 1;
                queue.offer(new int[]{newRow, newCol});
            }
        }

        Map<String, Integer> result = new HashMap<>();

        // Collect distances for customers only
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    result.put(
                            "(" + r + "," + c + ")",
                            distance[r][c]
                    );
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[][] grid = {
                {0, 1, 0, 0},
                {0, 0, 0, 2},
                {2, 0, 1, 0}
        };

        Map<String, Integer> result = nearestWarehouse(grid);

        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(
                    "Customer " + entry.getKey() +
                            " -> " + entry.getValue()
            );
        }
    }
}
