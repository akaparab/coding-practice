package com.example.demo.stack;

import java.util.Stack;

public class ValidParenthesis {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                st.push(s.charAt(i));
            } else {
                if (!st.empty()) {
                    if ((s.charAt(i) == ')' && st.peek() == '(') ||
                            (s.charAt(i) == ']' && st.peek() == '[') ||
                            (s.charAt(i) == '}' && st.peek() == '{')) {
                        st.pop();
                    }
                } else {
                    return false;
                }
            }
        }
        return st.isEmpty();

    }

    public static void main(String[] args) {
        String s = "()";
        String s1 = "()[]{}";
        String s2 = "(]{}";
        String s3 = "([])";
        String s4 = "(((((";
        String s5 = ")))))";
        ValidParenthesis vp = new ValidParenthesis();
        System.out.println("isValid : " + vp.isValid(s));
        System.out.println("isValid : " + vp.isValid(s1));
        System.out.println("isValid : " + vp.isValid(s2));
        System.out.println("isValid : " + vp.isValid(s3));
        System.out.println("isValid : " + vp.isValid(s4));
        System.out.println("isValid : " + vp.isValid(s5));

        // Time Complexity: O(n)
        // space Complexity: O(n)
    }
}
