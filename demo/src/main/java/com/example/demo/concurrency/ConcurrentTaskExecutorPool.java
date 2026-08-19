package com.example.demo.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentTaskExecutorPool {

    public static void executeTasks(
            List<Runnable> tasks,
            int maxConcurrency,
            int queueCapacity)
            throws InterruptedException {

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(
                        maxConcurrency,
                        maxConcurrency,
                        0L,
                        TimeUnit.MILLISECONDS,
                        new ArrayBlockingQueue<>(queueCapacity),
                        new ThreadPoolExecutor.CallerRunsPolicy()
                );

        try {

            List<Future<?>> futures = new ArrayList<>();

            for (Runnable task : tasks) {
                futures.add(executor.submit(task));
            }

            for (Future<?> future : futures) {

                try {
                    future.get();

                } catch (ExecutionException e) {

                    System.out.println(
                            "Task failed: "
                                    + e.getCause()
                    );
                }
            }

        } finally {

            executor.shutdown();

            if (!executor.awaitTermination(
                    30,
                    TimeUnit.SECONDS)) {

                executor.shutdownNow();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        List<Runnable> tasks =
                new ArrayList<>();

        for (int i = 1; i <= 25; i++) {

            int taskId = i;

            tasks.add(() -> {

                System.out.println(
                        "Executing task " + taskId
                                + " on "
                                + Thread.currentThread().getName()
                );

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Result-" + taskId);
            });
        }
        ConcurrentTaskExecutorPool.executeTasks(tasks, 10, 100);


    }
}
