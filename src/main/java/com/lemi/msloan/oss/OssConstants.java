package com.lemi.msloan.oss;

public final class OssConstants {
    private OssConstants() {
    }

    public static class OSS_CONFIG {

        public static final String EDNPOINT = "http://oss-cn-beijing.aliyuncs.com/";

        public static final String BOOKPOINT = "http://msloan.oss-cn-beijing.aliyuncs.com/";

        public static final String ACCESS_KEY_ID = "LTAIf26MUP0ru48B";

        public static final String ACCESS_KEY_SECRET = "snMhdzSnykmevlEsw6kHBEODekia37";

        public static final String BUCKET_NAME = "msloan";

        public static final String URL_PREFIX = BOOKPOINT + BUCKET_NAME;
        // 视频存放路径
        public static final String OSS_NORIYOSHI_FILE= URL_PREFIX + "/file/";

        public static final String OSS_FILE_KEY = "msloan/file/";
    }

}
