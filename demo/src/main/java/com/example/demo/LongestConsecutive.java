package com.example.demo;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int window = 1;
        int maxCount = 0;

        for (int num : nums) {
            while (set.contains(num + 1)) {
                window++;
                num += 1;
            }
            maxCount = Math.max(maxCount, window);
            window = 1;

        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        LongestConsecutive obj = new LongestConsecutive();
        System.out.println(obj.longestConsecutive(nums));
    }
}
