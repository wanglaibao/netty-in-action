package com.laibao.nettyinaction.biosocket;

import java.nio.ByteBuffer;

/**
 * @author laibao wang
 */
public class NioTestFive {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        byteBuffer.put((byte)10);
        byteBuffer.putShort((short)100);
        byteBuffer.putInt(1000);
        byteBuffer.putLong(1000000L);
        byteBuffer.putFloat(20.1F);
        byteBuffer.putDouble(28.09);
        byteBuffer.putChar('A');

        byteBuffer.flip();

        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getFloat());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getChar());
    }
}
