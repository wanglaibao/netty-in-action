package com.laibao.nettyinaction.nio;

import java.nio.ByteBuffer;

/**
 * Slice Buffer与原有buffer共享相同的底层数组
 *
 * @author laibao wang
 */
public class SliceByteBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(100);

        for(int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte)i);
        }

        //buffer.flip(); 如果没有这行的话，下面的这个while循环无效
        while(buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

        buffer.position(20);
        buffer.limit(60);

        ByteBuffer sliceBuffer = buffer.slice();

        for(int i = 0; i < sliceBuffer.capacity(); ++i) {
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while(buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
