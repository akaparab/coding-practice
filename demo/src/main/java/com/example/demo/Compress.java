package com.example.demo;

public class Compress {
    public String compress(char[] chars) {
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                count++;
            } else {
                sb.append(chars[i]).append(count);
                count = 1;
            }
        }
        sb.append(chars[chars.length - 1]).append(count);
        return sb.toString();

    }

    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        Compress obj = new Compress();
        System.out.println(obj.compress(chars));

    }
}
