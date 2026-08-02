package com.example.demo.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TriangleNumbers {
    public Integer triangleNumber(int[] nums) {
        // {4, 6, 9, 11, 15, 18}
        Arrays.sort(nums);
        int count = 0;

        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int[] nums = {11, 4, 9, 6, 15, 18};
        TriangleNumbers ts = new TriangleNumbers();
        int count = ts.triangleNumber(nums);
        System.out.println(count);

    }

}
