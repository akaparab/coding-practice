package com.example.demo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unique Combinations that Sum to a Target
 * Find all unique combinations in an array that sum to a target.
 * <p>
 * Input: candidates = [2,4,6], target = 6
 * <p>
 * Output: [[2,2,2], [2,4], [6]]
 * <p>
 * Explanation: Uses backtracking to find all valid combinations that sum to 6, allowing for explicitly repeated elements.
 */
public class UniqueSumToTarget {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> getSumCombinationToTarget(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());


        for (int i = 0; i < nums.length; i++) {
            list.addAll(createLists(nums[i], target, list));
        }


        return res;

    }

    private List<List<Integer>> createLists(int num, int target, List<List<Integer>> list1) {
        List<List<Integer>> lists = new ArrayList<>();
        for (List<Integer> list : list1) {
            List<Integer> newList = new ArrayList<>(list);
            newList.add(num);
            int sum = newList.stream().mapToInt(Integer::intValue).sum();
            if (sum == target) {
                res.add(newList);
            }
            lists.add(newList);
        }
        return lists;
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackDuplicates(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrackDuplicates(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                backtrackDuplicates(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.removeLast();
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums,
                           int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i - 1] == nums[i]) continue;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i + 1);
                tempList.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 6};
        UniqueSumToTarget obj = new UniqueSumToTarget();
        List<List<Integer>> list = obj.getSumCombinationToTarget(nums, 6);
        System.out.println(list.toString());
        System.out.println(obj.combinationSum(nums, 6));
        System.out.println(obj.combinationSum2(nums, 6));
        System.out.println(obj.combinationSum(new int[]{2, 3, 5}, 8));
        System.out.println(obj.combinationSum2(new int[]{2, 3, 5}, 8));


    }
}
