package com.example.demo.oauth;

public class OAuthTokenManager {
    private final TokenRepository repository;
    private final OAuthClient oauthClient;

    public OAuthTokenManager(
            TokenRepository repository,
            OAuthClient oauthClient) {
        this.repository = repository;
        this.oauthClient = oauthClient;
    }

    public String getAccessToken(
            String userId,
            String integrationId) {

        OAuthToken token =
                repository.find(userId, integrationId);

        if (token != null && !token.isExpired()) {
            return token.getAccessToken();
        }

        OAuthToken refreshed =
                oauthClient.refreshToken();

        repository.save(
                userId,
                integrationId,
                refreshed);

        return refreshed.getAccessToken();
    }

}
