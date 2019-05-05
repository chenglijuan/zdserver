package com.lemi.msloan.dao.impl;

import com.lemi.msloan.dao.CommunityDao;
import com.lemi.msloan.entity.Community;
import com.lemi.msloan.request.CommunityRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "communityDao")
public class CommunityDaoImpl extends BaseDaoImpl<Community> implements CommunityDao {
    @Override
    public List<Community> findAll() {
        return sqlSessionTemplate.selectList("tbl_community.findAll");
    }

    @Override
    public Community selectByUserId(CommunityRequest communityRequest) {
        return sqlSessionTemplate.selectOne("tbl_community.selectByUserId",communityRequest);
    }

    @Override
    public Community getByName(CommunityRequest communityRequest) {
        return sqlSessionTemplate.selectOne("tbl_community.getByName",communityRequest);
    }

    @Override
    public List<Community> selectCommunityPager(CommunityRequest request) {
        return sqlSessionTemplate.selectList("tbl_community.selectCommunityPager",request);
    }

    @Override
    public int selectCommunityCount(CommunityRequest request) {
        return sqlSessionTemplate.selectOne("tbl_community.selectCommunityCount",request);
    }
}
