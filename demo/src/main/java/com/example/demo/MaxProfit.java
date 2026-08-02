package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class MaxProfit {
    public int maxProfit1(int[] prices) {

        if (prices.length < 2) {
            return 0;
        }

        int i = 1;
        int max = 0;
        int dayIndex = -1;

        while (i < prices.length) {
            if (prices[i - 1] < prices[i]) {
                if (max == 0) {
                    max = prices[i] - prices[i - 1];
                    dayIndex = i - 1;
                } else {
                    max = Math.max(prices[i] - prices[i - 1], prices[i] - prices[dayIndex]);
                    if (max == prices[i] - prices[i - 1]) {
                        dayIndex = i - 1;
                    }
                }
            }
            i++;
        }
        return max;
    }

    public int maxProfit(int[] prices) {

        if (prices.length < 2) {
            return 0;
        }

        int minPrice = prices[0];  // Start with first day
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // Update maxProfit using current price and minPrice
            if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }

            // Update minPrice if current price is lower
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        MaxProfit mp = new MaxProfit();
        System.out.println("Max price : " + mp.maxProfit(prices));

        // Time complexity o(n)
        // space complexity o(1)
    }

}
