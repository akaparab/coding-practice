package com.example.demo.twopointers;

import java.util.*;

/**
 * Find list of indices, 3 nums elements sum = 0
 */

public class ThreeSum {

    public List<List<Integer>> threeSumToZero(int[] nums) {
        // {-1, -1, -1 0, 1, 2}
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[j] + nums[k] + nums[i];
                if (sum > 0) {
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    List<Integer> list = List.of(nums[i], nums[j], nums[k]);
                    lists.add(list);
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    while (j < k && nums[k] == nums[k - 1]) k--;
                    j++;
                    k--;
                }
            }
        }
        return lists;
    }

    public List<List<Integer>> threeSumToTarget(int[] nums, int target) {
        // {-1, -1, -1 0, 1, 2}
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[j] + nums[k] + nums[i];

                if (sum > target) {
                    k--;
                } else if (sum < target) {
                    j++;
                } else {
                    List<Integer> list = List.of(nums[i], nums[j], nums[k]);
                    lists.add(list);
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    while (j < k && nums[k] == nums[k - 1]) k--;
                    j++;
                    k--;
                }
            }
        }
        return lists;
    }


    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -1};
        ThreeSum ts = new ThreeSum();
        List<List<Integer>> lists = ts.threeSumToZero(nums);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }

        int[] nums1 = {1, 4, 45, 6, 10, 8};
        int target = 13;

        List<List<Integer>> lists1 = ts.threeSumToTarget(nums1, target);
        for (List<Integer> list : lists1) {
            System.out.println(list.toString());
        }
    }


}
