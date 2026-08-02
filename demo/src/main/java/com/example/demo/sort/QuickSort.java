package com.example.demo.sort;

public class QuickSort {

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {

        if (left >= right) {
            return;
        }

        int pivotIndex = partition(nums, left, right);

        quickSort(nums, left, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, right);
    }

    private int partition(int[] nums, int left, int right) {

        int pivot = nums[right];

        int i = left;

        for (int j = left; j < right; j++) {

            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }

        swap(nums, i, right);

        return i;
    }

//    private int partition(int[] nums, int left, int right) {
//
//        int pivot = nums[left];
//
//        int i = left - 1;
//        int j = right + 1;
//
//        while (true) {
//
//            do {
//                i++;
//            } while (nums[i] < pivot);
//
//            do {
//                j--;
//            } while (nums[j] > pivot);
//
//            if (i >= j) {
//                return j;
//            }
//
//            swap(nums, i, j);
//        }
//    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {

        int[] nums = {5, 2, 8, 1, 4};

        QuickSort qs = new QuickSort();
        qs.sort(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
