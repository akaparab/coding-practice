package com.example.demo;

import java.util.*;

public class CaseConversionPermutation {
    Set<String> casePermutation(String s) {
        Set<String> set = new HashSet<>();
        int len = s.length();
        set.add(s);
        set.add(s.toUpperCase());

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                continue;
            }
            set.add(dfs(s, i));
        }
        return set;

    }

    private String dfs(String str, int idx) {
        char c = str.charAt(idx);
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(idx, Character.toUpperCase(c));
        return sb.toString();
    }


    public static void main(String[] args) {
        String s = "a1z";
        String s1 = "a111zb";
        String s2 = "abc";
        String s3 = "abcd1";
        CaseConversionPermutation obj = new CaseConversionPermutation();
        System.out.println(obj.casePermutation(s).toString());
        System.out.println(obj.casePermutation(s1).toString());
        System.out.println(obj.casePermutation(s2).toString());
        System.out.println(obj.casePermutation(s3).toString());

    }
}
