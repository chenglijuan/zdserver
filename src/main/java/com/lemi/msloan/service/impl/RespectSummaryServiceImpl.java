package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.RespectSummaryDao;
import com.lemi.msloan.entity.RespectSummary;
import com.lemi.msloan.request.RespectSummaryRequest;
import com.lemi.msloan.service.RespectSummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/4/18.
 */
@Service
public class RespectSummaryServiceImpl extends BaseServiceImpl<RespectSummary> implements RespectSummaryService {

    @Resource
    private RespectSummaryDao respectSummaryDao;

    public RespectSummaryDao getRespectSummaryDao() {
        return respectSummaryDao;
    }

    @Resource
    public void setBaseDao(RespectSummaryDao respectSummaryDao) {
        super.setBaseDao(respectSummaryDao);
    }


    @Override
    public void insertBatchData(List<RespectSummary> list) {
        respectSummaryDao.insertBatchData(list);
    }

    @Override
    public int respectSummarySum(RespectSummaryRequest summaryRequest) {
        return respectSummaryDao.respectSummarySum(summaryRequest);
    }
}
