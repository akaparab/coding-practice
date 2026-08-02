package com.example.demo.karat;

public class WordsFormed {
    public int countCharacters(String[] words, String input) {
        int[] counts = new int[26];
        for (Character c : input.toCharArray()) {
            counts[c - 'a']++;
        }

        int ans = 0;
        for (String word : words) {
            int[] wordCount = new int[26];
            for (Character c : word.toCharArray()) {
                wordCount[c - 'a']++;
            }

            boolean good = true;
            for (int i = 0; i < 26; i++) {
                if (counts[i] < wordCount[i]) {
                    good = false;
                    break;
                }
            }

            if (good) {
                ans += word.length();
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"cat", "bt", "hat", "tree"};
        String chars = "atach";
        WordsFormed wf = new WordsFormed();
        System.out.println(wf.countCharacters(words, chars));
    }
}
