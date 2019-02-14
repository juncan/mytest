package com.test.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

/**
 * @author wujc
 * @ClassName CreateGroup
 * @Description: TODO
 * @create 2019-01-23 9:22
 */
public class CreateGroup extends ConnectionWatcher {
    public void create(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        String createPath = zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("Created:" + createPath);
    }
}
