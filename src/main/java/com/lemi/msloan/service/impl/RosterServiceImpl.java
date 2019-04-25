package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.RosterDao;
import com.lemi.msloan.entity.Roster;
import com.lemi.msloan.request.RosterRequest;
import com.lemi.msloan.service.RosterService;
import com.lemi.msloan.util.DateUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RosterServiceImpl extends BaseServiceImpl<Roster> implements RosterService {

    @Resource
    private RosterDao rosterDao;

    public RosterDao getRosterDao() {
        return rosterDao;
    }

    @Resource
    public void setBaseDao(RosterDao rosterDao) {
        super.setBaseDao(rosterDao);
    }

    @Override
    public Roster getByIdCard(String idCard) {

        RosterRequest rosterRequest = new RosterRequest();

        rosterRequest.setIdCard(idCard);
        return rosterDao.getByIdCard(rosterRequest);
    }

    @Override
    public List<Roster> selectRoster(String name, String idCard, Integer communityId, Integer isMove, Integer status, Integer age, Integer pageNum, Integer pageSize) {

        RosterRequest rosterRequest = new RosterRequest();
        if (!StringUtils.isBlank(name)) {
            rosterRequest.setName(name.trim());
        }
        if (!StringUtils.isBlank(idCard)) {
            rosterRequest.setIdCard(idCard.trim());
        }
        if (communityId != null) {
            rosterRequest.setCommunityId(communityId);
        }
        if (isMove != null) {
            rosterRequest.setIsMove(isMove);
        }
        if (status != null) {
            rosterRequest.setStatus(status);
        }
        if (age != null) {
            rosterRequest.setAge(age);
        }

        rosterRequest.setPager(pageNum, pageSize);

        return rosterDao.selectRoster(rosterRequest);
    }

    @Override
    public Integer selectRoster(String name, String idCard, Integer communityId, Integer isMove, Integer status, Integer age) {

        RosterRequest rosterRequest = new RosterRequest();
        if (!StringUtils.isBlank(name)) {
            rosterRequest.setName(name.trim());
        }
        if (!StringUtils.isBlank(idCard)) {
            rosterRequest.setIdCard(idCard.trim());
        }
        if (communityId != null) {
            rosterRequest.setCommunityId(communityId);
        }
        if (isMove != null) {
            rosterRequest.setIsMove(isMove);
        }
        if (status != null) {
            rosterRequest.setStatus(status);
        }
        if (age != null) {
            rosterRequest.setAge(age);
        }

        return rosterDao.selectRosterCount(rosterRequest);
    }
    @Override
    public Roster getByExamineId(Integer examineId) {
        RosterRequest rosterRequest = new RosterRequest();
        rosterRequest.setExamineId(examineId);
        return rosterDao.getByExamineId(rosterRequest);
    }

    @Override
    public List<String> getAllIdCards() {
        return rosterDao.getAllIdCards();
    }
}

