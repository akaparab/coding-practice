package com.example.demo.timebasedstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeMapBinarySearch {
    private final Map<String, List<Version>> map;

    public TimeMapBinarySearch() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {

        map.computeIfAbsent(
                key,
                k -> new ArrayList<>()
        ).add(new Version(timestamp, value));
    }

    public String get(String key, int timestamp) {

        List<Version> versions = map.get(key);

        if (versions == null) {
            return "";
        }

        int left = 0;
        int right = versions.size() - 1;

        String result = "";

        while (left <= right) {

            int mid = left + (right - left) / 2;

            Version version = versions.get(mid);

            if (version.timestamp <= timestamp) {

                // Valid candidate
                result = version.value;

                // Try to find a later timestamp
                left = mid + 1;

            } else {

                // Timestamp is too large
                right = mid - 1;
            }
        }

        return result;
    }

    private static class Version {

        int timestamp;
        String value;

        Version(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    public static void main(String[] args) {

        TimeMap timeMap = new TimeMap();

        // -----------------------------------
        // Test 1: Basic set operations
        // -----------------------------------

        timeMap.set("foo", "bar1", 1);
        timeMap.set("foo", "bar2", 4);
        timeMap.set("foo", "bar3", 7);

        System.out.println("Test 1:");
        System.out.println(timeMap.get("foo", 1));
        // Expected: bar1

        System.out.println(timeMap.get("foo", 4));
        // Expected: bar2

        System.out.println(timeMap.get("foo", 7));
        // Expected: bar3


        // -----------------------------------
        // Test 2: Timestamp between versions
        // -----------------------------------

        System.out.println("\nTest 2:");

        System.out.println(timeMap.get("foo", 3));
        // Expected: bar1

        System.out.println(timeMap.get("foo", 6));
        // Expected: bar2


        // -----------------------------------
        // Test 3: Timestamp after latest
        // -----------------------------------

        System.out.println("\nTest 3:");

        System.out.println(timeMap.get("foo", 100));
        // Expected: bar3


        // -----------------------------------
        // Test 4: Timestamp before first
        // -----------------------------------

        System.out.println("\nTest 4:");

        System.out.println(timeMap.get("foo", 0));
        // Expected: ""


        // -----------------------------------
        // Test 5: Unknown key
        // -----------------------------------

        System.out.println("\nTest 5:");

        System.out.println(timeMap.get("unknown", 10));
        // Expected: ""


        // -----------------------------------
        // Test 6: Multiple keys
        // -----------------------------------

        System.out.println("\nTest 6:");

        timeMap.set("user1", "ACTIVE", 10);
        timeMap.set("user1", "SUSPENDED", 20);

        timeMap.set("user2", "ACTIVE", 5);
        timeMap.set("user2", "CLOSED", 15);

        System.out.println(timeMap.get("user1", 15));
        // Expected: ACTIVE

        System.out.println(timeMap.get("user1", 25));
        // Expected: SUSPENDED

        System.out.println(timeMap.get("user2", 10));
        // Expected: ACTIVE

        System.out.println(timeMap.get("user2", 20));
        // Expected: CLOSED


        // -----------------------------------
        // Test 7: Same timestamp
        // -----------------------------------

        System.out.println("\nTest 7:");

        timeMap.set("foo", "new-value", 7);

        System.out.println(timeMap.get("foo", 7));
        // Expected: new-value
    }
}
