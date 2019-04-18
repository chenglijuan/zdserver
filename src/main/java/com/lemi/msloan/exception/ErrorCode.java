package com.lemi.msloan.exception;

/**
 * @Author: clj
 * @Data: 2018/7/3  下午3:07
 * @Decription:
 * @Modified:
 */
public enum ErrorCode {


    OPERATORERROR(-1, "操作失败"),
    OPERATORSUCCESS(0, "操作成功");


    public int code;    // 枚举类型，内部变量1

    public String msg;    // 枚举类型，内部变量2

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    };


}
