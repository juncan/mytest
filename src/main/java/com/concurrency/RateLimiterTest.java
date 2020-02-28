package com.concurrency;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wujc
 * @ClassName RateLimiterTest
 * @Description: TODO
 * @create 2019-08-02
 */
public class RateLimiterTest {
    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(2.0);
        ExecutorService es = Executors.newFixedThreadPool(1);

        AtomicLong prev = new AtomicLong(System.nanoTime());

        for (int i = 0; i < 20; i++) {
            limiter.acquire();

            es.execute(()->{
                long cur = System.nanoTime();
                System.out.println((cur - prev.get()) / 1000_000);
                prev.set(cur);
            });

        }
    }
}
