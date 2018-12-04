package com.laibao.nettyinaction.biosocket;

import java.nio.IntBuffer;

/**
 * @author laibao wang
 */
public class NioTestOne {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(100);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i+1);
        }
        // Exception in thread "main" java.nio.BufferOverflowException
        // intBuffer.put(1000);
        intBuffer.flip();
        int count = intBuffer.remaining();
        for (int i = 0;i < count; i++) {
            System.out.println(intBuffer.get());
        }

        if (intBuffer.hasRemaining()) {
            System.out.println("到顶了啊");
        } else {
            System.out.println("还没有到顶啊!");
        }

        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get()+" jinGe");
        }

        intBuffer.clear();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get()+" AAAAAAAAAAA");
        }
    }
}
