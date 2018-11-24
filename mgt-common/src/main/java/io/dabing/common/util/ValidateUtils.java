package io.dabing.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {

    /***
     * 正则表达式匹配
     * @param regex 正则表达式
     * @param text 文本
     * @return
     */
    public static final   Boolean match(String regex,String text){
        if (StringUtils.isEmpty (text) || StringUtils.isEmpty(regex))
            return Boolean.FALSE;
        return Pattern.compile(regex).matcher(text).matches();
    }

    /***
     * 邮政编码验证
     * @param text 文本
     * @return
     */
    public static final Boolean isZipCode(String text){
        return match( "^[0-9]{6}$",text);
    }

    /**
     * 匹配手机
     * @param mobileNo
     * @return
     */
    public static boolean isMobileNo(String mobileNo){
        return match ("^1\\d{10}$",mobileNo);
    }

    /**
     * 匹配邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        return match ("^(\\w-*\\.*)+@(\\w-?)+(\\.\\w{2,})+$",email);
    }

    /**
     * 匹配
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str==null||"".equals(str)){
            return true;
        }
        return false;
    }

    /**
     * 匹配整数或小数
     * @param str
     * @return
     */
    public static boolean isIntegerOrDecimal(String str){
        Pattern p = Pattern.compile("^[0-9]+\\.{0,1}[0-9]{0,2}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 匹配指定小数位数的正小数
     * @param str
     * @param len
     * @return
     */
    public static boolean isPositiveDecimal(String str,Integer len){
        Pattern p = Pattern.compile("^[0-9]+(.[0-9]{"+len+"})?$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 匹配中文字符
     * @param str
     * @return
     */
    public static boolean isChineseChar(String str){
        Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{0,}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     * 匹配英文字母
     * @param str
     * @return
     */
    public static boolean isLetter(String str){
        Pattern p = Pattern.compile("^[A-Za-z]+$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     * 匹配小写英文字母
     * @param str
     * @return
     */
    public static boolean isLowerLetter(String str){
        Pattern p = Pattern.compile("^[a-z]+$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     *匹配大写英文字母
     * @param str
     * @return
     */
    public static boolean isUpperLetter(String str){
        Pattern p = Pattern.compile("^[A-Z]+$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     * 匹配字母或数字
     * @param str
     * @return
     */
    public static boolean isLetterOrNumber(String str){
        Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     * 匹配正整数
     * @param str
     * @return
     */
    public static boolean isPositiveInteger(String str){
        Pattern p = Pattern.compile("^\\+?[0-9][0-9]*$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     * 匹配大于0的正整数
     * @param str
     * @return
     */
    public static boolean isPositiveIntegerExceptZero(String str){
        Pattern p = Pattern.compile("^\\+?[1-9][0-9]*$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     * 匹配URL
     * @param str
     * @return
     */
    public static boolean isURL(String str){
        Pattern p = Pattern.compile("^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$");
        Matcher m = p.matcher(str);
        Pattern p2 = Pattern.compile("^https://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$");
        Matcher m2 = p2.matcher(str);
        return m.matches()||m2.matches();
    }

    /***
     * 校验手机号
     * 1开头11位数字
     * @param mobilePhoneNo
     * @return
     */
    public static Boolean isOdpMobilePhoneNo(String mobilePhoneNo){
        if (isEmpty(mobilePhoneNo)){
            return false;
        }
        if (!isPositiveInteger(mobilePhoneNo)){
            return false;
        }
        if (mobilePhoneNo.length()!=11){
            return false;
        }
        if (!mobilePhoneNo.startsWith("1")){
            return false;
        }
        return true;
    }

    public static void main(String args[]) {

		System.out.println(isMobileNo ("18223986517"));
	}

}
