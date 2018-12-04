package com.laibao.nettyinaction.biosocket;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author laibao wang
 */
public class NioTestTwo {

    private static final String FILE_NAME = null;

    public static void main(String[] args) throws Exception {

        try (FileInputStream inputStream = new FileInputStream(FILE_NAME);
             FileChannel fileChannel = inputStream.getChannel();) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                while (true) {
                    byteBuffer.clear();
                    int index =  fileChannel.read(byteBuffer);
                    if (index == -1) {
                        break;
                    }
                    byteBuffer.flip();
                    int count = byteBuffer.remaining();
                    for (int i = 0;i < count;i++) {
                        System.out.println((char) byteBuffer.get());
                    }
                }
        }
    }
}
