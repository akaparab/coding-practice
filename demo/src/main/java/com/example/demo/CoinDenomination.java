package com.example.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoinDenomination {
    public static long getWays(int n, List<Long> c) {
        Set<Long> set = new HashSet<>();
        for (long num : c) {
            if (num <= n)
                set.add(num);
        }
        List<List<Long>> res = new ArrayList<>();
        for (Long num : set) {
            long k = n / num;
            long mod = n % num;
            if (mod != 0 && set.contains(mod)) {
                List<Long> list = new ArrayList<>();
                for (int i = 0; i < k; i++) {
                    list.add(num);
                }
                list.add(mod);
                res.add(list);
            } else {
                List<Long> list = new ArrayList<>();
                for (int i = 0; i < k; i++) {
                    list.add(num);
                }
                res.add(list);
            }
        }
        for (List<Long> list : res) {
            System.out.println(list.toString());
        }
        return res.size();
    }


    public static void main(String[] args) {
        List<Long> nums = List.of(8L, 3L, 1L, 2L);
        int k = 3;

        List<Long> nums1 = List.of(1L, 2L, 5L);
        System.out.println(CoinDenomination.getWays(k, nums));
        System.out.println(CoinDenomination.getWays(5, nums1));
    }
}
