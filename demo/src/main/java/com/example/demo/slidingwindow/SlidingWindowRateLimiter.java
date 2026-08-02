package com.example.demo.slidingwindow;

import java.time.Instant;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SlidingWindowRateLimiter {
    private final int limit;
    private final long windowMillis;
    private final ConcurrentHashMap<String, Deque<Long>> requestStore = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int limit, long windowMillis) {
        this.limit = limit;
        this.windowMillis = windowMillis;
    }

    public boolean allowRequest(String key) {
        long now = Instant.now().toEpochMilli();
        Deque<Long> deque = requestStore.computeIfAbsent(key, k -> new ConcurrentLinkedDeque<>());
        synchronized (deque) {
            removeExpired(deque, now);
            if (deque.size() >= limit) {
                return false;
            }
            deque.addLast(now);
            return true;
        }
    }


    private void removeExpired(Deque<Long> deque, long now) {
        while (!deque.isEmpty() && now - deque.peekFirst() >= windowMillis) {
            deque.pollFirst();
        }
    }

    public static void main(String[] args) throws Exception {
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(5, 10000);

        for (int i = 0; i < 15; i++) {
            boolean allowed = limiter.allowRequest("user-1");
            System.out.println("Request " + i + " => " + allowed);
            Thread.sleep(1000);
        }
    }
}


