package com.example.demo;

public class DistributeMoney {
    public int distMoney(int money, int children) {
        if (children > money) return -1;

        int x = money / 8;
        if (x == children) return x;
        int r = money % 8;

        if (children > x && r > children) {
            if (r == 4) x--;
        }
        return x;

    }

    public static void main(String[] args) {
        DistributeMoney obj = new DistributeMoney();
        System.out.println(obj.distMoney(20, 3));
        System.out.println(obj.distMoney(21, 3));
        System.out.println(obj.distMoney(24, 3));

    }
}
