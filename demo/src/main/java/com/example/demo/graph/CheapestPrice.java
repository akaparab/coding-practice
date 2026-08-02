package com.example.demo.graph;

import java.util.*;

/**
 * Cheapest Flights Within K Stops
 */
public class CheapestPrice {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>();

        for (int[] flight : flights) {
            map.computeIfAbsent(flight[0], key -> new ArrayList<>())
                    .add(new int[]{flight[1], flight[2]});
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 0, 0});

        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] vals = queue.poll();
                int city = vals[0];
                int price = vals[1];
                int stops = vals[2];

                if (dst == city) {
                    best[city] = Math.min(best[city], price);
                }
                if (k < stops) {
                    continue;
                }

                if (!map.containsKey(city)) {
                    continue;
                }

                List<int[]> routes = map.get(city);
                for (int[] route : routes) {
                    int nextCity = route[0];
                    int cost = route[1];
                    if (best[nextCity] >
                            cost + price) {
                        best[nextCity] = cost + price;

                        queue.offer(new int[]{nextCity, cost + price, stops + 1});
                    }

                }
            }
        }

        return best[dst] == Integer.MAX_VALUE
                ? -1
                : best[dst];

    }

    public static void main(String[] args) {
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}, {2, 3, 100}};
        int n = 4;
        int src = 0;
        int dst = 3;
        int k = 2;
        CheapestPrice cp = new CheapestPrice();
        System.out.println(cp.findCheapestPrice(n, flights, src, dst, k));
    }

}
