package com.example.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumberPermutation {
    Set<int[]> getPermutations(int[] nums) {
        Set<int[]> res = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            swap(i, nums, res);
        }
        return res;
    }

    private void swap(int idx, int[] orig, Set<int[]> set) {
        int[] nums = orig;
        for (int i = 0; i < orig.length; i++) {
            if (idx == i) {
                continue;
            }
            set.add(getChanged(idx, i, nums));

        }
    }

    private int[] getChanged(int idx, int idx1, int[] nums) {
        int tmp = nums[idx1];
        nums[idx1] = nums[idx];
        nums[idx] = tmp;
        int[] vals = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            vals[i] = nums[i];
        }
        return vals;

    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        NumberPermutation obj = new NumberPermutation();

        Set<int[]> res = obj.getPermutations(nums);
        for (int[] res1 : res) {
            System.out.println(Arrays.toString(res1));
        }
    }
}
