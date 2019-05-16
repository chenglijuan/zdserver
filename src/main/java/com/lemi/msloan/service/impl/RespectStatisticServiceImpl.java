package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.RespectStatisticDao;
import com.lemi.msloan.entity.RespectStatistic;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.request.StatisticRequest;
import com.lemi.msloan.service.RespectStatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/4/18.
 */
@Service
public class RespectStatisticServiceImpl extends BaseServiceImpl<RespectStatistic> implements RespectStatisticService {

    @Resource
    private RespectStatisticDao respectStatisticDao;

    public RespectStatisticDao getRespectStatisticDao() {
        return respectStatisticDao;
    }

    @Resource
    public void setBaseDao(RespectStatisticDao respectStatisticDao) {
        super.setBaseDao(respectStatisticDao);
    }


    @Override
    public void insertBatchData(List<RespectStatistic> list) {
        respectStatisticDao.insertBatchData(list);
    }

    @Override
    public List<RespectStatistic> getRespectStatisticPager(RespectRequest request) {
        return respectStatisticDao.getRespectStatisticPager(request);
    }

    @Override
    public int getRespectStatisticCount(RespectRequest request) {
        return respectStatisticDao.getRespectStatisticCount(request);
    }

    @Override
    public List<RespectStatistic> getStatistic(StatisticRequest request) {
        return respectStatisticDao.getStatistic(request);
    }

    @Override
    public RespectStatistic getStatisticByMonth(StatisticRequest request) {
        return respectStatisticDao.getStatisticByMonth(request);
    }


}
