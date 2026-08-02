package com.example.demo;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class HitCounter {
    Queue<Integer> queue;
    int count;

    public HitCounter() {
        queue = new LinkedList<>();
        count = 0;
    }

    public void hit(int timestamp) {
        queue.offer(timestamp);
    }

    public int getHits(int timestamp) {
        while (!queue.isEmpty()) {
            int diff = timestamp - queue.peek();
            if (diff >= 300) this.queue.remove();
            else break;
        }
        System.out.println(queue.size());
        return queue.size();
    }

    public static void main(String[] args) {
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1);       // hit at timestamp 1.
        hitCounter.hit(2);       // hit at timestamp 2.
        hitCounter.hit(3);       // hit at timestamp 3.
        hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
        hitCounter.hit(300);     // hit at timestamp 300.
        hitCounter.getHits(300); // get hits at timestamp 300, return 4.
        hitCounter.getHits(301); // get hits at timestamp 301, return 3.
    }
}
