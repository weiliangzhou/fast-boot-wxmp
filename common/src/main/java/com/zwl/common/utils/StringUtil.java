package com.zwl.common.utils;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具
 *
 * @author 二师兄超级帅
 * @date 2019/3/7 17:00 2018/9/4 14:48
 */
public class StringUtil {
    /**
     * 定义下划线
     */
    private static final char UNDERLINE = '_';

    /**
     * String为空判断(不允许空格)
     *
     * @param str
     * @return boolean
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/9/4 14:49
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * String不为空判断(不允许空格)
     *
     * @param str
     * @return boolean
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/9/4 14:51
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Byte数组为空判断
     *
     * @param bytes
     * @return boolean
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/9/4 15:39
     */
    public static boolean isNull(byte[] bytes) {
        // 根据byte数组长度为0判断
        return bytes.length == 0 || bytes == null;
    }

    /**
     * Byte数组不为空判断
     *
     * @param bytes
     * @return boolean
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/9/4 15:41
     */
    public static boolean isNotNull(byte[] bytes) {
        return !isNull(bytes);
    }

    /**
     * 驼峰转下划线工具
     *
     * @param param
     * @return java.lang.String
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/9/4 14:52
     */
    public static String camelToUnderline(String param) {
        if (isNotBlank(param)) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                char c = param.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(UNDERLINE);
                    sb.append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 下划线转驼峰工具
     *
     * @param param
     * @return java.lang.String
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/9/4 14:52
     */
    public static String underlineToCamel(String param) {
        if (isNotBlank(param)) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                char c = param.charAt(i);
                if (c == 95) {
                    ++i;
                    if (i < len) {
                        sb.append(Character.toUpperCase(param.charAt(i)));
                    }
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 在字符串两周添加''
     *
     * @param param
     * @return java.lang.String
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/9/4 14:53
     */
    public static String addSingleQuotes(String param) {
        return "\'" + param + "\'";
    }

//    public static String pin(String chinese) throws Exception {
//        String pinyin = "";
//        HanyuPinyinOutputFormat pinyinOutputFormat = new HanyuPinyinOutputFormat();
//        pinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//        pinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        String[] pinyinArray = null;
//        for (char ch : chinese.toCharArray()) {
//            pinyinArray = PinyinHelper.toHanyuPinyinStringArray(ch, pinyinOutputFormat);
//            pinyin += ComUtil.isEmpty(pinyinArray) ? ch : pinyinArray[0];
//        }
//        return pinyin;
//    }

    /**
     * 获取方法中指定注解的value值返回
     *
     * @param method               方法名
     * @param validationParamValue 注解的类名
     * @return
     */
    public static String getMethodAnnotationOne(Method method, String validationParamValue) {
        String retParam = null;
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                String str = parameterAnnotations[i][j].toString();
                if (str.indexOf(validationParamValue) > 0) {
                    retParam = str.substring(str.indexOf("=") + 1, str.indexOf(")"));
                }
            }
        }
        return retParam;
    }

    public static boolean isValidURLAddress(String url) {
        String pattern = "([h]|[H])([t]|[T])([t]|[T])([p]|[P])([s]|[S]){0,1}://([^:/]+)(:([0-9]+))?(/\\S*)*";
        return url.matches(pattern);
    }

    /**
     * 将utf-8编码的汉字转为中文
     *
     * @param str
     * @return
     * @author zhaoqiang
     */
    public static String utf8Decoding(String str) {
        String result = str;
        try {
            result = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkEmail(String email) {
        if (ComUtil.isEmpty(email)) {
            return false;
        }
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一 *
     *
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            // Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Pattern regex = Pattern.compile("^1[345789]\\d{9}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

}
