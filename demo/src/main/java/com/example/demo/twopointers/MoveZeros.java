package com.example.demo.twopointers;

import java.util.Arrays;

public class MoveZeros {
    public void moveZeroes1(int[] nums) {

        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            if (nums[i] == 0) {
                if (nums[i + 1] != 0) {
                    nums[i] = nums[i + 1];
                    nums[i + 1] = 0;
                }
            }
            if (nums[j] != 0) {
                if (nums[j - 1] == 0) {
                    nums[j - 1] = nums[j];
                    nums[j] = 0;
                }
            }
            i++;
            j--;
        }
        System.out.println(Arrays.toString(nums));
    }

    public void moveZeroes(int[] nums) {
        int nextNonZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[nextNonZero];
                nums[nextNonZero] = nums[i];
                nums[i] = temp;
                nextNonZero++;
            }
        }
    }

    public static void main(String[] args) {
        // 2,0,4,0,9
        int[] nums = {2, 0, 4, 0, 9};
        MoveZeros mz = new MoveZeros();
        mz.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
