package com.laibao.nettyinaction.biosocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author laibao wang
 */
public class BioSocketServer {

    private ServerSocket serverSocket;

    public BioSocketServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("服务器启动成功  端口号为: "+port);
        } catch (IOException e) {
            System.out.println("服务器启动失败");
        }
    }

    public void start() {
        new Thread(() -> doStart()).start();
    }

    public void doStart() {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                new BioSocketServerHandler(client).start();
            } catch (IOException e) {
                System.out.println("服务端异常了");
            }
        }
    }
}
