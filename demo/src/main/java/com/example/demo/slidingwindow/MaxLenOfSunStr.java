package com.example.demo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MaxLenOfSunStr {
    public int maximumLengthSubstring(String s) {
        Map<Character, Integer> freq = new HashMap<>();

        int left = 0;
        int maxLen = 0;
        String res = "";

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            freq.put(rightChar, freq.getOrDefault(rightChar, 0) + 1);

            // Window becomes invalid
            while (freq.get(rightChar) > 2) {
                char leftChar = s.charAt(left);

                freq.put(leftChar, freq.get(leftChar) - 1);

                if (freq.get(leftChar) == 0) {
                    freq.remove(leftChar);
                }

                left++;
            }

            if (right - left + 1 > maxLen) {
                res = s.substring(left, right + 1);
            }
            System.out.println(res);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }


    public static void main(String[] args) {
        String s = "bcbbbcba";
        MaxLenOfSunStr obj = new MaxLenOfSunStr();
        System.out.println(obj.maximumLengthSubstring(s));
    }

}
