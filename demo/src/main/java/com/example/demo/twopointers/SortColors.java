package com.example.demo.twopointers;

import java.util.Arrays;

public class SortColors {

    public void sortColors(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int zerosIndex = 0;
        while (i <= j) {
            if (nums[i] == 0) {
                int tmp = nums[i];
                nums[i] = nums[zerosIndex];
                nums[zerosIndex] = tmp;
                zerosIndex++;
                i++;
            } else if (nums[i] == 2) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                j--;
            } else {
                i++;
            }

        }
    }

    public static void main(String[] args) {
        // 2,0,4,0,9
        int[] nums = {2, 1, 2, 0, 1, 0, 1, 0, 1};
        SortColors mz = new SortColors();
        mz.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
