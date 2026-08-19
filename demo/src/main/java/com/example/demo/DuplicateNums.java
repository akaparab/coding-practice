package com.example.demo;

public class DuplicateNums {
    public int findDuplicate(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[nums[i]]++;
            if (res[nums[i]] == 2) {
                return nums[i];
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 2, 4};
        DuplicateNums obj = new DuplicateNums();
        System.out.println(obj.findDuplicate(nums));
    }
}
