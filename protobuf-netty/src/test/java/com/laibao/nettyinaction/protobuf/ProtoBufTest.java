package com.laibao.nettyinaction.protobuf;

import com.alibaba.fastjson.JSON;
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

    @Test
    public void testProtoBufSerializer() {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("金戈金戈金戈金戈")
                .setAge(200000)
                .setAddress("中国浙江杭州，中国浙江杭州")
                .build();

        ProtoBufSerializer protoBufSerializer = new ProtoBufSerializer();

        //序列化
        byte[] byteData= protoBufSerializer.serialize(student);

        //反序列化
        DataInfo.Student student1 = protoBufSerializer.deserialize(byteData,DataInfo.Student.class);
       // System.out.println(JSON.toJSONString(student1)); 这么做是错误的，但是我目前还不知道具体原因啊
        System.out.println(student1.getName());
        System.out.println(student1.getAge());
        System.out.println(student1.getAddress());
    }
}
