package com.example.demo.ratelimitterdemo;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SlidingWindowRateLimiter implements RateLimiter {

    private final int limit;
    private final long windowMillis;

    private final ConcurrentHashMap<
            String,
            ClientWindow> clients =
            new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(
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

        ClientWindow clientWindow =
                clients.computeIfAbsent(
                        clientId,
                        key -> new ClientWindow());

        synchronized (clientWindow) {

            long now =
                    System.currentTimeMillis();

            removeExpired(
                    clientWindow.timestamps,
                    now);

            if (clientWindow.timestamps.size()
                    + permits > limit) {

                long retryAfter = 0;

                if (!clientWindow.timestamps.isEmpty()) {

                    long oldest =
                            clientWindow.timestamps
                                    .peekFirst();

                    retryAfter =
                            windowMillis -
                                    (now - oldest);
                }

                return RateLimitResult.rejected(
                        Math.max(
                                0,
                                limit -
                                        clientWindow.timestamps.size()),
                        Math.max(0, retryAfter));
            }

            for (int i = 0; i < permits; i++) {
                clientWindow.timestamps.addLast(now);
            }

            long remaining =
                    limit -
                            clientWindow.timestamps.size();

            return RateLimitResult.allowed(
                    Math.max(0, remaining));
        }
    }

    private void removeExpired(
            Deque<Long> timestamps,
            long now) {

        long expirationTime =
                now - windowMillis;

        while (!timestamps.isEmpty() &&
                timestamps.peekFirst()
                        <= expirationTime) {

            timestamps.pollFirst();
        }
    }

    private static class ClientWindow {

        Deque<Long> timestamps =
                new ArrayDeque<>();
    }
}


