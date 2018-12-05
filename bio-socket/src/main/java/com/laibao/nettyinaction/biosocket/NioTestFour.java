package com.laibao.nettyinaction.biosocket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author laibao wang
 */
public class NioTestFour {

    private static final String IN_FILE_NAME = null;

    private static final String OUT_FILE_NAME = null;

    public static void main(String[] args) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(IN_FILE_NAME);
             FileOutputStream fileOutputStream = new FileOutputStream(OUT_FILE_NAME);
             FileChannel inFileChannel = fileInputStream.getChannel();
             FileChannel outFileChannel = fileOutputStream.getChannel();
        ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            while (true) {
                System.out.println("文件读写操作");
                byteBuffer.clear();
                int readCount = inFileChannel.read(byteBuffer);
                if (readCount == -1) {
                    break;
                }
                byteBuffer.flip();
                outFileChannel.write(byteBuffer);
            }
        }
    }
}
