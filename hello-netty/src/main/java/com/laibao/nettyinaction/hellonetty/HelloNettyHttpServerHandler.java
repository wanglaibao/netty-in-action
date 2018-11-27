package com.laibao.nettyinaction.hellonetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

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
        System.out.println("有关的处理器handler信息" +message.getClass());
        System.out.println();
        System.out.println("获取请求的远程地址" +context.channel().remoteAddress());

        //还可以让请求的这个线程休眠几秒钟
        //Thread.sleep(8000);

        // 下面的这个if逻辑判断没有也是可以的，加了就更有保障了
        if (message instanceof HttpRequest) {
            HttpRequest request = (HttpRequest)message;
            System.out.println("请求的方法名称" + request.method().name());
            String uri = request.uri();
            System.out.println("请求的uri" + uri);
            URI uri1 = new URI((uri));
            if ("/favicon.ico".equals(uri1.getPath())) {
                System.out.println("请求favicon.ico");
                return;
            }
            System.out.println();

            ByteBuf content = Unpooled.copiedBuffer("welcome to netty world",CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");

            //response.headers().set(HttpHeaderNames.ACCEPT_CHARSET,"utf-8");

            //response.headers().set(HttpHeaderNames.ACCEPT_ENCODING,"utf-8");

            //response.headers().set(HttpHeaderNames.CONTENT_ENCODING,"utf-8");

            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            context.writeAndFlush(response);
            context.channel().close();  //关闭请求channel的连接
        }
    }

    /*
            下面所添加的一些override方法是为了理解netty的处理流程而添加的，对于一个请求处理的流程如下
             1： handlerAdded(...)
             2：channelRegistered(...)
             3：channelActive(.....)
             4：channelRead0(ChannelHandlerContext context, HttpObject message)  //这个是处理的业务方法由开发人员来处理业务逻辑
             5：channelInactive()
             6：channelUnregistered()
             7: handlerRemoved()
     */

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Added");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Registered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Inactive");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Unregistered");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Removed");
    }
}
