package com.lemi.msloan.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RespectStatistic {
    private Integer id;

    private Date summaryMonth;

    private Date createTime;

    private Integer communityId;

    private Integer type;

    private Integer range1Count;

    private Integer range2Count;

    private Integer range3Count;

    private Integer range4Count;

    private Integer totalCount;

    private BigDecimal range1Money;

    private BigDecimal range2Money;

    private BigDecimal range3Money;

    private BigDecimal range4Money;

    private BigDecimal totalMoney;

    private String communityName;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRange1Count() {
        return range1Count;
    }

    public void setRange1Count(Integer range1Count) {
        this.range1Count = range1Count;
    }

    public Integer getRange2Count() {
        return range2Count;
    }

    public void setRange2Count(Integer range2Count) {
        this.range2Count = range2Count;
    }

    public Integer getRange3Count() {
        return range3Count;
    }

    public void setRange3Count(Integer range3Count) {
        this.range3Count = range3Count;
    }

    public Integer getRange4Count() {
        return range4Count;
    }

    public void setRange4Count(Integer range4Count) {
        this.range4Count = range4Count;
    }

    public BigDecimal getRange1Money() {
        return range1Money;
    }

    public void setRange1Money(BigDecimal range1Money) {
        this.range1Money = range1Money;
    }

    public BigDecimal getRange2Money() {
        return range2Money;
    }

    public void setRange2Money(BigDecimal range2Money) {
        this.range2Money = range2Money;
    }

    public BigDecimal getRange3Money() {
        return range3Money;
    }

    public void setRange3Money(BigDecimal range3Money) {
        this.range3Money = range3Money;
    }

    public BigDecimal getRange4Money() {
        return range4Money;
    }

    public void setRange4Money(BigDecimal range4Money) {
        this.range4Money = range4Money;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}