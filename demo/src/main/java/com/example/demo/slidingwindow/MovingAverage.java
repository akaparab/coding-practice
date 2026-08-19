package com.example.demo.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

public class MovingAverage {
    static class Trade {
        long timestamp;
        double price;

        Trade(long timestamp, double price) {
            this.timestamp = timestamp;
            this.price = price;
        }
    }

    private final long windowMillis;

    private final Deque<Trade> queue =
            new ArrayDeque<>();

    private double sum = 0.0;

    public MovingAverage(long windowMillis) {
        this.windowMillis = windowMillis;
    }

    public double addTrade(
            long timestamp,
            double price) {

        // Add new trade
        Trade trade =
                new Trade(timestamp, price);

        queue.offerLast(trade);
        sum += price;

        // Remove expired trades
        long cutoff =
                timestamp - windowMillis;

        while (!queue.isEmpty()
                && queue.peekFirst().timestamp < cutoff) {

            Trade expired =
                    queue.pollFirst();

            sum -= expired.price;
        }

        if (queue.isEmpty()) {
            return 0.0;
        }

        return sum / queue.size();
    }

    public static void main(String[] args) {
        MovingAverage average =
                new MovingAverage(60_000);

        System.out.println(average.addTrade(1000, 100));
        System.out.println(average.addTrade(20000, 102));
        System.out.println(average.addTrade(40000, 104));
        System.out.println(average.addTrade(70000, 110));
    }

}
