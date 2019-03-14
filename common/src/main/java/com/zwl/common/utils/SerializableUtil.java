package com.zwl.common.utils;

import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * Serializable工具(JDK)(也可以使用Protobuf自行百度)
 *
 * @author 二师兄超级帅
 * @date 2019/3/7 17:00 2018/9/4 15:13
 */
@Slf4j
public class SerializableUtil {

    /**
     * 序列化
     *
     * @param object
     * @return byte[]
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/9/4 15:14
     */
    public static byte[] serializable(Object object) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            log.error("SerializableUtil工具类序列化出现IOException异常:" + e.getMessage());
            throw new BizException(ErrorEnum.IO_ERROR);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                log.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
                throw new BizException(ErrorEnum.IO_ERROR);
            }
        }
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return java.lang.Object
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/9/4 15:14
     */
    public static Object unserializable(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (ClassNotFoundException e) {
            log.error("SerializableUtil工具类反序列化出现ClassNotFoundException异常:" + e.getMessage());
            throw new BizException(ErrorEnum.IO_ERROR);
        } catch (IOException e) {
            log.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
            throw new BizException(ErrorEnum.IO_ERROR);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                log.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
                throw new BizException(ErrorEnum.IO_ERROR);
            }
        }
    }

}
