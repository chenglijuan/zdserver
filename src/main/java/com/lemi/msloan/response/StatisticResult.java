package com.lemi.msloan.response;

import com.lemi.msloan.entity.Community;
import com.lemi.msloan.entity.RespectStatistic;

import java.util.List;

/** 接口查询状态返回，不包含业务内容 */
public class StatisticResult {
    private Community Community;

    private List<RespectStatistic> respectStatisticList;

    public com.lemi.msloan.entity.Community getCommunity() {
        return Community;
    }

    public void setCommunity(com.lemi.msloan.entity.Community community) {
        Community = community;
    }

    public List<RespectStatistic> getRespectStatisticList() {
        return respectStatisticList;
    }

    public void setRespectStatisticList(List<RespectStatistic> respectStatisticList) {
        this.respectStatisticList = respectStatisticList;
    }
}
