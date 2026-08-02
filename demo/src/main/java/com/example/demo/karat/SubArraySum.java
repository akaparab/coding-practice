package com.example.demo.karat;

import com.example.demo.slidingwindow.SubArrayTargetSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubArraySum {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;

        // Step 1: Build the prefix sum array
        int[] prefix = new int[n + 1];
        prefix[0] = 0;
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Step 2: Use a map to solve like two-sum on prefix sums
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // handle subarrays starting at index 0
        int count = 0;

        for (int j = 1; j <= n; j++) {
            int current = prefix[j];
            // Check how many previous prefix sums differ by k
            count += map.getOrDefault(current - k, 0);
            // Add current prefix sum to map
            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        return count;
    }

    public int subarraySum1(int[] nums, int k) {
        int n = nums.length;

        // Step 1: Build the prefix sum array
        int[] prefix = new int[n + 1];
        prefix[0] = 0;
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Step 2: Use a map to solve like two-sum on prefix sums
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // handle subarrays starting at index 0
        int count = 0;

        for (int j = 1; j <= n; j++) {
            int current = prefix[j];
            // Check how many previous prefix sums differ by k
            if (map.containsKey(current - k)) {
                count++;
            }
            // Add current prefix sum to map
            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        return count;
    }

    public List<List<Integer>> subarraySum2(int[] nums, int k) {
        int n = nums.length;

        // Step 1: Build the prefix sum array
        int[] prefix = new int[n + 1];
        prefix[0] = 0;
//        for (int i = 0; i < n; i++) {
//            prefix[i + 1] = prefix[i] + nums[i];
//        }
        List<List<Integer>> res = new ArrayList<>();

        // Step 2: Use a map to solve like two-sum on prefix sums
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, List.of(-1)); // handle subarrays starting at index 0
        int current = 0;

        for (int i = 0; i < n; i++) {
            current += nums[i];
            int target = current - k;
            // Check how many previous prefix sums differ by k
            if (map.containsKey(target)) {
                List<Integer> list = map.get(target);

                for (int startIndex : list) {
                    List<Integer> subList = new ArrayList<>();
                    for (int j = startIndex + 1; j <= i; j++) {
                        subList.add(nums[j]);
                    }
                    res.add(subList);
                }

            }
            // Add current prefix sum to map
            //map.put(current, map.getOrDefault(current, 0) + 1);
            map.computeIfAbsent(current, x -> new ArrayList<>()).add(i);
        }

        return res;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 3;
        SubArraySum test = new SubArraySum();
        //    System.out.println(test.subarraySum(nums, k));
        // System.out.println(test.subarraySum1(nums, k));
        int[] nums1 = {3, 4, 7, 2, -3, 1, 4, 2};
        int k1 = 7;
        System.out.println(test.subarraySum1(nums1, k1));
        List<List<Integer>> res = test.subarraySum2(nums1, k1);
        for (List<Integer> list : res) {
            System.out.println(list.toString());
        }

    }


}
