package com.laibao.nettyinaction.nio;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author laibao wang
 */
public class ByteBufferReaderMain {

    private static final String filePath = "your_file_path";

    public static void main(String[] args) throws Exception {
        try(FileInputStream fileInputStream = new FileInputStream(filePath);
            FileChannel fileChannel = fileInputStream.getChannel();){
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byteBuffer.clear();
                fileChannel.read(byteBuffer);
                byteBuffer.flip();
                while (byteBuffer.remaining() > 0) {
                    byte b = byteBuffer.get();
                    System.out.println("Character: " + (char)b);
                }
        }
    }
}
