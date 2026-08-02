package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {

    Map<Character, Integer> map = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100,
            'D', 500, 'M', 1000);

    public int romanConvertToInt1(String s) {
        if (s.isEmpty() || s.isBlank()) return 0;

        if (s.length() == 1) {
            return map.get(s.charAt(0));
        }

        int i = 1;
        int j = s.length();
        int num = map.get(s.charAt(0));

        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch == 'M' || ch == 'D' || ch == 'C' || ch == 'X' || ch == 'L' || ch == 'I') {
                //    if (ch == 'M' || ch == 'D' || ch == 'C' || ch == 'X' || ch == 'L' || ch == 'V' || ch == 'I') {
                if (i + 1 < j) {
                    char ch1 = s.charAt(i + 1);

                    if (ch != ch1 && (ch1 == 'M' || ch1 == 'D' || ch1 == 'C' || ch1 == 'X' ||
                            ch1 == 'L' || ch1 == 'V') && (map.get(ch1) > map.get(ch))) {
                        int num1 = map.get(ch1) - map.get(ch);
                        num += num1;
                        i++;
                    } else {
                        num += map.get(ch);
                    }
                } else {
                    num += map.get(ch);
                }
            } else {
                num += map.get(ch);
            }
            i++;
        }
        return num;
    }

    public int romanConvertToInt(String s) {
        if (s.isEmpty()) return 0;

        if (s.length() == 1) {
            return map.get(s.charAt(0));
        }

        int i = 1;
        int j = s.length();
        int num = map.get(s.charAt(0));

        while (i < s.length()) {
            char ch = s.charAt(i);
            if (i + 1 < j) {
                char ch1 = s.charAt(i + 1);
                if (ch != ch1 && map.get(ch1) > map.get(ch)) {
                    int num1 = map.get(ch1) - map.get(ch);
                    num += num1;
                    i++;
                } else {
                    num += map.get(ch);
                }
            } else {
                num += map.get(ch);
            }
            i++;
        }
        return num;
    }

    public int romanToInt(String s) {
        int num = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int currentVal = map.get(s.charAt(i));
            // Check if current value is less than next value (subtraction case)
            if (i < n - 1 && currentVal < map.get(s.charAt(i + 1))) {
                num -= currentVal;  // Subtract current value
            } else {
                num += currentVal;  // Add current value
            }
        }
        return num;
    }

    public static void main(String[] args) {
        String roman = "III";
        String roman1 = "LVIII";
        String roman2 = "MCMXCIV";
        String roman3 = "XXXIV";
        String roman4 = "CXLIV";
        String roman5 = "CXXXVIII";
        RomanToInt rt = new RomanToInt();
        System.out.println("Roman=" + roman + " Int = " + rt.romanConvertToInt(roman));
        System.out.println("Roman=" + roman1 + " Int = " + rt.romanConvertToInt(roman1));
        System.out.println("Roman=" + roman2 + " Int = " + rt.romanConvertToInt(roman2));
        System.out.println("Roman=" + roman3 + " Int = " + rt.romanConvertToInt(roman3));
        System.out.println("Roman=" + roman4 + " Int = " + rt.romanConvertToInt(roman4));
        System.out.println("Roman=" + roman5 + " Int = " + rt.romanConvertToInt(roman5));

        System.out.println("Roman=" + roman + " Int = " + rt.romanToInt(roman));
        System.out.println("Roman=" + roman1 + " Int = " + rt.romanToInt(roman1));
        System.out.println("Roman=" + roman2 + " Int = " + rt.romanToInt(roman2));
        System.out.println("Roman=" + roman3 + " Int = " + rt.romanToInt(roman3));
        System.out.println("Roman=" + roman4 + " Int = " + rt.romanToInt(roman4));
        System.out.println("Roman=" + roman5 + " Int = " + rt.romanToInt(roman5));

        // Time complexity O(n)
        //space complexity O(1) The map has a fixed size (7 Roman numeral mappings) - constant space

    }
}
