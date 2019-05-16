package com.lemi.msloan.service;

import com.lemi.msloan.entity.Examine;

import java.util.List;

public interface ExamineService extends BaseService<Examine> {
    List<Examine> findAllExamine(Integer state,String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum);

    Integer findAllExamineCount(Integer state,String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId);

    Integer getByIdCard(String idCard);

    Examine getBeanByIdCard(String idCard);

    List<Examine> getExamineWillStart(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum);

    Integer getExamineWillStartCount(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId);

    List<Examine> getExamineWillStop(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum);

    Integer getExamineWillStopCount(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId);

    List<Examine> findAgainExamine(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum);

    Integer findAgainExamineCount(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId);

    List<Examine> findUndeterminedExamine(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum);

    Integer findUndeterminedExamineCount(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId);

    List<Examine> getByStatus();

    Integer getAddedCountByCommunityId(Integer communityId, String beginTime, String endTime);

    Integer getExitCountByCommunityId(Integer communityId, String beginTime, String endTime, Integer exitType);
}
