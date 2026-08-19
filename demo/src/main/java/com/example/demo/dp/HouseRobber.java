package com.example.demo.dp;

public class HouseRobber {
    public int rob(int[] nums) {
        int prev1 = nums[0];
        int prev2 = nums[1];

        int max = 0;
        int res = 0;
        for (int i = 2; i < nums.length; i++) {

            max = Math.max(prev2, prev1 + nums[i]);
            prev1 = prev2;
            prev2 = max;

            res = Math.max(res, max);
        }
        return res;
    }

    public int rob1(int[] nums, int start, int end) {
        int t1 = 0;
        int t2 = 0;

        for (int i = start; i <= end; i++) {
            int temp = t1;
            t1 = Math.max(nums[i] + t2, t1);
            t2 = temp;
        }
        return t1;
    }

    public int robCircularHouses(int[] nums) {
        if (nums.length == 0) return 0;

        if (nums.length == 1) return nums[0];

        int max1 = rob1(nums, 0, nums.length - 2);
        int max2 = rob1(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        HouseRobber obj = new HouseRobber();
        System.out.println(obj.rob(nums));
        System.out.println(obj.robCircularHouses(nums));
        int[] nums1 = {2, 7, 9, 3, 1};
        System.out.println(obj.rob(nums1));
        System.out.println(obj.robCircularHouses(nums1));
    }
}
