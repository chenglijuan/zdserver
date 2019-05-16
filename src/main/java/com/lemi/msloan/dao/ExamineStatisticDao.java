package com.lemi.msloan.dao;

import com.lemi.msloan.entity.ExamineStatistic;
import com.lemi.msloan.request.ExamineStatisticRequest;

/**
 * Created by Administrator on 2019/5/10.
 */
public interface ExamineStatisticDao extends BaseDao<ExamineStatistic> {
    Integer getTotalMoneyByCommunity(ExamineStatisticRequest examineStatisticRequest);

    Integer getTotalCountByCommunity(ExamineStatisticRequest examineStatisticRequest);
}
