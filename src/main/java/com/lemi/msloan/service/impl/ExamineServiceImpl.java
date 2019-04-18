package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.ExamineDao;
import com.lemi.msloan.entity.Examine;
import com.lemi.msloan.service.ExamineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExamineServiceImpl extends BaseServiceImpl<Examine> implements ExamineService {

    @Resource
    private ExamineDao examineDao;

    public ExamineDao getExamineDao() {
        return examineDao;
    }

    @Resource
    public void setBaseDao(ExamineDao examineDao) {
        super.setBaseDao(examineDao);
    }
}
