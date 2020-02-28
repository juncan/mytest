package com.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wujc
 * @ClassName FutureTaskTest
 * @Description: TODO
 * @create 2019-06-12
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);
        Thread t1 = new Thread(futureTask);
        t1.start();
        Integer result = futureTask.get();
        System.out.println(String.valueOf(result));

    }
}
