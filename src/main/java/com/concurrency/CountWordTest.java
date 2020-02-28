package com.concurrency;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;

/**
 * @author wujc
 * @ClassName CountWordTest
 * @Description: TODO
 * @create 2019-06-13
 */
public class CountWordTest {
    public static void main(String[] args) {
        String[] fc = {"hello world", "hello me", "hello fork", "hello join", "fork join in world"};
        //创建ForkJoin线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        //创建任务
        MR mr = new MR(fc, 0, fc.length);
        //启动任务
        Map<String, Long> result = forkJoinPool.invoke(mr);
        //输出结果
        result.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
