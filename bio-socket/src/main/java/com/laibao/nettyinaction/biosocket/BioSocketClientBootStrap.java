package com.laibao.nettyinaction.biosocket;

import java.io.IOException;
import java.net.Socket;

/**
 * @author laibao wang
 */
public class BioSocketClientBootStrap {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8989;
    private static final int SLEEP_TIME = 1000;

    public static void main(String[] args) {
        try {
            final Socket socket = new Socket(HOST,PORT);
            new Thread(() -> doRun(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doRun(Socket socket) {
        System.out.println();
        while (true) {

            String message = "this is the hello world message";
            System.out.println("客户端向服务端发送数据: "+message);
            try {
                socket.getOutputStream().write(message.getBytes());
            } catch (IOException e) {
                System.out.println("客户端向服务端写数据失败了");
            }
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                System.out.println("线程异常了");
            }
        }
    }

}
