package com.example.demo;

public class FirstOccurenceStr {

    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i <= n - m; i++) {
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
                if (j == m - 1) {
                    return i;
                }

            }

        }
        return -1;

    }

    public static void main(String[] args) {
        String haystack = "sadbutsad";
        String needle = "sad";

        String haystack1 = "pineapple";
        String needle1 = "apple";
        FirstOccurenceStr obj = new FirstOccurenceStr();
        System.out.println(obj.strStr(haystack, needle));
        System.out.println(obj.strStr(haystack1, needle1));


    }
}
