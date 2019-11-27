package com.lemi.msloan.response;

import com.lemi.msloan.entity.Community;

import java.util.List;

/**
 * Created by Administrator on 2019/5/16.
 */
public class CommunityMoneyInfoResponse {

    private Community community;

    private List<CommunityMoneyItemResponse> list;

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public List<CommunityMoneyItemResponse> getList() {
        return list;
    }

    public void setList(List<CommunityMoneyItemResponse> list) {
        this.list = list;
    }
}
