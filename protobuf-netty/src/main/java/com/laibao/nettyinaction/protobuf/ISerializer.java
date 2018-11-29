package com.laibao.nettyinaction.protobuf;

/**
 * @author laibao wang
 */
public interface ISerializer{

    /**
     * 序列化
     * @param t
     * @param <T>
     * @return byte[]
     */
    <T> byte[] serialize(T t);

    /**
     * 反序列化
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] data,Class<T> clazz);
}
