package com.example.demo.heap;

import java.util.PriorityQueue;

public class KthLatgestElement {
    public Integer kthLargest(int[] nums, Integer k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {

            if (!pq.isEmpty() && pq.peek() < nums[i]) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 2, 1, 4};
        int k = 2;

        KthLatgestElement lm = new KthLatgestElement();
        System.out.println(lm.kthLargest(nums, k));

    }
}
