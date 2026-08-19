package com.example.demo.oauth;

public class OAuthClient {
    public OAuthToken refreshToken() {
        return new OAuthToken("token1",
                "refresh_token1", System.currentTimeMillis() + 5000);
    }
}
