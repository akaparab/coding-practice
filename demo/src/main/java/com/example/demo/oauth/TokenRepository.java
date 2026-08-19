package com.example.demo.oauth;

import java.util.HashMap;
import java.util.Map;

public class TokenRepository {
    private Map<String, OAuthToken> cache = new HashMap<>();

    public void save(String userId, String integrationId, OAuthToken token) {
        cache.put(userId + "_" + integrationId, token);
    }

    public OAuthToken find(String userId, String integrationId) {
        return cache.get(userId + "_" + integrationId);
    }


}
