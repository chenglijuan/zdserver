package com.lemi.msloan.dao.impl;

import com.lemi.msloan.dao.UserDao;
import com.lemi.msloan.entity.User;
import com.lemi.msloan.request.UserRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2019/4/18.
 */
@Repository(value = "userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User getByUsername(UserRequest userRequest) {
        return sqlSessionTemplate.selectOne("tbl_user.getByUsername",userRequest);
    }

    @Override
    public User getByUserId(Integer userId) {
        return sqlSessionTemplate.selectOne("tbl_user.getByUserId",userId);
    }

    @Override
    public List<User> selectUserPager(UserRequest request) {
        return sqlSessionTemplate.selectList("tbl_user.selectUserPager",request);
    }

    @Override
    public int selectUserCount(UserRequest request) {
        return sqlSessionTemplate.selectOne("tbl_user.selectUserCount",request);
    }
}
