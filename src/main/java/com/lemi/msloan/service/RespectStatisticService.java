package com.lemi.msloan.service;

import com.lemi.msloan.entity.RespectStatistic;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.request.StatisticRequest;

import java.util.List;


/**
 * Created by Administrator on 2019/4/18.
 */
public interface RespectStatisticService extends BaseService<RespectStatistic> {

    void insertBatchData(List<RespectStatistic> list);

    List<RespectStatistic> getRespectStatisticPager(RespectRequest request);

    int getRespectStatisticCount(RespectRequest request);

    List<RespectStatistic> getStatistic(StatisticRequest request);

    RespectStatistic getStatisticByMonth(StatisticRequest request);

    RespectStatistic getSumStatistics(StatisticRequest request);

}
