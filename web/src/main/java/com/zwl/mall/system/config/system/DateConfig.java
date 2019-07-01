//package com.zwl.mall.system.config.system;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.Formatter;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.Locale;
//
///**
// * @author 二师兄超级帅
// * @Title: 转换解析器
// * @ProjectName mall
// * @Description: TODO
// * @date 2019/1/2614:05
// */
//@Configuration
//public class DateConfig {
//    /***
//     * Date日期类型转换器
//     * @return
//     */
//    @Bean
//    public Formatter<Date> dateFormatter() {
//        return new Formatter<Date>() {
//
//            @Override
//            public Date parse(String text, Locale locale) throws ParseException {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date = null;
//                try {
//                    date = sdf.parse(text);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return date;
//            }
//
//            @Override
//            public String print(Date object, Locale locale) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                return sdf.format(object);
//            }
//        };
//    }
//
//    @Bean
//    public Formatter<LocalDate> localDateFormatter() {
//        return new Formatter<LocalDate>() {
//            @Override
//            public LocalDate parse(String text, Locale locale) throws ParseException {
//                return LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
//            }
//
//            @Override
//            public String print(LocalDate object, Locale locale) {
//                return DateTimeFormatter.ISO_LOCAL_DATE.format(object);
//            }
//        };
//    }
//
//    @Bean
//    public Formatter<LocalDateTime> localDateTimeFormatter() {
//        return new Formatter<LocalDateTime>() {
//            @Override
//            public String print(LocalDateTime localDateTime, Locale locale) {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                return formatter.format(localDateTime);
//            }
//
//            @Override
//            public LocalDateTime parse(String text, Locale locale) throws ParseException {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            }
//        };
//    }
//}
//
