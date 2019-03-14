package com.zwl.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 二师兄超级帅
 * @Title: LocalDateUtil
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2613:34
 */
public class LocalDateUtil {
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyyMMddHHmmss
     */
    public static final String YMDHMS = "yyyyMMddHHmmss";
    /**
     * yyyy-MM-dd
     */
    public static final String YMD = "yyyy-MM-dd";
    /**
     * MM-dd
     */
    public static final String MD = "MM月dd日";

    public static LocalDateTime parseTime(String str, String parseStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(parseStr);
        return LocalDateTime.parse(str, df);
    }

    public static LocalDate parseDate(String str, String parseStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(parseStr);
        return LocalDate.parse(str, df);
    }

    public static void main(String[] args) {
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime time = LocalDateTime.now();
//        String localTime = df.format(time);
//        LocalDateTime ldt = LocalDateTime.parse("2018-01-12 17:07:05", df);
//        System.out.println("LocalDateTime转成String类型的时间：" + localTime);
//        System.out.println("String类型的时间转成LocalDateTime：" + ldt);
        System.out.println(parseDate("2018-01-12 17:07:05", Y_M_D_HMS));
    }
}
