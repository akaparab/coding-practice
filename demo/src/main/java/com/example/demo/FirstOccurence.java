package com.example.demo;

public class FirstOccurence {
    public int strStr1(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;

        if (haystack.length() < needle.length()) return -1;
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) != needle.charAt(j)) {
                j = -1;
                k = -1;
            }
            i++;
            j++;

            if (j == needle.length()) {
                return k;
            }
            if (k == -1 && haystack.length() - i >= needle.length()) {
                k = i;
            }
        }
        return k;
    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        if (needle.length() == 0) return 0; // Empty needle matches at index 0

        int n = haystack.length();
        int m = needle.length();

        // Brute-force search with early termination
        for (int i = 0; i <= n - m; i++) { // Only check valid starting positions
            int j = 0;
            // Compare characters one by one
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            // Full match found
            if (j == m) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String haystack = "sadbutsad";
        String needle = "sad";
        String haystack1 = "butsad";
        String needle1 = "sad";
        String haystack2 = "leetcode";
        String needle2 = "leeto";
        FirstOccurence fo = new FirstOccurence();
        System.out.println("Index: " + fo.strStr(haystack, needle));
        System.out.println("Index: " + fo.strStr(haystack1, needle1));
        System.out.println("Index: " + fo.strStr(haystack2, needle2));

    }
}
