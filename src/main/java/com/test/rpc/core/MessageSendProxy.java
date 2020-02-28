package com.test.rpc.core;

import com.test.rpc.core.client.MessageSendHandler;
import com.test.rpc.model.MessageRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author wujc
 * @ClassName MessageSendProxy
 * @Description: Rpc客户端消息处理
 * @create 2019-12-19
 */
public class MessageSendProxy<T> implements InvocationHandler {

    private Class<T> cls;

    public MessageSendProxy(Class<T> cls) {
        this.cls = cls;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MessageRequest request = new MessageRequest();
        request.setMessageId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setTypeParameters(method.getParameterTypes());
        request.setParametersVal(args);

        MessageSendHandler handler = RpcServerLoader.getInstance().getMessageSendHandler();
        MessageCallBack callBack = handler.sendRequest(request);
        return callBack.start();
    }
}
