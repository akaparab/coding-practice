package com.example.demo;

public class ClimbStairs {
    public int climbStairs(int n) {
        if (n <= 2) return n;

        return climbStairs(n - 1) + climbStairs(n - 2);

    }

    public static void main(String[] args) {
        int n = 5;
        ClimbStairs obj = new ClimbStairs();
        System.out.println(obj.climbStairs(1));
        System.out.println(obj.climbStairs(2));
        System.out.println(obj.climbStairs(3));
        System.out.println(obj.climbStairs(4));
        System.out.println(obj.climbStairs(5));
        System.out.println(obj.climbStairs(6));

    }
}
