package com.lemi.msloan.service;

import com.lemi.msloan.entity.User;
import com.lemi.msloan.request.UserRequest;

import java.util.List;

/**
 * Created by Administrator on 2019/4/18.
 */
public interface UserService extends BaseService<User> {
    User getByUsername(String username);

    User getByUserId(Integer userId);

    List<User> selectUserPager(UserRequest request);

    int selectUserCount(UserRequest request);
}
