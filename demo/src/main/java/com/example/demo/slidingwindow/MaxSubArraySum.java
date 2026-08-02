package com.example.demo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MaxSubArraySum {
    // Number should not repeat in the subarray
    public static int maxSubarraySum(int[] arr, int k) {
        int maxSum = 0;
        int j = 0;
        int sum = 0;

        for (int i = 0; i < arr.length - k; i++) {
            sum = 0;
            for (j = i; j < k + i; j++) {
                if (i != 0 && arr[j - 1] != arr[j])
                    sum += arr[j];
            }
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;

    }

    // // Number should not repeat in the subarray - sliding window
    public static Long maxSubarraySum1(int[] nums, int k) {
        Long maxSum = Long.MIN_VALUE;
        long sum = 0;
        int start = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            sum += nums[i];

            if (i - start + 1 == k) {
                if (map.size() == k) {
                    maxSum = Math.max(sum, maxSum);
                }
                sum -= nums[start];
                map.put(nums[start], map.getOrDefault(nums[start], 0) - 1);
                if (map.get(nums[start]) == 0) {
                    map.remove(nums[start]);
                }
                start++;
            }
        }
        return maxSum == Long.MIN_VALUE ? 0 : maxSum;

    }

    public static int maxSum(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (i - start + 1 == k) {
                maxSum = Math.max(maxSum, sum);
                sum -= nums[start];
                start += 1;
            }
        }
        return maxSum;

    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        int[] nums1 = {1, 5, 4, 2, 9, 9, 9};
        int k = 4;
        int k1 = 3;
        int[] nums2 = {4, 4, 4};
        int[] nums3 = {2, 1, 5, 1, 3, 2};

        System.out.println(MaxSubArraySum.maxSubarraySum(nums, k));
        System.out.println(MaxSubArraySum.maxSubarraySum(nums1, k1));
        System.out.println(MaxSubArraySum.maxSubarraySum(nums2, k1));
        System.out.println(MaxSubArraySum.maxSubarraySum(nums3, k1));

        System.out.println(MaxSubArraySum.maxSubarraySum1(nums, k));
        System.out.println(MaxSubArraySum.maxSubarraySum1(nums1, k1));
        System.out.println(MaxSubArraySum.maxSubarraySum1(nums2, k1));
        System.out.println(MaxSubArraySum.maxSubarraySum1(nums3, k1));

        System.out.println(MaxSubArraySum.maxSum(nums, k));
        System.out.println(MaxSubArraySum.maxSum(nums1, k1));
        System.out.println(MaxSubArraySum.maxSum(nums2, k1));
        System.out.println(MaxSubArraySum.maxSum(nums3, k1));
    }
}
