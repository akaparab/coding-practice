package com.example.demo.tree;

public class MaximumValueSum {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {

        long originalSum = 0;

        long even = 0;
        long odd = Long.MIN_VALUE / 2; // Avoid overflow

        for (int num : nums) {

            originalSum += num;

            long gain = (num ^ k) - num;

            long newEven = Math.max(even, odd + gain);
            long newOdd = Math.max(odd, even + gain);

            even = newEven;
            odd = newOdd;
        }

        return originalSum + even;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        int k = 3;
        int[][] edges = {{0, 1}, {0, 2}};

        MaximumValueSum obj = new MaximumValueSum();
        System.out.println(obj.maximumValueSum(nums, k, edges));

    }

}

