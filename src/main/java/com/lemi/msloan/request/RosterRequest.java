package com.lemi.msloan.request;

public class RosterRequest extends BaseRequest {

    private String idCard;

    private String name;

    private Integer communityId;

    private Integer isMove;

    private Integer status;

    private String birthday;

    private Integer age;

    private Integer comping;

    private Integer changes;

    private Integer unemployment;

    private Integer isInsured;

    private String house;

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
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

    public Integer getChanges() {
        return changes;
    }

    public void setChanges(Integer changes) {
        this.changes = changes;
    }

    public Integer getComping() {
        return comping;
    }

    public void setComping(Integer comping) {
        this.comping = comping;
    }

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

    public Integer getIsMove() {
        return isMove;
    }

    public void setIsMove(Integer isMove) {
        this.isMove = isMove;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
