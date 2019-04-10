package com.zwl.common.utils;

import java.util.UUID;

/**
 * @author 二师兄超级帅
 * @Title: UUIDUtil
 * @ProjectName parent
 * @Description: TODO
 * @date 2018/7/614:09
 */
public class UUIDUtil {
    public static String getUUID32() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        System.out.println(uuid);
    }
}
