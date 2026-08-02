package com.example.demo;

public class LongestPalindrome {

    private int start = 0;
    private int end = 0;

    public String longestPalindrome(String s) {

        if (s == null || s.length() < 2)
            return s;

        for (int i = 0; i < s.length(); i++) {

            expand(s, i, i);       // Odd length

            expand(s, i, i + 1);   // Even length
        }

        return s.substring(start, end + 1);
    }

    private void expand(String s, int left, int right) {

        while (left >= 0 &&
                right < s.length() &&
                s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        // left/right moved one step too far
        left++;
        right--;

        if (right - left > end - start) {
            start = left;
            end = right;
        }
    }


    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "babad";
        String s1 = "abba";
        LongestPalindrome lp = new LongestPalindrome();
//        System.out.println(lp.isPalindrome(s1));
//        System.out.println(lp.isPalindrome(s));
        System.out.println(lp.longestPalindrome(s));
    }
}
