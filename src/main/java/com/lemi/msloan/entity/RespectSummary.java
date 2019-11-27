package com.lemi.msloan.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RespectSummary {
    private Integer id;

    private Date summaryMonth;

    private BigDecimal money;

    private Integer respectId;

    private Date createTime;

    private Integer communityId;

    private Integer age;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSummaryMonth() {
        return summaryMonth;
    }

    public void setSummaryMonth(Date summaryMonth) {
        this.summaryMonth = summaryMonth;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getRespectId() {
        return respectId;
    }

    public void setRespectId(Integer respectId) {
        this.respectId = respectId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}