package com.example.demo.ratelimitterdemo;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBucketRateLimiter implements RateLimiter {


    // ============================================================
    // 7. TOKEN BUCKET
    // ===========================================================

    private final int capacity;
    private final double refillRatePerSecond;

    private final ConcurrentHashMap<
            String,
            Bucket> clients =
            new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(
            int capacity,
            double refillRatePerSecond) {

        this.capacity = capacity;
        this.refillRatePerSecond =
                refillRatePerSecond;
    }

    @Override
    public RateLimitResult allow(
            String clientId,
            int permits) {

        if (permits <= 0) {
            throw new IllegalArgumentException(
                    "Permits must be > 0");
        }

        if (permits > capacity) {

            return RateLimitResult.rejected(
                    0,
                    Long.MAX_VALUE);
        }

        Bucket bucket =
                clients.computeIfAbsent(
                        clientId,
                        key -> new Bucket(
                                capacity,
                                System.nanoTime()));

        synchronized (bucket) {

            long now =
                    System.nanoTime();

            refill(
                    bucket,
                    now);

            if (bucket.tokens >= permits) {

                bucket.tokens -= permits;

                long remaining =
                        (long) bucket.tokens;

                return RateLimitResult.allowed(
                        remaining);
            }

            double missing =
                    permits - bucket.tokens;

            long retryAfterMillis =
                    (long) Math.ceil(
                            missing /
                                    refillRatePerSecond
                                    * 1000);

            return RateLimitResult.rejected(
                    (long) bucket.tokens,
                    retryAfterMillis);
        }
    }

    private void refill(
            Bucket bucket,
            long now) {

        long elapsedNanos =
                now -
                        bucket.lastRefillTime;

        if (elapsedNanos <= 0) {
            return;
        }

        double elapsedSeconds =
                elapsedNanos /
                        1_000_000_000.0;

        double tokensToAdd =
                elapsedSeconds *
                        refillRatePerSecond;

        bucket.tokens =
                Math.min(
                        capacity,
                        bucket.tokens +
                                tokensToAdd);

        bucket.lastRefillTime = now;
    }

    private static class Bucket {

        double tokens;
        long lastRefillTime;

        Bucket(
                double tokens,
                long lastRefillTime) {

            this.tokens = tokens;
            this.lastRefillTime =
                    lastRefillTime;
        }

    }
}
