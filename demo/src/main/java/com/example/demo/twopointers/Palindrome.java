package com.example.demo.twopointers;

public class Palindrome {
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            // Skip non-alphanumeric characters from left
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            // Skip non-alphanumeric characters from right
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            // Compare characters case-insensitively
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true; // Empty string is valid palindrome
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        Palindrome p = new Palindrome();
        System.out.println("Is Palindrome : " + p.isPalindrome(s));

    }
}
