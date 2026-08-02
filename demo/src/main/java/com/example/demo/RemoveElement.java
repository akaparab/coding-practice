package com.example.demo;

import java.util.Arrays;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int k = 0; // Position to place next valid element

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k++] = nums[i]; // Copy valid element to front
            }
        }
        System.out.println(Arrays.toString(nums));
        return k; // k is the new length
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        RemoveElement re = new RemoveElement();
        System.out.println("Num Elements : " + re.removeElement(nums, val));


        int[] nums1 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val1 = 2;
        System.out.println("Num Elements : " + re.removeElement(nums1, val1));

        // Time Complexity: O(1)
        // Space Complexity: O(1)
    }
}
