package com.lemi.msloan.request;

import java.util.Date;

/**
 * Created by Administrator on 2019/5/16.
 */
public class ExamineStatisticRequest extends BaseRequest {

    private Integer communityId;

    private Date startDate;

    private Date endDate;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
