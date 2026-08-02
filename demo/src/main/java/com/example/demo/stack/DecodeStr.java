package com.example.demo.stack;

import java.util.Stack;

public class DecodeStr {
    public String decodeString(String s) {
        Stack<String> strStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        String curStr = "";
        int curNum = 0;

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == '[') {
                strStack.add(curStr);
                numStack.add(curNum);
                curStr = "";
                curNum = 0;
            } else if (c == ']') {
                int num = numStack.pop();
                String prevString = strStack.pop();
                curStr = prevString + curStr.repeat(num);
            } else if (Character.isDigit(c)) {
                curNum = curNum * 10 + (c - '0');
            } else {
                curStr += c;
            }

        }
        return curStr;


    }

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        DecodeStr ds = new DecodeStr();
        System.out.println(ds.decodeString(s));

    }
}
