package com.laibao.nettyinaction.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

/**
 * @author laibao wang
 */
public class ProtoBufTest {

    @Test
    public void testProtoBuf() {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                                                    .setName("金戈")
                                                    .setAge(200)
                                                    .setAddress("浙江杭州")
                                                    .build();

        byte[] student2ByteArray = student.toByteArray();

        try {
            DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);
            System.out.println(student2.getName());
            System.out.println(student2.getAge());
            System.out.println(student2.getAddress());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
