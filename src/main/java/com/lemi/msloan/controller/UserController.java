package com.lemi.msloan.controller;

import com.lemi.msloan.entity.Community;
import com.lemi.msloan.entity.User;
import com.lemi.msloan.request.UserRequest;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.CommunityService;
import com.lemi.msloan.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "userLoginPage")
    public ModelAndView userLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/login");
        return modelAndView;
    }

    @RequestMapping(value = "userPage")
    public ModelAndView userPager(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/user/user_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "addUserForm")
    public ModelAndView addUserForm(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/user/add_user");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "updateUserForm")
    public ModelAndView updateUserForm(Integer loginId, Integer userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/user/update_user");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("userId", userId);
        return modelAndView;
    }

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
        session.setAttribute("loginId", userId+"");
        return new ApiResult(true, "登录成功", 0, userId);
    }

    /**
     *loginId
     * 退出登录
     * @return
     */
    @RequestMapping(value = "loginOut")
    @ResponseBody
    public ApiResult loginOut(Integer loginId, HttpSession session) {
        try {
            User user = userService.get(loginId);
            if (user == null) {
                return new ApiResult(false, "用户不存在", -1);
            }
            Integer userId = user.getId();
            session.setAttribute("loginId", "");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResult(true, "退出成功", 0, null);

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
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "getCommunityByUserId")
    @ResponseBody
    public ApiResult getCommunityByUserId(Integer userId) {

        if (userId == null) {
            return new ApiResult(false, "请传入登录用户ID", -1);
        }
        User user = userService.get(userId);
        if (user == null) {
            return new ApiResult(false, "登录信息不存在", -1);
        }
        if (user.getType().intValue() != 2) {
            return new ApiResult(false, "该用户类型非社区用户", -1);
        }
        Community community = communityService.selectByUserId(userId);
        if (community == null) {
            return new ApiResult(false, "社区不存在", -1);
        }
        return new ApiResult(true, "查询成功", 0, community);
    }

    /**
     * 获取用户列表
     *
     * @param pageNum
     * @param pageSize
     * @param loginId
     * @return
     */
    @RequestMapping(value = "selectUser")
    @ResponseBody
    public ApiResult selectUser(Integer pageNum, Integer pageSize, Integer loginId) {
        try {
            User user = userService.getByUserId(loginId);
            if (user == null) {
                return new ApiResult(false, "登录用户异常", -1);
            }
            if (user.getType().intValue() == 2) {
                return new ApiResult(false, "无授权操作", -1);
            }
            UserRequest userRequest = new UserRequest();
            userRequest.setPager(pageNum, pageSize);
            List<User> list = userService.selectUserPager(userRequest);
            Integer count = userService.selectUserCount(userRequest);
            Map<String, Object> map = new HashMap<>();
            for (User temp : list) {
                temp.setPassword("");
                Community community = communityService.selectByUserId(temp.getId());
                temp.setCommunity(community);
            }
            map.put("list", list);
            map.put("count", count);
            if (count != null && count.intValue() > 0) {
                Float totalPage = count * 1.0f / pageSize;
                map.put("totalPage", Math.ceil(totalPage));
            } else {
                map.put("totalPage", 1);
            }
            return new ApiResult(true, "操作成功", 0, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }

    /**
     * 新增用户
     *
     * @param nickname
     * @param username
     * @param password
     * @param comfirePwd
     * @param communityId 社区为空默认是 街道管理员
     * @param loginId
     * @return
     */
    @RequestMapping(value = "addUser")
    @ResponseBody
    public ApiResult addUser(String nickname, String username, String password, String comfirePwd, Integer communityId, Integer loginId) {
        try {
            if (StringUtils.isBlank(nickname)) {
                return new ApiResult(false, "用户昵称不能为空", -1);
            }
            if (StringUtils.isBlank(username)) {
                return new ApiResult(false, "用户名不能为空", -1);
            }
            if (StringUtils.isBlank(password)) {
                return new ApiResult(false, "密码不能为空", -1);
            }
            if (StringUtils.isBlank(comfirePwd)) {
                return new ApiResult(false, "确认密码不能为空", -1);
            }
            if (!comfirePwd.equals(password)) {
                return new ApiResult(false, "确认密码和密码不一致", -1);
            }
            User user = userService.getByUserId(loginId);
            if (user == null) {
                return new ApiResult(false, "登录用户异常", -1);
            }
            if (user.getType().intValue() == 2) {
                return new ApiResult(false, "无授权操作", -1);
            }
            User userName = userService.getByUsername(username);
            if (userName != null) {
                return new ApiResult(false, "账号名已存在", -1);
            }
            User adduser = new User();
            adduser.setPassword(password);
            adduser.setNickname(nickname);
            adduser.setUsername(username);
            adduser.setType(2);
//            if(communityId == null){
//                adduser.setType(1);
//            }else{
//                adduser.setType(2);
//            }
            adduser.setState(1);
            adduser.setCreateTime(new Date());
            userService.save(adduser);
            if (communityId != null) {
                Integer userId = adduser.getId();
                Community community = communityService.get(communityId);
                if (community != null) {
                    community.setUserId(userId);
                    communityService.update(community);
                }
            }
            return new ApiResult(true, "操作成功", 0, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }

    /**
     * 修改用户
     *
     * @param userId
     * @param nickname
     * @param username
     * @param password
     * @param comfirePwd
     * @param communityId
     * @param loginId
     * @return
     */
    @RequestMapping(value = "updateUser")
    @ResponseBody
    public ApiResult updateUser(Integer userId, String nickname, String username, String password, String comfirePwd, Integer communityId, Integer loginId) {
        try {
            if (StringUtils.isBlank(nickname)) {
                return new ApiResult(false, "用户昵称不能为空", -1);
            }
            if (StringUtils.isBlank(username)) {
                return new ApiResult(false, "用户名不能为空", -1);
            }
            if (StringUtils.isBlank(password)) {
                return new ApiResult(false, "密码不能为空", -1);
            }
            if (StringUtils.isBlank(comfirePwd)) {
                return new ApiResult(false, "确认密码不能为空", -1);
            }
            if (!comfirePwd.equals(password)) {
                return new ApiResult(false, "确认密码和密码不一致", -1);
            }
            User user = userService.getByUserId(loginId);
            if (user == null) {
                return new ApiResult(false, "登录用户异常", -1);
            }
            if (user.getType().intValue() == 2) {
                return new ApiResult(false, "无授权操作", -1);
            }
            User adduser = userService.getByUserId(userId);
            adduser.setPassword(password);
            adduser.setNickname(nickname);
            adduser.setUsername(username);
            adduser.setType(2);
//            if(communityId == null){
//                adduser.setType(1);
//            }else{
//                adduser.setType(2);
//            }
            adduser.setState(1);
            adduser.setCreateTime(new Date());
            if (communityId != null) {
                Community community = communityService.get(communityId);
                if (community != null) {
                    community.setUserId(userId);
                    communityService.update(community);
                }
            }
            userService.update(adduser);
            return new ApiResult(true, "操作成功", 0, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }

    @RequestMapping(value = "deleteById")
    @ResponseBody
    public ApiResult deleteById(Integer userId) {
        try {
            Community community = communityService.selectByUserId(userId);
            userService.delete(userId);
            if(community != null){
                community.setUserId(null);
                communityService.update(community);
            }
            return new ApiResult(true, "操作成功", 0, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }

    /**
     * 获取详情  社区等
     * @param userId
     * @return
     */
    @RequestMapping(value = "getUserInfo")
    @ResponseBody
    public ApiResult getUserInfo(Integer userId) {
        try {
            if (userId == null) {
                return new ApiResult(false, "请传入登录用户ID", -1);
            }
            User user = userService.get(userId);
            if (user == null) {
                return new ApiResult(false, "登录信息不存在", -1);
            }
            Community community = communityService.selectByUserId(userId);
            user.setCommunity(community);
            return new ApiResult(true, "查询成功", 0, user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }

    /**
     * 修改状态
     * @param state 1.启用 2.冻结
     * @return
     */
    @RequestMapping(value = "updateState")
    @ResponseBody
    public ApiResult updateState(Integer userId,Integer state) {
        try {
            if (userId == null) {
                return new ApiResult(false, "请传入登录用户ID", -1);
            }
            User user = userService.get(userId);
            if (user == null) {
                return new ApiResult(false, "登录信息不存在", -1);
            }
            user.setState(state);
            userService.update(user);
            return new ApiResult(true, "查询成功", 0, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }
}
