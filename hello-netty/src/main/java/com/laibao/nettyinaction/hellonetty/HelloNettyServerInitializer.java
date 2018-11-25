package com.laibao.nettyinaction.hellonetty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @author laibao wang
 */
public class HelloNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //HttpRequestDecoder
        //pipeline.addLast("httpRequestDecoder", new HttpRequestDecoder());
        //HttpResponseEncoder
        //pipeline.addLast("httpResponseEncoder", new HttpResponseEncoder());
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("helloNettyHttpServerHandler", new HelloNettyHttpServerHandler());
    }
}
