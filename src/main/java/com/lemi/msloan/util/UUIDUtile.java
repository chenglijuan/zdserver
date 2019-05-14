package com.lemi.msloan.util;

import java.util.UUID;

/**
 * Created by Administrator on 2019/4/3.
 */
public class UUIDUtile {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
