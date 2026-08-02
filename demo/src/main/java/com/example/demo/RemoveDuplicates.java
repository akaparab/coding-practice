package com.example.demo;

import java.util.Arrays;

public class RemoveDuplicates {
    public int removeDuplicates1(int[] nums) {
        int i = 1;
        int k = i;
        if (nums.length == 0 || nums.length == 1)
            return nums.length;

        int result = 1;

        while (i < nums.length) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
            i++;
        }
        System.out.println(Arrays.toString(nums));
        return k;
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums.length == 1)
            return nums.length;

        int i = 1;
        int k = 1; // Pointer for placing unique elements

        while (i < nums.length) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i]; // Place unique element at position k
                k++;
            }
            i++;
        }

        return k; // k represents the count of unique elements
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        RemoveDuplicates rd = new RemoveDuplicates();
        System.out.println("Number of elements: " + rd.removeDuplicates(nums));
        int[] nums1 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("Number of elements: " + rd.removeDuplicates(nums1));

        int[] nums2 = {1, 1, 2, 2, 3};
        System.out.println("Number of elements: " + rd.removeDuplicates(nums2));
        //Time Complexity: O(n)
        //Space Complexity: O(1)
    }
}
