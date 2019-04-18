package com.lemi.msloan.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: chenglijuan
 * @Data: 2018/7/26  下午1:42
 * @Decription:
 * @Modified:
 */
public class NumberFormatUtil {

     /*方法二：推荐，速度最快
      * 判断是否为整数
      * @param str 传入的字符串
      * @return 是整数返回true,否则返回false
    */

    public static boolean isInteger(String str) {
        if(StringUtils.isBlank(str)){
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    /**
     * 判断字符串是否为数字  含小数
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumer(String str){
        if(StringUtils.isBlank(str)){
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /*public static void main(String[] args) {
        System.out.println(isNumer(""));
    }*/
}
