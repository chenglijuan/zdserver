package com.lemi.msloan.util;

public final class Constants {
    private Constants() {
    }

    public static class OSS_CONFIG {

        public static final String EDNPOINT = "http://oss-cn-beijing.aliyuncs.com/";

        public static final String BOOKPOINT = "http://financialserver.oss-cn-beijing.aliyuncs.com/";
        // public static final String BOOKPOINT = "http://dev.deshanhui.joylingame.com/";


        public static final String ACCESS_KEY_ID = "LTAIf26MUP0ru48B";

        public static final String ACCESS_KEY_SECRET = "snMhdzSnykmevlEsw6kHBEODekia37";

        public static final String BUCKET_NAME = "financialserver";

        public static final String URL_PREFIX = BOOKPOINT + BUCKET_NAME;
        // 图片头像存放路径
        public static final String OSS_NORIYOSHI_PIC = URL_PREFIX + "/pic/";
        // 视频存放路径
        public static final String OSS_NORIYOSHI_VEDIO = URL_PREFIX + "/file/";

        public static final String OSS_PIC_KEY = "financialserver/pic/";

        public static final String OSS_VEDIO_KEY = "financialserver/file/";
    }

//    public static void main(String[] args) {
//        String state = "3_1545026481461";
//        System.out.println(state.substring(state.indexOf("_")+1,state.length()));
//    }
}
