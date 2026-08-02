package com.example.demo.graph;

import java.util.*;

public class ProductRecommendation {
    List<String> getRecommendations(Map<String, List<String>> graph, String product) {

        Queue<String> queue = new LinkedList<>();

        queue.offer(product);
        Set<String> visited = new HashSet<>();
        visited.add(product);

        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            String productName = queue.remove();

            List<String> tmpList = graph.getOrDefault(productName, new ArrayList<>());
            for (String name : tmpList) {
                if (!visited.contains(name)) {
                    res.add(name);
                    visited.add(name);
                    queue.offer(name);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = Map.of("milk", List.of("bread", "eggs"),
                "bread", List.of("butter"), "eggs", List.of("butter"));
        ProductRecommendation pr = new ProductRecommendation();
        System.out.println(pr.getRecommendations(graph, "milk").toString());

    }

}
