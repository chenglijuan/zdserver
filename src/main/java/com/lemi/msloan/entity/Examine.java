package com.lemi.msloan.entity;

import java.util.Date;

public class Examine {
    private Integer id;

    private Date startTime;

    private Date stopTime;

    private String dtxsny;

    private String ffbj;

    private Integer isInsured;

    private Integer unemployment;

    private Integer comping;

    private Integer changes;

    private String remark;

    private String batch;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getDtxsny() {
        return dtxsny;
    }

    public void setDtxsny(String dtxsny) {
        this.dtxsny = dtxsny == null ? null : dtxsny.trim();
    }

    public String getFfbj() {
        return ffbj;
    }

    public void setFfbj(String ffbj) {
        this.ffbj = ffbj == null ? null : ffbj.trim();
    }

    public Integer getIsInsured() {
        return isInsured;
    }

    public void setIsInsured(Integer isInsured) {
        this.isInsured = isInsured;
    }

    public Integer getUnemployment() {
        return unemployment;
    }

    public void setUnemployment(Integer unemployment) {
        this.unemployment = unemployment;
    }

    public Integer getComping() {
        return comping;
    }

    public void setComping(Integer comping) {
        this.comping = comping;
    }

    public Integer getChanges() {
        return changes;
    }

    public void setChanges(Integer changes) {
        this.changes = changes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}