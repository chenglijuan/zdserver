package com.lemi.msloan.entity;

import java.util.Date;

public class Roster {
    private Integer id;

    private String idCard;

    private String name;

    private Integer gender;

    private Date birthday;

    private Integer age;

    private String address;

    private String village;

    private Integer isMove;

    private Integer communityId;

    private Community community;

    private String communityName;

    private String house;

    private Integer status;

    private String remark;

    private Date time;

    private Integer examineId;

    private Examine examine;

    private Date villageTime;

    private Integer villageAge;

    private Integer cdState;

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
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

    public Integer getExamineId() {
        return examineId;
    }

    public void setExamineId(Integer examineId) {
        this.examineId = examineId;
    }

    public Examine getExamine() {
        return examine;
    }

    public void setExamine(Examine examine) {
        this.examine = examine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}