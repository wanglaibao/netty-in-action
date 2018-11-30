package com.laibao.nettyinaction.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author laibao wang
 */
public class ByteBufferWriterMain {

    private static final String FILE_PATH = "your file path";

    public static void main(String[] args) throws Exception {

        try(FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            FileChannel fileChannel = fileOutputStream.getChannel();) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byte[] data = "hello world welcome hello world welcome hello world welcome".getBytes();
                for (int i = 0;i<data.length;i++) {
                    byteBuffer.put(data[i]);
                }
                byteBuffer.flip();
                fileChannel.write(byteBuffer);
                fileOutputStream.close();
        }
    }
}
