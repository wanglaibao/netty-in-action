package com.laibao.nettyinaction.hellonetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
/**
 * @author laibao wang
 */
public class HelloNettyHttpServerHandler extends SimpleChannelInboundHandler{

    /**
     *  读取客户端发送过来的请求，并且对请求进行处理，并且把响应结果返回给客户端
     * @param context
     * @param message
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext context, Object message) throws Exception {
        ByteBuf content = Unpooled.copiedBuffer("hello world netty",CharsetUtil.UTF_8);

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                                                         HttpResponseStatus.OK, content);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
        context.writeAndFlush(response);
    }
}
