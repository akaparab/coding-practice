package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram {
    public List<List<String>> getAnagramGroup(String[] list) {
        Map<String, List<String>> map = new HashMap<>();
        // List<List<String>> group = new ArrayList<>();
        for (String str : list) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            // 3. Convert char array back to String
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());

        // Time complexity O(N Klog(K) - O(KlogK is for sorting each str
        // space complexity O(NK) - storing N strs
    }

    public List<List<String>> getAnagramGroup1(String[] list) {
        Map<String, List<String>> map = new HashMap<>();
        int[] chars = new int[26];
        for (String str : list) {
            String key = getKey(str);
            System.out.println(key);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());

        // Time complexity O(N Klog(K) - O(KlogK is for sorting each str
        // space complexity O(NK) - storing N strs
    }

    private String getKey(String word) {
        int[] counts = new int[26];
        for (char c : word.toCharArray()) {
            counts[c - 'a']++;
        }
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < 26; i++) {
            sb.append('#');
            sb.append(counts[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] list = {"eat", "tea", "tan", "ate", "nat", "bat"};

        GroupAnagram ga = new GroupAnagram();
        List<List<String>> res = ga.getAnagramGroup(list);

        for (List<String> item : res) {
            System.out.println(item.toString());
        }

        List<List<String>> res1 = ga.getAnagramGroup1(list);
        for (List<String> item : res) {
            System.out.println(item.toString());
        }

    }


}
