package com.zwl.common.utils;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;

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

    public static LocalDateTime parseTime(String str, String parseStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(parseStr);
        return LocalDateTime.parse(str, df);
    }

    public static LocalDate parseDate(String str, String parseStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(parseStr);
        return LocalDate.parse(str, df);
    }

    /**
     * char(8)
     */
    public static final String YYYYMMDD = "yyyyMMdd";
    /**
     * char(10)
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * char(10)
     */
    public static final String YYYY_U_MM_U_DD = "yyyy年MM月dd日";
    /**
     * char(10)
     */
    public static final String YYYY__MM__DD = "yyyy/MM/dd";
    /**
     * char(14)
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /**
     * char(17)
     */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    /**
     * char(17)
     */
    public static final String YYYYMMDD_HH_MM_SS = "yyyyMMdd HH:mm:ss";
    /**
     * char(19)
     */
    public static final String YYYY__MM__DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
    /**
     * char(19)
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * char(19)
     */
    public static final String YYYY_U_MM_U_DD_HH_MM_SS = "yyyy年MM月dd日 HH:mm:ss";
    /**
     * char(19)
     */
    public static final String YYYY_U_MM_U_DD_HH_U_MM_U_SS = "yyyy年MM月dd日 HH时mm分ss";
    /**
     * char(20)
     */
    public static final String YYYY_U_MM_U_DD_HH_U_MM_U_SS_U = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * char(6)
     */
    public static final String HHMMSS = "HHmmss";
    /**
     * char(8)
     */
    public static final String HH_MM_SS = "HH:mm:ss";
    /**
     * char(8)
     */
    public static final String HH_U_MM_U_SS = "HH时mm分ss";
    /**
     * char(9)
     */
    public static final String HH_U_MM_U_SS_U = "HH时mm分ss秒";

    /**
     * 年
     */
    public static final String UNIT_Y = "Y";
    /**
     * 月
     */
    public static final String UNIT_M = "M";
    /**
     * 日
     */
    public static final String UNIT_D = "D";

    /**
     * 一天的毫秒数
     */
    private static final long ND = 1000 * 24 * 60 * 60;
    /**
     * 一小时的毫秒数
     */
    private static final long NH = 1000 * 60 * 60;
    /**
     * 一分钟的毫秒数
     */
    private static final long NM = 1000 * 60;
    /**
     * 一秒钟的毫秒数
     */
    private static final long NS = 1000;

    private static final int LEN_6 = 6;
    private static final int LEN_8 = 8;
    private static final int LEN_10 = 10;
    private static final int LEN_14 = 14;
    private static final int LEN_17 = 17;
    private static final int LEN_19 = 19;

    /* 初始化时定义类型集合，传入 formart 自定义类型需在里面 */
    private static final HashSet<String> DATE_SET = new HashSet<String>() {
        {
            add(YYYYMMDD);
            add(YYYY_MM_DD);
            add(YYYY__MM__DD);
            add(YYYY_U_MM_U_DD);
        }
    };

    private static final HashSet<String> TIME_SET = new HashSet<String>() {
        {
            add(HHMMSS);
            add(HH_MM_SS);
            add(HH_U_MM_U_SS);
            add(HH_U_MM_U_SS_U);
        }
    };


    private static final HashSet<String> DATETIME_SET = new HashSet<String>() {
        {
            add(YYYYMMDDHHMMSS);
            add(YYYYMMDDHHMMSSSSS);
            add(YYYYMMDD_HH_MM_SS);
            add(YYYY__MM__DD_HH_MM_SS);
            add(YYYY_MM_DD_HH_MM_SS);
            add(YYYY_U_MM_U_DD_HH_MM_SS);
            add(YYYY_U_MM_U_DD_HH_U_MM_U_SS);
            add(YYYY_U_MM_U_DD_HH_U_MM_U_SS_U);
        }
    };

    private static final HashSet<String> ALL_DATE_TIME_SET = new HashSet<String>() {
        {
            addAll(DATE_SET);
            addAll(TIME_SET);
            addAll(DATETIME_SET);
        }
    };

    /**
     * 带 view 的是测试方法
     */
    @Test
    public void viewAllDateOrTime() {
        int i = 0;
        for (String s : ALL_DATE_TIME_SET) {
            System.out.println(i++ + "-->   " + s);
            System.out.println(getDateTime(s));
        }
    }

    /**
     * @return yyyyMMddHHmmss
     */
    public static String getYyyymmddhhmmss() {
        return getDateTime(YYYYMMDDHHMMSS);
    }

    /**
     * @return yyyyMMddHHmmssSSS
     */
    public static String getYyyymmddhhmmsssss() {
        return getDateTime(YYYYMMDDHHMMSSSSS);
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getYyyy_mm_dd_hh_mm_ss() {
        return getDateTime(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getYyyy__mm__dd_hh_mm_ss() {
        return getDateTime(YYYY__MM__DD_HH_MM_SS);
    }

    /**
     * @return yyyyMMdd HH:mm:ss
     */
    public static String getYyyymmdd_hh_mm_ss() {
        return getDateTime(YYYYMMDD_HH_MM_SS);
    }

    /**
     * @return yyyy年MM月dd日 HH:mm:ss
     */
    public static String getYyyyUMmUDdHhMmSs() {
        return getDateTime(YYYY_U_MM_U_DD_HH_MM_SS);
    }

    /**
     * @return yyyy年MM月dd日 HH时mm分ss
     */
    public static String getYyyyUMmUDdHhUMmUSs() {
        return getDateTime(YYYY_U_MM_U_DD_HH_U_MM_U_SS);
    }

    /**
     * @return yyyy年MM月dd日 HH时mm分ss秒
     */
    public static String getYyyyUMmUDdHhUMmUSsU() {
        return getDateTime(YYYY_U_MM_U_DD_HH_U_MM_U_SS_U);
    }

    /**
     * @return yyyyMMdd
     */
    public static String getYyyyMmDd() {
        return getDateTime(YYYYMMDD);
    }

    /**
     * @return yyyy-MM-dd
     */
    public static String getYyyy_mm_dd() {
        return getDateTime(YYYY_MM_DD);
    }

    /**
     * @return yyyy/MM/dd
     */
    public static String getYyyy__mm__dd() {
        return getDateTime(YYYY__MM__DD);
    }

    /**
     * @return yyyy年MM月dd日
     */
    public static String getYyyyUMmUDd() {
        return getDateTime(YYYY_U_MM_U_DD);
    }

    /**
     * @return HHmmss
     */
    public static String getHhmmss() {
        return getDateTime(HHMMSS);
    }

    /**
     * @return HH:mm:ss
     */
    public static String getHh_mm_ss() {
        return getDateTime(HH_MM_SS);
    }

    /**
     * @return HH时mm分ss
     */
    public static String getHhUMmUSs() {
        return getDateTime(HH_U_MM_U_SS);
    }

    /**
     * @return HH时mm分ss秒
     */
    public static String getHhUMmUSsU() {
        return getDateTime(HH_U_MM_U_SS_U);
    }

    /**
     * 获取当日 yyyy-MM-dd 00:00:00
     */
    public static String getBeginDateTime() {
        return getDateTime(getNowDate().atTime(0, 0, 0));
    }

    /**
     * 获取当日 yyyy-MM-dd 00:00:00
     */
    public static LocalDateTime getNowBeginDate() {
        return getNowDate().atTime(0, 0, 0);
    }

    /**
     * 获取当日 yyyy-MM-dd 23:59:59
     */
    public static String getEndDateTime() {
        return getDateTime(getNowDate().atTime(23, 59, 59));
    }

    /**
     * 获取 yyyy-MM-dd 00:00:00
     *
     * @param date yyyy-MM-dd
     */
    public static String getBeginDateTime(String date) {
        return date + " 00:00:00";
    }

    /**
     * 获取 yyyy-MM-dd 23:59:59
     *
     * @param date yyyy-MM-dd
     */
    public static String getEndDateTime(String date) {
        return date + " 23:59:59";
    }


    @Test
    public void viewBeginAndEndDateTime() {
        System.out.println(getBeginDateTime());
        System.out.println(getEndDateTime());
        System.out.println(getBeginDateTime("2018-01-01"));
        System.out.println(getEndDateTime("2018-01-01"));
    }

    /**
     * @param format 将要格式的类型
     * @return format格式的当前日期时间
     */
    public static String getDateTime(String format) {
        return getDateTime(format, LocalDateTime.now());
    }

    /**
     * @param format        将要格式的类型
     * @param localDateTime 日期时间
     * @return format格式的当前日期时间
     */
    public static String getDateTime(String format, LocalDateTime localDateTime) {
        if (!ALL_DATE_TIME_SET.contains(format)) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 默认 yyyy-MM-dd HH:mm:ss
     *
     * @param localDateTime 日期时间
     * @return format格式的当前日期时间
     */
    public static String getDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }

    @Test
    public void viewYearOrMonthOrWeek() {
        // 2019年03月08日 22时49分38秒
        System.out.println(getYyyyUMmUDdHhUMmUSsU());
        // 2019
        System.out.println(getYear());
        // march
        System.out.println(getMonth());
        // 3
        System.out.println(getMonthValue());
        // friday
        System.out.println(getDayOfWeek());
        // 8
        System.out.println(getDayOfMonth());
        // 67
        System.out.println(getDayOfYear());
    }

    /**
     * @return 获取当前年份
     */
    public static int getYear() {
        return getDateTime().getYear();
    }

    /**
     * @return 获取月份对象
     */
    public static Month getMonth() {
        return getDateTime().getMonth();
    }

    /**
     * @return 获取当前月份
     */
    public static int getMonthValue() {
        return getDateTime().getMonthValue();
    }

    /**
     * @return 获取当前是星期几
     */
    public static DayOfWeek getDayOfWeek() {
        return getDateTime().getDayOfWeek();
    }

    /**
     * @return 获取当前是本月第几天
     */
    public static int getDayOfMonth() {
        return getDateTime().getDayOfMonth();
    }

    /**
     * @return 获取当前是本年第几天
     */
    public static int getDayOfYear() {
        return getDateTime().getDayOfYear();
    }

    private static LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    private static LocalDate getNowDate() {
        return LocalDate.now();
    }

    /**
     * @param dateTime 待转日期时间字符串
     * @param formart  待转日期时间字符串的格式类型
     * @return 将字符日期转为 LocalDateTime
     */
    public static LocalDateTime getDateTime(String dateTime, String formart) {
        requireNonNull(dateTime, "dateTime");
        if (!DATETIME_SET.contains(formart)) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(formart);
        try {
            return LocalDateTime.parse(dateTime, df);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param date    待转日期字符串
     * @param formart 待转日期字符串的格式类型
     * @return 将字符日期转为 LocalDateTime
     */
    public static LocalDate getDate(String date, String formart) {
        requireNonNull(date, "date");
        if (!DATE_SET.contains(formart)) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(formart);
        return LocalDate.parse(date, df);
    }

    /**
     * @param time    待转时间字符串
     * @param formart 待转时间字符串的格式类型
     * @return 将字符日期转为 LocalDateTime
     */
    public static LocalTime getTime(String time, String formart) {
        requireNonNull(time, "time");
        if (!TIME_SET.contains(formart)) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(formart);
        return LocalTime.parse(time, df);
    }

    /**
     * 判断是否为空或空字符，当前类不能信息不能为空字符
     */
    private static <T> T requireNonNull(T t, String message) {
        if (t == null || (t instanceof String) ? ((String) t).trim().equals("") : false) {
            throw new NullPointerException(message);
        }
        return t;
    }


    @Test
    public void viewStringToLocalDateTime() {
        /**
         *
         * 2018-06-01T10:12:05
         * 2018-06-01T10:12:05
         * 2018-06-01T10:12:05
         * 2018-06-01T10:12:05
         * 2018-06-01
         * 2018-06-01
         * 10:12:05
         *
         * */
        System.out.println(getDateTime("2018-06-01 10:12:05", YYYY_MM_DD_HH_MM_SS));
        System.out.println(getDateTime("2018年06月01日 10时12分05秒", YYYY_U_MM_U_DD_HH_U_MM_U_SS_U));
        System.out.println(getDateTime("2018年06月01日 10时12分05", YYYY_U_MM_U_DD_HH_U_MM_U_SS));
        System.out.println(getDateTime("2018/06/01 10:12:05", YYYY__MM__DD_HH_MM_SS));
//        System.out.println(getDateTime(null, YYYY__MM__DD_HH_MM_SS));

        System.out.println(getDate("2018-06-01", YYYY_MM_DD));
        System.out.println(getDate("2018/06/01", YYYY__MM__DD));

        System.out.println(getTime("10:12:05", HH_MM_SS));
    }

    /**
     * 获取当前 Date 类型日期时间
     */
    public static Date getDate() {
        return localToDate(LocalDateTime.now());
    }

    /**
     * LocalDateTime -> Date
     *
     * @param localDateTime
     * @return java.util.Date
     */
    public static Date localToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 将要增加或删减的年、月、日、时、分、秒，参数可为负数
     *
     * @param localDateTime
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param second
     * @param minute
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime add(LocalDateTime localDateTime, long year, long month, long day,
                                    long hour, long second, long minute) {
        localDateTime = localDateTime.plusYears(year);
        localDateTime = localDateTime.plusMonths(month);
        localDateTime = localDateTime.plusDays(day);
        localDateTime = localDateTime.plusHours(hour);
        localDateTime = localDateTime.plusSeconds(second);
        localDateTime = localDateTime.plusMinutes(minute);
        return localDateTime;
    }

    /**
     * 将要增加或删减的年、月、日、时、分、秒，参数可为负数
     *
     * @param localDateTime
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param second
     * @param minute
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime minus(LocalDateTime localDateTime, long year, long month, long day,
                                      long hour, long minute, long second) {
        localDateTime = localDateTime.minusYears(year);
        localDateTime = localDateTime.minusMonths(month);
        localDateTime = localDateTime.minusDays(day);
        localDateTime = localDateTime.minusHours(hour);
        localDateTime = localDateTime.minusMinutes(minute);
        localDateTime = localDateTime.minusSeconds(second);
        return localDateTime;
    }

    @Test
    public void viewAddOrMinus() {
        LocalDateTime dateTime = getDateTime("2018-06-01 10:12:05", YYYY_MM_DD_HH_MM_SS);
        LocalDateTime add = add(dateTime, -1, 0, 0, 0, 0, 0);
        LocalDateTime minus = minus(dateTime, -1, -1, -1, -1, -1, -1);
        System.out.println(add);
        System.out.println(minus);
    }

    /**
     * 判断月日是否重复，可用于判断生日节日等
     *
     * @param localDateTime
     * @param month
     * @param day
     * @return boolean
     */
    public static boolean repetitionMonthDay(LocalDateTime localDateTime, int month, int day) {
        return MonthDay.of(month, day).equals(MonthDay.from(localDateTime));
    }

    @Test
    public void viewRepetition() {
        /**
         * 2019年03月09日 01时14分11秒
         * true
         * */
        System.out.println(getYyyyUMmUDdHhUMmUSsU());
        System.out.println(repetitionMonthDay(LocalDateTime.now(), 3, 9));
    }


    /**
     * differenceDay
     * 只是天数，最大为30
     */
    public static int diffDay(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate).getDays();
    }

    /**
     * differenceMonth
     * 只是月数，最大11
     */
    public static int diffMonth(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate).getMonths();
    }

    /**
     * differenceYear
     */
    public static int diffYear(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate).getYears();
    }

    @Test
    public void viewDiff() {
        /**
         * 2019年03月09日 01时30分12秒
         * 8
         * 2
         * 1
         * 2018-01-01 2019-03-09 相差 1年2月8日
         * */
        LocalDate date = getDate("2018-01-01", YYYY_MM_DD);
        System.out.println(getYyyyUMmUDdHhUMmUSsU());
        System.out.println(diffDay(date, LocalDate.now()));
        System.out.println(diffDay(LocalDate.now(), date));
        System.out.println(diffMonth(date, LocalDate.now()));
        System.out.println(diffYear(date, LocalDate.now()));
    }

    /**
     * interval 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffYear(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.YEARS.between(startDateTime, endDateTime);
    }

    /**
     * interval 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffMonth(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.MONTHS.between(startDateTime, endDateTime);
    }

    /**
     * interval 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffDay(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.DAYS.between(startDateTime, endDateTime);
    }

    /**
     * interval 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffHour(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.HOURS.between(startDateTime, endDateTime);
    }

    /**
     * interval 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffMinute(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.MINUTES.between(startDateTime, endDateTime);
    }

    /**
     * interval 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffSecond(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.SECONDS.between(startDateTime, endDateTime);
    }

    /**
     * interval 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffYear(String startDateTime, String endDateTime, String formart) {
        LocalDateTime beginDateTime = getDateTime(startDateTime, formart);
        LocalDateTime overDateTime = getDateTime(endDateTime, formart);
        return ChronoUnit.YEARS.between(beginDateTime, overDateTime);
    }

    /**
     * interval 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffMonth(String startDateTime, String endDateTime, String formart) {
        LocalDateTime beginDateTime = getDateTime(startDateTime, formart);
        LocalDateTime overDateTime = getDateTime(endDateTime, formart);
        return ChronoUnit.MONTHS.between(beginDateTime, overDateTime);
    }

    /**
     * interval 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffDay(String startDateTime, String endDateTime, String formart) {
        LocalDateTime beginDateTime = getDateTime(startDateTime, formart);
        LocalDateTime overDateTime = getDateTime(endDateTime, formart);
        return ChronoUnit.DAYS.between(beginDateTime, overDateTime);
    }

    /**
     * interval 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffHour(String startDateTime, String endDateTime, String formart) {
        LocalDateTime beginDateTime = getDateTime(startDateTime, formart);
        LocalDateTime overDateTime = getDateTime(endDateTime, formart);
        return ChronoUnit.HOURS.between(beginDateTime, overDateTime);
    }

    /**
     * interval 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffMinute(String startDateTime, String endDateTime, String formart) {
        LocalDateTime beginDateTime = getDateTime(startDateTime, formart);
        LocalDateTime overDateTime = getDateTime(endDateTime, formart);
        return ChronoUnit.MINUTES.between(beginDateTime, overDateTime);
    }

    /**
     * intervalYear 计算间隔
     *
     * @param startDateTime
     * @param endDateTime
     * @return long
     */
    public static long diffSecond(String startDateTime, String endDateTime, String formart) {
        LocalDateTime beginDateTime = getDateTime(startDateTime, formart);
        LocalDateTime overDateTime = getDateTime(endDateTime, formart);
        return ChronoUnit.SECONDS.between(beginDateTime, overDateTime);
    }

    @Test
    public void viewDiffLong() {
        /**
         * 2018-01-01T10:12:05
         * 2019-03-09T01:51:15.028
         * 相差年：1
         * 相差月：14
         * 相差日：431
         * 相差时：10359
         * 相差分：621579
         * 相差秒：37294750
         * */
        LocalDateTime dateTime = getDateTime("2018-01-01 10:12:05", YYYY_MM_DD_HH_MM_SS);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dateTime);
        System.out.println(now);
        System.out.println("相差年：" + diffYear(dateTime, now));
        System.out.println("相差月：" + diffMonth(dateTime, now));
        System.out.println("相差日：" + diffDay(dateTime, now));
        System.out.println("相差时：" + diffHour(dateTime, now));
        System.out.println("相差分：" + diffMinute(dateTime, now));
        System.out.println("相差秒：" + diffSecond(dateTime, now));
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
