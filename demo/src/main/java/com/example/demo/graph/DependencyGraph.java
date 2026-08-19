package com.example.demo.graph;

import java.util.*;

public class DependencyGraph {
    static class Task {
        String id;
        List<String> dependencies;

        Task(String id, List<String> list) {
            this.id = id;
            this.dependencies = list;
        }
    }

    List<String> getExecutionOrder(List<Task> tasks) {

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

        for (Map.Entry<String, Integer> entry :
                indegree.entrySet()) {

            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        List<String> result = new ArrayList<>();

        // Kahn's algorithm
        while (!queue.isEmpty()) {

            String task = queue.poll();

            result.add(task);

            for (String dependent : graph.get(task)) {

                int newIndegree =
                        indegree.get(dependent) - 1;

                indegree.put(dependent, newIndegree);

                if (newIndegree == 0) {
                    queue.offer(dependent);
                }
            }
        }

        // Cycle detection
        if (result.size() != tasks.size()) {
            throw new IllegalArgumentException(
                    "Dependency graph contains a cycle");
        }

        return result;
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


    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Task t1 = new Task("A", List.of());
        Task t2 = new Task("B", List.of());
        Task t3 = new Task("C", List.of("A", "B"));
        Task t4 = new Task("D", List.of("C"));


        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        tasks.add(t4);
        DependencyGraph obj = new DependencyGraph();
        System.out.println(obj.getExecutionOrder(tasks));
        System.out.println(obj.getExecutionLevels(tasks));

    }

}
