package com.example.demo.ratelimitter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLogRateLimitter {
    private final int maxRequests;
    private final long windowSizeMillis;

    private final ConcurrentHashMap<String, Deque<Long>> userRequests =
            new ConcurrentHashMap<>();

    public SlidingWindowLogRateLimitter(int maxRequests,
                                        long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    public boolean allowRequest(String userId) {

        long now = System.currentTimeMillis();

        Deque<Long> requests =
                userRequests.computeIfAbsent(
                        userId,
                        k -> new ArrayDeque<>());

        synchronized (requests) {

            // Remove expired timestamps
            while (!requests.isEmpty()
                    && now - requests.peekFirst() >= windowSizeMillis) {
                requests.pollFirst();
            }

            if (requests.size() >= maxRequests) {
                return false;
            }

            requests.addLast(now);
            return true;
        }
    }

    public static void main(String[] args) throws Exception {

        SlidingWindowLogRateLimitter limiter =
                new SlidingWindowLogRateLimitter(
                        3,
                        10_000);

        System.out.println(limiter.allowRequest("user1"));
        System.out.println(limiter.allowRequest("user1"));
        System.out.println(limiter.allowRequest("user1"));

        // Rejected
        System.out.println(limiter.allowRequest("user1"));
    }
}


