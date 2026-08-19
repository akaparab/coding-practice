package com.example.demo.stack;

import java.util.Stack;

public class EvalNumber {
    public static int evalRPN(String[] tokens) {
        Stack<String> st = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            boolean isDigit = false;
            if (token.charAt(0) == '-' && Character.isDigit(token.charAt(1))) {
                String str = token.substring(1);
                isDigit = str.chars().allMatch(Character::isDigit);
            } else {
                isDigit = token.chars().allMatch(Character::isDigit);
            }
            if (isDigit) {
                st.push(token);
            } else {
                if (token.equals("+")) {
                    int num1 = Integer.parseInt(st.peek());
                    st.pop();
                    int num2 = Integer.parseInt(st.peek());
                    st.pop();
                    st.push(String.valueOf(num1 + num2));

                } else if (token.equals("*")) {
                    int num1 = Integer.parseInt(st.peek());
                    st.pop();
                    int num2 = Integer.parseInt(st.peek());
                    st.pop();
                    st.push(String.valueOf(num1 * num2));
                } else if (token.equals("-")) {
                    int num1 = Integer.parseInt(st.peek());
                    st.pop();
                    int num2 = Integer.parseInt(st.peek());
                    st.pop();
                    st.push(String.valueOf(num1 - num2));
                } else if (token.equals("/")) {
                    int num1 = Integer.parseInt(st.peek());
                    st.pop();
                    int num2 = Integer.parseInt(st.peek());
                    st.pop();
                    st.push(String.valueOf(num2 / num1));
                }
            }
        }
        if (!st.isEmpty())
            return Integer.parseInt(st.peek());
        return -1;

    }

    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
//        System.out.println(EvalNumber.evalRPN(tokens));
//        System.out.println(EvalNumber.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(EvalNumber.evalRPN(new String[]{"4", "13", "5", "/", "+"}));

    }
}
