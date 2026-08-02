package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDistance {
    Map<String, List<Integer>> map;

    public WordDistance(String[] wordsDict) {
        map = new HashMap<>();
        for (int i = 0; i < wordsDict.length; i++) {
            map.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> loc1 = map.get(word1);
        List<Integer> loc2 = map.get(word2);
        int idx1 = 0;
        int idx2 = 0;
        int minDiff = Integer.MAX_VALUE;
        while (idx1 < loc1.size() && idx2 < loc2.size()) {
            int val1 = loc1.get(idx1);
            int val2 = loc2.get(idx2);
            minDiff = Math.min(minDiff, Math.abs(val1 - val2));
            if (val1 < val2) {
                idx1++;
            } else {
                idx2++;
            }
        }
        return minDiff;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding";
        String word2 = "practice";
        String word3 = "makes";
        String word4 = "coding";
        WordDistance wd = new WordDistance(words);
        System.out.println(wd.shortest(word1, word2));
        System.out.println(wd.shortest(word3, word4));
    }
}
