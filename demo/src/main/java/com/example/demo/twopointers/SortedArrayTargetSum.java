package com.example.demo.twopointers;

import java.util.Arrays;

public class SortedArrayTargetSum {

    public int[] twoSum(int[] numbers, int target) {
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
        int target = 9;
        SortedArrayTargetSum rs = new SortedArrayTargetSum();
        System.out.println(Arrays.toString(rs.twoSum(nums, target)));
    }
}
