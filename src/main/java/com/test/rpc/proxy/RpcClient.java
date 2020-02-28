package com.test.rpc.proxy;

import com.test.rpc.annotation.Service;

/**
 * @author wujc
 * @ClassName RpcClient
 * @create 2019-09-02
 */
public class RpcClient {
    public static <T> T createProxy(Class<T> interfaceClass) {
        String service, action;
        Service sv = interfaceClass.getAnnotation(Service.class);
        if(sv == null) throw new RuntimeException("rpc接口 " + interfaceClass.getClass().getName() + " 上未定义");
        return null;
    }
}
