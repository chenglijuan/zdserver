package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.ExamineStatisticDao;
import com.lemi.msloan.entity.ExamineStatistic;
import com.lemi.msloan.service.ExamineStatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
