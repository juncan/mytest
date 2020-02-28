package com.jdk8.supervene;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wujc
 * @ClassName Increment
 * @Description: TODO
 * @create 2019-06-08
 */
public class Increment {
    int count = 0;

    ReentrantLock lock = new ReentrantLock();

    void incrementSync() {
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();
        }
    }
}
