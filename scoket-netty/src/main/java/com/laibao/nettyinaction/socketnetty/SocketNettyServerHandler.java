package com.laibao.nettyinaction.socketnetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.Socket;
import java.util.UUID;

/**
 * @author laibao wang
 */
public class SocketNettyServerHandler extends SimpleChannelInboundHandler<String>{

    @Override
    protected void channelRead0(ChannelHandlerContext context, String message) throws Exception {
        System.out.println("the remote address is : " + context.channel().remoteAddress());
        System.out.println("the message is : "+message);

        context.channel().writeAndFlush("the response from server is : "+ UUID.randomUUID());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception {
        cause.printStackTrace();
        context.channel().close();
    }
}
