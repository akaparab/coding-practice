package com.example.demo;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestSubArrayWithLimitDiff {

    public int longestSubarray(int[] nums, int limit) {

        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;
        int answer = 0;

        for (int right = 0; right < nums.length; right++) {

            // Maintain decreasing deque for maximum
            while (!maxDeque.isEmpty() &&
                    maxDeque.peekLast() < nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(nums[right]);

            // Maintain increasing deque for minimum
            while (!minDeque.isEmpty() &&
                    minDeque.peekLast() > nums[right]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(nums[right]);

            // Shrink window until valid
            while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {

                if (nums[left] == maxDeque.peekFirst()) {
                    maxDeque.pollFirst();
                }

                if (nums[left] == minDeque.peekFirst()) {
                    minDeque.pollFirst();
                }

                left++;
            }

            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }


    public static void main(String[] args) {
        int[] nums = {8, 2, 4, 7};
        int limit = 4;
        LongestSubArrayWithLimitDiff mp = new LongestSubArrayWithLimitDiff();
        System.out.println("count : " + mp.longestSubarray(nums, limit));

        // Time complexity o(n)
        // space complexity o(1)
    }
}
