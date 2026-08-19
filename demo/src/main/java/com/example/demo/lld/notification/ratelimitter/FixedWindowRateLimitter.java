package com.example.demo.lld.notification.ratelimitter;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimitter {

    private final int maxRequests;
    private final long windowSizeMillis;

    private volatile long windowStart;
    private final AtomicInteger requestCount;

    public FixedWindowRateLimitter(int maxRequests, long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
        this.windowStart = System.currentTimeMillis();
        this.requestCount = new AtomicInteger(0);
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        // Reset window if expired
        if (currentTime - windowStart >= windowSizeMillis) {
            windowStart = currentTime;
            requestCount.set(0);
        }

        if (requestCount.get() < maxRequests) {
            requestCount.incrementAndGet();
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        FixedWindowRateLimitter limiter =
                new FixedWindowRateLimitter(5, 10_000); // 5 requests per 10 seconds

        for (int i = 1; i <= 10; i++) {
            System.out.println(
                    "Request " + i + ": " +
                            (limiter.allowRequest() ? "Allowed" : "Rejected"));
        }
    }

}


