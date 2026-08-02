package com.example.demo;

public class SubSequence {

    public boolean isSubsequence1(String s, String t) {
        if (s == null || t == null) return false;
        if (s.isEmpty() || t.isEmpty()) return true;

        if (s.length() > t.length()) return false;
        int count = 0;
        int j = 0;

        for (int i = 0; i < s.length(); i++) {

            while (j < t.length()) {
                if (s.charAt(i) == t.charAt(j)) {
                    count++;
                    break;
                }
                j++;
            }
        }
        return s.length() == count;
    }

    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) return false;
        if (s.isEmpty()) return true;  // Empty string is always a subsequence
        if (t.isEmpty()) return false; // Non-empty s can't be subsequence of empty t
        if (s.length() > t.length()) return false;

        int i = 0; // Pointer for s
        int j = 0; // Pointer for t

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++; // Move s pointer only when match found
            }
            j++; // Always move t pointer forward
        }

        return i == s.length(); // Check if we matched all characters in s
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        String s1 = "axc";
        String t1 = "ahbgdc";
        SubSequence ss = new SubSequence();
        System.out.println("is subsequence : " + ss.isSubsequence(s, t));
        System.out.println("is subsequence : " + ss.isSubsequence(s1, t1));

    }
}
