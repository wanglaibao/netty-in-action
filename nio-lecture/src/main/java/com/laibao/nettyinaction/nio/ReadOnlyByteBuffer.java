package com.laibao.nettyinaction.nio;

import java.nio.ByteBuffer;

/**
 * @author laibao wang
 */
public class ReadOnlyByteBuffer {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        for (int i = 0;i<byteBuffer.capacity();i++) {
            byteBuffer.put((byte)i);
        }

        ByteBuffer readOnlyByteBuffer = byteBuffer.asReadOnlyBuffer();
        for (int i =0;i < readOnlyByteBuffer.capacity();i++) {
            System.out.println(readOnlyByteBuffer.get(i));
        }
    }
}
