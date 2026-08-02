package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return true if there are two distinct
 * indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 */
public class ConainsDuplicate {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }

            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        int[] nums1 = {1, 0, 1, 1};
        int k1 = 1;
        int[] nums2 = {1, 2, 3, 1, 2, 3};
        int k2 = 2;

        ConainsDuplicate cd = new ConainsDuplicate();
        System.out.println("has duplicates in range : " + cd.containsNearbyDuplicate(nums, k));
        System.out.println("has duplicates in range : " + cd.containsNearbyDuplicate(nums1, k1));
        System.out.println("has duplicates in range : " + cd.containsNearbyDuplicate(nums2, k2));
    }
}
