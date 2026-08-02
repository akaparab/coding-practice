package com.example.demo.backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack(result, new StringBuilder(), 0, 0, n);

        return result;
    }

    private void backtrack(
            List<String> result,
            StringBuilder current,
            int open,
            int close,
            int n) {

        // Base case
        if (current.length() == 2 * n) {
            result.add(current.toString());
            return;
        }

        // Add opening parenthesis
        if (open < n) {
            current.append('(');

            backtrack(
                    result,
                    current,
                    open + 1,
                    close,
                    n);

            current.deleteCharAt(current.length() - 1);
        }

        // Add closing parenthesis
        if (close < open) {
            current.append(')');

            backtrack(
                    result,
                    current,
                    open,
                    close + 1,
                    n);

            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses obj = new GenerateParentheses();
        System.out.println(obj.generateParenthesis(2).toString());
        System.out.println(obj.generateParenthesis(3).toString());
        System.out.println(obj.generateParenthesis(4).toString());
    }

}
