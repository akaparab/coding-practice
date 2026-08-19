package com.example.demo.graph;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ExecuteWorkFLow {
    static class Task {
        String id;
        List<String> dependencies;

        Task(String id, List<String> list) {
            this.id = id;
            this.dependencies = list;
        }


    }

    private void executeTask(String taskId) {
        System.out.println(
                Thread.currentThread().getName()
                        + " executing " + taskId
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Task interrupted: " + taskId,
                    e
            );
        }

        System.out.println(
                Thread.currentThread().getName()
                        + " completed " + taskId
        );
    }

    void executeWorkflow(
            List<Task> tasks,
            int maxConcurrency)
            throws ExecutionException, InterruptedException {

        if (maxConcurrency <= 0) {
            throw new IllegalArgumentException(
                    "maxConcurrency must be > 0");
        }

        List<List<String>> taskLevels =
                getExecutionLevels(tasks);

        ExecutorService executor =
                Executors.newFixedThreadPool(maxConcurrency);

        try {
            Map<String, CompletableFuture<Void>> futures =
                    new HashMap<>();

            Map<String, Task> taskMap =
                    tasks.stream()
                            .collect(Collectors.toMap(
                                    task -> task.id,
                                    task -> task
                            ));

            for (Task task : tasks) {

                CompletableFuture<Void> taskFuture;

                if (task.dependencies == null ||
                        task.dependencies.isEmpty()) {

                    // No dependencies -> execute immediately
                    taskFuture = CompletableFuture.runAsync(
                            () -> executeTask(task.id),
                            executor
                    );

                } else {

                    CompletableFuture<?>[] dependencyFutures =
                            task.dependencies.stream()
                                    .map(futures::get)
                                    .toArray(
                                            CompletableFuture[]::new
                                    );
                    taskFuture =
                            CompletableFuture
                                    .allOf(dependencyFutures)
                                    .thenRunAsync(
                                            () -> executeTask(task.id),
                                            executor
                                    );
                }

                futures.put(task.id, taskFuture);
            }

            // Wait for entire workflow
            CompletableFuture<Void> workflow =
                    CompletableFuture.allOf(
                            futures.values().toArray(
                                    new CompletableFuture[0]
                            )
                    );

            workflow.get();

        } finally {
            executor.shutdown();
        }
    }


    List<List<String>> getExecutionLevels(List<Task> tasks) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        // Initialize graph
        for (Task task : tasks) {
            graph.put(task.id, new ArrayList<>());
            indegree.put(task.id, 0);
        }

        // Build graph
        for (Task task : tasks) {

            for (String dependency : task.dependencies) {
                if (!graph.containsKey(dependency)) {
                    throw new IllegalArgumentException(
                            "Unknown dependency: " + dependency);
                }

                // dependency -> task
                graph.get(dependency).add(task.id);

                indegree.put(
                        task.id,
                        indegree.get(task.id) + 1
                );
            }
        }

        // Add tasks with no dependencies
        Queue<String> queue = new LinkedList<>();
        List<List<String>> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry :
                indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        int processed = 0;
        // Kahn's algorithm
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                String task = queue.poll();

                list.add(task);
                processed++;

                for (String dependent : graph.get(task)) {
                    int newIndegree =
                            indegree.get(dependent) - 1;
                    indegree.put(dependent, newIndegree);
                    if (newIndegree == 0) {
                        queue.offer(dependent);
                    }
                }
            }
            result.add(list);
        }

        // Cycle detection
        if (processed != tasks.size()) {
            throw new IllegalArgumentException(
                    "Dependency graph contains a cycle");
        }
        return result;
    }

    public static void main(String[] args) throws Exception {

        ExecuteWorkFLow executor = new ExecuteWorkFLow();

        List<Task> tasks = List.of(

                new Task("A", List.of()),

                new Task("B", List.of()),

                new Task("C", List.of()),

                new Task("D", List.of("A", "B")),

                new Task("E", List.of("C")),

                new Task("F", List.of("D", "E"))
        );

        executor.executeWorkflow(tasks, 2);
    }
}
