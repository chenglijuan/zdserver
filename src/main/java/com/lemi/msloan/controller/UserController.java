package com.lemi.msloan.controller;

import com.lemi.msloan.entity.Community;
import com.lemi.msloan.entity.User;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.CommunityService;
import com.lemi.msloan.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/4/18.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommunityService communityService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public ApiResult login(String username, String password, HttpSession session) {
        if (StringUtils.isBlank(username)) {
            return new ApiResult(false, "请输入用户名", -1);
        }
        if (StringUtils.isBlank(password)) {
            return new ApiResult(false, "请输入密码", -1);
        }
        User user = userService.getByUsername(username);
        if (user == null) {
            return new ApiResult(false, "用户不存在", -1);
        }
        if (!user.getPassword().equals(password)) {
            return new ApiResult(false, "用户名或密码错误", -1);
        }
        Integer userId = user.getId();
        session.setAttribute("loginId",userId);
        return new ApiResult(true, "登录成功", 0, userId);
    }

    /**
     * 根据ID获取用户信息
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "getUserByUserId")
    @ResponseBody
    public ApiResult getUserByUserId(Integer userId) {
        if (userId == null) {
            return new ApiResult(false, "请登录", -1);
        }
        User user = userService.get(userId);
        if (user == null) {
            return new ApiResult(false, "登录信息不存在", -1);
        }
        user.setPassword(null);
        return new ApiResult(true, "登录成功", 0, user);
    }

    /**
     * 根据用户ID获取社区信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "getCommunityByUserId")
    @ResponseBody
    public ApiResult getCommunityByUserId(Integer userId){

        if (userId == null){
            return new ApiResult(false, "请传入登录用户ID", -1);
        }
        User user = userService.get(userId);
        if (user == null) {
            return new ApiResult(false, "登录信息不存在", -1);
        }
        if (user.getType().intValue() != 2){
            return new ApiResult(false, "该用户类型非社区用户", -1);
        }
        Community community = communityService.selectByUserId(userId);
        if (community == null){
            return new ApiResult(false, "社区不存在", -1);
        }
        return new ApiResult(true, "查询成功", 0,community);
    }

}
