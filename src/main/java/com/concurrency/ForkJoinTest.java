package com.concurrency;

import java.util.concurrent.ForkJoinPool;

/**
 * @author wujc
 * @ClassName ForkJoinTest
 * @Description: TODO
 * @create 2019-06-13
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        //创建分治任务线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        //创建分治任务
        Fibonacci fibonacci = new Fibonacci(30);
        //启动分治任务
        Integer result = forkJoinPool.invoke(fibonacci);
        //输出结果
        System.out.println(result);

    }
}
