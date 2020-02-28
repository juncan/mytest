package com.jdk8.supervene;

import javafx.scene.paint.Stop;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2019-06-08
 */
public class Client {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run();

        Thread thread = new Thread(task);

        thread.start();

        System.out.println("Done");*/

        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });*/

        /*Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(task);

        System.out.println("future done?" + future.isDone());

        Integer result = future.get();

        System.out.println("future done?" + future.isDone());
        System.out.println("result:" + result);*/

        /*ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling:" + System.nanoTime());
            } catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        executorService.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);*/

        Increment increment = new Increment();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000).forEach(i -> executorService.submit(increment::incrementSync));

        ConcurrentUtils.stop(executorService);

        System.out.println(increment.count);

    }
}
