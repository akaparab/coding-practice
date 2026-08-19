package com.example.demo;

public class ActiveTrades {
    public int maxActiveSectionsAfterTrade(String s) {
        if (s.length() <= 2) {
            return 1;
        }
        int idx = s.indexOf('1');
        StringBuffer sb = new StringBuffer();
        if (idx != -1 && idx > 0 &&
                s.charAt(idx - 1) == '0' &&
                s.charAt(idx + 1) == '0') {
            sb.append('1').append(s).append('1');

            sb.setCharAt(idx, '0');
        }

        int firstIndex = sb.indexOf("0");
        int lastIndex = sb.indexOf("0");
        return lastIndex - firstIndex;

    }

    public static void main(String[] args) {
        String s = "0100";
        ActiveTrades obj = new ActiveTrades();
        System.out.println(obj.maxActiveSectionsAfterTrade(s));
    }
}
