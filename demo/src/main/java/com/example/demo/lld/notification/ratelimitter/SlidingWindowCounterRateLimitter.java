package com.example.demo.lld.notification.ratelimitter;

import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterRateLimitter {
    private int maxRequests;
    private long windowSizeMillis;

    private final ConcurrentHashMap<String, UserWindow> userWindows =
            new ConcurrentHashMap<>();

    public SlidingWindowCounterRateLimitter(
            int maxRequests,
            long windowSizeMillis) {

        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    public boolean allowRequest(String userId) {

        long now = System.currentTimeMillis();

        UserWindow window = userWindows.computeIfAbsent(
                userId,
                id -> {
                    UserWindow w = new UserWindow();
                    long currentWindow =
                            (now / windowSizeMillis) * windowSizeMillis;

                    w.currentWindowStart = currentWindow;
                    return w;
                });

        synchronized (window) {

            long currentWindow =
                    (now / windowSizeMillis) * windowSizeMillis;

            // Window rollover
            if (currentWindow > window.currentWindowStart) {

                // Shift current -> previous
                window.previousWindowStart = window.currentWindowStart;
                window.previousCount = window.currentCount;

                // New current window
                window.currentWindowStart = currentWindow;
                window.currentCount = 0;

                // If more than one window elapsed,
                // previous window is no longer relevant
                if (currentWindow - window.previousWindowStart >
                        windowSizeMillis) {

                    window.previousCount = 0;
                }
            }

            long elapsed =
                    now - window.currentWindowStart;

            double overlapRatio =
                    (double) (windowSizeMillis - elapsed)
                            / windowSizeMillis;

            double effectiveCount =
                    window.currentCount +
                            (window.previousCount * overlapRatio);

            if (effectiveCount >= maxRequests) {
                return false;
            }

            window.currentCount++;
            return true;
        }
    }

    public static void main(String[] args) throws Exception {

        SlidingWindowCounterRateLimitter limiter =
                new SlidingWindowCounterRateLimitter(
                        5,      // max requests
                        5000    // 5 second window
                );

        String user = "user1";

        System.out.println("=== First burst ===");

        for (int i = 1; i <= 6; i++) {
            boolean allowed = limiter.allowRequest(user);
            System.out.printf(
                    "Request %d -> %s%n",
                    i,
                    allowed ? "ALLOWED" : "REJECTED");
        }

        System.out.println("\nSleeping 3 seconds...");
        Thread.sleep(3000);

        System.out.println("\n=== During sliding overlap ===");

        for (int i = 1; i <= 3; i++) {
            boolean allowed = limiter.allowRequest(user);
            System.out.printf(
                    "Request %d -> %s%n",
                    i,
                    allowed ? "ALLOWED" : "REJECTED");
        }

        System.out.println("\nSleeping 3 more seconds...");
        Thread.sleep(3000);

        System.out.println("\n=== Previous window mostly expired ===");

        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.allowRequest(user);
            System.out.printf(
                    "Request %d -> %s%n",
                    i,
                    allowed ? "ALLOWED" : "REJECTED");
        }
    }


    private static class UserWindow {
        long currentWindowStart;
        int currentCount;

        long previousWindowStart;
        int previousCount;
    }
}

