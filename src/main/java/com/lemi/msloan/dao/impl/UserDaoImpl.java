package com.lemi.msloan.dao.impl;

import com.lemi.msloan.dao.UserDao;
import com.lemi.msloan.entity.User;
import com.lemi.msloan.request.UserRequest;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2019/4/18.
 */
@Repository(value = "userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User getByUsername(UserRequest userRequest) {
        return sqlSessionTemplate.selectOne("tbl_user.getByUsername",userRequest);
    }
}
