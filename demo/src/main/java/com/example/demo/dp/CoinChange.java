package com.example.demo.dp;

import java.util.Arrays;

public class CoinChange {
    /**
     * Min required coins to form amount
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    System.out.println("coin[" + j + "]:" + coins[j] + "  dp[" + i + "]:" + dp[i] + " dp[" + i + " -coins[" + j + "]: " + dp[i - coins[j]]);
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    System.out.println("dp[" + i + "]:" + dp[i]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**
     * nUmber of different cpmbinations
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        long[] dp = new long[amount + 1]; // Use long to avoid overflow
        dp[0] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }

        //return dp[amount] <= Integer.MAX_VALUE ? (int) dp[amount] : -1;
        return (int) dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int sum = 11;
        CoinChange cc = new CoinChange();
        System.out.println("coinChange : " + cc.coinChange(coins, sum));
        System.out.println("change : " + cc.change(sum, coins));

    }

}
