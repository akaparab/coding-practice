package com.example.demo.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentTaskExecutor {

    public static <T> List<T> executeTasks(
            List<Callable<T>> tasks,
            int maxConcurrency)
            throws InterruptedException {

        ExecutorService executor =
                Executors.newFixedThreadPool(maxConcurrency);

        try {

            List<Future<T>> futures =
                    new ArrayList<>();

            for (Callable<T> task : tasks) {
                futures.add(executor.submit(task));
            }

            List<T> results = new ArrayList<>();

            for (Future<T> future : futures) {

                try {
                    results.add(future.get());

                } catch (ExecutionException e) {

                    System.out.println(
                            "Task failed: "
                                    + e.getCause()
                    );
                }
            }

            return results;

        } finally {
            executor.shutdown();
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        List<Callable<String>> tasks =
                new ArrayList<>();

        for (int i = 1; i <= 25; i++) {

            int taskId = i;

            tasks.add(() -> {

                System.out.println(
                        "Executing task " + taskId
                                + " on "
                                + Thread.currentThread().getName()
                );

                Thread.sleep(1000);

                return "Result-" + taskId;
            });
        }

        List<String> results =
                executeTasks(tasks, 10);

        System.out.println(results);
    }
    
}
