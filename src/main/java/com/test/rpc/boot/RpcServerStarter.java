package com.test.rpc.boot;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wujc
 * @ClassName RpcServerStarter
 * @Description: 服务端启动
 */
public class RpcServerStarter {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:rpc-invoke-config.xml");
    }
}
