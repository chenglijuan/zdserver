package com.lemi.msloan.service;

import com.lemi.msloan.entity.Examine;

import java.util.List;

public interface ExamineService extends BaseService<Examine> {
    List<Examine> findAllExamine(Integer gender,Integer state,String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum);

    Integer findAllExamineCount(Integer gender,Integer state,String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId);

    Integer getByIdCard(String idCard);

    Examine getBeanByIdCard(String idCard);

    List<Examine> getExamineWillStart(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum);

    Integer getExamineWillStartCount(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId);

    List<Examine> getExamineWillStop(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum);

    Integer getExamineWillStopCount(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId);

    List<Examine> findAgainExamine(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum);

    Integer findAgainExamineCount(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId);

    List<Examine> findUndeterminedExamine(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum);

    Integer findUndeterminedExamineCount(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId);

    List<Examine> getByStatus();

    Integer getAddedCountByCommunityId(Integer communityId, String beginTime, String endTime);

    Integer getExitCountByCommunityId(Integer communityId, String beginTime, String endTime, Integer exitType);

    List<Examine> getNotExitExamine(Integer communityId, String beginTime, String endTime);
}
