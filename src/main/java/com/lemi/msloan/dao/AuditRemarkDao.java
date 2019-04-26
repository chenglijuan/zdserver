package com.lemi.msloan.dao;

import com.lemi.msloan.entity.AuditRemark;

import java.util.List;


public interface AuditRemarkDao extends BaseDao<AuditRemark> {

    List<AuditRemark> selectByRespectId(Integer respectId);

}
