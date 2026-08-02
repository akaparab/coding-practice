package com.example.demo;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public boolean isHappy1(int n) {
        int sum = 0;
        while (n / 10 > 0) {
            int val = n % 10;
            sum += val * val;
            n = n / 10;
            if (n < 10) {
                sum += n * n;
                if (sum == 1) return true;
                else {
                    n = sum;
                    sum = 0;
                    isHappy(n);
                }
            }

        }
        return false;
    }

    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>(); // Track visited numbers to detect cycles
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            int sum = 0;
            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            n = sum; // Update n to the sum of squares
        }
        return n == 1; // Return true only if we reached 1
    }


    public static void main(String[] args) {
        int n = 19;
        int n1 = 2;
        int n2 = 29;
        HappyNumber hn = new HappyNumber();
        System.out.println("is Happy Number : " + hn.isHappy(n));
        System.out.println("is Happy Number : " + hn.isHappy(n1));
        System.out.println("is Happy Number : " + hn.isHappy(n2));

    }
}
