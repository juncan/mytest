package com.test.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * @author wujc
 * @ClassName DeleteGroup
 * @Description: TODO
 * @create 2019-01-23 9:19
 */
public class DeleteGroup extends ConnectionWatcher {
    public void delete(String groupName) {
        String path = "/" + groupName;
        try {
            List<String> children = zk.getChildren(path, false);
            for (String child : children) {
                zk.delete(path + "/" + child, -1);
            }
            zk.delete(path, -1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
