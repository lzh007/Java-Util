package com.lzh.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 〈时间格式工具类〉
 *
 * @author lzh
 * @create 2019/11/6
 *
 */
public class DateTimeUtil {

    private static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    public static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DAY = "yyyy-MM-dd";
    public static final String PATTERN_COMPACT = "yyyyMMdd";
    public static final String PATTERN_DAY_SLASH = "yyyy/MM/dd";
    public static final String PATTERN_MONTH_SLASH = "yyyy/MM";

    public static SimpleDateFormat formatDisplayDate = new SimpleDateFormat("M月d号");
    public static SimpleDateFormat formatDisplayTimeA = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static Date today() {
        return new Date();
    }

    public static String now() {
        return formatDate(today());
    }

    public static String formatDate(Date date) {
        return formatDate(date, PATTERN_DEFAULT);
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            throw new IllegalArgumentException("date is null");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("pattern is null");
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }


    /**
     * 获得可以正常显示的时间
     *
     * @param time
     * @return
     */
    public static String normalizeTime(long time) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return sm.format(date);
    }


    /**
     * 获得可以正常显示的时间
     *
     * @param date"yyyy-MM-dd"
     * @return
     */
    public static String parseDateTime(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sm = new SimpleDateFormat(format);
        return sm.format(date);
    }


    /**
     * 获取今天凌晨的时间
     * @param date
     */
    public static Date getMorningDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取昨天凌晨的时间
     */
    public static Date getYesterdayDate() {
        Date date = new Date(System.currentTimeMillis() - 86400 * 1000L);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 计算两个时间相差的年份
     *
     * @param date
     * @return
     */
    public static long getDateDiff(Date dateStart, Date dateStop) {
        if (dateStart != null && dateStop != null) {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");
            formatDate.format(dateStart);
            formatDate.format(dateStop);
            long diff = (dateStart.getTime() - dateStop.getTime()) / 1000 / 3600 / 24 / 365;
            return diff;
        }
        return 0;
    }

    /**
     * 字符串转为Date
     *
     * @param date"yyyy-MM-dd"
     * @return
     */
    public static Date parseStringToDate(String date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sm = new SimpleDateFormat(format);
        try {
            return sm.parse(date);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

	/**
     * 字符串转为Date
     *
     * @param string
     * @return
     */
    public static Date parseStringToDate(String time) {
        if (StringUtil.isEmpty(time)) {
            return null;
        }

        int year = 0, month = 1, day = 1, hour = 0, min = 0, sec = 0;

        List<String> strList = new ArrayList<String>();
        StringBuffer nsb = new StringBuffer();
        for (int i = 0; i < time.length(); i++) {
            char c = time.charAt(i);
            if (Character.isDigit(c)) {
                nsb.append(c);
            } else {
                if (nsb.length() > 0) {
                    strList.add(nsb.toString());
                    nsb = new StringBuffer();
                }
            }
        }
        if (nsb.length() > 0) {
            strList.add(nsb.toString());
        }

        for (int i = 0; i < strList.size(); i++) {
            int n = CommonUtil.getIntValue(strList.get(i));
            switch (i) {
                case 0:
                    year = n;
                    break;
                case 1:
                    month = n;
                    break;
                case 2:
                    day = n;
                    break;
                case 3:
                    hour = n;
                    break;
                case 4:
                    min = n;
                    break;
                case 5:
                    sec = n;
                    break;
            }
        }

        StringBuffer sb = new StringBuffer();
        sb.append(StringUtil.formatString("%04d", year)).append("-")
                .append(StringUtil.formatString("%02d", month)).append("-")
                .append(StringUtil.formatString("%02d", day)).append(" ")
                .append(StringUtil.formatString("%02d", hour)).append(":")
                .append(StringUtil.formatString("%02d", min)).append(":")
                .append(StringUtil.formatString("%02d", sec));
        return parseStringToDate(sb.toString(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 月与当前的时间差
     *
     * @param Date
     * @return
     */
    public static long diffMonth(Date end) {
        Date begin = new Date();
        if (begin != null && end != null) {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
            formatDate.format(begin);
            formatDate.format(end);
            long diff = (begin.getTime() - end.getTime()) / 1000 / 3600 / 24;


            return diff;
        }
        return 0;
    }

    /**
     * 月与月的时间差
     *
     * @param Date，Date
     * @return
     */
    public static long diffMonth(Date begin, Date end) {
        if (begin != null && end != null) {

            Calendar beginCal = Calendar.getInstance();
            beginCal.setTime(begin);

            Calendar endCal = Calendar.getInstance();
            endCal.setTime(end);

            long month = (endCal.get(Calendar.YEAR) - beginCal.get(Calendar.YEAR)) * 12;
            return month + (endCal.get(Calendar.MONTH) - beginCal.get(Calendar.MONTH));

        }
        return 0;
    }

    /**
     * 取一天最大的时间
     *
     * @param date
     * @return
     */
    public static Date ceiling(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        String day = sm.format(date) + " 23:59:59";
        return parseStringToDate(day, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 取一天最小的时间
     *
     * @param date
     * @return
     */
    public static Date floor(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        String day = sm.format(date) + " 00:00:00";
        return parseStringToDate(day, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 取一年最大的日期
     *
     * @param date
     * @return
     */
    public static Date maxDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sm = new SimpleDateFormat("yyyy");
        String day = sm.format(date) + "-12-31 00:00:00";
        return parseStringToDate(day, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Date类型转换Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp toTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * 获取差值（相隔几天）的时间
     *
     * @param date
     * @param daltaDays daltaDays>0 表示后面几天 daltaDays<0 表示前面几天
     * @return
     */
    public static Date delta(Date date, int daltaDays) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime() + (daltaDays * 86400 * 1000l));
    }

    /**
     * Date类型转换sql Date
     *
     * @param date
     * @return
     */
    public static java.sql.Date toSqlDate(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    /**
     * 获取本月第一天
     */
    public static Date firstDayInMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    /**
     * 获取本月最后一天
     */
    public static Date lastDayInMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));

        return cal.getTime();
    }


 	/**
     * 中文年月日格式
     *
     * @param String
     * @return
     */
    public static String getChinaDateFromString(String str) {
        try {
            if(str==null ){
                return getChinaCurrentDate();
            }
            str = str.trim();
            int year = 0;
            int month = 0;
            int day = 0;
//                  System.out.println("==="+str);
            if(str==null || str.equals("null") || str.equals("")){
                return getChinaCurrentDate();
            }
            else if (str.indexOf("年") > 0||str.indexOf("月") > 0||str.indexOf("日") > 0) {
                return str;
            }
            else {
                if (str.length() == 10 && (str.indexOf("-") > 0)) { // by meconsea  add str.indexOf("-") > 0
                    year = Integer.parseInt(str.substring(0, 4));
                    month = Integer.parseInt(str.substring(5, 7));
                    day = Integer.parseInt(str.substring(8, 10));
                }
                else if (str.length() == 8) {
                    year = Integer.parseInt(str.substring(0, 4));
                    month = Integer.parseInt(str.substring(4, 6));
                    day = Integer.parseInt(str.substring(6, 8));
                }
                else if (str.length() == 6) {
                    year = Integer.parseInt(str.substring(0, 4));
                    month = Integer.parseInt(str.substring(4, 6));
                }
                if (day == 0) {
                    str = month + "月";
                } else {
                    str = month + "月" + day + "日";
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

 	/**
     * 当前（中文）年月日
     *
     * @param date
     * @return
     */
    public static String getChinaCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String str_date = dateFormat.format(Calendar.getInstance().getTime());
        str_date = str_date.substring(4, 6) + "月" + str_date.substring(6, 8) + "日 ";
        return str_date;
    }
}

