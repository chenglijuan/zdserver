package com.lemi.msloan.service;

import com.lemi.msloan.entity.User;

/**
 * Created by Administrator on 2019/4/18.
 */
public interface UserService extends BaseService<User> {
    User getByUsername(String username);
}
