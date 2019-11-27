package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.ExamineStatisticDao;
import com.lemi.msloan.entity.ExamineStatistic;
import com.lemi.msloan.request.ExamineStatisticRequest;
import com.lemi.msloan.service.ExamineStatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2019/5/10.
 */
@Service
public class ExamineStatisticServiceImpl extends BaseServiceImpl<ExamineStatistic> implements ExamineStatisticService {

    @Resource
    private ExamineStatisticDao examineStatisticDao;

    public ExamineStatisticDao getExamineStatisticDao() {
        return examineStatisticDao;
    }

    @Resource
    public void setBaseDao(ExamineStatisticDao examineStatisticDao) {
        super.setBaseDao(examineStatisticDao);
    }

    @Override
    public Integer getTotalMoneyByCommunity(Integer communityId, Date startDate, Date endDate) {

        ExamineStatisticRequest examineStatisticRequest = new ExamineStatisticRequest();
        if (communityId != null){
            examineStatisticRequest.setCommunityId(communityId);
        }
        if (startDate != null){
            examineStatisticRequest.setStartDate(startDate);
        }
        if (endDate != null){
            examineStatisticRequest.setEndDate(endDate);
        }

        return examineStatisticDao.getTotalMoneyByCommunity(examineStatisticRequest);
    }

    @Override
    public Integer getTotalCountByCommunity(Integer communityId, Date startDate, Date endDate) {
        ExamineStatisticRequest examineStatisticRequest = new ExamineStatisticRequest();
        if (communityId != null){
            examineStatisticRequest.setCommunityId(communityId);
        }
        if (startDate != null){
            examineStatisticRequest.setStartDate(startDate);
        }
        if (endDate != null){
            examineStatisticRequest.setEndDate(endDate);
        }

        return examineStatisticDao.getTotalCountByCommunity(examineStatisticRequest);
    }
}
