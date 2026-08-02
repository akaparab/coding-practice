package com.example.demo.dp;

import java.util.Arrays;

public class LongestIncreaseSubSeq {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);
        int n = nums.length;
        int res = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            res = Math.max(dp[i], res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        
        LongestIncreaseSubSeq obj = new LongestIncreaseSubSeq();
        System.out.println(obj.lengthOfLIS(nums));

    }
}
