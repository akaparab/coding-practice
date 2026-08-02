package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class MaxProfitTrading {

    public int maximumProfit(int[] present, int[] future, int budget) {

        int[] dp = new int[budget + 1];

        for (int i = 0; i < present.length; i++) {

            int cost = present[i];
            int profit = future[i] - present[i];

            // Ignore stocks that don't generate profit
            if (profit <= 0)
                continue;

            for (int b = budget; b >= cost; b--) {
                dp[b] = Math.max(dp[b],
                        dp[b - cost] + profit);
            }
        }

        return dp[budget];
    }


    public static void main(String[] args) {
        int[] present = {5, 4, 6, 2, 3};
        int[] future = {8, 5, 4, 3, 5};
        int[] future1 = {8, 5, 4, 3, 5};
        int[] present1 = {15, 4, 6, 12, 3};
        int budget = 10;

        MaxProfitTrading mpt = new MaxProfitTrading();
        System.out.println(mpt.maximumProfit(present, future, budget));
        System.out.println(mpt.maximumProfit(present1, future1, budget));
    }
}
