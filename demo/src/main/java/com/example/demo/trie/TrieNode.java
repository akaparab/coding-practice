package com.example.demo.trie;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
public class TrieNode {
    private boolean isWord;
    private Map<Character, TrieNode> children;
    private List<String> topSuggestions;
    private Map<String, Integer> frequency;

    public TrieNode() {
        isWord = false;
        children = new HashMap<>();
        topSuggestions = new ArrayList<>();
        frequency = new HashMap<>();
    }

    public TrieNode(Map<Character, TrieNode> children, boolean eow, List<String> suggestions,
                    Map<String, Integer> freq) {
        this.children = children;
        this.isWord = eow;
        this.topSuggestions = suggestions;
        this.frequency = freq;
    }


}
