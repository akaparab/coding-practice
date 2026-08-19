package com.example.demo.ratelimitterdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RateLimitResult {
    private final boolean allowed;
    private final long remaining;
    private final long retryAfterMillis;


    public static RateLimitResult allowed(long remaining) {
        return new RateLimitResult(
                true,
                remaining,
                0);
    }

    public static RateLimitResult rejected(
            long remaining,
            long retryAfterMillis) {

        return new RateLimitResult(
                false,
                remaining,
                retryAfterMillis);
    }

    @Override
    public String toString() {
        return "RateLimitResult{" +
                "allowed=" + allowed +
                ", remaining=" + remaining +
                ", retryAfterMillis=" +
                retryAfterMillis +
                '}';
    }
}
