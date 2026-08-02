package com.example.demo;

public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        if (s.isEmpty()) return 0;
        s = s.trim();

        int i = s.length() - 1;
        int count = 0;
        while (i > 0) {
            if (s.charAt(i) == ' ') {
                return count;
            }
            count++;
            i--;
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "Hello World";
        String s1 = "   fly me   to   the moon  ";
        String s2 = "luffy is still joyboy";


        LengthOfLastWord wl = new LengthOfLastWord();
        System.out.println("Length : " + wl.lengthOfLastWord(s));
        System.out.println("Length : " + wl.lengthOfLastWord(s1));
        System.out.println("Length : " + wl.lengthOfLastWord(s2));


    }
}
