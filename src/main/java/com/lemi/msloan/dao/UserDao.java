package com.lemi.msloan.dao;

import com.lemi.msloan.entity.User;
import com.lemi.msloan.request.UserRequest;

import java.util.List;

/**
 * Created by Administrator on 2019/4/18.
 */
public interface UserDao extends BaseDao<User> {
    User getByUsername(UserRequest userRequest);

    User getByUserId(Integer userId);

    List<User> selectUserPager(UserRequest request);

    int selectUserCount(UserRequest request);


}
