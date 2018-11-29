package com.laibao.nettyinaction.protobuf;

import com.google.protobuf.GeneratedMessageV3;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author laibao wang
 */
public class ProtoBufSerializer implements ISerializer{

    @Override
    public <T> byte[] serialize(T t) {
        try {
            if (t instanceof GeneratedMessageV3) {
                return (byte[]) MethodUtils.invokeMethod(t,"toByteArray");
            }else {
                throw new UnsupportedOperationException("not supported object type");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        try{
            if (GeneratedMessageV3.class.isAssignableFrom(clazz)) {
                Object obj = MethodUtils.invokeExactStaticMethod(clazz,"getDefaultInstance");
                return (T) MethodUtils.invokeMethod(obj,"parseFrom",new Object[] {data});
            }else {
                throw new UnsupportedOperationException("not supported object type");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
