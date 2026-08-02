package com.example.demo.slidingwindow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterSlidingWindow {
    private final int limit;
    private final long windowSizeMillis;
    private final ConcurrentHashMap<Long, AtomicInteger> windows = new ConcurrentHashMap<>();

    public RateLimiterSlidingWindow(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        long currentWindowKey = currentTime / windowSizeMillis;
        long previousWindowKey = currentWindowKey - 1;

        // Get counts for current and previous windows
        int currentCount = windows.getOrDefault(currentWindowKey, new AtomicInteger(0)).get();
        int previousCount = windows.getOrDefault(previousWindowKey, new AtomicInteger(0)).get();

        // Calculate weighted overlap
        double weight = 1 - (double) (currentTime % windowSizeMillis) / windowSizeMillis;
        double estimatedCount = currentCount + (previousCount * weight);

        if (estimatedCount < limit) {
            windows.computeIfAbsent(currentWindowKey, k -> new AtomicInteger(0)).incrementAndGet();
            cleanupOldWindows(previousWindowKey);
            return true;
        }
        return false;
    }

    public synchronized boolean allowRequests(String key, long timestampMillis) {
        long currentTime = System.currentTimeMillis();
        long currentWindowKey = currentTime / windowSizeMillis;
        long previousWindowKey = currentWindowKey - 1;

        // Get counts for current and previous windows
        int currentCount = windows.getOrDefault(currentWindowKey, new AtomicInteger(0)).get();
        int previousCount = windows.getOrDefault(previousWindowKey, new AtomicInteger(0)).get();

        // Calculate weighted overlap
        double weight = 1 - (double) (currentTime % windowSizeMillis) / windowSizeMillis;
        double estimatedCount = currentCount + (previousCount * weight);

        if (estimatedCount < limit) {
            windows.computeIfAbsent(currentWindowKey, k -> new AtomicInteger(0)).incrementAndGet();
            cleanupOldWindows(previousWindowKey);
            return true;
        }
        return false;
    }

    private void cleanupOldWindows(long previousWindowKey) {
        // Simple cleanup for demo; in production, use a scheduled task
        windows.keySet().removeIf(key -> key < previousWindowKey);
    }

    public static void main(String[] args) {
        List<RateLimitTestCase> testCases = new ArrayList<>();


        // Base Configuration: 10 requests / 60-second window (60,000 ms)

        // --- SCENARIO A: Simple Linear Requests (Baseline Success) ---
        testCases.add(new RateLimitTestCase("First request at 5s", 5_000L, 1, true, "Window empty"));
        testCases.add(new RateLimitTestCase("Second request at 15s", 15_000L, 1, true, "Count is 2/10"));
        testCases.add(new RateLimitTestCase("Third request at 40s", 40_000L, 1, true, "Count is 3/10"));


        // --- SCENARIO B: Hit Strict Limit & Verify Sliding Eviction ---
        // Simulating 6 more requests coming in right after to hit 9/10 total
        testCases.add(new RateLimitTestCase("Fill window to 9 requests at 42s", 42_000L, 6, true, "Count is 9/10"));
        testCases.add(new RateLimitTestCase("10th request hits strict limit at 55s", 55_000L, 1, true, "Count is 10/10"));
        testCases.add(new RateLimitTestCase("11th request rejected at 58s", 58_000L, 1, false, "Rate limit exceeded (11/10)"));

        // At 1m 05s (65,000ms), lookback window is 5s to 65s.
        // The first 2 requests (at 5s and 15s) drop off. Active count becomes 8. New request brings it to 9.
        testCases.add(new RateLimitTestCase("Request at 65s allowed after partial eviction", 65_000L, 1, true, "Historical requests aged out"));


        // --- SCENARIO C: Perimeter / Boundary Burst Challenge ---
        // Reset baseline mentally: Let's jump ahead to a completely clean window later in time
        long cleanWindowStart = 300_000L; // 5 minutes later

        testCases.add(new RateLimitTestCase("Burst 1: 5 requests at end of minute boundary", cleanWindowStart + 59_000L, 5, true, "First half of burst"));
        testCases.add(new RateLimitTestCase("Burst 2: 5 requests right after minute turns", cleanWindowStart + 61_000L, 5, true, "Hits strict limit of 10"));
        testCases.add(new RateLimitTestCase("Burst 3: Rejected immediately due to sliding overlap", cleanWindowStart + 62_000L, 2, false, "Window still contains prior 10 requests"));


        // --- SCENARIO D: Weighted Estimation Progress Variant ---
        // Looks back across the progress ratio of adjacent 60s blocks
        long trackingBlockStart = 600_000L; // 10 minutes later

        // Simulating Window 1: 10 requests sent at the start of this block
        testCases.add(new RateLimitTestCase("Setup: 10 requests in Block 1", trackingBlockStart + 5_000L, 10, true, "Fills Block 1"));
        // Simulating Window 2 progress: 2 requests already sent in Block 2
        testCases.add(new RateLimitTestCase("Setup: 2 requests in Block 2", trackingBlockStart + 65_000L, 2, true, "Fills Block 2 base"));

        // Test at exactly 30% progress into Window 2 (18 seconds past the minute mark)
        // Calculated remaining ratio from Block 1: 10 * (1 - 0.30) = 7. Current Block 2: 2. Total estimated = 9.
        testCases.add(new RateLimitTestCase("Weighted calculation allow check at 30% progress", trackingBlockStart + 78_000L, 1, true, "Estimated count is 9/10"));
        testCases.add(new RateLimitTestCase("Weighted calculation reject check immediately after", trackingBlockStart + 79_000L, 2, false, "Estimated count swings past 10"));

        RateLimiterSlidingWindow limiter = new RateLimiterSlidingWindow(10, 60_000); // 10 req / 60s

        // Process bulk increments or individual hits
        testCases.forEach(testCase -> {
            boolean result = limiter.allowRequest();
        });
    }

    @Data
    @RequiredArgsConstructor
    @AllArgsConstructor
    static class RateLimitTestCase {
        String description;
        long timestampMillis;
        int requestCount; // For bulk burst testing
        boolean expectedAllow;
        String failureReason;
    }

}

