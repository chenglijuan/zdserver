package com.lemi.msloan.oss;

import java.util.UUID;

/**
 * Created by zhoufeng on 17/3/31.
 */
public class Uuid {


    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }

    public static String GetGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

        /*public static void main(String[] args) {
        System.out.println(getUUID());
    }*/

}