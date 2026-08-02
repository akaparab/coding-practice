package com.example.demo.karat;

public class WordSearch {
    private char[][] board;
    private int rows;
    private int cols;
    private String word;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;
        this.word = word;
        boolean[][] visited = new boolean[rows][cols];

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                if (this.backtrack(row, col, 0, visited)) {
                    return true;
                }
        return false;
    }

    protected boolean backtrack(int row, int col, int index, boolean[][] visited) {
        if (index == word.length()) return true;
        //System.out.println("board[" + row + "]" + "[" + col + "]" + " word[" + index + "]=" + word.charAt(index));
        /* Step 1). check the bottom case. */


        /* Step 2). Check the boundaries. */
        if (row < 0 || row == rows || col < 0 || col == cols ||
                this.board[row][col] != word.charAt(index))
            return false;

        /* Step 3). explore the neighbors in DFS */
        // mark the path before the next exploration
        visited[row][col] = true;

        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        for (int d = 0; d < 4; ++d) {
            if (this.backtrack(
                    row + rowOffsets[d],
                    col + colOffsets[d],
                    index + 1, visited
            )) {
                visited[row][col] = false;
                return true;
            }
        }

        /* Step 4). clean up and return the result. */
        visited[row][col] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        String word1 = "ABCCEX";
        WordSearch ws = new WordSearch();
        System.out.println(ws.exist(board, word));
        System.out.println(ws.exist(board, word1));
    }
}
