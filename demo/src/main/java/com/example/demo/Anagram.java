package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    public boolean isAnagram(String s, String t) {
        if (s.isEmpty() && t.isEmpty()) return false;
        if (s.length() != t.length()) return false;

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            Character ch1 = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            map.put(ch1, map.getOrDefault(ch1, 0) - 1);
        }
        for (char c : map.keySet()) {
            if (map.get(c) != 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String pattern = "anagram";
        String s = "nagaram";

        Anagram anagram = new Anagram();
        System.out.println("wordPattern : " + anagram.isAnagram(pattern, s));

        //The time complexity is still O(n)
        // space complexity becomes O(n) because of the hashtable.
    }
}
