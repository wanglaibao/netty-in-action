package com.laibao.nettyinaction.socketchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author laibao wang
 */
public class ChatNettyServerHandler extends SimpleChannelInboundHandler<String>{

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        Channel currentChannel = ctx.channel();
        channelGroup.forEach(channel -> {
            if(currentChannel != channel) {
                channel.writeAndFlush(currentChannel.remoteAddress() + " 发送的消息：" + message + "\n");
            } else {
                channel.writeAndFlush("【自己】" + message + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception {
        cause.printStackTrace();
        context.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端和服务器端的链接建立好了");
        System.out.println("handler Added");
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】- " + channel.remoteAddress() + " 加入\n");
        channelGroup.add(channel);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Registered");
        Channel channel = ctx.channel();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Active");
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Inactive");
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Unregistered");
        Channel channel = ctx.channel();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Removed");
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】- " + channel.remoteAddress() + " 离开\n");
        System.out.println(channelGroup.size());
    }
}
