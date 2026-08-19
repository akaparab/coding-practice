package com.example.demo.ratelimitterdemo;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class LeakyBucketRateLimiter implements RateLimiter {

    private final int capacity;

    /*
     * Number of requests that can leave
     * the bucket per second.
     */
    private final double leakRatePerSecond;

    private final ConcurrentHashMap<
            String,
            Bucket> clients =
            new ConcurrentHashMap<>();

    public LeakyBucketRateLimiter(
            int capacity,
            double leakRatePerSecond) {

        this.capacity = capacity;
        this.leakRatePerSecond =
                leakRatePerSecond;
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
                                0,
                                System.nanoTime()));

        synchronized (bucket) {

            long now =
                    System.nanoTime();

            leak(bucket, now);

            if (bucket.queueSize + permits
                    <= capacity) {

                bucket.queueSize += permits;

                long remaining =
                        capacity -
                                bucket.queueSize;

                return RateLimitResult.allowed(
                        remaining);
            }

            double excess =
                    bucket.queueSize +
                            permits -
                            capacity;

            long retryAfterMillis =
                    (long) Math.ceil(
                            excess /
                                    leakRatePerSecond
                                    * 1000);

            return RateLimitResult.rejected(
                    Math.max(
                            0,
                            capacity -
                                    bucket.queueSize),
                    retryAfterMillis);
        }
    }

    private void leak(
            Bucket bucket,
            long now) {

        long elapsedNanos =
                now -
                        bucket.lastLeakTime;

        if (elapsedNanos <= 0) {
            return;
        }

        double elapsedSeconds =
                elapsedNanos /
                        1_000_000_000.0;

        long leaked =
                (long) (
                        elapsedSeconds *
                                leakRatePerSecond);

        if (leaked > 0) {

            bucket.queueSize =
                    Math.max(
                            0,
                            bucket.queueSize -
                                    leaked);

            bucket.lastLeakTime = now;
        }
    }

    private static class Bucket {

        long queueSize;
        long lastLeakTime;

        Bucket(
                long queueSize,
                long lastLeakTime) {

            this.queueSize = queueSize;
            this.lastLeakTime =
                    lastLeakTime;
        }
    }
}
