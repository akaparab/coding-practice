package com.example.demo.retry;

import java.util.concurrent.ThreadLocalRandom;

public class RetryUtil {

    public static <T> T execute(
            RetryableOperation<T> operation,
            int maxRetries,
            long initialDelayMs,
            long maxDelayMs) throws Exception {

        Exception lastException = null;

        for (int attempt = 0; attempt <= maxRetries; attempt++) {

            try {
                return operation.execute();

            } catch (Exception e) {
                lastException = e;

                if (attempt == maxRetries) {
                    break;
                }

                // Exponential backoff:
                // initialDelay * 2^attempt
                long exponentialDelay =
                        Math.min(
                                maxDelayMs,
                                initialDelayMs * (1L << attempt)
                        );

                // Full jitter:
                // random value between 0 and exponentialDelay
                long jitter =
                        ThreadLocalRandom.current()
                                .nextLong(exponentialDelay + 1);

                System.out.println(
                        "Attempt " + (attempt + 1)
                                + " failed. Retrying in "
                                + jitter + " ms"
                );

                Thread.sleep(jitter);
            }
        }

        throw lastException;
    }

    @FunctionalInterface
    interface RetryableOperation<T> {
        T execute() throws Exception;
    }

    public static void main(String[] args) {

        try {

            String result = RetryUtil.execute(
                    () -> callExternalService(),
                    5,          // max retries
                    100,        // initial delay
                    5000        // max delay
            );

            System.out.println("Result: " + result);

        } catch (Exception e) {
            System.out.println("Operation failed: " + e.getMessage());
        }
    }

    private static String callExternalService() {

        System.out.println("Calling external service...");

        // Simulate failure
        if (Math.random() < 0.8) {
            throw new RuntimeException("Service unavailable");
        }

        return "SUCCESS";
    }
}
