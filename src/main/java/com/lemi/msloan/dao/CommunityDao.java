package com.lemi.msloan.dao;

import com.lemi.msloan.entity.Community;
import com.lemi.msloan.request.CommunityRequest;

import java.util.List;

public interface CommunityDao extends BaseDao<Community> {
    List<Community> findAll();

    Community selectByUserId(CommunityRequest communityRequest);
}
