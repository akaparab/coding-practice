package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        if (pattern.isEmpty() || s.isEmpty()) return false;

        Map<Character, String> map = new HashMap<>();
        Map<String, Character> map1 = new HashMap<>();

        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;

        for (int i = 0; i < pattern.length(); i++) {
            Character ch = pattern.charAt(i);
            if (map.containsKey(ch)) {
                if (!map.get(ch).equals(words[i])) return false;

            } else {
                map.put(ch, words[i]);
            }
            if (map1.containsKey(words[i])) {
                if (map1.get(words[i]) != ch) return false;

            } else {
                map1.put(words[i], ch);
            }
        }
        return true;

    }


    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat dog";
        String s1 = "dog cat cat fish";
        WordPattern wp = new WordPattern();
        System.out.println("wordPattern : " + wp.wordPattern(pattern, s));
        System.out.println("wordPattern : " + wp.wordPattern(pattern, s1));

        /**
         * Time Complexity: O(N). We process each character in both the strings exactly once to determine if the strings are isomorphic.
         * Space Complexity: O(1) since the size of the ASCII character set is fixed and the keys in our
         * dictionary are all valid ASCII characters according to the problem statement.
         */

    }
}
