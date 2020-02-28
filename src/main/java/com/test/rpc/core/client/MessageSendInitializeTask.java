package com.test.rpc.core.client;

import com.test.rpc.core.RpcServerLoader;
import com.test.rpc.serialize.support.RpcSerializeProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author wujc
 * @ClassName MessageSendInitializeTask
 * @Description: Rpc客户端线程任务处理
 */
public class MessageSendInitializeTask implements Callable<Boolean> {

    private EventLoopGroup eventLoopGroup = null;
    private InetSocketAddress serverAddress = null;
    private RpcSerializeProtocol protocol;

    public MessageSendInitializeTask(EventLoopGroup eventLoopGroup, InetSocketAddress serverAddress, RpcSerializeProtocol protocol) {
        this.eventLoopGroup = eventLoopGroup;
        this.serverAddress = serverAddress;
        this.protocol = protocol;
    }

    @Override
    public Boolean call() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new MessageSendChannelInitializer());

        ChannelFuture channelFuture = bootstrap.connect(serverAddress);
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (channelFuture.isSuccess()) {
                    MessageSendHandler handler = channelFuture.channel().pipeline().get(MessageSendHandler.class);
                    RpcServerLoader.getInstance().setMessageSendHandler(handler);
                } else {
                    EventLoop loop = (EventLoop) eventLoopGroup.schedule(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("NettyRPC server is down,start to reconnecting to: " + serverAddress.getAddress().getHostAddress() + ':' + serverAddress.getPort());
                            call();
                        }
                    }, 10, TimeUnit.SECONDS);
                }
            }
        });
        return Boolean.TRUE;
    }
}
