package com.example.demo.stack;

import java.util.Stack;

public class MinParentheses {
    public int minAddToMakeValid(String s) {
        char[] chars = s.toCharArray();

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (!st.isEmpty()) {
                if (chars[i] == ')' && st.peek() == '(') {
                    st.pop();
                } else {
                    st.add(chars[i]);
                }
            } else {
                st.add(chars[i]);
            }
        }
        return st.size();


    }

    public static void main(String[] aegs) {
        String s = "())";
        MinParentheses obj = new MinParentheses();
        System.out.println(obj.minAddToMakeValid(s));
        System.out.println(obj.minAddToMakeValid("(((("));

    }
}
