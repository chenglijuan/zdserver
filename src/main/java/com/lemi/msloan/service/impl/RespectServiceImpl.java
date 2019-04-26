package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.RespectDao;
import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.service.RespectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RespectServiceImpl extends BaseServiceImpl<Respect> implements RespectService {

    @Resource
    private RespectDao respectDao;

    public RespectDao getRespectDao() {
        return respectDao;
    }

    @Resource
    public void setBaseDao(RespectDao respectDao) {
        super.setBaseDao(respectDao);
    }


    @Override
    public List<Respect> selectRespectPager(RespectRequest request) {
        return respectDao.selectRespectPager(request);
    }

    @Override
    public Integer selectRespectCount(RespectRequest request) {
        return respectDao.selectRespectCount(request);
    }

    @Override
    public int insertBatchData(List<Respect> list) {
        return respectDao.insertBatchData(list);
    }

    @Override
    public List<String> getAllIdCards() {
        return respectDao.getAllIdCards();
    }
}

