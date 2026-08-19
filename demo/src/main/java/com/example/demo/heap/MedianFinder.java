package com.example.demo.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    // Max Heap - stores smaller half
    private PriorityQueue<Integer> left;

    // Min Heap - stores larger half
    private PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>((a, b) -> b - a);
        right = new PriorityQueue<>();
    }

    public void addNum(int num) {

        // Step 1: Add to max heap
        left.offer(num);

        // Step 2: Maintain ordering
        right.offer(left.poll());

        // Step 3: Balance heaps
        if (right.size() > left.size()) {
            left.offer(right.poll());
        }
    }

    public double findMedian() {

        if (left.size() > right.size()) {
            return left.peek();
        }

        return (left.peek() + right.peek()) / 2.0;
    }

    public static void main(String[] args) {

        MedianFinder mf = new MedianFinder();

        mf.addNum(2);
        mf.addNum(3);

        System.out.println(mf.findMedian());   // 2.5

        mf.addNum(4);

        System.out.println(mf.findMedian());   // 3.0
    }

}
