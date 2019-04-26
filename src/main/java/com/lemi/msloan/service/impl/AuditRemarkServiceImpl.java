package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.AuditRemarkDao;
import com.lemi.msloan.entity.AuditRemark;
import com.lemi.msloan.service.AuditRemarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuditRemarkServiceImpl extends BaseServiceImpl<AuditRemark> implements AuditRemarkService {

    @Resource
    private AuditRemarkDao auditRemarkDao;

    public AuditRemarkDao getAuditRemarkDao() {
        return auditRemarkDao;
    }

    @Resource
    public void setBaseDao(AuditRemarkDao auditRemarkDao) {
        super.setBaseDao(auditRemarkDao);
    }


    @Override
    public List<AuditRemark> selectByRespectId(Integer respectId ) {
        return auditRemarkDao.selectByRespectId(respectId);
    }
}

