package com.laibao.nettyinaction.biosocket;

/**
 * @author laibao wang
 */
public class BioSocketServerBootStrap {

    private static final int SERVER_PORT = 8989;

    public static void main(String[] args) {
        BioSocketServer server = new BioSocketServer(SERVER_PORT);
        server.start();
    }
}
