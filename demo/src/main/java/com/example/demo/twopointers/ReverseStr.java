package com.example.demo.twopointers;

public class ReverseStr {
    public static String reverseInPlace(String str) {
        if (str == null) return null;

        // Convert to mutable char array
        char[] chars = str.toCharArray();
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            // Swap characters
            char temp = str.charAt(left);
            chars[left] = str.charAt(right);
            chars[right] = temp;

            left++;
            right--;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String str = "hello";
        System.out.println(ReverseStr.reverseInPlace(str));
    }
}
