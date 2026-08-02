package com.example.demo.slidingwindow;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MaxCardScore {
    public Integer maxScore(int[] cards, Integer k) {
        int total = 0;
        for (int card : cards) {
            total += card;
        }

        if (k == cards.length) {
            return total;
        }
        int sum = 0;
        int maxScore = 0;
        int start = 0;
        for (int i = 0; i < cards.length; i++) {
            sum += cards[i];
            if (i - start + 1 == cards.length - k) {
                maxScore = Math.max(total - sum, maxScore);
                sum -= cards[start];
                start++;
            }
        }
        return maxScore;

    }

    public Integer maxScore1(int[] cards, Integer k) {
        if (cards.length <= k) {
            return IntStream.of(cards).sum();
        }

        int bestScore = 0;
        for (int i = 0; i <= k; i++) {
            int leftSum = IntStream.of(
                    Arrays.copyOfRange(cards, 0, i)
            ).sum();
            int remainingPicksNum = k - i;
            int rightSum = IntStream.of(
                    Arrays.copyOfRange(
                            cards,
                            cards.length - remainingPicksNum,
                            cards.length
                    )
            ).sum();
            bestScore = Math.max(leftSum + rightSum, bestScore);
        }

        return bestScore;
    }


    public static void main(String[] args) {
        int[] cards = {2, 11, 4, 5, 3, 9, 2};
        int k = 3;
        MaxCardScore mcs = new MaxCardScore();
        System.out.println(mcs.maxScore(cards, 3));
        System.out.println(mcs.maxScore1(cards, 3));
    }
}
