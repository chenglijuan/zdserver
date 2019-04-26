package com.lemi.msloan.service;

import com.lemi.msloan.entity.AuditRemark;
import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.request.RespectRequest;

import java.util.List;


public interface AuditRemarkService extends BaseService<AuditRemark> {


    List<AuditRemark> selectByRespectId(Integer respectId);
}
