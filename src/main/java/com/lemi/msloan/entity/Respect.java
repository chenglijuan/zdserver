package com.lemi.msloan.entity;

import java.util.Date;

public class Respect {
    private Integer id;

    private String name;

    private Integer gender;

    private String idCard;

    private String birthday;

    private Integer type;

    private String house;

    private Integer communityId;

    private String communityName;

    private String dynamicYearMonth;

    private String grantTime;

    private String phone;

    private String remark;

    private Integer issuStandard;

    private Integer auditState;

    private Integer grantState;

    private Date updateTime;

    private Date createTime;

    private Integer operator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house == null ? null : house.trim();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
    }

    public String getDynamicYearMonth() {
        return dynamicYearMonth;
    }

    public void setDynamicYearMonth(String dynamicYearMonth) {
        this.dynamicYearMonth = dynamicYearMonth == null ? null : dynamicYearMonth.trim();
    }

    public String getGrantTime() {
        return grantTime;
    }

    public void setGrantTime(String grantTime) {
        this.grantTime = grantTime == null ? null : grantTime.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIssuStandard() {
        return issuStandard;
    }

    public void setIssuStandard(Integer issuStandard) {
        this.issuStandard = issuStandard;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }
}