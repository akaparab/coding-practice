package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSumUnique {
    public List<List<Integer>> twoSumUnique(
            int[] nums,
            int target) {

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int sum = nums[left] + nums[right];

            if (sum == target) {

                result.add(
                        Arrays.asList(
                                nums[left],
                                nums[right]
                        )
                );

                // Skip duplicate left values
                while (left < right &&
                        nums[left] == nums[left + 1]) {
                    left++;
                }

                // Skip duplicate right values
                while (left < right &&
                        nums[right] == nums[right - 1]) {
                    right--;
                }

                left++;
                right--;

            } else if (sum < target) {

                left++;

            } else {

                right--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 4, 4, 5};
        TwoSumUnique obj = new TwoSumUnique();
        System.out.println((obj.twoSumUnique(nums, 6).toString()));
    }
}
