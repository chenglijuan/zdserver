package com.lemi.msloan.dao.impl;

import com.lemi.msloan.dao.RespectDao;
import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.request.RespectRequest;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "respectDao")
public class RespectDaoImpl extends BaseDaoImpl<Respect> implements RespectDao {

    @Override
    public List<Respect> selectRespectPager(RespectRequest request) {
        return sqlSessionTemplate.selectList("tbl_respect.selectRespectPager",request);
    }

    @Override
    public Integer selectRespectCount(RespectRequest request) {
        return sqlSessionTemplate.selectOne("tbl_respect.selectRespectCount",request);
    }

    @Override
    public int insertBatchData(List<Respect> list) {
        return sqlSessionTemplate.insert("tbl_respect.insertBatchData",list);
    }

    @Override
    public List<String> getAllIdCards() {
        return sqlSessionTemplate.selectList("tbl_respect.getAllIdCards");
    }

    @Override
    public Respect getDataByIdCard(String idCard) {
        return sqlSessionTemplate.selectOne("tbl_respect.getDataByIdCard",idCard);
    }
}
