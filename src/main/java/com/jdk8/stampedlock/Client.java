package com.jdk8.stampedlock;

import com.jdk8.supervene.ConcurrentUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2019-06-08
 */
public class Client {
    public static void main(String[] args) throws IOException {
        /*ExecutorService executorService = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        StampedLock lock = new StampedLock();

        executorService.submit(() -> {
            long stamp = lock.writeLock();
            try {
                ConcurrentUtils.sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.unlockWrite(stamp);
            }
        });

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            try {
                System.out.println(map.get("foo"));
                ConcurrentUtils.sleep(1);
            } finally {
                lock.unlockRead(stamp);
            }
        };

        executorService.submit(readTask);
        executorService.submit(readTask);

        ConcurrentUtils.stop(executorService);*/
        try (Stream<Path> stream = Files.list(Paths.get(""))) {
            String joined = stream
                    .map(String::valueOf)
                    .filter(path -> !path.startsWith("."))
                    .sorted()
                    .collect(Collectors.joining("; "));
            System.out.println("List: " + joined);
        }
    }
}
