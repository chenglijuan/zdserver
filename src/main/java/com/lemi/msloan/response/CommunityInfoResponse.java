package com.lemi.msloan.response;

import com.lemi.msloan.entity.Community;

/**
 * Created by Administrator on 2019/5/15.
 */
public class CommunityInfoResponse {

    private Community community;

    private Integer addedCount;

    private Integer jobCount;

    private Integer compingCount;

    private Integer _count;

    private Integer retireCount;

    private Integer deathCount;

    private Integer otherCount;

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Integer getAddedCount() {
        return addedCount;
    }

    public void setAddedCount(Integer addedCount) {
        this.addedCount = addedCount;
    }

    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

    public Integer getCompingCount() {
        return compingCount;
    }

    public void setCompingCount(Integer compingCount) {
        this.compingCount = compingCount;
    }

    public Integer get_count() {
        return _count;
    }

    public void set_count(Integer _count) {
        this._count = _count;
    }

    public Integer getRetireCount() {
        return retireCount;
    }

    public void setRetireCount(Integer retireCount) {
        this.retireCount = retireCount;
    }

    public Integer getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(Integer deathCount) {
        this.deathCount = deathCount;
    }

    public Integer getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(Integer otherCount) {
        this.otherCount = otherCount;
    }
}
