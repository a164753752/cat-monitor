package com.oriental.finance.cat.monitor.common.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: AnsonChan
 * Date: 13-9-23
 */
public class DateUtils {

    /** 时间格式：HHmmss*/
    public static final String timePattern = "HHmmss";
    /** 时间格式：yyyy/MM/ddHH:mm:ss*/
    public static final String timesPattern = "yyyy/MM/ddHH:mm:ss";
    /** 日期格式：yyyyMMdd*/
    public static final String datePattern = "yyyyMMdd";
    public static final String shortDatePattern = "yyMMdd";
    /** 日期时间格式：yyyyMMddHHmmss*/
    public static final String fullPattern = "yyyyMMddHHmmss";
    /** 日期时间格式：yyyyMMddHHmmssSS*/
    public static final String fullPatternSS = "yyyyMMddHHmmssSS";
    /** 日期时间格式：yyyyMMddHHmmssSS*/
    public static final String fullPatternSSS = "yyyy-MM-dd HH:mm:ss,SSS";
    /** 日期时间格式：yyMMddHHmmss*/
    public static final String partPattern = "yyMMddHHmmss";
    /** 日期时间格式：yyyy.MM.dd HH:mm:ss*/
    public static final String ticketPattern = "yyyy.MM.dd HH:mm:ss";//火车票购买格式
    public static final String settlePattern = "yyyy-MM-dd HH:mm:ss";
    /* 时间式：HHmm*/
    public static final String hour_of_minute = "HHmm";
    /** 时间格式：HH:mm:ss*/
    public static final String timeColPattern = "HH:mm:ss";
    /** 日期时间格式：yyyyMMdd HH:mm:ss*/
    public  static final String datefullPattern="yyyyMMdd HH:mm:ss";

    public static final String year_of_minute = "yyyyMMddHHmm";

    public static final String ymdhm = "yyyy-MM-dd HH:mm";

    public static final String shotPattern = "yyyy-MM-dd";

    public static Date getCurrentDate() {
        return DateTime.now().toDate();
    }

    public static String getCurrent() {
        return getCurrent(fullPattern);
    }

    public static String getCurrent(String pattern) {
        return DateTime.now().toString(pattern);
    }

    public static Date parse(String date) {
        if(!StringUtils.isBlank(date)&&date.length() > 14){
            return parse(date, fullPatternSS);
        }
        return parse(date, fullPattern);
    }
    public static Date parse(String date, String pattern) {
        DateTime dateTime = parseTime(date, pattern);
        if (dateTime == null) return null;
        return dateTime.toDate();
    }

    public static DateTime parseTime(String date, String pattern) {
        if (StringUtils.isBlank(date)) return null;
        if (fullPattern.equals(pattern)) { // 这段逻辑整合自 gateway-bank 中的 DateUtil
            date = StringUtils.rightPad(date, 14, '0');
        }
        return DateTimeFormat.forPattern(pattern).parseDateTime(date);
    }

    public static DateTime parseDate(Date date,String patten){
        if (null == date) return null;
        return new DateTime(date);
    }

    public static String format(Date date) {
        return format(date, fullPattern);
    }

    public static String format(Date date, String pattern) {
        if (date == null) return null;
        return new DateTime(date).toString(pattern);
    }

    public static String convert(String date, String targetPattern) {
        return convert(date, fullPattern, targetPattern);
    }

    public static String convert(String date, String originPattern, String targetPattern) {
        Date originDate = parse(date, originPattern);
        return format(originDate, targetPattern);
    }

    public static Date getMillsByStepToDate(String date, int step) {
        DateTime dateTime = parseTime(date, fullPattern);
        return dateTime.plusMillis(step).toDate();
    }

    public static String getMillsByStep(String date, int step){
        return format(getMillsByStepToDate(date, step), fullPattern);
    }

    /**
     * 日期计算
     * @param date 需要计算的日期
     * @param day 需要往前(负数)或往后(正数)的天数
     * @param inPattern 输入时间的格式
     * @param outPattern 输出时间的格式
     * @return 按照输出时间格式进行格式化后的时间
     */
    public static String computeDate(String date,int day,String inPattern,String outPattern){
       try{
            DateTime dateTime = parseTime(date, inPattern);
           dateTime=dateTime.minusDays(0-day);
            return format(dateTime.toDate(),outPattern);
       //输入时间转换错误时返回空值 避免因时间转换错误而导致原流程无法继续执行
       }catch (Exception e){
           return null;
       }
    }

    /**
     * 源日期和（目标日期加上毫秒数）比较大小， 大则返回false ，小返回true
     * @param src 源日期
     * @param target 目的日期
     * @param second 秒数
     * @return 成功，失败
     */
    public static boolean compareDateForSecond(Date src,Date target,int second){
        Calendar targetTime = Calendar.getInstance();
        targetTime.setTime(target);
        targetTime.add(Calendar.SECOND, second);
        Calendar srcTime = Calendar.getInstance();
        srcTime.setTime(src);
        return srcTime.compareTo(targetTime) <= 0;
    }

    /**
     * 源日期和（目标日期加上月数）比较大小， 大则返回false ，小返回true
     * @param src 源日期
     * @param target 目的日期
     * @param monthNum 月数
     * @return 成功，失败
     */
    public static boolean compareDateForMonth(Date src,Date target,int monthNum){
        Calendar targetDate = Calendar.getInstance();
        targetDate.setTime(target);
        targetDate.add(Calendar.MONTH, monthNum);
        Calendar srcDate = Calendar.getInstance();
        srcDate.setTime(src);

        return srcDate.compareTo(targetDate) > 0;
    }

    /**
     * 比较两个时间大小，并跟当前时间进行比较
     * @param firstDate
     * @param secondDate
     * @return
     * @throws Exception
     */
    public static boolean timeVerify(String firstDate,String secondDate) throws ParseException{
        //设置时间格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        Date nowDate = new Date();
        //格式化时间
        Date resTime = dateFormat.parse(firstDate);
        //格式化时间
        Date closTime = dateFormat.parse(secondDate);
        //转换成时间戳，如果当前时间处于通道关闭时间和恢复时间之内，则银行通道处于关闭
        if (resTime.getTime() > nowDate.getTime() && closTime.getTime() <= nowDate.getTime()){
            return false;
        }else {//如果通道恢复
            return true;
        }
    }

    /**
     * 比较两个时间是否为同一天
     * @param firstDate
     * @param secondDate
     * @return
     * @throws Exception
     */
    public static boolean checkSameDay(Date firstDate,Date secondDate){
        return (firstDate.getDay() == secondDate.getDay() &&
                firstDate.getMonth() == secondDate.getMonth() &&
                firstDate.getYear() == secondDate.getYear());
    }

    /**
     * 计算两个日期相差的周期时间
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return  计算结果
     */
    public static long computeDateByMS(Date startDate,Date endDate){
        Duration duration = new Duration(new DateTime(startDate),new DateTime(endDate));
        return duration.getMillis();
    }

}
