package com.laibao.nettyinaction.chapter04;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 传统的阻塞网络编程
 * @author laibao wang
 */
public class PlainBioServer {

    private  final int port = 8989;

    public void serve() throws Exception {
        //将服务器绑定到指定端口
        final ServerSocket socket = new ServerSocket(port);
        while (true) {
            //接受连接
            final Socket clientSocket = socket.accept();
            System.out.println("Accepted connection from " + clientSocket);
            handlerRequest(clientSocket);
        }
    }

    private void handlerRequest(Socket clientSocket) {
        //创建一个新的线程来处理该连接
        new Thread(new Runnable() {
            @Override
            public void run() {
                OutputStream outputStream;
                try {
                    //将消息写给已连接的客户端
                    outputStream = clientSocket.getOutputStream();
                    outputStream.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                    outputStream.flush();
                    //关闭连接
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        clientSocket.close();
                    } catch (IOException ex) {
                        // ignore on close
                    }
                }
            }
        }).start();  //启动线程
    }
}
