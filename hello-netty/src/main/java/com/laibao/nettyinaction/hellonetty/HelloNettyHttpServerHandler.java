package com.laibao.nettyinaction.hellonetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
/**
 * @author laibao wang
 */
public class HelloNettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{

    /**
     *  读取客户端发送过来的请求，并且对请求进行处理，并且把响应结果返回给客户端
     * @param context
     * @param message
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext context, HttpObject message) throws Exception {
        // 下面的这个if逻辑判断没有也是可以的，加了就更有保障了
        if (message instanceof HttpRequest) {
            ByteBuf content = Unpooled.copiedBuffer("welcome to netty world",CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");

            //response.headers().set(HttpHeaderNames.ACCEPT_CHARSET,"utf-8");

            //response.headers().set(HttpHeaderNames.ACCEPT_ENCODING,"utf-8");

            //response.headers().set(HttpHeaderNames.CONTENT_ENCODING,"utf-8");

            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            context.writeAndFlush(response);
        }
    }
}
