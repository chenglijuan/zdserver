package com.lemi.msloan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by qing on 2017/3/28.
 */
public class AgeUtils {
    // 根据年月日计算年龄,birthTimeString:"1994-11-14"
    public static int getAgeFromBirthTime(String birthTimeString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 当前时间
        Calendar curr = Calendar.getInstance();
        // 生日
        Calendar born = Calendar.getInstance();
        try {
            born.setTime(sdf.parse(birthTimeString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 年龄 = 当前年 - 出生年
        int age = curr.get(Calendar.YEAR) - born.get(Calendar.YEAR);
        if (age <= 0) {
            return 0;
        }
        // 如果当前月份小于出生月份: age-1
        // 如果当前月份等于出生月份, 且当前日小于出生日: age-1
        int currMonth = curr.get(Calendar.MONTH);
        int currDay = curr.get(Calendar.DAY_OF_MONTH);
        int bornMonth = born.get(Calendar.MONTH);
        int bornDay = born.get(Calendar.DAY_OF_MONTH);
        if ((currMonth < bornMonth) || (currMonth == bornMonth && currDay <= bornDay)) {
            age--;
        }
        return age < 0 ? 0 : age;
    }

    public static int getIssuStandard(int age,int respectType){
        int standard = 0;
        if(respectType == 1){
            if(age < 79){
                standard = 0;
            } else if(age >= 80 && age <= 89){
                standard = 50;
            }else if(age >= 90 && age <= 99){
                standard = 100;
            } else if(age >= 100 ){
                standard = 300;
            }
        }else if(respectType == 2){
            if(age < 70){
                standard = 0;
            } else if(age >= 70 && age <= 79){
                standard = 50;
            }else if(age >= 80 && age <= 89){
                standard = 200;
            }else if(age >= 90 && age <= 99){
                standard = 500;
            } else if(age >= 100 ){
                standard = 1000;
            }
        }
        return standard;
    }


    // 根据时间戳计算年龄
    public static int getAgeFromBirthTime(long birthTimeLong) {
        Date date = new Date(birthTimeLong * 1000l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String birthTimeString = format.format(date);
        return getAgeFromBirthTime(birthTimeString);
    }


    public static void main(String[] args) {
        System.out.println("32051119260909102X".substring(6,14));
    }
}

