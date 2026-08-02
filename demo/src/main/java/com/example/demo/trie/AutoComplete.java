package com.example.demo.trie;


import java.util.*;

public class AutoComplete {

//    class TrieNode {
//
//        Map<Character, TrieNode> children;
//        Map<String, Integer> counts;
//
//        TrieNode() {
//            children = new HashMap<>();
//            counts = new HashMap<>();
//        }
//    }

    private TrieNode root;
    private StringBuilder current;
    private final int DEFAULT_TOP_K = 10;

    public AutoComplete(
            String[] sentences,
            int[] times) {

        root = new TrieNode();
        current = new StringBuilder();

        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    private void insert(String sentence,
                        int count) {

        TrieNode node = root;
        for (char c : sentence.toCharArray()) {
            node.getChildren().putIfAbsent(c, new TrieNode());

            node = node.getChildren().get(c);

            // No need to store count at each node. can be stored at the end of the sentence
            // by storing at node, query will be faster, but it needs more space
            // Instead topK can be stored at each node, not to consume too much space and performance
            node.getFrequency().put(sentence, node.getFrequency().getOrDefault(
                    sentence,
                    0
            ) + count);
            updateTopSuggestions(sentence, node);
        }
    }

    public List<String> input(char c) {

        if (c == '#') {
            insert(current.toString(), 1);
            current = new StringBuilder();
            return new ArrayList<>();
        }

        current.append(c);

        TrieNode node = root;

        for (char ch : current.toString()
                .toCharArray()) {

            if (!node.getChildren().containsKey(ch))
                return new ArrayList<>();

            node = node.getChildren().get(ch);
        }

        TrieNode finalNode = node;
        PriorityQueue<String> pq =
                new PriorityQueue<>(
                        (a, b) -> {

                            int fa = finalNode.getFrequency().get(a);
                            int fb = finalNode.getFrequency().get(b);

                            if (fa == fb)
                                return b.compareTo(a);

                            return fa - fb;
                        });

        for (String s : node.getFrequency().keySet()) {

            pq.offer(s);

            if (pq.size() > 3)
                pq.poll();
        }

        List<String> result =
                new ArrayList<>();

        while (!pq.isEmpty())
            result.add(0, pq.poll());

        return result;
    }

    private void updateTopSuggestions(String sentence, TrieNode node) {
        List<String> suggestions = node.getTopSuggestions();
        if(!suggestions.contains(sentence)) {
            suggestions.add(sentence);
        }

        suggestions.sort(Comparator.comparingInt(a -> node.getFrequency().get(a)));

        if (suggestions.size() > DEFAULT_TOP_K) {
            suggestions.removeLast();
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
        // "i love you" searched for 5 times already, island searched for 3 times, etc
        int[] freq = {5, 3, 2, 2};
        AutoComplete ac = new AutoComplete(sentences, freq);
        System.out.println(ac.input('i'));
        System.out.println(ac.input(' '));
        System.out.println(ac.input('l'));
        System.out.println(ac.input('#'));

        System.out.println(ac.input('i'));
        System.out.println(ac.input('s'));
        System.out.println(ac.input('#'));

    }
}
