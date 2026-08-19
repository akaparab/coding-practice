package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * . It should return 4 numbsers.
 * 1. if it is found even index, set value to 1
 * 2. if it is found at odd index, set value to 1
 * 3. if it is found in first half, set it to 1
 * 4. if it is found in second half, set it to 1
 */
public class SearchNumberIndex {
    public List<Integer> getSearchIndex(List<Integer> nums, int value) {
        int len = nums.size();

        int[] res = new int[4];
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == value) {
                if (i % 2 == 0) {
                    res[0] = 1;
                }
                if (i % 2 == 1) {
                    res[1] = 1;
                }
                if (i <= len / 2) {
                    res[2] = 1;
                }
                if (i > len / 2) {
                    res[3] = 1;
                }
            }
        }
        return Arrays.stream(res)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

    }

    public static void main(String[] args) {
        Integer[] nums = {2, -3, 5, 8, 7, 15, 6};
        int val = 8;
        SearchNumberIndex obj = new SearchNumberIndex();
        System.out.println(obj.getSearchIndex(Arrays.asList(nums), val).toString());
        System.out.println(obj.getSearchIndex(Arrays.asList(nums), 5).toString());
        System.out.println(obj.getSearchIndex(Arrays.asList(nums), 15).toString());

    }
}
