package com.example.demo.ratelimitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindow {

    private static final int MAX_REQUESTS = 100;
    private static final long WINDOW_SIZE_MS = 60_000;

    private final Map<String, UserWindow> users =
            new ConcurrentHashMap<>();

    public boolean allow(String userId) {

        long currentTime = System.currentTimeMillis();

        UserWindow window = users.computeIfAbsent(
                userId,
                key -> new UserWindow(currentTime, 0)
        );

        synchronized (window) {

            // Current window expired
            if (currentTime - window.windowStart
                    >= WINDOW_SIZE_MS) {

                window.windowStart = currentTime;
                window.requestCount = 1;

                return true;
            }

            // Limit reached
            if (window.requestCount >= MAX_REQUESTS) {
                return false;
            }

            window.requestCount++;

            return true;
        }
    }

    private static class UserWindow {

        long windowStart;
        int requestCount;

        UserWindow(long windowStart, int requestCount) {
            this.windowStart = windowStart;
            this.requestCount = requestCount;
        }
    }

    public static void main(String[] args) {

        FixedWindow limiter = new FixedWindow();

        String userId = "user-123";

        int allowed = 0;
        int rejected = 0;

        for (int i = 1; i <= 105; i++) {

            if (limiter.allow(userId)) {
                allowed++;
                System.out.println(
                        "Request " + i + " → ALLOWED"
                );
            } else {
                rejected++;
                System.out.println(
                        "Request " + i + " → REJECTED"
                );
            }
        }

        System.out.println();
        System.out.println("Allowed = " + allowed);
        System.out.println("Rejected = " + rejected);
    }
}
