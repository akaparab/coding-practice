package com.example.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Duplicates {
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                list.add(nums[i]);
            }
            set.add(nums[i]);
        }
        return list;
    }

    public static void main(String[] args) {
        Duplicates dup = new Duplicates();
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(dup.findDuplicates(nums).toString());

        // Time complexity: O(n)
        // Space complexity: Up to O(n)
    }
}
