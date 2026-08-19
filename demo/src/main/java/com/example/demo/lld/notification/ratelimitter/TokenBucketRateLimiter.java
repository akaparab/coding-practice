package com.example.demo.lld.notification.ratelimitter;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter {

    private final int capacity = 100;
    private final double refillRate = 100.0 / 60.0;

    private final ConcurrentHashMap<String, TokenBucket>
            buckets = new ConcurrentHashMap<>();

    public boolean allow(String userId) {

        TokenBucket bucket =
                buckets.computeIfAbsent(
                        userId,
                        id -> new TokenBucket(
                                capacity,
                                refillRate
                        )
                );

        return bucket.allowRequest();
    }

    public void processRequest() {
        System.out.println("Processed Request");
    }

    public static void main(String[] args) {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter();

        if (limiter.allow("user123")) {
            limiter.processRequest();
        } else {
            System.out.println("Too many requests return 429");
        }
    }
}
