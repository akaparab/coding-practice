package com.example.demo;

import java.util.Map;

public class FIbonacciNumber {
    Map<Integer, Integer> map = Map.of(0, 0, 1, 1);

    public int fib(int n) {
        if (n <= 1) return n;

        return fib(n - 1) + fib(n - 2);

    }

    public int fib1(int n) {
        if (n <= 1) return n;

        if (map.containsKey(n)) {
            return map.get(n);
        }
        map.put(n, fib(n - 1) + fib(n - 2));

        return map.get(n);
    }

    public static void main(String[] args) {
        int num = 5;
        FIbonacciNumber fn = new FIbonacciNumber();
        System.out.println(fn.fib(num));
        // time complexity o(2 power n)
        //space complexity o(n)
        System.out.println(fn.fib1(num));

        // time complexity o(N)
        // space complexity o(n)
    }
}
