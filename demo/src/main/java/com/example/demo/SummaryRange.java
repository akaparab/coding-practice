package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SummaryRange {
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            // Keep iterating until the next element is one more than the current element.
            while (i + 1 < nums.length && nums[i] + 1 == nums[i + 1]) {
                i++;
            }

            if (start != nums[i]) {
                ranges.add(start + "->" + nums[i]);
            } else {
                ranges.add(String.valueOf(start));
            }
        }

        return ranges;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 7};
        int[] nums1 = {0, 1, 2, 4, 5, 6};
        int[] nums2 = {0, 2, 3, 4, 6, 8, 9};
        SummaryRange sr = new SummaryRange();

        System.out.println(" Range : " + sr.summaryRanges(nums).toString());
        System.out.println(" Range : " + sr.summaryRanges(nums1).toString());
        System.out.println(" Range : " + sr.summaryRanges(nums2).toString());

    }
}
