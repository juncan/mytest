package com.test.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author wujc
 * @ClassName ConnectionWatcher
 * @Description: TODO
 * @create 2019-01-23 9:04
 */
public class ConnectionWatcher implements Watcher {
    private static final int SEESION_TIMEOUT = 5000;

    protected ZooKeeper zk = null;

    private CountDownLatch countDownLatch = new CountDownLatch(1);


    @Override
    public void process(WatchedEvent event) {
        Event.KeeperState state = event.getState();

        if (state == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }

    /**
     * @MethodsName: connecttion
     * @Description: 连接资源
     * @author: wujc
     * @date: 2019-1-23 9:16
     * @param hosts
     * @return:
     */
    public void connecttion(String hosts) throws IOException, InterruptedException {
        zk = new ZooKeeper(hosts, SEESION_TIMEOUT, this);
        countDownLatch.await();
    }

    /**
     * @MethodsName: close
     * @Description: 释放资源
     * @author: wujc
     * @date: 2019-1-23 9:18
     * @return:
     */
    public void close() throws InterruptedException {
        if (null == zk) {
            try {
                zk.close();
            } catch (InterruptedException e) {
                throw e;
            }finally {
                zk = null;
                System.gc();
            }
        }
    }


}
