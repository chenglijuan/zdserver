package com.lemi.msloan.service.impl;

import com.lemi.msloan.dao.UserDao;
import com.lemi.msloan.entity.User;
import com.lemi.msloan.request.UserRequest;
import com.lemi.msloan.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/4/18.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Resource
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    @Resource
    public void setBaseDao(UserDao userDao) {
        super.setBaseDao(userDao);
    }

    @Override
    public User getByUsername(String username) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(username);
        return userDao.getByUsername(userRequest);
    }

    @Override
    public User getByUserId(Integer userId) {
        return userDao.getByUserId(userId);
    }

    @Override
    public List<User> selectUserPager(UserRequest request) {
        return userDao.selectUserPager(request);
    }

    @Override
    public int selectUserCount(UserRequest request) {
        return userDao.selectUserCount(request);
    }
}
