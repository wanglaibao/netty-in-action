package com.laibao.nettyinaction.nio;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * @author laibao wang
 */
public class CharBufferFillDrain {

    private static int index = 0;

    private static String [] strArray = {
            "A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly",
            "Help Me! Help Me!",
    };

    private static boolean fillBuffer (CharBuffer charBuffer) {
        if (index >= strArray.length) {
            return false;
        }
        String string = strArray [index++];
//        for (int i = 0; i < string.length( ); i++) {
//            charBuffer.put (string.charAt (i));
//        }
        Arrays.asList(string).forEach(c -> charBuffer.put(c));
        return true;
    }

    private static void drainBuffer (CharBuffer charBuffer) {
//        while (charBuffer.hasRemaining( )) {
//            System.out.print (charBuffer.get( ));
//        }
//        System.out.println ("");
        int count = charBuffer.remaining( );
        for (int i = 0; i < count; i++) {
            System.out.println(charBuffer.get());
        }
        System.out.println ("");
    }

    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        while (fillBuffer(charBuffer)) {
            charBuffer.flip();
            drainBuffer(charBuffer);
            charBuffer.clear();
        }
    }

}
