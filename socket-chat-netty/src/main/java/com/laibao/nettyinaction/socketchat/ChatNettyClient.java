package com.laibao.nettyinaction.socketchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author laibao wang
 */
public class ChatNettyClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup clientLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientLoopGroup)
                     .channel(NioSocketChannel.class)
                    .handler(new ChatNettyClientInitializer());                    //开发人员来实现

            //服务器绑定到某个端口上面
            //ChannelFuture channelFuture = bootstrap.connect("localhost",8989).sync();
            //channelFuture.channel().closeFuture().sync();
            Channel channel = bootstrap.connect("localhost",8989).sync().channel();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                channel.writeAndFlush(bufferedReader.readLine()+"\r\n");
            }
        }finally {
            clientLoopGroup.shutdownGracefully();
        }
    }

}
