package com.example.demo.graph;

import java.util.Arrays;

public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int rows = image.length;
        int cols = image[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == sr && c == sc) {
                    //visited[r][c] = true;
                    dfs(image, r, c, color, image[r][c]);
                }
            }
        }
        return image;
    }

    private void dfs(int[][] image, int r, int c, int color, int orig) {
        int rows = image.length;
        int cols = image[0].length;
        if (image[r][c] == orig) {
            image[r][c] = color;

            if (r >= 1)
                dfs(image, r - 1, c, color, orig);
            if (r + 1 < rows)
                dfs(image, r + 1, c, color, orig);
            if (c >= 1)
                dfs(image, r, c - 1, color, orig);
            if (c + 1 < cols)
                dfs(image, r, c + 1, color, orig);
        }
    }

    public static void main(String[] args) {
        int[][] image = {{1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}};
        int sr = 1;
        int sc = 1;
        int color = 2;
        FloodFill ff = new FloodFill();
        int[][] image1 = ff.floodFill(image, sr, sc, color);
        for (int[] row : image1) {
            System.out.println(Arrays.toString(row));
        }

    }
}
