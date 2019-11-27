package com.lemi.msloan.request;

import com.lemi.msloan.util.DateUtil;

public class RespectRequest extends BaseRequest {

    private String idCard;

    private String name;

    private Integer communityId;

    private String phone;

    private Integer changeState;

    private Integer auditState;

    private String grantBeginTime;

    private String grantEndTime;

    private Integer type;

    private  String birthdayBegin;

    private String birthdayEnd;

    //发放状态1.已暂停 2.发放
    private Integer grantState;

    private String year;

    private String month;

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

    public String getGrantBeginTime() {
        return grantBeginTime;
    }

    public void setGrantBeginTime(String grantBeginTime) {
        this.grantBeginTime = grantBeginTime;
    }

    public String getGrantEndTime() {
        return grantEndTime;
    }

    public void setGrantEndTime(String grantEndTime) {
        this.grantEndTime = grantEndTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBirthdayBegin() {
        return birthdayBegin;
    }

    public void setBirthdayBegin(String birthdayBegin) {
        this.birthdayBegin = birthdayBegin;
    }

    public String getBirthdayEnd() {
        return birthdayEnd;
    }

    public void setBirthdayEnd(String birthdayEnd) {
        this.birthdayEnd = birthdayEnd;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public Integer getGrantState() {
        return grantState;
    }

    public void setGrantState(Integer grantState) {
        this.grantState = grantState;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
