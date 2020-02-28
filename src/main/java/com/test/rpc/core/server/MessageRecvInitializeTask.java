package com.test.rpc.core.server;

import com.test.rpc.model.MessageRequest;
import com.test.rpc.model.MessageResponse;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.util.Map;

/**
 * @author wujc
 * @ClassName MessageRecvInitializeTask
 * @Description: Rpc服务器消息线程任务处理
 * @create 2019-12-20
 */
public class MessageRecvInitializeTask implements Runnable {

    private MessageRequest request = null;
    private MessageResponse response = null;
    private Map<String, Object> handlerMap = null;
    private ChannelHandlerContext ctx = null;

    public MessageResponse getResponse() {
        return response;
    }

    public MessageRequest getRequest() {
        return request;
    }

    public void setRequest(MessageRequest request) {
        this.request = request;
    }

    public MessageRecvInitializeTask(MessageRequest request, MessageResponse response, Map<String, Object> handlerMap, ChannelHandlerContext ctx) {
        this.request = request;
        this.response = response;
        this.handlerMap = handlerMap;
        this.ctx = ctx;
    }

    public void run() {
        response.setMessageId(request.getMessageId());
        try {
            Object result = reflect(request);
            response.setResultDesc(result);
        } catch (Throwable t) {
            response.setError(t.toString());
            t.printStackTrace();
            System.err.printf("RPC Server invoke error!\n");
        }

        ctx.writeAndFlush(response).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println("RPC Server Send message-id respone:" + request.getMessageId());
            }
        });
    }

    private Object reflect(MessageRequest request) throws Throwable {
        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);
        String methodName = request.getMethodName();
        Object[] parameters = request.getParametersVal();
        return MethodUtils.invokeMethod(serviceBean, methodName, parameters);
    }
}
