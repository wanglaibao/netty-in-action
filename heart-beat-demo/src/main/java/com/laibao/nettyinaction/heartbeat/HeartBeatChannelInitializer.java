package com.laibao.nettyinaction.heartbeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author laibao wang
 */
public class HeartBeatChannelInitializer extends ChannelInitializer{

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 读空闲5秒
        // 写空闲7秒
        // 读写空闲3秒
        pipeline.addLast(new IdleStateHandler(5, 7, 3, TimeUnit.SECONDS));
        pipeline.addLast(new HeartBeatChannelHandler());
    }
}
