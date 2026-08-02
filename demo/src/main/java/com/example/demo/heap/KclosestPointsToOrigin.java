package com.example.demo.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KclosestPointsToOrigin {

    public int[][] kClosest1(int[][] points, Integer k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (int i = 0; i < k; i++) {
            int x = points[i][0];
            int y = points[i][1];

            int dist = (x * x) + (y * y);
            pq.offer(new int[]{dist, i});
        }

        for (int i = k; i < points.length; i++) {
            if (!pq.isEmpty()) {
                int[] el = pq.peek();
                int dist = points[i][1] * points[i][1] + points[i][0] * points[i][0];
                if (el[0] > dist) {
                    pq.poll();
                    pq.offer(new int[]{dist, i});
                }
            }
        }

        int[][] res = new int[k][];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i++] = points[pq.poll()[1]];
        }
        return res;
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            int distance = x * x + y * y;

            if (heap.size() < k) {
                heap.offer(new int[]{distance, i});
            } else if (distance < heap.peek()[0]) {
                heap.poll();
                heap.offer(new int[]{distance, i});
            }
        }

        int[][] result = new int[k][];
        for (int[] p : heap) {
            result[--k] = points[p[1]];
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] points = {{3, 4}, {2, 2}, {1, 1}, {0, 0}, {5, 5}};
        int k = 3;
        int[][] points1 = {{46341, 0}, {1, 1}, {46340, 0}};
        ;
        int k1 = 1;
        KclosestPointsToOrigin po = new KclosestPointsToOrigin();

        //int[][] res = po.kClosest(points, k);
        int[][] res = po.kClosest(points1, k1);

        for (int[] it : res) {
            System.out.println(Arrays.toString(it));
        }

    }
}
