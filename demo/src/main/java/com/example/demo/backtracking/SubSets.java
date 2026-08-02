package com.example.demo.backtracking;

import java.util.ArrayList;
import java.util.List;

public class SubSets {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        res.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            res.addAll(createLists(nums[i]));
        }
        return res;
    }

    private List<List<Integer>> createLists(int num) {
        List<List<Integer>> lists = new ArrayList<>();
        for (List<Integer> list : res) {
            List<Integer> newList = new ArrayList<>(list);
            newList.add(num);
            lists.add(newList);
        }
        return lists;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        SubSets sets = new SubSets();
        List<List<Integer>> res = sets.subsets(nums);
        for (List<Integer> list : res) {
            System.out.println(list.toString());
        }
    }
}
