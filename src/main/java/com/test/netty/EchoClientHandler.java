package com.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author wujc
 * @ClassName EchoClientHandler
 * @Description: TODO
 * @create 2019-01-24 10:52
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务器端通道-开启：" + ctx.channel().localAddress() + "channelActivie");

        String sendInfo = "信息 测生活就是积分";
        System.out.println("客户端准备发送的数据包：" + sendInfo);
        ctx.writeAndFlush(Unpooled.copiedBuffer(sendInfo, CharsetUtil.UTF_8)); //必须有flush
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端通道-关闭：" + ctx.channel().localAddress() + "channelinactive");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("读取客户端通道信息。。。");
        ByteBuf buf = msg.readBytes(msg.readableBytes());
        System.out.println("客户端接收到的服务器信息：" + ByteBufUtil.hexDump(buf) + ";数据包为：" +
                buf.toString(Charset.forName("UTF-8")));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常退出：" + cause.getMessage());
    }
}
