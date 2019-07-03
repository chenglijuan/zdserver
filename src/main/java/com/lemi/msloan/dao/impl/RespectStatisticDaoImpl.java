package com.lemi.msloan.dao.impl;

import com.lemi.msloan.dao.RespectStatisticDao;
import com.lemi.msloan.entity.RespectStatistic;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.request.StatisticRequest;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Administrator on 2019/4/18.
 */
@Repository(value = "respectStatisticDao")
public class RespectStatisticDaoImpl extends BaseDaoImpl<RespectStatistic> implements RespectStatisticDao {

    @Override
    public void insertBatchData(List<RespectStatistic> list) {
        sqlSessionTemplate.insert("tbl_respectstatistic.insertBatchData",list);
    }

    @Override
    public List<RespectStatistic> getRespectStatisticPager(RespectRequest request) {
        return sqlSessionTemplate.selectList("tbl_respectstatistic.getRespectStatisticPager",request);
    }

    @Override
    public int getRespectStatisticCount(RespectRequest request) {
        return sqlSessionTemplate.selectOne("tbl_respectstatistic.getRespectStatisticCount",request);
    }

    @Override
    public List<RespectStatistic> getStatistic(StatisticRequest request) {
        return sqlSessionTemplate.selectList("tbl_respectstatistic.getStatistic",request);
    }

    @Override
    public RespectStatistic getStatisticByMonth(StatisticRequest request) {
        return sqlSessionTemplate.selectOne("tbl_respectstatistic.getStatisticByMonth",request);
    }

    @Override
    public RespectStatistic getSumStatistics(StatisticRequest request) {
        return sqlSessionTemplate.selectOne("tbl_respectstatistic.getSumStatistics",request);
    }
}
