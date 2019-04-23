package com.lemi.msloan.request;

public class RespectRequest extends BaseRequest {

    private String idCard;

    private String name;

    private Integer communityId;

    private String phone;

    private Integer changeState;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getChangeState() {
        return changeState;
    }

    public void setChangeState(Integer changeState) {
        this.changeState = changeState;
    }
}
