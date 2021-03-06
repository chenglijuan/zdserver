package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.CommunityDao;
import com.lemi.msloan.entity.Community;
import com.lemi.msloan.request.CommunityRequest;
import com.lemi.msloan.service.CommunityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {

    @Resource
    private CommunityDao communityDao;

    public CommunityDao getCommunityDao() {
        return communityDao;
    }

    @Resource
    public void setBaseDao(CommunityDao communityDao) {
        super.setBaseDao(communityDao);
    }

    @Override
    public List<Community> findAll() {
        return communityDao.findAll();
    }

    @Override
    public Community selectByUserId(Integer userId) {
        CommunityRequest communityRequest = new CommunityRequest();
        communityRequest.setUserId(userId);
        return communityDao.selectByUserId(communityRequest);
    }

    @Override
    public Community getByName(String communityName) {
        CommunityRequest communityRequest = new CommunityRequest();
        communityRequest.setName(communityName);
        return communityDao.getByName(communityRequest);
    }

    @Override
    public List<Community> selectCommunityPager(CommunityRequest request) {
        return communityDao.selectCommunityPager(request);
    }

    @Override
    public int selectCommunityCount(CommunityRequest request) {
        return communityDao.selectCommunityCount(request);
    }
}
