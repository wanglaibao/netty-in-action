package com.laibao.nettyinaction.biosocket;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author laibao wang
 */
public class NioTestThree {

    private static final String FILE_NAME = null;

    public static void main(String[] args) throws Exception {

        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
             FileChannel fileChannel = fileOutputStream.getChannel();) {
            byte[] data = "fasfasfasfasfasfsfasfdasfasfasfdasfdasfasfasf".getBytes();
            ByteBuffer byteBuffer = ByteBuffer.wrap(data);
            for (int i = 0; i < data.length;i++) {
                byteBuffer.put(data[i]);
            }
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
        }
    }
}
