package com.laibao.nettyinaction.hellonetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author laibao wang
 */
public class HelloNettyServler {
    public static void main(String[] args) throws Exception {
        //定义两个事件循环组
        EventLoopGroup boosGroup = new NioEventLoopGroup();                 // 死循环
        EventLoopGroup workerGroup = new NioEventLoopGroup();               //死循环
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HelloNettyServerInitializer());  //开发人员自己来实现
            //服务器绑定到某个端口上面
            ChannelFuture channelFuture = null;
            channelFuture.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
