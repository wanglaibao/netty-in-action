package com.laibao.nettyinaction.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author laibao wang
 */
public class IntBufferMain {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(1024);

        byte[] data = "asdfasdfasfdasfdasfdasfdasfdasfd".getBytes();
        for (int i = 0;i< data.length;i++) {
            buffer.put(data[i]);
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
//        for (int i = 0;i < buffer.capacity();i++) {
//            int randomNumber = new SecureRandom().nextInt(10000);
//            buffer.put(randomNumber);
//        }
//        buffer.flip();
//        while (buffer.hasRemaining()) {
//            System.out.println(buffer.get());
//        }
    }
}
