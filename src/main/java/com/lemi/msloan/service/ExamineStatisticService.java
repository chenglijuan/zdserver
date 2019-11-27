package com.lemi.msloan.service;

import com.lemi.msloan.entity.ExamineStatistic;

import java.util.Date;

/**
 * Created by Administrator on 2019/5/10.
 */
public interface ExamineStatisticService extends BaseService<ExamineStatistic> {
    Integer getTotalMoneyByCommunity(Integer communityId, Date startDate, Date endDate);

    Integer getTotalCountByCommunity(Integer communityId, Date startDate, Date endDate);
}
