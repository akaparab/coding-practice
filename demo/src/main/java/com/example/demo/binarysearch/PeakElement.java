package com.example.demo.binarysearch;

/**
 * A peak element is an element that is strictly greater than its neighbors.
 * You must write an algorithm that runs in O(log n) time.
 */
public class PeakElement {
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }

        }
        return start;
    }

    public static void main(String[] args) {
        int nums[] = {1, 2, 1, 3, 5, 6, 4};
        PeakElement obj = new PeakElement();
        System.out.println(obj.findPeakElement(nums));
        System.out.println(obj.findPeakElement(new int[]{1, 2, 3, 1}));


    }
}
