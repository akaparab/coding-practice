package com.example.demo.ratelimitter;

public interface RateLimitConfiguration {

    int getLimit(String key);
}
