package com.example.demo.webhook;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class WebhookDispatcher {

    private final HttpClient httpClient;
    private final ExecutorService executor;
    private final ScheduledExecutorService scheduler;

    private final Map<String, List<WebhookSubscription>> subscriptions
            = new ConcurrentHashMap<>();

    public WebhookDispatcher(int workerThreads) {

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();

        this.executor = Executors.newFixedThreadPool(workerThreads);

        this.scheduler = Executors.newScheduledThreadPool(2);
    }

    // Register webhook endpoint for an event type
    public void subscribe(String eventType, String url) {

        subscriptions
                .computeIfAbsent(
                        eventType,
                        key -> new CopyOnWriteArrayList<>()
                )
                .add(new WebhookSubscription(url));
    }

    // Dispatch an event
    public void dispatch(WebhookEvent event) {

        List<WebhookSubscription> endpoints =
                subscriptions.getOrDefault(
                        event.eventType(),
                        new ArrayList<>()
                );

        for (WebhookSubscription subscription : endpoints) {

            executor.submit(() ->
                    deliver(event, subscription, 0)
            );
        }
    }

    private void deliver(
            WebhookEvent event,
            WebhookSubscription subscription,
            int attempt) {

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(subscription.url()))
                    .timeout(Duration.ofSeconds(5))
                    .header("Content-Type", "application/json")
                    .header("X-Webhook-Event", event.eventType())
                    .header("X-Webhook-Id", event.id())
                    .POST(HttpRequest.BodyPublishers.ofString(event.payload()))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            if (response.statusCode() >= 200 &&
                    response.statusCode() < 300) {

                System.out.println(
                        "Webhook delivered successfully: "
                                + subscription.url()
                );

                return;
            }

            handleFailure(
                    event,
                    subscription,
                    attempt,
                    "HTTP " + response.statusCode()
            );

        } catch (Exception e) {

            handleFailure(
                    event,
                    subscription,
                    attempt,
                    e.getMessage()
            );
        }
    }

    private void handleFailure(
            WebhookEvent event,
            WebhookSubscription subscription,
            int attempt,
            String reason) {

        int maxRetries = 5;

        if (attempt >= maxRetries) {

            System.out.println(
                    "Webhook permanently failed: "
                            + subscription.url()
                            + ", reason=" + reason
            );

            // Production:
            // persist in DLQ / failed_webhooks table

            return;
        }

        long delay = calculateBackoffWithJitter(attempt);

        System.out.println(
                "Webhook failed. Retry in "
                        + delay + " ms"
        );

        scheduler.schedule(
                () -> deliver(
                        event,
                        subscription,
                        attempt + 1
                ),
                delay,
                TimeUnit.MILLISECONDS
        );
    }

    private long calculateBackoffWithJitter(int attempt) {

        long baseDelay = 100;

        long exponentialDelay =
                baseDelay * (1L << attempt);

        long maxDelay = 10_000;

        exponentialDelay =
                Math.min(
                        exponentialDelay,
                        maxDelay
                );

        // Full jitter
        return ThreadLocalRandom.current()
                .nextLong(exponentialDelay + 1);
    }

    public void shutdown() {
        executor.shutdown();
        scheduler.shutdown();
    }

    // ----------------------------
    // Models
    // ----------------------------

    public record WebhookEvent(
            String id,
            String eventType,
            String payload
    ) {
    }

    public record WebhookSubscription(
            String url
    ) {
    }

    public static void main(String[] args) {

        WebhookDispatcher dispatcher =
                new WebhookDispatcher(10);

        dispatcher.subscribe(
                "ORDER_CREATED",
                //"https://customer-a.com/webhook"
                "http://localhost:9090/api/v1/webhook"
        );

        dispatcher.subscribe(
                "ORDER_CREATED",
                "http://localhost:9090/api/v1/webhook"
        );

        WebhookDispatcher.WebhookEvent event =
                new WebhookDispatcher.WebhookEvent(
                        "evt-123",
                        "ORDER_CREATED",
                        """
                                {
                                  "operation": "add",
                                  "list": [1, 2, 3, 4, 5]
                                }
                                """
                );

        dispatcher.dispatch(event);
    }

}
