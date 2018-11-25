package com.laibao.nettyinaction.hellonetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author laibao wang
 */
public class HelloNettyServer {

    /**
     * Netty 应用程序的启动入口
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //定义两个事件循环组
        EventLoopGroup bossGroup = new NioEventLoopGroup();         //接收客户端的链接请求，并且把链接转交给worker group // 死循环
        EventLoopGroup workerGroup = new NioEventLoopGroup();        //正真完成对客户端链接请求的处理       //死循环
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HelloNettyServerInitializer());  //开发人员自己来实现
            //服务器绑定到某个端口上面
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
