package com.example.demo.graph;

public class WordSearch {

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (dfs(board, r, c, visited, word, 0)) {
                    return true;
                }

            }
        }
        return false;

    }

    private boolean dfs(char[][] board, int r, int c, boolean[][] visited, String word, int len) {
        if (len >= word.length())
            return true;
        int rows = board.length;
        int cols = board[0].length;
        if (r < 0 || r >= rows || c < 0 || c >= cols || visited[r][c] ||
                word.charAt(len) != board[r][c]) {
            return false;
        }
        visited[r][c] = true;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : dirs) {
            int ridx = r + dir[0];
            int cidx = c + dir[1];
            boolean res = dfs(board, ridx, cidx, visited, word, len + 1);
            if (res) return res;
        }
        visited[r][c] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        WordSearch ws = new WordSearch();
        System.out.println(ws.exist(board, word));

    }
}
