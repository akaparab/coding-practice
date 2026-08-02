package com.example.demo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstr {
    public Integer longestSubstringWithoutRepeat(String str) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int maxSize = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.get(c) > 1) {
                char ch = str.charAt(start);
                map.put(ch, map.get(ch) - 1);

                start++;
            }
            maxSize = Math.max(maxSize, i - start + 1);
        }
        return maxSize;
    }

    /**
     * This is more efficient way, not using hashmap
     *
     * @param str
     * @return
     */
    public Integer longestSubstringWithoutRepeatChar(String str) {
        int[] freq = new int[26];
        int start = 0;
        int maxSize = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            freq[c - 'a']++;

            while (freq[c - 'a'] > 1) {
                freq[c - 'a']--;
                start++;
            }
            maxSize = Math.max(maxSize, i - start + 1);
        }
        return maxSize;
    }

    public String longestSubstrWithoutRepeat(String str) {
        int[] freq = new int[26];
        int start = 0;
        int maxSize = 0;
        String res = "";

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            freq[c - 'a']++;

            while (freq[c - 'a'] > 1) {
                freq[c - 'a']--;
                start++;
            }
            //maxSize = Math.max(maxSize, i - start + 1);
            if (res.length() < i - start + 1) {
                res = str.substring(start, i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "eghghhgg";
        String s1 = "substring";
        String s2 = "pwwkew";
        LongestSubstr ss = new LongestSubstr();
        System.out.println(ss.longestSubstringWithoutRepeat(s2));
        System.out.println(ss.longestSubstringWithoutRepeat(s));
        System.out.println(ss.longestSubstringWithoutRepeat(s1));

        System.out.println(ss.longestSubstringWithoutRepeatChar(s));
        System.out.println(ss.longestSubstringWithoutRepeatChar(s1));

        System.out.println(ss.longestSubstrWithoutRepeat(s));
        System.out.println(ss.longestSubstrWithoutRepeat(s1));
    }
}
