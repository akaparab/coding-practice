package com.example.demo;

import java.util.Arrays;

public class productExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];

        int prod = 1;
        boolean zeroFlag = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                prod = prod * nums[i];
            else {
                if (!zeroFlag) {
                    zeroFlag = true;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                res[i] = prod;
            } else {
                if (zeroFlag) {
                    res[i] = 0;
                } else {
                    res[i] = prod / nums[i];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] nums1 = {-1, 1, 0, -3, 3};
        productExceptSelf obj = new productExceptSelf();
        System.out.println(Arrays.toString(obj.productExceptSelf(nums)));
        System.out.println(Arrays.toString(obj.productExceptSelf(nums1)));
    }
}
