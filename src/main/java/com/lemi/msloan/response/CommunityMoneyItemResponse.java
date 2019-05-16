package com.lemi.msloan.response;

/**
 * Created by Administrator on 2019/5/16.
 */
public class CommunityMoneyItemResponse {

    private String date;

    private Integer totalCount;

    private Integer totalMoney;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }
}
