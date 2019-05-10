package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.RespectDao;
import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.service.RespectService;
import com.lemi.msloan.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RespectServiceImpl extends BaseServiceImpl<Respect> implements RespectService {

    @Resource
    private RespectDao respectDao;

    public RespectDao getRespectDao() {
        return respectDao;
    }

    @Resource
    public void setBaseDao(RespectDao respectDao) {
        super.setBaseDao(respectDao);
    }


    @Override
    public List<Respect> selectRespectPager(RespectRequest request) {
        return respectDao.selectRespectPager(request);
    }

    @Override
    public Integer selectRespectCount(RespectRequest request) {
        return respectDao.selectRespectCount(request);
    }

    @Override
    public int insertBatchData(List<Respect> list) {
        return respectDao.insertBatchData(list);
    }

    @Override
    public List<String> getAllIdCards() {
        return respectDao.getAllIdCards();
    }

    @Override
    public List<Respect> selectRemindRespect(Integer communityId, Integer type, Integer auditState) {
        // 1.城镇  2农村  3. 长寿
        //auditState 1.待审核2.审核通过 3.审核为通过
        Date nowDate = new Date();
        RespectRequest respectRequest = new RespectRequest();
        respectRequest.setAuditState(auditState);
        respectRequest.setCommunityId(communityId);
        if(type.intValue() == 1 || type.intValue() == 2){
            respectRequest.setType(type);
            String birthdayBegin = DateUtil.getYearsbefore(nowDate, 79);
            String birthdayEnd = DateUtil.getYearsbefore(nowDate, 70);
            respectRequest.setBirthdayBegin(birthdayBegin);
            respectRequest.setBirthdayEnd(birthdayEnd);
            return respectDao.selectRespectPager(respectRequest);
        }else if(type.intValue() == 3){
            String birthdayEnd = DateUtil.getYearsbefore(nowDate, 80);
            respectRequest.setType(null);
            respectRequest.setBirthdayEnd(birthdayEnd);
            return respectDao.selectRespectPager(respectRequest);
        }
        //3长寿
        return null;
    }

    @Override
    public int selectRemindRespectCount(Integer communityId, Integer type, Integer auditState) {
        // 1.城镇  2农村  3. 长寿
        //auditState 1.待审核2.审核通过 3.审核为通过
        Date nowDate = new Date();
        RespectRequest respectRequest = new RespectRequest();
        respectRequest.setAuditState(auditState);
        respectRequest.setCommunityId(communityId);
        if(type.intValue() == 1 || type.intValue() == 2){
            respectRequest.setType(type);
            String birthdayBegin = DateUtil.getYearsbefore(nowDate, 90);
            String birthdayEnd = DateUtil.getYearsbefore(nowDate, 70);
            respectRequest.setBirthdayBegin(birthdayBegin);
            respectRequest.setBirthdayEnd(birthdayEnd);
            return respectDao.selectRespectCount(respectRequest);
        }else if(type.intValue() == 3){
            String birthdayEnd = DateUtil.getYearsbefore(nowDate, 80);
            respectRequest.setType(null);
            respectRequest.setBirthdayEnd(birthdayEnd);
            return respectDao.selectRespectCount(respectRequest);
        }
        //3长寿
        return 0;
    }

    @Override
    public Respect getDataByIdCard(String idCard) {
        return respectDao.getDataByIdCard(idCard);
    }
}

