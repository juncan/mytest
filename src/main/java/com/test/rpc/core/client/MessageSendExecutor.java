package com.test.rpc.core.client;

import com.test.rpc.core.MessageSendProxy;
import com.test.rpc.core.RpcServerLoader;
import com.test.rpc.serialize.support.RpcSerializeProtocol;

import java.lang.reflect.Proxy;

/**
 * @author wujc
 * @ClassName MessageSendExecutor
 * @Description: Rpc客户端执行模块
 */
public class MessageSendExecutor {

    private static class MessageSendExecutorHolder {
        private static final MessageSendExecutor INSTANCE = new MessageSendExecutor();
    }

    public static MessageSendExecutor getInstance() {
        return MessageSendExecutorHolder.INSTANCE;
    }

    private MessageSendExecutor() {

    }

    private RpcServerLoader loader = RpcServerLoader.getInstance();

    public MessageSendExecutor(String serverAddress,RpcSerializeProtocol serializeProtocol) {
        loader.load(serverAddress,serializeProtocol);
    }

    public void stop() {
        loader.unLoad();
    }

    public static <T> T execute(Class<T> rpcInterface) {
        return (T) Proxy.newProxyInstance(
                rpcInterface.getClassLoader(),
                new Class<?>[]{rpcInterface},
                new MessageSendProxy<T>(rpcInterface)
        );
    }
}
