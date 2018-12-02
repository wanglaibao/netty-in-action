package com.laibao.nettyinaction.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author laibao wang
 *
 */
public class DirectByteBufferMain {

    private static final String INPUT_FILE_PATH="your input file_name path";

    private static final String OUTPUT_FILE_PATH="your output file_name path";

    public static void main(String[] args) throws Exception {

        try (FileInputStream fileInputStream = new FileInputStream(INPUT_FILE_PATH);
             FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE_PATH);
             FileChannel inputFileChannel = fileInputStream.getChannel();
             FileChannel outputFileChannel = fileOutputStream.getChannel();
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                buffer.clear();
                int position = inputFileChannel.read(buffer);
                if (-1 == position) {
                    break;
                }
                buffer.flip();
                outputFileChannel.write(buffer);
            }
            fileOutputStream.close();
            fileInputStream.close();
        }

    }
}
