package com.example.demo.oauth;

import lombok.Getter;

@Getter
public class OAuthToken {

    private String accessToken;
    private String refreshToken;
    private long expiresAt;

    public OAuthToken(
            String accessToken,
            String refreshToken,
            long expiresAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }

    public boolean isExpired() {
        // Refresh slightly before actual expiration
        return System.currentTimeMillis() >= expiresAt - 60_000;
    }
}
