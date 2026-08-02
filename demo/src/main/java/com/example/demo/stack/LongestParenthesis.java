package com.example.demo.stack;

import java.util.Stack;

public class LongestParenthesis {
    public Integer longest_valid_parentheses(String s) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (ch == '(') {
                st.push(i);
            } else {
                if (!st.empty())
                    st.pop();
                if (st.isEmpty()) {
                    st.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - st.peek());
                }

            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "()";
        String s1 = "((()()())";
//        String s2 = "(]{}";
//        String s3 = "([])";
        String s4 = "(((((";
        String s5 = ")))))";
        LongestParenthesis vp = new LongestParenthesis();
        System.out.println("maxLen : " + vp.longest_valid_parentheses(s));
        System.out.println("isValid : " + vp.longest_valid_parentheses(s1));
//        System.out.println("isValid : " + vp.longest_valid_parentheses(s2));
//        System.out.println("isValid : " + vp.longest_valid_parentheses(s3));
        System.out.println("maxLen : " + vp.longest_valid_parentheses(s4));
        System.out.println("maxLen : " + vp.longest_valid_parentheses(s5));
    }
}