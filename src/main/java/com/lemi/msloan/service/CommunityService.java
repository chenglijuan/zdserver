package com.lemi.msloan.service;

import com.lemi.msloan.entity.Community;

import java.util.List;

public interface CommunityService extends BaseService<Community> {
    List<Community> findAll();
}
