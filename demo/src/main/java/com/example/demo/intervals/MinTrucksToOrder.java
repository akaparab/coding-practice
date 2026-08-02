package com.example.demo.intervals;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinTrucksToOrder {
    static class Order {
        int start;
        int end;

        Order(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int minTruckstoOrder(List<Order> orders) {
        orders.sort((a, b) -> Integer.compare(a.start, b.start));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Integer::compare);

        for (Order order : orders) {

            if (!pq.isEmpty()) {
                if (pq.peek() <= order.start) {
                    pq.poll();
                }
            }
            pq.offer(order.end);
        }
        return pq.size();
    }

    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order(1, 4),
                new Order(2, 5),
                new Order(7, 9),
                new Order(3, 6),
                new Order(8, 9)
        );

        System.out.println(MinTrucksToOrder.minTruckstoOrder(orders));
    }
}
