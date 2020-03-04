package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.ExamineDao;
import com.lemi.msloan.entity.Examine;
import com.lemi.msloan.request.ExamineRequest;
import com.lemi.msloan.service.ExamineService;
import com.lemi.msloan.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    public List<Examine> findAllExamine(Integer gender,Integer state,String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (gender!=null){
            examineRequest.setGender(gender);
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
    public Integer findAllExamineCount(Integer gender,Integer state,String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (gender!=null){
            examineRequest.setGender(gender);
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
    public Examine getBeanByIdCard(String idCard) {
        ExamineRequest examineRequest = new ExamineRequest();
        examineRequest.setIdCard(idCard);

        return examineDao.getBeanByIdCard(examineRequest);
    }

    @Override
    public List<Examine> getExamineWillStart(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (gender!=null){
            examineRequest.setGender(gender);
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
    public Integer getExamineWillStartCount(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (gender!=null){
            examineRequest.setGender(gender);
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
    public List<Examine> getExamineWillStop(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (gender!=null){
            examineRequest.setGender(gender);
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
    public Integer getExamineWillStopCount(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (gender!=null){
            examineRequest.setGender(gender);
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

    @Override
    public List<Examine> findAgainExamine(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (gender!=null){
            examineRequest.setGender(gender);
        }
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
        return examineDao.findAgainExamine(examineRequest);
    }

    @Override
    public Integer findAgainExamineCount(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId) {
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
        return examineDao.findAgainExamineCount(examineRequest);
    }

    @Override
    public List<Examine> findUndeterminedExamine(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        ExamineRequest examineRequest = new ExamineRequest();
        if (!StringUtils.isBlank(house)){
            examineRequest.setHouse(house);
        }
        if (gender!=null){
            examineRequest.setGender(gender);
        }
        if (!StringUtils.isBlank(name)) {
            examineRequest.setName(name);
        }
        if (!StringUtils.isBlank(idCard)) {
            examineRequest.setIdCard(idCard);
        }
        if (gender != null) {
            examineRequest.setGender(gender);
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
        return examineDao.findUndeterminedExamine(examineRequest);
    }

    @Override
    public Integer findUndeterminedExamineCount(Integer gender, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId) {
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
        if (gender != null) {
            examineRequest.setGender(gender);
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
        return examineDao.findUndeterminedExamineCount(examineRequest);
    }

    @Override
    public List<Examine> getByStatus() {
        return examineDao.getByStatus();
    }

    @Override
    public Integer getAddedCountByCommunityId(Integer communityId, String beginTime, String endTime) {
        ExamineRequest examineRequest = new ExamineRequest();

        if (communityId != null){
            examineRequest.setCommunityId(communityId);
        }
        if (!StringUtils.isBlank(beginTime)){
            examineRequest.setBeginTime(DateUtil.getDateToString(beginTime,"yyyy-MM-dd"));
        }
        if (!StringUtils.isBlank(endTime)){
            examineRequest.setEndTime(DateUtil.getDateToString(endTime,"yyyy-MM-dd"));
        }

        return examineDao.getAddedCountByCommunityId(examineRequest);
    }

    @Override
    public Integer getExitCountByCommunityId(Integer communityId, String beginTime, String endTime, Integer exitType) {
        ExamineRequest examineRequest = new ExamineRequest();

        if (communityId != null){
            examineRequest.setCommunityId(communityId);
        }
        if (!StringUtils.isBlank(beginTime)){
            examineRequest.setBeginTime(DateUtil.getDateToString(beginTime,"yyyy-MM-dd"));
        }
        if (!StringUtils.isBlank(endTime)){
            examineRequest.setEndTime(DateUtil.getDateToString(endTime,"yyyy-MM-dd"));
        }
        if (exitType != null){
            examineRequest.setExitType(exitType);
        }
        return examineDao.getExitCountByCommunityId(examineRequest);
    }

    @Override
    public List<Examine> getNotExitExamine(Integer communityId, String beginTime, String endTime) {
        ExamineRequest examineRequest = new ExamineRequest();

        if (communityId != null){
            examineRequest.setCommunityId(communityId);
        }
        if (!StringUtils.isBlank(beginTime)){
            examineRequest.setBeginTime(DateUtil.getDateToString(beginTime,"yyyy-MM-dd 00:00:00"));
        }
        if (!StringUtils.isBlank(endTime)){
            examineRequest.setEndTime(DateUtil.getDateToString(endTime,"yyyy-MM-dd 23:59:59"));
        }
        return examineDao.getNotExitExamine(examineRequest);
    }
}
