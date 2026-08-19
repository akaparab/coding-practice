package com.example.demo;

public class ValidPalindrome {
    public boolean validPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) return false;
        return isPalindrome(s);
    }

    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                if (s.charAt(i + 1) == s.charAt(j)) {
                    i++;
                } else if (s.charAt(i) == s.charAt(j - 1)) {
                    j--;
                } else {
                    return false;
                }
            }
            i++;
            j--;
        }
        return true;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {

            // Odd-length palindrome
            // Example: "aba"
            int len1 = expandFromCenter(s, i, i);

            // Even-length palindrome
            // Example: "abba"
            int len2 = expandFromCenter(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > end - start + 1) {

                // Calculate start and end of palindrome
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandFromCenter(String s, int left, int right) {

        while (left >= 0 &&
                right < s.length() &&
                s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        // left and right are now one position
        // outside the palindrome

        return right - left - 1;
    }

    public static void main(String[] args) {
        ValidPalindrome obj = new ValidPalindrome();
        System.out.println(obj.validPalindrome("aba"));
        System.out.println(obj.validPalindrome("abca"));
        System.out.println(obj.validPalindrome("abc"));
        System.out.println(obj.longestPalindrome("babad"));
    }
}
