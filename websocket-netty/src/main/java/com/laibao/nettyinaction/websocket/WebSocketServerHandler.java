package com.laibao.nettyinaction.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;

import java.util.UUID;

/**
 * @author laibao wang
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<HttpObject>{

    @Override
    protected void channelRead0(ChannelHandlerContext context, HttpObject message) throws Exception {
        System.out.println("the remote address is : " + context.channel().remoteAddress());
        System.out.println("the message is : "+message.toString());

        context.channel().writeAndFlush("the response from server is : "+ UUID.randomUUID());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception {
        cause.printStackTrace();
        context.channel().close();
    }
}
