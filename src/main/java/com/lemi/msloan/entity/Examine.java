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

    private String idCard;

    private String name;

    private Integer gender;

    private Date birthday;

    private Integer age;

    private String address;

    private String village;

    private Integer isMove;

    private Integer communityId;

    private String communityName;

    private String house;

    private Integer status;

    private Date time;

    private Date villageTime;

    private Integer villageAge;

    private Integer cdState;

    private Date unStart;

    private Date unEnd;

    private Integer stopType;

    private String stopReason;

    private String phone;

    private String remark1;

    private String remark2;

    private String remark3;

    private String remark4;

    private Date nextTime;

    private Date nextOut;

    private Date time1;

    private Date time2;

    private Date time3;

    private Date time4;

    public Date getNextOut() {
        return nextOut;
    }

    public void setNextOut(Date nextOut) {
        this.nextOut = nextOut;
    }

    public Date getTime1() {
        return time1;
    }

    public void setTime1(Date time1) {
        this.time1 = time1;
    }

    public Date getTime2() {
        return time2;
    }

    public void setTime2(Date time2) {
        this.time2 = time2;
    }

    public Date getTime3() {
        return time3;
    }

    public void setTime3(Date time3) {
        this.time3 = time3;
    }

    public Date getTime4() {
        return time4;
    }

    public void setTime4(Date time4) {
        this.time4 = time4;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getUnStart() {
        return unStart;
    }

    public void setUnStart(Date unStart) {
        this.unStart = unStart;
    }

    public Date getUnEnd() {
        return unEnd;
    }

    public void setUnEnd(Date unEnd) {
        this.unEnd = unEnd;
    }

    public Integer getStopType() {
        return stopType;
    }

    public void setStopType(Integer stopType) {
        this.stopType = stopType;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village == null ? null : village.trim();
    }

    public Integer getIsMove() {
        return isMove;
    }

    public void setIsMove(Integer isMove) {
        this.isMove = isMove;
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

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house == null ? null : house.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getVillageTime() {
        return villageTime;
    }

    public void setVillageTime(Date villageTime) {
        this.villageTime = villageTime;
    }

    public Integer getVillageAge() {
        return villageAge;
    }

    public void setVillageAge(Integer villageAge) {
        this.villageAge = villageAge;
    }

    public Integer getCdState() {
        return cdState;
    }

    public void setCdState(Integer cdState) {
        this.cdState = cdState;
    }
}