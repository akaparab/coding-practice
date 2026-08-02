package com.example.demo.slidingwindow;

public class FixedSlidingWindowTemplate {
    public int fixedLengthSlidingWindow(int[] nums, int k) {
        // choose appropriate data structure
        // Map<Integer, Integer> state = new HashMap<>();
        // Integer - depends on the problem statement
        int target = Integer.MIN_VALUE;
        int start = 0;
        int max = 0;

        for (int end = 0; end < nums.length; end++) {
            // extend window
            // add nums[end] to state in O(1) time

            if (end - start + 1 == k) {
                // INVARIANT: size of the window is k here.
                max = Math.max(max, target);

                // contract window
                // remove nums[start] from state in O(1) time
                start++;
            }
        }

        return max;
    }
}
