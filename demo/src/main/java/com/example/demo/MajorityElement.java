package com.example.demo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 */
public class MajorityElement {
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }

        for (int num : nums) {
            int val = map.getOrDefault(num, 0);
            map.put(num, val + 1);
        }

        // Go through map
        Map.Entry<Integer, Integer> maxEntry = map.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(); // Throws NoSuchElementException if map is empty

        return maxEntry.getKey();
    }

    public int majorityElement(int[] nums) {
        int candidate = 0;  // Will hold our current candidate
        int count = 0;      // Tracks the "votes" for candidate

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            // Update count based on whether num matches candidate
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        MajorityElement me = new MajorityElement();
        System.out.println("Majority Num: " + me.majorityElement(nums));
        int[] nums1 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Majority Num: " + me.majorityElement(nums1));
        int[] nums2 = {1, 1, 1, 2, 3, 4};
        System.out.println("Majority Num: " + me.majorityElement(nums2));
        int[] nums3 = {1, 2, 3, 5, 5, 5, 5, 2};
        System.out.println("Majority Num: " + me.majorityElement(nums3));

        // Time complexity O(1)
        // Space complexity O(1)

    }
}
