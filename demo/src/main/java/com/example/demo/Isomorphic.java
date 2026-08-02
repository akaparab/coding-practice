package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Isomorphic {

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> sTot = new HashMap<>();
        Map<Character, Character> tTos = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Character sChar = s.charAt(i);
            Character tChar = t.charAt(i);

            if (sTot.containsKey(sChar)) {
                if (tChar != sTot.get(sChar)) return false;
                sTot.put(sChar, tChar);
            }

            if (tTos.containsKey(tChar)) {
                if (sChar != tTos.get(sChar)) return false;
                tTos.put(tChar, sChar);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "egg";
        String t = "add";
        Isomorphic iso = new Isomorphic();
        System.out.println("is Iso : " + iso.isIsomorphic(s, t));

        /**
         * Time Complexity: O(N). We process each character in both the strings exactly once to determine if the strings are isomorphic.
         * Space Complexity: O(1) since the size of the ASCII character set is fixed and the keys in our
         * dictionary are all valid ASCII characters according to the problem statement.
         */

    }
}
