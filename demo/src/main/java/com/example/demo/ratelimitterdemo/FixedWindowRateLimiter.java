package com.example.demo.ratelimitterdemo;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class FixedWindowRateLimiter implements RateLimiter {

    private final int limit;
    private final long windowMillis;

    private final ConcurrentHashMap<String, Window> clients =
            new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(
            int limit,
            long windowMillis) {

        this.limit = limit;
        this.windowMillis = windowMillis;
    }

    @Override
    public RateLimitResult allow(
            String clientId,
            int permits) {

        if (permits <= 0) {
            throw new IllegalArgumentException(
                    "Permits must be > 0");
        }

        long now = System.currentTimeMillis();

        Window window = clients.compute(
                clientId,
                (key, existing) -> {

                    if (existing == null ||
                            now - existing.startTime
                                    >= windowMillis) {

                        return new Window(
                                now,
                                permits);
                    }

                    existing.count += permits;

                    return existing;
                });

        if (window.count <= limit) {

            long remaining =
                    Math.max(
                            0,
                            limit - window.count);

            return RateLimitResult.allowed(
                    remaining);
        }

        long retryAfter =
                windowMillis -
                        (now - window.startTime);

        return RateLimitResult.rejected(
                0,
                Math.max(0, retryAfter));
    }

    private static class Window {

        long startTime;
        long count;

        Window(
                long startTime,
                long count) {

            this.startTime = startTime;
            this.count = count;
        }
    }
}
