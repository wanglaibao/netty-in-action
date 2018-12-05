package com.laibao.nettyinaction.biosocket;

import java.nio.ByteBuffer;

/**
 * @author laibao wang
 */
public class NioTestSix {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        for (int i = 0;i < byteBuffer.capacity();i++) {
            byteBuffer.put((byte)(i + 1));
        }
        byteBuffer.position(10).limit(20);
        ByteBuffer slice = byteBuffer.slice();
        for (int i = 0; i < slice.capacity(); i++) {
            byte value = slice.get(i);
            value += 10;
            slice.put(i,value);
        }
        //byteBuffer.position(0).limit(byteBuffer.capacity());
        //byteBuffer.flip();
        byteBuffer.clear();
        while (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.get());
        }
        System.out.println("搞定了啊");
    }
}
