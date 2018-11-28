package com.laibao.nettyinaction.heartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author laibao wang
 */
public class HeartBeatChannelHandler extends SimpleChannelInboundHandler{

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)msg;
            String eventType = null;
            switch (event.state()) {
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "超时事件： " + eventType);
            ctx.channel().close();
        }
    }
}
