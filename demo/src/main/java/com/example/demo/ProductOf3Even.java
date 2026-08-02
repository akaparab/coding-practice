package com.example.demo;

public class ProductOf3Even {

    public long countEvenProductTriplets(int[] nums) {

        int n = nums.length;

        int oddCount = 0;

        for (int num : nums) {
            if ((num & 1) == 1) {
                oddCount++;
            }
        }

        long totalTriplets = combination(n, 3);
        long oddTriplets = combination(oddCount, 3);

        return totalTriplets - oddTriplets;
    }

    private long combination(int n, int r) {
        if (n < r) {
            return 0;
        }

        return (long) n * (n - 1) * (n - 2) / 6;
    }

    public static void main(String[] args) {

        ProductOf3Even solution = new ProductOf3Even();

        int[] nums = {1, 2, 3, 4};

        System.out.println(
                solution.countEvenProductTriplets(nums)
        );
    }

}
