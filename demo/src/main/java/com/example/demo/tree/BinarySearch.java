package com.example.demo.tree;

public class BinarySearch {
    int search(int nums[], int target) {

        int begin = 0;
        int end = nums.length - 1;
        int mid = 0;


        while (begin <= end) {
            mid = begin + (end - begin) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 9, 10, 12};
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target = 9;

        BinarySearch bs = new BinarySearch();
        System.out.println(bs.search(nums, target));
        System.out.println(bs.search(nums1, target));

        // The time complexity of this solution is logarithmic, o(log(n)
        // The space complexity of this solution is constant o(1)


    }
}
