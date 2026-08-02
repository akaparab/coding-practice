package com.example.demo;

import java.util.Arrays;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        boolean flag = false;
        int carryOver = 0;
        while (i >= 0) {
            if (i == digits.length - 1) {
                if (digits[i] < 9) {
                    digits[i] += 1;
                } else {
                    carryOver = 1;
                    digits[i] = 0;
                }
            } else {
                if (carryOver + digits[i] == 10) {
                    digits[i] = 0;
                    carryOver = 1;
                } else {
                    if (carryOver == 1) {
                        digits[i] += 1;
                    }
                    carryOver = 0;
                }
            }

            i--;
        }
        if (carryOver == 1) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            int j = 1;
            for (i = 0; i < digits.length; i++) {
                res[j++] = digits[i];
            }
            return res;
        }
        return digits;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] nums3 = {9};
        int[] nums1 = {1, 2, 9};
        int[] nums2 = {9, 9, 9};
        PlusOne po = new PlusOne();
        System.out.println("Result : " + Arrays.toString(po.plusOne(nums)));
        System.out.println("Result : " + Arrays.toString(po.plusOne(nums3)));
        System.out.println("Result : " + Arrays.toString(po.plusOne(nums1)));
        System.out.println("Result : " + Arrays.toString(po.plusOne(nums2)));

        // Time Complexity: O(n) - processes each digit once in worst case
        //Space Complexity: O(n) - creates new array only when all digits are 9
    }
}
