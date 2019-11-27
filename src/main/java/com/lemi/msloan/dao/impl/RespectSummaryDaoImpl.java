package com.lemi.msloan.dao.impl;

import com.lemi.msloan.dao.RespectSummaryDao;
import com.lemi.msloan.entity.RespectSummary;
import com.lemi.msloan.request.RespectSummaryRequest;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Administrator on 2019/4/18.
 */
@Repository(value = "respectSummaryDao")
public class RespectSummaryDaoImpl extends BaseDaoImpl<RespectSummary> implements RespectSummaryDao {

    @Override
    public void insertBatchData(List<RespectSummary> list) {
         sqlSessionTemplate.insert("tbl_respectsummary.insertBatchData",list);
    }

    @Override
    public int respectSummarySum(RespectSummaryRequest summaryRequest) {
        return sqlSessionTemplate.selectOne("tbl_respectsummary.respectSummarySum",summaryRequest);
    }
}
