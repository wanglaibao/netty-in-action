package com.laibao.nettyinaction.nio;

import java.nio.ByteBuffer;

/**
 * @author laibao wang
 */
public class PrimitiveTypeByteBuffer {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.putInt(15);
        byteBuffer.putLong(500000000L);
        byteBuffer.putDouble(14.123456);
        byteBuffer.putChar('你');
        byteBuffer.putShort((short)2);
        byteBuffer.putChar('我');

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getChar());
    }
}
