package com.example.demo.trie;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void createTrie(String[] words) {
        for (String word : words) {
            insert(word);
        }
    }

    public void insert(String word) {
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for (char c : chars) {
            if (!node.getChildren().containsKey(c)) {
                node.getChildren().put(c, new TrieNode());
            }
            node = node.getChildren().get(c);
        }
        node.setWord(true);

    }

    public boolean search(String word) {
        char[] chars = word.toCharArray();
        TrieNode node = root;

        for (char c : chars) {
            if (!node.getChildren().containsKey(c)) {
                return false;
            }
            node = node.getChildren().get(c);
        }
        return node.isWord();
    }

    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        TrieNode node = root;

        for (char c : chars) {
            if (!node.getChildren().containsKey(c)) {
                return false;
            }
            node = node.getChildren().get(c);
        }
        return true;
    }

    public List<String> prefix(String prefix) {
        char[] chars = prefix.toCharArray();
        TrieNode node = root;
        List<String> res = new ArrayList<>();

        for (char c : chars) {
            if (!node.getChildren().containsKey(c)) {
                return res;
            }
            node = node.getChildren().get(c);
        }
        matchingWord(prefix, node, res);

        return res;
    }

    private void matchingWord(String prefix, TrieNode node, List<String> res) {
        if (node.isWord()) {
            res.add(prefix);
        }
        if (!node.getChildren().isEmpty()) {
            for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
                matchingWord(prefix + entry.getKey(), entry.getValue(), res);
            }
        }
    }

    public void delete(String word) {
        deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(TrieNode node, String word, int index) {
        // base case: We have reached the end of the word
        if (index == word.length()) {
            // Mark the node as not being the end of a word
            node.setWord(false);
            // Return true if the node should be deleted
            return node.getChildren().isEmpty();
        }

        char c = word.charAt(index);
        TrieNode child = node.getChildren().get(c);

        if (child == null) {
            return false;  // Word not found
        }

        boolean shouldDeleteChild = deleteHelper(child, word, index + 1);

        if (shouldDeleteChild) {
            node.getChildren().remove(c);
        }

        // Return true if current node should be deleted
        return !node.isWord() && node.getChildren().isEmpty();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] words = {"Anita", "Kaparaboyna"};
        trie.createTrie(words);
        System.out.println(trie.search("Anita"));
        System.out.println(trie.startsWith("An"));
        System.out.println(trie.startsWith("Ak"));
        System.out.println(trie.search("Anika"));
        System.out.println(trie.search("Kaparaboyna"));
        trie.delete("Anita");
        System.out.println(trie.search("Anita"));
        String[] words1 = {"apple", "app", "apartment", "ap", "apricot"};
        String prefixWord = "app";
        trie.createTrie(words1);
        List<String> matchWords = trie.prefix(prefixWord);
        System.out.println(matchWords.toString());
        System.out.println(trie.prefix("ap"));
    }
}
