package com.example.demo;

import ch.qos.logback.core.joran.sanity.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

public class WordLadder {
    @AllArgsConstructor
    @Data
    static class Pair {
        String key;
        int value;
    }

    public int ladderLength(
            String beginWord,
            String endWord,
            List<String> wordList
    ) {
        // Since all words are of same length.
        int L = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> allComboDict = new HashMap<>();

        wordList.forEach(word -> {
            for (int i = 0; i < L; i++) {
                // Key is the generic word
                // Value is a list of words which have the same intermediate generic word.
                String newWord =
                        word.substring(0, i) + '*' + word.substring(i + 1, L);
                allComboDict.computeIfAbsent(newWord, k -> new ArrayList<>()).add(word);
            }
        });

        // Queue for BFS
        Queue<Pair> Q = new LinkedList<>();
        Q.add(new Pair(beginWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Pair node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < L; i++) {
                // Intermediate words for current word
                String newWord =
                        word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(
                        newWord,
                        new ArrayList<>()
                )) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
    }

//    public List<String> getWordladderList(
//            String beginWord,
//            String endWord,
//            List<String> wordList
//    ) {
//        // Since all words are of same length.
//        int L = beginWord.length();
//
//        // Dictionary to hold combination of words that can be formed,
//        // from any given word. By changing one letter at a time.
//        Map<String, List<String>> allComboDict = new HashMap<>();
//
//        wordList.forEach(word -> {
//            for (int i = 0; i < L; i++) {
//                // Key is the generic word
//                // Value is a list of words which have the same intermediate generic word.
//                String newWord =
//                        word.substring(0, i) + '*' + word.substring(i + 1, L);
//                allComboDict.computeIfAbsent(newWord, k -> new ArrayList<>()).add(word);
//            }
//        });
//
//        // Queue for BFS
//        Queue<String> Q = new LinkedList<>();
//        Q.offer(beginWord);
//
//        // Visited to make sure we don't repeat processing same word.
//        Map<String, Boolean> visited = new HashMap<>();
//        visited.put(beginWord, true);
//        Map<String, String> parentMap = new HashMap<>();
//
//        while (!Q.isEmpty()) {
//            String word = Q.poll();
//            for (int i = 0; i < L; i++) {
//                // Intermediate words for current word
//                String newWord =
//                        word.substring(0, i) + '*' + word.substring(i + 1, L);
//
//                // Next states are all the words which share the same intermediate state.
//                for (String adjacentWord : allComboDict.getOrDefault(
//                        newWord,
//                        new ArrayList<>()
//                )) {
//                    // If at any point if we find what we are looking for
//                    // i.e. the end word - we can return with the answer.
//                    if (adjacentWord.equals(endWord)) {
//                        return buildPath(beginWord, endWord, parentMap);
//                    }
//                    // Otherwise, add it to the BFS Queue. Also mark it visited
//                    if (!visited.containsKey(adjacentWord)) {
//                        visited.put(adjacentWord, true);
//                        parentMap.put(adjacentWord, word);
//                        Q.offer(adjacentWord);
//
//                    }
//                }
//            }
//        }
//
//        return new ArrayList<>();
//    }
//
//    private List<String> buildPath(String beginWord,
//                                   String endWord,
//                                   Map<String, String> parent) {
//
//        LinkedList<String> path = new LinkedList<>();
//
//        String curr = endWord;
//
//        while (curr != null) {
//            path.addFirst(curr);
//            curr = parent.get(curr);
//        }
//
//        if (!path.getFirst().equals(beginWord))
//            return new ArrayList<>();
//
//        return path;
//    }

    public List<String> getWordladderList(
            String beginWord,
            String endWord,
            List<String> wordList) {

        int L = beginWord.length();

        Map<String, List<String>> allComboDict = new HashMap<>();

        for (String word : wordList) {
            for (int i = 0; i < L; i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i + 1);
                allComboDict.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
            }
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        // child -> parent
        Map<String, String> parent = new HashMap<>();

        while (!queue.isEmpty()) {

            String word = queue.poll();

            for (int i = 0; i < L; i++) {

                String pattern = word.substring(0, i) + "*" + word.substring(i + 1);

                for (String adjacentWord : allComboDict.getOrDefault(pattern, Collections.emptyList())) {

                    if (visited.contains(adjacentWord))
                        continue;

                    visited.add(adjacentWord);

                    parent.put(adjacentWord, word);

                    if (adjacentWord.equals(endWord)) {
                        return buildPath(beginWord, endWord, parent);
                    }

                    queue.offer(adjacentWord);
                }
            }
        }

        return new ArrayList<>();
    }

    private List<String> buildPath(String beginWord,
                                   String endWord,
                                   Map<String, String> parent) {

        LinkedList<String> path = new LinkedList<>();

        String curr = endWord;

        while (curr != null) {
            path.addFirst(curr);
            curr = parent.get(curr);
        }

        if (!path.getFirst().equals(beginWord))
            return new ArrayList<>();

        return path;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        WordLadder wl = new WordLadder();
        System.out.println(wl.ladderLength(beginWord, endWord, wordList));
        System.out.println(wl.getWordladderList(beginWord, endWord, wordList));

        // Time complexity O(M*M*N)
        // Space complexity O(M*M*N)
    }
}
