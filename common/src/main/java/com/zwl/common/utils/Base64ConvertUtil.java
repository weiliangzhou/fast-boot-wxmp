package com.zwl.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Base64工具
 * @author 二师兄超级帅
 * @date 2019/3/7 17:00 2018/8/21 15:14
 */
public class Base64ConvertUtil {

    /**
     * 加密JDK1.8
     * @param str
     * @return java.lang.String
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/8/21 15:28
     */
    public static String encode(String str) throws UnsupportedEncodingException {
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
        return new String(encodeBytes);
    }

    /**
     * 解密JDK1.8
     * @param str
     * @return java.lang.String
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/8/21 15:28
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes("utf-8"));
        return new String(decodeBytes);
    }

}
