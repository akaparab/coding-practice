package com.example.demo.ratelimitterdemo;

public class RateLimiterFactory {
    public static RateLimiter create(
            RateLimitConfig config) {

        return switch (config.getAlgorithm()) {

            case FIXED_WINDOW -> new FixedWindowRateLimiter(
                    config.getLimit(),
                    config.getWindowMillis());

            case SLIDING_WINDOW -> new SlidingWindowRateLimiter(
                    config.getLimit(),
                    config.getWindowMillis());

            case TOKEN_BUCKET -> new TokenBucketRateLimiter(
                    config.getCapacity(),
                    config.getRefillRatePerSecond());

            case LEAKY_BUCKET -> new LeakyBucketRateLimiter(
                    config.getCapacity(),
                    config.getRefillRatePerSecond());
        };
    }

}
