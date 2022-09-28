package com.jdk8.supervene;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
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


        BigDecimal os = new BigDecimal("0.10");
        BigDecimal os1 = new BigDecimal("0.1");
        System.out.println(os.compareTo(os1));


        String title = "会撩人的小姐姐尽在美岁直播！";
        title = title.replace("美岁", "嗨兔");
        System.out.println(title);

        Double d1 = 0.01;
        Double d2 = 0.09;
        Double d3 = 0.10;

        System.out.println(d3.equals(add(d1 , d2)));

        String num = null;
        if (!StringUtils.isBlank(num) && Integer.parseInt(num) >= 10) {
            System.out.println("true");
        }

        System.out.println(new BigDecimal(String.valueOf(0.069)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
    }

    /**
     * * 两个Double数相加 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double add(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.add(b2).doubleValue());
    }
}
