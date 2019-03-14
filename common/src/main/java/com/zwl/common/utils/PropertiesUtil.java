package com.zwl.common.utils;

import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Properties工具
 *
 * @author 二师兄超级帅
 * @date 2019/3/7 17:00 2018/8/31 17:29
 */
@Slf4j
public class PropertiesUtil {

    /**
     * PROP
     */
    private static final Properties PROP = new Properties();

    /**
     * 读取配置文件
     *
     * @param fileName
     * @return void
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/8/31 17:29
     */
    public static void readProperties(String fileName) {
        InputStream in = null;
        try {
            in = PropertiesUtil.class.getResourceAsStream("/" + fileName);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            PROP.load(bf);
        } catch (IOException e) {
            log.error("PropertiesUtil工具类读取配置文件出现IOException异常:" + e.getMessage());
            throw new BizException(ErrorEnum.IO_ERROR);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("PropertiesUtil工具类读取配置文件出现IOException异常:" + e.getMessage());
                throw new BizException(ErrorEnum.IO_ERROR);
            }
        }
    }

    /**
     * 根据key读取对应的value
     *
     * @param key
     * @return java.lang.String
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/8/31 17:29
     */
    public static String getProperty(String key) {
        return PROP.getProperty(key);
    }
}
