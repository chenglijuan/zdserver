package com.lemi.msloan.request;

/**
 * Created by Administrator on 2019/4/22.
 */
public class CommunityRequest extends BaseRequest {

    private Integer userId;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
