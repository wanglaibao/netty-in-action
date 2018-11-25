package com.laibao.nettyinaction.biosocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author laibao wang
 */
public class BioSocketServerHandler {

    private static final int MAX_DATA_LEN = 1024;

    private final Socket socket;

    public BioSocketServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        System.out.println("有新用户请求进来了啊");
        new Thread(() -> doStart()).start();
    }

    private void doStart() {
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                byte[] data = new byte[MAX_DATA_LEN];
                int len;
                while ((len = inputStream.read(data)) != -1) {
                    String message = new String(data,0,len);
                    System.out.println("客户端传来消息: "+message);
                    socket.getOutputStream().write(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
