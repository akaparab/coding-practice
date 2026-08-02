package com.example.demo;

public class Ransome {
    public boolean canConstruct1(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null) return false;
        if (ransomNote.isEmpty() || magazine.isEmpty()) return false;
        if (magazine.length() < ransomNote.length()) return false;
        int i = 0;
        int j = 0;

        while (i < magazine.length() && j < ransomNote.length()) {
            if (ransomNote.charAt(j) != magazine.charAt(i)) {
                i++;
            } else {
                i++;
                j++;
            }
        }
        return j == ransomNote.length();
    }


    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null) return false;
        if (ransomNote.isEmpty()) return true;  // Empty ransomNote can always be constructed
        if (magazine.isEmpty()) return false;
        if (magazine.length() < ransomNote.length()) return false;

        // Use fixed-size array for O(1) character frequency counting (26 lowercase letters)
        int[] charCount = new int[26];

        // Count all characters in magazine
        for (int i = 0; i < magazine.length(); i++) {
            charCount[magazine.charAt(i) - 'a']++;
        }

        // Check if ransomNote can be constructed
        for (int i = 0; i < ransomNote.length(); i++) {
            int idx = ransomNote.charAt(i) - 'a';
            if (--charCount[idx] < 0) {  // Decrement and check if we've used more than available
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "aab";
        String ransomNote1 = "aa";
        String magazine1 = "ab";
        Ransome r = new Ransome();
        System.out.println("Ransome : " + r.canConstruct(ransomNote, magazine));
        System.out.println("Ransome : " + r.canConstruct(ransomNote1, magazine1));
    }
}
