package com.example.demo.slidingwindow;

import java.util.*;

public class SubArrayTargetSum {

    public int subArraySumTo(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);

        int prefixSum = 0;
        int result = 0;

        for (int num : nums) {
            prefixSum += num;

            int needed = prefixSum - k;

            result += countMap.getOrDefault(needed, 0);

            countMap.put(
                    prefixSum,
                    countMap.getOrDefault(prefixSum, 0) + 1
            );
        }

        return result;
    }

    public List<List<Integer>> subArraySumList(int[] nums, int k) {
        Map<Integer, List<Integer>> prefixMap = new HashMap<>();

        prefixMap.put(0, (List.of(-1)));

        int prefixSum = 0;
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            int needed = prefixSum - k;

            if (prefixMap.containsKey(needed)) {
                for (int startIdx : prefixMap.get(needed)) {
                    List<Integer> subarray = new ArrayList<>();

                    for (int j = startIdx + 1; j <= i; j++) {
                        subarray.add(nums[j]);
                    }
                    list.add(subarray);
                }
            }
            prefixMap.computeIfAbsent(
                    prefixSum, x -> new ArrayList<>()).add(i);
        }

        return list;
    }

    public List<List<Integer>> subarraysWithSumK(int[] nums, int k) {
        List<List<Integer>> result = new ArrayList<>();

        Map<Integer, List<Integer>> prefixMap = new HashMap<>();

        // Handle subarrays starting from index 0
        prefixMap.put(0, new ArrayList<>(Arrays.asList(-1)));

        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            int target = prefixSum - k;

            if (prefixMap.containsKey(target)) {
                for (int startIdx : prefixMap.get(target)) {
                    List<Integer> subarray = new ArrayList<>();

                    for (int j = startIdx + 1; j <= i; j++) {
                        subarray.add(nums[j]);
                    }

                    result.add(subarray);
                }
            }

            prefixMap.computeIfAbsent(prefixSum, x -> new ArrayList<>()).add(i);
        }

        return result;

        /**
         * Time: O(N + M)
         * N = array size
         * M = number of matching subarrays
         * Space: O(N)
         * Returning actual subarray elements
         *
         * Because you copy elements into each result:
         *
         * Time: O(N + totalElementsReturned)
         * Worst case: O(N²) output size
         */
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 3;
        int[] nums1 = {1, 2, 3, -2, 2};
        SubArrayTargetSum test = new SubArrayTargetSum();
        System.out.println(test.subArraySumTo(nums, k));
        List<List<Integer>> list = test.subarraysWithSumK(nums1, k);
        for (List<Integer> l : list) {
            System.out.println(l.toString());
        }

    }

}
