package com.example.demo.graph;

public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            if (search(matrix[i], target)) {
                return true;
            }
        }
        return false;
    }

    private boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        SearchMatrix obj = new SearchMatrix();
        System.out.println(obj.searchMatrix(grid, 8));
        System.out.println(obj.searchMatrix(grid, 3));
        System.out.println(obj.searchMatrix(grid, 23));
        System.out.println(obj.searchMatrix(grid, 60));
        System.out.println(obj.searchMatrix(grid, 65));

    }
}
