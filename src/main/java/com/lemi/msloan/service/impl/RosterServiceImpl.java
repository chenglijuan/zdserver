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
    public Integer insertRoster(String idCard, String name, Integer gender, String birthday, String address, String village, Integer isMove, Integer communityId, String communityName, String house, Integer status, String remark) {

        Roster roster = new Roster();
        roster.setIdCard(idCard);
        roster.setName(name);
        roster.setGender(gender);
        roster.setBirthday(DateUtil.getDateToString(birthday, "yyyy-MM-dd"));
        roster.setAddress(address);
        roster.setVillage(village);
        roster.setIsMove(isMove);
        roster.setCommunityId(communityId);
        roster.setHouse(house);
        roster.setRemark(remark);
        roster.setStatus(1);
        roster.setTime(new Date());
        return rosterDao.save(roster);
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
    public List<Roster> selectRosterExamine(String house,String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {

        RosterRequest rosterRequest = new RosterRequest();
        if (!StringUtils.isBlank(house)){
            rosterRequest.setHouse(house);
        }
        if (!StringUtils.isBlank(name)) {
            rosterRequest.setName(name);
        }
        if (!StringUtils.isBlank(idCard)) {
            rosterRequest.setIdCard(idCard);
        }
        if (comping != null) {
            rosterRequest.setComping(comping);
        }
        if (age != null) {
            rosterRequest.setAge(age);
        }
        if (changes != null) {
            rosterRequest.setChanges(changes);
        }
        if (status != null) {
            rosterRequest.setStatus(status);
        }
        if (unemployment != null) {
            rosterRequest.setUnemployment(unemployment);
        }
        if (isInsured != null) {
            rosterRequest.setIsInsured(isInsured);
        }
        if (communityId != null) {
            rosterRequest.setCommunityId(communityId);
        }
        if (pageNum != null && pageSize != null) {
            rosterRequest.setPager(pageNum, pageSize);
        }

        return rosterDao.selectRosterExamine(rosterRequest);
    }

    @Override
    public Integer selectRosterExamineCount(String house,String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId) {
        RosterRequest rosterRequest = new RosterRequest();
        if (!StringUtils.isBlank(house)){
            rosterRequest.setHouse(house);
        }
        if (!StringUtils.isBlank(name)) {
            rosterRequest.setName(name);
        }
        if (!StringUtils.isBlank(idCard)) {
            rosterRequest.setIdCard(idCard);
        }
        if (comping != null) {
            rosterRequest.setComping(comping);
        }
        if (age != null) {
            rosterRequest.setAge(age);
        }
        if (changes != null) {
            rosterRequest.setChanges(changes);
        }
        if (status != null) {
            rosterRequest.setStatus(status);
        }
        if (unemployment != null) {
            rosterRequest.setUnemployment(unemployment);
        }
        if (isInsured != null) {
            rosterRequest.setIsInsured(isInsured);
        }
        if (communityId != null) {
            rosterRequest.setCommunityId(communityId);
        }
        return rosterDao.selectRosterExamineCount(rosterRequest);
    }

    @Override
    public List<Roster> selectStartWarning(Integer pageNum, Integer pageSize) {
        RosterRequest rosterRequest = new RosterRequest();
        rosterRequest.setPager(pageNum,pageSize);
        return rosterDao.selectStartWarning(rosterRequest);
    }

    @Override
    public Integer selectStartWarningCount() {
        return rosterDao.selectStartWarningCount();
    }

    @Override
    public List<Roster> selectEndWarning(Integer pageNum, Integer pageSize) {
        RosterRequest rosterRequest = new RosterRequest();
        rosterRequest.setPager(pageNum,pageSize);
        return rosterDao.selectEndWarning(rosterRequest);
    }

    @Override
    public Integer selectEndWarningCount() {
        return rosterDao.selectEndWarningCount();
    }

    @Override
    public List<Roster> selectExamine(Integer pageNum, Integer pageSize) {
        RosterRequest rosterRequest = new RosterRequest();
        rosterRequest.setPager(pageNum,pageSize);
        return rosterDao.selectExamine(rosterRequest);
    }

    @Override
    public Integer selectExamineCount() {
        return rosterDao.selectExamineCount();
    }

    @Override
    public List<Roster> selectUndetermined(Integer pageNum, Integer pageSize) {
        RosterRequest rosterRequest = new RosterRequest();
        rosterRequest.setPager(pageNum,pageSize);
        return rosterDao.selectUndetermined(rosterRequest);
    }

    @Override
    public Integer selectUndeterminedCount() {
        return rosterDao.selectUndeterminedCount();
    }
}

