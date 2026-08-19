package com.example.demo.binarysearch;

public class SearchRotatedArray {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;

        // find boundaries
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // check left side of the boundary
        int answer = binarySearch(nums, 0, left - 1, target);
        if (answer != -1) {
            return answer;
        }

        // Find right side of the array
        return binarySearch(nums, left, n - 1, target);
    }

    // search target
    private int binarySearch(
            int[] nums,
            int leftBoundary,
            int rightBoundary,
            int target
    ) {
        int left = leftBoundary, right = rightBoundary;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        SearchRotatedArray obj = new SearchRotatedArray();
        System.out.println(obj.search(nums, target));

    }
}
