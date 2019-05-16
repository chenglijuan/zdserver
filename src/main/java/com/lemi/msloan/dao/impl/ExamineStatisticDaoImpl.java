package com.lemi.msloan.dao.impl;

import com.lemi.msloan.dao.ExamineStatisticDao;
import com.lemi.msloan.entity.ExamineStatistic;
import com.lemi.msloan.request.ExamineStatisticRequest;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2019/5/10.
 */
@Repository(value = "examineStatisticDao")
public class ExamineStatisticDaoImpl extends BaseDaoImpl<ExamineStatistic> implements ExamineStatisticDao{
    @Override
    public Integer getTotalMoneyByCommunity(ExamineStatisticRequest examineStatisticRequest) {

        Object object = sqlSessionTemplate.selectOne("tbl_examinestatistic.getTotalMoneyByCommunity",examineStatisticRequest);

        return object==null?0: (int) object;
    }

    @Override
    public Integer getTotalCountByCommunity(ExamineStatisticRequest examineStatisticRequest) {

        Object object = sqlSessionTemplate.selectOne("tbl_examinestatistic.getTotalCountByCommunity",examineStatisticRequest);

        return object==null?0: (int) object;
    }
}
