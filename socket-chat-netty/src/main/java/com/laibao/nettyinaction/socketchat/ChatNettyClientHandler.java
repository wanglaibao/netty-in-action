package com.laibao.nettyinaction.socketchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @author laibao wang
 */
public class ChatNettyClientHandler extends SimpleChannelInboundHandler<String>{

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client output: " + msg);
    }

    /**
     *  这个方法必须要重写，否则客户端和服务端无法看到任何数据，怀疑系统出了问题
     * @param handlerContext
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext handlerContext) throws Exception {
        // 重写该方法的目的是为了激活客户端向服务端发送数据
        handlerContext.writeAndFlush("来自于客户端的问候！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext handlerContext, Throwable cause) throws Exception {
        cause.printStackTrace();
        handlerContext.close();
    }
}
