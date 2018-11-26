package com.laibao.nettyinaction.hellonetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @author laibao wang
 */
public class NettyServerStartDemo {

    /**
     * 这个DEMO是配合网络资源闪电侠的视频来学习源码分析的
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup,childGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childAttr(AttributeKey.newInstance("childAtrr"),"childAtrrValue")
                    .handler(new ServerHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //ChannelPipeline pipeline = socketChannel.pipeline();
                            //pipeline.addLast()
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(8989).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
