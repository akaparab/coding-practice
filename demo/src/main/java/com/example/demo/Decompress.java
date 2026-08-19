package com.example.demo;

import java.util.Stack;

public class Decompress {
    public String decodeString(String s) {

        int num = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = c - '0';
            } else if (Character.isLetter(c)) {
                for (int j = 0; j < num; j++)
                    sb.append(c);
                num = 0;
            }
        }
        return sb.toString();
    }

    String decodeString1(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                // push the number k to countStack
                countStack.push(k);
                // push the currentString to stringStack
                stringStack.push(currentString);
                // reset currentString and k
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = stringStack.pop();
                // decode currentK[currentString] by appending currentString k times
                for (int currentK = countStack.pop(); currentK > 0; currentK--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();

    }


    public static void main(String[] args) {
        Decompress obj = new Decompress();
        System.out.println(obj.decodeString1("3[a]2[bc]"));
    }
}
