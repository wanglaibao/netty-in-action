package com.laibao.nettyinaction.socketnetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author laibao wang
 */
public class SocketNettyClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup clientLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientLoopGroup)
                     .channel(NioSocketChannel.class)
                    .handler(new SocketNettyClientInitializer());                    //开发人员来实现

            //服务器绑定到某个端口上面
            ChannelFuture channelFuture = bootstrap.connect("localhost",8989).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            clientLoopGroup.shutdownGracefully();
        }
    }
}
