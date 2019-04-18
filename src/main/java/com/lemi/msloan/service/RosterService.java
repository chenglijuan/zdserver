package com.lemi.msloan.service;

import com.lemi.msloan.entity.Roster;

import java.util.List;

public interface RosterService extends BaseService<Roster> {
    Roster getByIdCard(String idCard);

    Integer insertRoster(String idCard, String name, Integer gender, String birthday, String address, String village, Integer isMove, Integer communityId, String communityName, String house, Integer status, String remark);

    List<Roster> selectRoster(String name, String idCard, Integer communityId, Integer isMove, Integer status, Integer age, Integer pageNum, Integer pageSize);

    Integer selectRoster(String name, String idCard, Integer communityId, Integer isMove, Integer status, Integer age);

    List<Roster> selectRosterExamine(String house,String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum);

    Integer selectRosterExamineCount(String house,String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId);

    List<Roster> selectStartWarning(Integer pageNum, Integer pageSize);

    Integer selectStartWarningCount();

    List<Roster> selectEndWarning(Integer pageNum, Integer pageSize);

    Integer selectEndWarningCount();

    List<Roster> selectExamine(Integer pageNum, Integer pageSize);

    Integer selectExamineCount();

    List<Roster> selectUndetermined(Integer pageNum, Integer pageSize);

    Integer selectUndeterminedCount();
}
