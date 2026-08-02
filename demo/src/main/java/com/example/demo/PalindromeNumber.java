package com.example.demo;

public class PalindromeNumber {
    public boolean isPalindrome1(int num) {
        int i = 0;
        if (num < 0) return false;
        String s = Integer.toString(num);
        int j = s.length() - 1;

        while (i < j) {
            // Compare characters case-insensitively
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true; // Empty string is valid palindrome
    }

    public boolean isPalindrome(int num) {
        int i = 0;
        if (num < 0) return false;
        if (num < 10) return false;
        if (num % 10 == 0) return false;

        int revertedNum = 0;

        while (num > revertedNum) {
            revertedNum += revertedNum * 10 + num % 10;
            num = num / 10;
        }
        return num == revertedNum || num == revertedNum / 10;


    }

    public static void main(String[] args) {
        int num = 121;
        int num1 = -121;
        PalindromeNumber p = new PalindromeNumber();
        System.out.println("Is Palindrome : " + p.isPalindrome(num));
        System.out.println("Is Palindrome : " + p.isPalindrome(num1));

    }
}
