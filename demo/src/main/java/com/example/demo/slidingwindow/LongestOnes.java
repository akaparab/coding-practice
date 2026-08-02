package com.example.demo.slidingwindow;

public class LongestOnes {
    public int longestOnes(int[] nums, int k) {
        int maxCount = 0;
        int start = 0;
        int zeroCount = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            }

            while (zeroCount > k) {
                if (nums[start] == 0) {
                    zeroCount--;
                }
                start++;
            }
            maxCount = Math.max(maxCount, i - start + 1);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        LongestOnes obj = new LongestOnes();
        System.out.println(obj.longestOnes(nums, k));
        System.out.println(obj.longestOnes(nums, 3));
    }
}
