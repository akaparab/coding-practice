package com.example.demo.sort;

public class MergeSort {

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        mergeSort(nums, 0, nums.length - 1);
    }

    private void mergeSort(int[] nums, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        merge(nums, left, mid, right);
    }

    private void merge(int[] nums,
                       int left,
                       int mid,
                       int right) {

        int[] temp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {

            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        while (j <= right) {
            temp[k++] = nums[j++];
        }

        for (int p = 0; p < temp.length; p++) {
            nums[left + p] = temp[p];
        }
    }

    public static void main(String[] args) {

        int[] nums = {5, 2, 4, 6, 1, 3};

        MergeSort ms = new MergeSort();
        ms.sort(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
