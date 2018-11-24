package io.dabing.common.util;/**
 * Created by jovi on 6/22/16.
 */

import io.dabing.common.date.DateUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * All rights reserved. 2016-06-22 14:50
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public class NumberUtil {
    /**
     * 是否数字正则表达式
     * */
    public static final String PATTREN_ISNUMERIC="^(-?\\d+)(\\.\\d+)?$";
    /**判断是否小数正则表达式**/
    public static final String PARTTENN_ISDECIMAL="[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+";

    /**
     * 生成指定的位数编号,不足补零
     * @param businessTypeStr
     * @param number
     * @return
     */
    public static String getStringNumber(String businessTypeStr, Long number) {

        String randomNoStr = RandomStringUtils.randomNumeric(number.intValue());
        String partyNo = businessTypeStr + randomNoStr;
        return partyNo;
    }

    public static String getOrderNo(Long number){
        String date = DateUtil.formatDate(new Date(),"yyyyMMdd");
        return getStringNumber(date, number);
    }

    public static String getBatchNo(Long number) {
        String date = "B" + DateUtil.formatDate(new Date(),"yyyyMMdd");
        return getStringNumber(date, number-date.length());
    }

    /***
     * 是否匹配
     * @param regex 正则表达式
     * @param str  检查字符串
     * @return 匹配则返回true,否则返回false
     */
    private static Boolean isMatch(String regex, String str){
        if (StringUtils.isEmpty(str)) {
            return Boolean.FALSE;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /***
     * 保留三位小数;
     * @param val
     * @return
     */
    public static final String scale(Double val){
        if(val==null){
            return "0.00%";
        }
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("##0.00");
        return df.format(val*100)+"%";
    }

    /***
     * 判断字符是否数字类型为：小数、正负数等
     * @param str
     * @return Boolean 是数字则返回true,否则返回fase
     */
    public static Boolean isNumeric(String str) {
        return isMatch(PATTREN_ISNUMERIC,str);
    }
    /****
     * 判断字符串是否是小数
     * @param str  字符串
     * @return
     */
    public static Boolean isDecimal(String str){
        return isMatch(PARTTENN_ISDECIMAL,str);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int randomNo = rand.nextInt(100000);
        System.out.println(getStringNumber("ORDER",1L));
    }
}
