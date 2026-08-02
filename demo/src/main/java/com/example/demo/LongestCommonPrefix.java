package com.example.demo;

import java.util.Arrays;
import java.util.Comparator;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";

        for (String str : strs) {
            if (str.isEmpty()) return "";
        }

//        String smallest = Arrays.stream(strs)
//                .min(Comparator.comparingInt(String::length))
//                .orElse("");
        String smallest = strs[0];

        for (int i = 0; i < smallest.length(); i++) {
            for (String str : strs) {
                if (str.charAt(i) != smallest.charAt(i)) {
                    return smallest.substring(0, i);
                }

            }
        }
        return smallest;
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        String[] strs1 = {"dog", "racecar", "car"};
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        System.out.println("Prefix : " + lcp.longestCommonPrefix(strs));
        System.out.println("Prefix : " + lcp.longestCommonPrefix(strs1));

        // Time Complexity: O(m × n)
        //m = length of the shortest string in the array
        //n = number of strings in the array

        // space complexity o(1)

    }
}
