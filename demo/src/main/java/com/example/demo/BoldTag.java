package com.example.demo;

public class BoldTag {
    public String addBoldTag(String s, String[] words) {

        int i = 0;
        boolean[] bold = new boolean[s.length()];
        StringBuffer sb = new StringBuffer();
        for (String substr : words) {
            int start = 0;
            while (start >= 0) {
                start = s.indexOf(substr, start);
                if (start < 0) break;
                int end = start + substr.length();
                for (i = start; i < end; i++) {
                    bold[i] = true;
                }
                start++; // Just start from next index, instead of iterating through entire string
            }
        }
        for (i = 0; i < s.length(); i++) {
            if (bold[i] && (i - 1 < 0 || !bold[i - 1])) {
                sb.append("<b>");
            }
            sb.append(s.charAt(i)); // Just go character by character rather than cutting up the string
            if (bold[i] && (i + 1 == s.length() || !bold[i + 1])) {
                sb.append("</b>");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "abcxyz123";
        String words[] = {"abc", "123"};
        BoldTag bt = new BoldTag();
        System.out.println(bt.addBoldTag(s, words));
        String s1 = "aaabbb";
        String[] words1 = {"aa", "b"};
        System.out.println(bt.addBoldTag(s1, words1));


    }
}
