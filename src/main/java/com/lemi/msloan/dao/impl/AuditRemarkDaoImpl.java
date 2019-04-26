package com.lemi.msloan.dao.impl;

import com.lemi.msloan.dao.AuditRemarkDao;
import com.lemi.msloan.dao.RespectDao;
import com.lemi.msloan.entity.AuditRemark;
import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.request.RespectRequest;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "auditRemarkDao")
public class AuditRemarkDaoImpl extends BaseDaoImpl<AuditRemark> implements AuditRemarkDao {


    @Override
    public List<AuditRemark> selectByRespectId(Integer respectId) {
        return sqlSessionTemplate.selectList("tbl_auditremark.selectByRespectId",respectId);
    }
}
