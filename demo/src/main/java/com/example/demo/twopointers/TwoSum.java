package com.example.demo.twopointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);

        }
        return new int[]{};
    }

    public boolean twoSum2(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            if (nums[i] + nums[j] == target) {
                return true;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public int[] twoSum1(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                return new int[]{i + 1, j + 1};
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] nums1 = {7, 11, 15, 2};
        int target = 9;
        TwoSum ts = new TwoSum();
        System.out.println("Indices = " + Arrays.toString(ts.twoSum(nums, target)));
        System.out.println("Indices = " + Arrays.toString(ts.twoSum(nums1, target)));

        // Time Complexity: O(n) - iterates ones all the elements
        // space Complexity: O(n) - hashmap stores n elements worse case
    }

}
