package com.example.demo.graph;

public class LongestPath {
    public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int count = 0;
        boolean[][] visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                count = Math.max(count, dfs(r, c, matrix));
            }
        }
        return count;
    }

    private int dfs(int r, int c, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int res = 0;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int ans = 0;
        for (int[] d : dirs) {
            int x = r + d[0], y = c + d[1];
            if (0 <= x && x < rows && 0 <= y && y < cols && matrix[x][y] > matrix[r][c])
                ans = Math.max(ans, dfs(x, y, matrix));
        }
        return ++ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        LongestPath obj = new LongestPath();
        System.out.println(obj.longestIncreasingPath(matrix));

    }

}
