package com.example.demo.trie;

import java.util.*;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] seen = new boolean[s.length() + 1];

        while (!queue.isEmpty()) {
            int len = queue.remove();
            if (len == s.length()) {
                return true;
            }
            for (int end = len + 1; end <= s.length(); end++) {

                if (!seen[end]) {
                    if (wordSet.contains(s.substring(len, end))) {
                        seen[end] = true;
                        queue.add(end);
                    }

                }
            }

        }
        return false;
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        //boolean[] seen = new boolean[s.length() + 1];

        while (!queue.isEmpty()) {
            int len = queue.remove();
            if (len == s.length()) {
                return true;
            }
            for (int end = len + 1; end <= s.length(); end++) {

                // if (!seen[end]) {
                if (wordSet.contains(s.substring(len, end))) {
                    //  seen[end] = true;
                    queue.add(end);
                }

            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        String s1 = "leetcod";
        List<String> wordDict = Arrays.asList("leet", "code");
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreak(s, wordDict));
        System.out.println(wb.wordBreak1(s, wordDict));
        System.out.println(wb.wordBreak(s1, wordDict));
    }
}
