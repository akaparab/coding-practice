package com.example.demo.ratelimitterdemo;

public interface RateLimiter {
    RateLimitResult allow(
            String clientId,
            int permits);

    default RateLimitResult allow(String clientId) {
        return allow(clientId, 1);
    }
}
