package com.example.demo;

import java.util.Arrays;

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 */
public class SubStrPermutation {
    public boolean checkInclusion(String s1, String s2) {
        char[] chars = s1.toCharArray();
        Arrays.sort(chars);
        s1 = new String(chars);

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (s1.equals(getSortedSubString(s2, i, i + s1.length()))) {
                return true;
            }
        }
        return false;
    }

    private String getSortedSubString(String s, int idx, int len) {
        char[] chars = s.substring(idx, len).toCharArray();
        System.out.println(chars);
        Arrays.sort(chars);
        return new String(chars);

    }

    public static void main(String[] args) {
        SubStrPermutation sol = new SubStrPermutation();
        String s1 = "ab";
        String s2 = "eidbaooo";

        System.out.println(sol.checkInclusion(s1, s2));
        System.out.println("*************");
        System.out.println(sol.checkInclusion("ac", s2));
    }

}
