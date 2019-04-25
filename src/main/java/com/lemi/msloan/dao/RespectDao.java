package com.lemi.msloan.dao;

import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.request.RespectRequest;

import java.util.List;

public interface RespectDao extends BaseDao<Respect> {

    List<Respect> selectRespectPager(RespectRequest request);

    Integer selectRespectCount(RespectRequest request);


    int insertBatchData(List<Respect> list);

}
