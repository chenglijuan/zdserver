package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.ExamineDao;
import com.lemi.msloan.entity.Examine;
import com.lemi.msloan.request.ExamineRequest;
import com.lemi.msloan.service.ExamineService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamineServiceImpl extends BaseServiceImpl<Examine> implements ExamineService {

    @Resource
    private ExamineDao examineDao;

    public ExamineDao getExamineDao() {
        return examineDao;
    }

    @Resource
    public void setBaseDao(ExamineDao examineDao) {
        super.setBaseDao(examineDao);
    }

    @Override
    public List<Examine> findAllExamine(Integer state,String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (state!=null){
            examineRequest.setState(state);
        }
        if (!StringUtils.isBlank(name)) {
            examineRequest.setName(name);
        }
        if (!StringUtils.isBlank(idCard)) {
            examineRequest.setIdCard(idCard);
        }
        if (comping != null) {
            examineRequest.setComping(comping);
        }
        if (age != null) {
            examineRequest.setAge(age);
        }
        if (changes != null) {
            examineRequest.setChanges(changes);
        }
        if (status != null) {
            examineRequest.setStatus(status);
        }
        if (unemployment != null) {
            examineRequest.setUnemployment(unemployment);
        }
        if (isInsured != null) {
            examineRequest.setIsInsured(isInsured);
        }
        if (communityId != null) {
            examineRequest.setCommunityId(communityId);
        }
        if (pageNum != null && pageSize != null) {
            examineRequest.setPager(pageNum, pageSize);
        }
        return examineDao.findAllExamine(examineRequest);
    }

    @Override
    public Integer findAllExamineCount(Integer state,String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (state!=null){
            examineRequest.setState(state);
        }
        if (!StringUtils.isBlank(name)) {
            examineRequest.setName(name);
        }
        if (!StringUtils.isBlank(idCard)) {
            examineRequest.setIdCard(idCard);
        }
        if (comping != null) {
            examineRequest.setComping(comping);
        }
        if (age != null) {
            examineRequest.setAge(age);
        }
        if (changes != null) {
            examineRequest.setChanges(changes);
        }
        if (status != null) {
            examineRequest.setStatus(status);
        }
        if (unemployment != null) {
            examineRequest.setUnemployment(unemployment);
        }
        if (isInsured != null) {
            examineRequest.setIsInsured(isInsured);
        }
        if (communityId != null) {
            examineRequest.setCommunityId(communityId);
        }
        return examineDao.findAllExamineCount(examineRequest);
    }

    @Override
    public Integer getByIdCard(String idCard) {

        ExamineRequest examineRequest = new ExamineRequest();
        examineRequest.setIdCard(idCard);

        return examineDao.getByIdCard(examineRequest);
    }

    @Override
    public List<Examine> getExamineWillStart(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (!StringUtils.isBlank(name)) {
            examineRequest.setName(name);
        }
        if (!StringUtils.isBlank(idCard)) {
            examineRequest.setIdCard(idCard);
        }
        if (comping != null) {
            examineRequest.setComping(comping);
        }
        if (age != null) {
            examineRequest.setAge(age);
        }
        if (changes != null) {
            examineRequest.setChanges(changes);
        }
        if (status != null) {
            examineRequest.setStatus(status);
        }
        if (unemployment != null) {
            examineRequest.setUnemployment(unemployment);
        }
        if (isInsured != null) {
            examineRequest.setIsInsured(isInsured);
        }
        if (communityId != null) {
            examineRequest.setCommunityId(communityId);
        }
        if (pageNum != null && pageSize != null) {
            examineRequest.setPager(pageNum, pageSize);
        }
        return examineDao.getExamineWillStart(examineRequest);
    }

    @Override
    public Integer getExamineWillStartCount(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (!StringUtils.isBlank(name)) {
            examineRequest.setName(name);
        }
        if (!StringUtils.isBlank(idCard)) {
            examineRequest.setIdCard(idCard);
        }
        if (comping != null) {
            examineRequest.setComping(comping);
        }
        if (age != null) {
            examineRequest.setAge(age);
        }
        if (changes != null) {
            examineRequest.setChanges(changes);
        }
        if (status != null) {
            examineRequest.setStatus(status);
        }
        if (unemployment != null) {
            examineRequest.setUnemployment(unemployment);
        }
        if (isInsured != null) {
            examineRequest.setIsInsured(isInsured);
        }
        if (communityId != null) {
            examineRequest.setCommunityId(communityId);
        }
        return examineDao.getExamineWillStartCount(examineRequest);
    }

    @Override
    public List<Examine> getExamineWillStop(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (!StringUtils.isBlank(name)) {
            examineRequest.setName(name);
        }
        if (!StringUtils.isBlank(idCard)) {
            examineRequest.setIdCard(idCard);
        }
        if (comping != null) {
            examineRequest.setComping(comping);
        }
        if (age != null) {
            examineRequest.setAge(age);
        }
        if (changes != null) {
            examineRequest.setChanges(changes);
        }
        if (status != null) {
            examineRequest.setStatus(status);
        }
        if (unemployment != null) {
            examineRequest.setUnemployment(unemployment);
        }
        if (isInsured != null) {
            examineRequest.setIsInsured(isInsured);
        }
        if (communityId != null) {
            examineRequest.setCommunityId(communityId);
        }
        if (pageNum != null && pageSize != null) {
            examineRequest.setPager(pageNum, pageSize);
        }
        return examineDao.getExamineWillStop(examineRequest);
    }

    @Override
    public Integer getExamineWillStopCount(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (!StringUtils.isBlank(name)) {
            examineRequest.setName(name);
        }
        if (!StringUtils.isBlank(idCard)) {
            examineRequest.setIdCard(idCard);
        }
        if (comping != null) {
            examineRequest.setComping(comping);
        }
        if (age != null) {
            examineRequest.setAge(age);
        }
        if (changes != null) {
            examineRequest.setChanges(changes);
        }
        if (status != null) {
            examineRequest.setStatus(status);
        }
        if (unemployment != null) {
            examineRequest.setUnemployment(unemployment);
        }
        if (isInsured != null) {
            examineRequest.setIsInsured(isInsured);
        }
        if (communityId != null) {
            examineRequest.setCommunityId(communityId);
        }
        return examineDao.getExamineWillStopCount(examineRequest);
    }
}
