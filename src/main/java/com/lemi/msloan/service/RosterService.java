package com.lemi.msloan.service;

import com.lemi.msloan.entity.Roster;

import java.util.List;

public interface RosterService extends BaseService<Roster> {
    Roster getByIdCard(String idCard);

    List<Roster> selectRoster(String name, String idCard, Integer communityId, Integer isMove, Integer status, Integer age, Integer pageNum, Integer pageSize);

    Integer selectRoster(String name, String idCard, Integer communityId, Integer isMove, Integer status, Integer age);

    Roster getByExamineId(Integer examineId);

    //获取所有的身份证号码
    List<String> getAllIdCards();
}
