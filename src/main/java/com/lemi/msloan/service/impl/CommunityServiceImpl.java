package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.CommunityDao;
import com.lemi.msloan.entity.Community;
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
}
