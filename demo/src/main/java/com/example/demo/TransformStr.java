package com.example.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class TransformStr {
    public boolean[] transformStr(String s, String[] strs) {
        boolean[] ans = new boolean[strs.length];
        int count = 0;
        for (int i = 0; i < strs.length; i++) {
            ans[i] = compareStr(s, strs[i]);
        }
        return ans;

    }

    private boolean compareStr(String s1, String s2) {

        int count = 0;
        int[] chars = new int[2];
        for (int i = 0; i < s1.length(); i++) {
            chars[s1.charAt(i) - '0']++;
            if (s2.charAt(i) != '?')
                chars[s2.charAt(i) - '0']--;
            else count++;
        }

        // FIX: Check that ? count matches total deficit and both are non-negative
        if (chars[0] < 0 || chars[1] < 0 || count != chars[0] + chars[1])
            return false;

        // FIX: Greedily assign ?s (first chars[0] ?s become '0', rest become '1')
        // and check prefix condition: at every position, # of 1s in target <= # of 1s in s
        int onesInS = 0;
        int onesInTarget = 0;
        int zerosAssigned = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == '1') onesInS++;
            if (s2.charAt(i) == '1') {
                onesInTarget++;
            } else if (s2.charAt(i) == '?') {
                if (zerosAssigned < chars[0]) {
                    zerosAssigned++;
                    // assigned '0', no increment to onesInTarget
                } else {
                    onesInTarget++;
                }
            }
            if (onesInTarget > onesInS) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "101";
        String[] strs = {"1?1", "0?1", "0?0"};
        String[] strs1 = {"0011", "11?1", "1?1?"};
        String s1 = "1100";
        TransformStr obj = new TransformStr();
        System.out.println(Arrays.toString(obj.transformStr(s, strs)));
        System.out.println(Arrays.toString(obj.transformStr(s1, strs1)));
        System.out.println(Arrays.toString(obj.transformStr("01", new String[]{"?0"})));
    }
}


