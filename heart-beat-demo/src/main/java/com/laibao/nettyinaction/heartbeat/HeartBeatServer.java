package com.laibao.nettyinaction.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author laibao wang
 */
public class HeartBeatServer {
    public static void main(String[] args) throws Exception {

        EventLoopGroup bossLoop = new NioEventLoopGroup();
        EventLoopGroup childLoop = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossLoop,childLoop)
                          .channel(NioServerSocketChannel.class)
                          //.handler(new ServerHandler())
                          .handler(new LoggingHandler(LogLevel.INFO))
                          .childHandler(new HeartBeatChannelInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossLoop.shutdownGracefully();
            childLoop.shutdownGracefully();
        }
    }
}
