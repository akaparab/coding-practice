package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeMap {
    Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> timeMap = map.get(key);
        if (timeMap == null) {
            return "";
        }
        Integer floorTimestamp = timeMap.floorKey(timestamp);
        return floorTimestamp == null ? "" : timeMap.get(floorTimestamp);
    }
}
