package com.lemi.msloan.dao;

import com.lemi.msloan.entity.Community;

import java.util.List;

public interface CommunityDao extends BaseDao<Community> {
    List<Community> findAll();
}
