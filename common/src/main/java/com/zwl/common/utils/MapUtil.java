package com.zwl.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 二师兄超级帅
 * @Title: MapUtil
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2613:53
 */
public class MapUtil {
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }
        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }

    public static Map<String, Object> objectToUnderlineMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(humpToUnderLine(field.getName()), field.get(obj));
        }

        return map;
    }


    /***
     * 下划线命名转为驼峰命名
     *
     * @param para
     *        下划线命名的字符串
     */

    public static String underLineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String[] arr = para.split("_");
        for (String s : arr) {
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }


    /***
     * 驼峰命名转为下划线命名
     *
     * @param para
     *        驼峰命名的字符串
     */

    public static String humpToUnderLine(String para) {
        StringBuilder sb = new StringBuilder(para);
        /**
         *  定位
         */
        int temp = 0;
        if (!para.contains("_")) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

}
