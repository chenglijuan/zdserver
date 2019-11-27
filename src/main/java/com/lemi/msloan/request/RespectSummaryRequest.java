package com.lemi.msloan.request;

public class RespectSummaryRequest {

    private Integer communityId;

    private String summaryMonth;

    private Integer type;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getSummaryMonth() {
        return summaryMonth;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setSummaryMonth(String summaryMonth) {
        this.summaryMonth = summaryMonth;
    }

}
