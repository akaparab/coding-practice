package com.example.demo.sort;

import java.util.PriorityQueue;

public class MedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums1) {
            queue.offer(num);
        }
        for (int num : nums2) {
            queue.offer(num);
        }
        int size = queue.size();
        int len1 = -1;
        int len2 = -1;
        boolean flag = true;
        int len = size / 2;

        if (size % 2 == 1) {
            flag = false;
        }
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i <= len; i++) {
            int val = queue.poll();
            if (i == len - 1) {
                num1 = val;
            }
            if (i == len) {
                num2 = val;
            }


        }
        if (!flag) {
            return num2;
        } else {
            return (double) (num1 + num2) / 2;
        }


    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2, 4};
        MedianSortedArrays obj = new MedianSortedArrays();
        System.out.println(obj.findMedianSortedArrays(nums1, nums2));

    }
}
