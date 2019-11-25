package com.lzh.utils;


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 〈格式转换工具类（里程、钱、时间、指定数字打码）〉
 *
 * @author lzh
 * @create 2019/11/25
 *
 */
public class FormateUtil {
    private static final String UNIT_YUAN = "元";
    private static final String UNIT_WAN_YUAN = "万元";
    private static final String YUAN_0_00 = "0.00";
    private static final String YUAN_0 = "0";

    private static final String UNIT_WAN_MILAGE = "万公里";
    /**
     * 分转元  100 -> 1.00
     * @param money
     * @return
     */
    public static String fenToIntYuan(Long money){
        if(money == null || money.longValue() == 0L){
            return YUAN_0;
        }
        BigDecimal yuan = new BigDecimal(money).divide(new BigDecimal(100));
        return String.valueOf(yuan.longValue());
    }
    
    /**
     * 分转元  100 -> 1.00
     * @param money
     * @return
     */
    public static String fenToYuan(Long money){
        if(money == null || money.longValue() == 0L){
            return YUAN_0_00;
        }
        BigDecimal yuan = new BigDecimal(money).divide(new BigDecimal(100));
        return String.format("%.2f",yuan.doubleValue());
    }

    /**
     * 分转元 带单位 100 -> 1.00元
     * @param money
     * @return
     */
    public static String fenToYuanWithUnit(Long money){
       return fenToYuan(money).concat(UNIT_YUAN);
    }

    /**
     * 分转 万元 1200000 -> 1.20
     * @param money
     * @return
     */
    public static String fenToWanYuan(Long money){
        if(money == null || money.longValue() == 0L){
            return YUAN_0_00;
        }
        BigDecimal wanyuan = new BigDecimal(money).divide(new BigDecimal(100)).divide(new BigDecimal(10000));
        return String.format("%.2f",wanyuan.doubleValue());
    }

    /**
     * 分转 万元 1200000 -> 1.20万元
     * @param money
     * @return
     */
    public static String fenToWanYuanWithUnit(Long money){
        return fenToWanYuan(money).concat(UNIT_WAN_YUAN);
    }


    /**
     * seconds to 时:分:秒
     *  3200 -> 50:03:20
     * @param seconds
     * @return
     */
    public static String formatSeconds(long seconds) {
        if(seconds <= 0){
            return "00:00:00";
        }
        long h=seconds/3600;
        long m=(seconds%3600)/60;
        long s=(seconds%3600)%60;
        StringBuilder sb = new StringBuilder();
        if(h<10) {
            sb.append("0");
        }
        sb.append(h);
        sb.append(":");
        if(m<10) {
            sb.append("0");
        }
        sb.append(m);
        sb.append(":");

        if(s<10){
            sb.append("0");
        }
        sb.append(s);

        return sb.toString();
    }

    /**
     * 公里 -> 万公里
     * @param mileage
     * @return
     */
    public static String formatMileageToWan(Integer mileage){
        if(mileage == null || mileage.longValue() == 0L){
            return "0.00".concat(UNIT_WAN_MILAGE);
        }
        BigDecimal wan = new BigDecimal(mileage).divide(new BigDecimal(10000));
        return String.format("%.2f",wan.doubleValue()).concat(UNIT_WAN_MILAGE);
    }

    /**
     * seconds to Date
     * @param seconds
     * @param pattern
     * @return
     */
    public static String formateDateBySeconds(Long seconds, String pattern){
        Date date = new Date(seconds);
        return DateFormatUtil.formatDate(date, pattern);
    }

    /**
     * 后n位每位指定符号自动打码
     *
     * @param oldStr
     * @param symbol
     * @param n
     * @return
     */
    public static String endWithMosaic(String oldStr, String symbol, int n) {
        if (StringUtils.isEmpty(oldStr) || symbol == null)
            return "";
        int l = oldStr.length();
        if (n > l) n = l;
        StringBuilder result = new StringBuilder(oldStr.substring(0, l - n));
        while (StringUtils.isNotBlank(symbol) && n > 0) {
            result.append(symbol);
            n--;
        }
        return result.toString();
    }

}
