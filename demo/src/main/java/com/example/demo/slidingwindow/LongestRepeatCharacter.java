package com.example.demo.slidingwindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LongestRepeatCharacter {
    public Integer characterReplacement1(String s, Integer k) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int maxSize = 0;
        int j = 0;
        int maxFreq = 0;

        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(ch));

            while ((i - start + 1) - maxFreq > k) {

                map.put(s.charAt(start), map.get(s.charAt(i)) - 1);
                start++;
            }
            maxSize = Math.max(maxSize, i - start + 1);
        }
        return maxSize;
    }

    public String characterReplacementStr(String s, Integer k) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int maxSize = 0;
        int j = 0;
        int maxFreq = 0;
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(ch));

            while ((i - start + 1) - maxFreq > k) {

                map.put(s.charAt(start), map.get(s.charAt(i)) - 1);
                start++;
            }
            if (i - start + 1 > maxSize) {
                res = s.substring(start, i + 1);
            }
            maxSize = Math.max(maxSize, i - start + 1);

        }
        //return maxSize;
        return res;
    }

    /**
     * This is mpre efficient. Not using map
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];

        int left = 0;
        int maxFreq = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq[c - 'A']++;
            maxFreq = Math.max(maxFreq, freq[c - 'A']);

            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            maxLen = Math.max(
                    maxLen,
                    right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "BBABCCDD";
        int k = 2;
        String s1 = "BBABCCCC";

        LongestRepeatCharacter lrc = new LongestRepeatCharacter();
        System.out.println(lrc.characterReplacement1(s, k));
        System.out.println(lrc.characterReplacementStr(s, k));
        System.out.println(lrc.characterReplacement1(s1, k));

        System.out.println(lrc.characterReplacement(s, k));
        System.out.println(lrc.characterReplacement(s1, k));
    }
}
