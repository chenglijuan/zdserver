package com.lemi.msloan.service;

import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.request.RespectRequest;

import java.util.List;


public interface RespectService extends BaseService<Respect> {


    List<Respect> selectRespectPager(RespectRequest request);

    Integer selectRespectCount(RespectRequest request);

    int insertBatchData(List<Respect> list);

    List<String> getAllIdCards();


    List<Respect> selectRemindRespect(Integer communityId,Integer type,Integer auditState);

    int selectRemindRespectCount(Integer communityId,Integer type,Integer auditState);

    Respect getDataByIdCard(String idCard);

}
