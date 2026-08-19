package com.example.demo.ratelimitterdemo;

import lombok.Getter;

@Getter
public class RateLimitConfig {
    private final RateLimitterAlgorithm algorithm;

    /*
     * Generic limit.
     *
     * Fixed Window:
     *      max requests per window
     *
     * Sliding Window:
     *      max requests per window
     *
     * Token Bucket:
     *      not directly used
     *
     * Leaky Bucket:
     *      not directly used
     */
    private final int limit;

    /*
     * Window size for Fixed/Sliding Window.
     */
    private final long windowMillis;

    /*
     * Token/Leaky bucket capacity.
     */
    private final int capacity;

    /*
     * Tokens/messages added per second.
     */
    private final double refillRatePerSecond;

    public RateLimitConfig(RateLimitterAlgorithm algorithm, int limit,
                           long windowMillis, int capacity, double refillRatePerSecond) {
        this.algorithm = algorithm;
        this.limit = limit;
        this.windowMillis = windowMillis;
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }
}
