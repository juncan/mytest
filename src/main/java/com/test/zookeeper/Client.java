package com.test.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2019-01-23 9:24
 */
public class Client {
    private static final String HOSTS = "192.168.209.20";
    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        /*CreateGroup createGroup = new CreateGroup();
        createGroup.connecttion(HOSTS);
        createGroup.create("kkkk");
        createGroup.close();*/
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connecttion(HOSTS);
        deleteGroup.delete("kkkk");
        deleteGroup.close();


    }
}
