package com.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author wujc
 * @ClassName EchoServer
 * @Description: TODO
 * @create 2019-01-24 9:43
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            sb.group(group, bossGroup)  //绑定线程池
                    .channel(NioServerSocketChannel.class)  //指定使用的channel
                    .localAddress(this.port) //绑定监听端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {//绑定客户端连接时候触发操作
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("报告");
                            System.out.println("信息：有一客户端链接到本服务端");
                            System.out.println("IP:" + ch.localAddress().getHostName());
                            System.out.println("Port:" + ch.localAddress().getPort());
                            System.out.println("报告完毕");
                            ch.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
                            ch.pipeline().addLast(new EchoServerHandler()); //客户端触发操作
                            ch.pipeline().addLast(new ByteArrayDecoder());
                        }
                    });
            ChannelFuture cf = sb.bind().sync(); //服务器异步创建绑定
            System.out.println(EchoServer.class + "启动正在监听：" + cf.channel().localAddress());
            cf.channel().closeFuture().sync(); //关闭服务器通道
        }finally {
            group.shutdownGracefully().sync(); //释放线程资源
            bossGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer(8888).start();
    }
}