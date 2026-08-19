package com.example.demo.graph;

import java.util.*;

public class WorkFlow {

    static class Task {
        String id;
        List<String> dependencies;
        Runnable action;

        Task(
                String id,
                List<String> dependencies,
                Runnable action) {

            this.id = id;
            this.dependencies = dependencies;
            this.action = action;
        }
    }

    public static void execute(List<Task> tasks) {

        Map<String, Task> taskMap = new HashMap<>();

        for (Task task : tasks) {
            taskMap.put(task.id, task);
        }

        // Number of dependencies for each task
        Map<String, Integer> indegree = new HashMap<>();

        // dependency -> tasks depending on it
        Map<String, List<String>> graph = new HashMap<>();

        for (Task task : tasks) {

            indegree.put(
                    task.id,
                    task.dependencies.size()
            );

            graph.putIfAbsent(
                    task.id,
                    new ArrayList<>()
            );

            for (String dependency : task.dependencies) {

                graph.computeIfAbsent(
                        dependency,
                        k -> new ArrayList<>()
                ).add(task.id);
            }
        }

        // Tasks with no dependencies
        Queue<String> queue = new LinkedList<>();

        for (Map.Entry<String, Integer> entry
                : indegree.entrySet()) {

            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        int executed = 0;

        while (!queue.isEmpty()) {

            String taskId = queue.poll();

            Task task = taskMap.get(taskId);

            System.out.println(
                    "Executing: " + taskId
            );

            task.action.run();

            executed++;

            // Tell dependent tasks that
            // this dependency has completed.
            for (String dependent : graph.get(taskId)) {

                int remaining =
                        indegree.get(dependent) - 1;

                indegree.put(
                        dependent,
                        remaining
                );

                if (remaining == 0) {
                    queue.offer(dependent);
                }
            }
        }

        // If not everything executed,
        // there must be a cycle.
        if (executed != tasks.size()) {

            throw new IllegalStateException(
                    "Workflow contains a dependency cycle"
            );
        }
    }

    public static void main(String[] args) {

        List<Task> tasks = List.of(

                new Task(
                        "A",
                        List.of(),
                        () -> System.out.println("Running A")
                ),

                new Task(
                        "B",
                        List.of(),
                        () -> System.out.println("Running B")
                ),

                new Task(
                        "C",
                        List.of("A", "B"),
                        () -> System.out.println("Running C")
                ),

                new Task(
                        "D",
                        List.of("A"),
                        () -> System.out.println("Running D")
                ),

                new Task(
                        "E",
                        List.of("C"),
                        () -> System.out.println("Running E")
                )
        );

        WorkFlow.execute(tasks);
    }

}
