package com.test.rpc.core.server;

import com.test.rpc.core.NamedThreadFactory;
import com.test.rpc.core.RpcThreadPool;
import com.test.rpc.model.MessageKeyVal;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;

/**
 * @author wujc
 * @ClassName MessageRecvExecutor
 * @Description: Rpc服务器执行模块
 */
public class MessageRecvExecutor implements ApplicationContextAware,InitializingBean {

    private String serverAddress;
    private final static String DELIMITER = ":";

    private Map<String, Object> handlerMap = new ConcurrentHashMap<>();

    private static ThreadPoolExecutor threadPoolExecutor;

    public MessageRecvExecutor(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public static void submit(Runnable task) {
        if (threadPoolExecutor == null) {
            synchronized (MessageRecvExecutor.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = (ThreadPoolExecutor) RpcThreadPool.getExcutor(16, -1);
                }
            }
        }
        threadPoolExecutor.submit(task);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //Netty的线程池模型设置成主从线程持模式，这样可以应对高并发请求
        //当然netty还支持单线程，多线程网络IO模型，可以根据业务需求灵活配置
        ThreadFactory threadFactory = new NamedThreadFactory("NettyRpc ThreadFactory");

        //方法返回到java虚拟机的可用的处理器数量
        int parallel = Runtime.getRuntime().availableProcessors() * 2;

        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup(parallel, threadFactory, SelectorProvider.provider());

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boos, worker).channel(NioServerSocketChannel.class)
                    .childHandler(new MessageRecvChannelInitializer(handlerMap))
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            String[] ipAddr = serverAddress.split(MessageRecvExecutor.DELIMITER);

            if (ipAddr.length == 2) {
                String host = ipAddr[0];
                int port = Integer.parseInt(ipAddr[1]);
                ChannelFuture future = bootstrap.bind(host, port).sync();
                System.out.printf("Netty RPC Server start success ip:%s port:%d\n", host, port);
                future.channel().closeFuture().sync();
            }else{
                System.out.printf("Netty RPC Server start fail!\n");
            }
        }finally {
            worker.shutdownGracefully();
            boos.shutdownGracefully();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        try {
            MessageKeyVal keyVal = (MessageKeyVal) ctx.getBean(Class.forName("com.test.rpc.model.MessageKeyVal"));
            Map<String, Object> rpcServiceObject = keyVal.getMessageKeyVal();

            Set s = rpcServiceObject.entrySet();
            Iterator<Map.Entry<String, Object>> it = s.iterator();
            Map.Entry<String, Object> entry;

            while (it.hasNext()) {
                entry = it.next();
                handlerMap.put(entry.getKey(), entry.getValue());
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MessageRecvExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
