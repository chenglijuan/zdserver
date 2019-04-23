package com.lemi.msloan.service;

import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.request.RespectRequest;

import java.util.List;


public interface RespectService extends BaseService<Respect> {


    List<Respect> selectRespectPager(RespectRequest request);

    Integer selectRespectCount(RespectRequest request);

}
