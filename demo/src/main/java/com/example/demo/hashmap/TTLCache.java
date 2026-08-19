package com.example.demo.hashmap;

import java.util.HashMap;
import java.util.Map;

public class TTLCache<K, V> {
    Map<K, Entry<V>> map = new HashMap<>();

    static class Entry<V> {
        V value;
        long expiryTimeMillis;

        Entry(V val, long time) {
            this.value = val;
            this.expiryTimeMillis = time;
        }
    }

    public V get(K key) {
        Entry<V> value = map.get(key);
        if (value == null) {
            return null;
        }
        if (System.currentTimeMillis() >= value.expiryTimeMillis) {
            map.remove(key);
            return null;
        }
        return value.value;
    }

    public void put(K key, V value, long timems) {
        Entry<V> entry = new Entry<V>(value, System.currentTimeMillis() + timems);
        map.put(key, entry);
    }

    public static void main(String[] args) throws InterruptedException {
        TTLCache<String, String> cache = new TTLCache<>();

        cache.put("user:1", "Kishore", 5000);

        System.out.println(cache.get("user:1")); // Kishore

        Thread.sleep(6000);

        System.out.println(cache.get("user:1")); // null
    }
}
