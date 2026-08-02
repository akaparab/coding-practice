package com.example.demo.intervals;

import java.util.Arrays;

public class MaxOrderProcessed {
    public int maxOrdersProcessed(int[][] orders) {
        Arrays.sort(orders, (a, b) -> a[1] - b[1]);
        int count = 1;
        int end = orders[0][1];

        for (int i = 1; i < orders.length; i++) {
            if (end <= orders[i][0]) {
                count++;
                end = orders[i][1];
            }
        }
        return count;

    }

    public static void main(String[] args) {
        int[][] orders = {{1, 3}, {2, 5}, {4, 6}};
        int[][] order1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        MaxOrderProcessed mp = new MaxOrderProcessed();
        System.out.println(mp.maxOrdersProcessed(orders));
        System.out.println(mp.maxOrdersProcessed(order1));

    }

}
