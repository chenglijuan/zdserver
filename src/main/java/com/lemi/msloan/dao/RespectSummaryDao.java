package com.lemi.msloan.dao;

import com.lemi.msloan.entity.RespectSummary;
import com.lemi.msloan.request.RespectSummaryRequest;

import java.util.List;


/**
 * Created by Administrator on 2019/4/18.
 */
public interface RespectSummaryDao extends BaseDao<RespectSummary> {

    void insertBatchData(List<RespectSummary> list);

    int respectSummarySum(RespectSummaryRequest summaryRequest);

}
