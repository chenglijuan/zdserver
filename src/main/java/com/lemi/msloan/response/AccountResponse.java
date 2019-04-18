package com.lemi.msloan.response;

import java.util.Date;

/**
 * Created by Administrator on 2019/1/17.
 */
public class AccountResponse {

    private Integer accountId;

    private String name;

    private Date time;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
