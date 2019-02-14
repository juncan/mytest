package com.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

/**
 * @author wujc
 * @ClassName EchoServerHandler
 * @Description: TODO
 * @create 2019-01-24 10:00
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * @MethodsName: channelActive
     * @Description: 当客户端主动链接服务端的链接后，这个通道就是活跃的了，也就是客户端与服务端建立了通信通道并且可以传输数据
     * @date: 2019-1-24 10:04 
     * @param ctx
     * @return:
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println(ctx.channel().localAddress().toString() + "通道已激活！");
    }

    /**
     * @MethodsName: channelInactive
     * @Description: 当客户端主动断开服务端的链接后，这个通道就是不活跃的，也就是说客户端的关闭通信通道并且不可以传输数据
     * @date: 2019-1-24 10:07
     * @param ctx
     * @return:
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        System.out.println(ctx.channel().localAddress().toString() + "通道不活跃!");
    }

    /**
     * @MethodsName: getMessage
     * @Description: 此处用来处理收到的数据中含有中文时出现的乱码问题
     * @date: 2019-1-24 10:12 
     * @param buf
     * @return:
     */
    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @MethodsName: channelRead
     * @Description: 读取服务器发过来的信息
     * @date: 2019-1-24 10:19
     * @param ctx
     * @param msg
     * @return:
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //第一种：接收字符串时的处理
        ByteBuf buf = (ByteBuf) msg;
        String rev = getMessage(buf);
        System.out.println("客户端收到服务器数据；" + rev);
    }

    /**
     * @MethodsName: channelReadComplete
     * @Description: 读取完毕客户端发送过来的数据之后的操作
     * @date: 2019-1-24 10:20 
     * @param ctx
     * @return:
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("服务端接收数据完毕。。");
    }

    /**
     * @MethodsName: exceptionCaught
     * @Description: 服务端发生异常的操作
     * @date: 2019-1-24 10:23 
     * @param ctx
     * @param cause
     * @return:
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }

    
    
}
