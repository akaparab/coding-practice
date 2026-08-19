package com.example.demo.ratelimitterdemo;

public class RateLimiterDemo {

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * 5 requests / second
         *
         * Bucket can hold 10 tokens.
         */
        RateLimitConfig config =
                new RateLimitConfig(
                        RateLimitterAlgorithm.TOKEN_BUCKET,
                        0,
                        0,
                        10,
                        5);

        RateLimiter limiter =
                RateLimiterFactory.create(config);

        String userId = "user-123";

        for (int i = 1; i <= 15; i++) {

            RateLimitResult result =
                    limiter.allow(userId);

            System.out.println(
                    "Request " + i +
                            " allowed=" +
                            result.isAllowed() +
                            ", remaining=" +
                            result.getRemaining() +
                            ", retryAfter=" +
                            result.getRetryAfterMillis());

            Thread.sleep(50);
        }
    }
}

