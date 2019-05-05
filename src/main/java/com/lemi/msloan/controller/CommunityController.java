package com.lemi.msloan.controller;

import com.lemi.msloan.entity.Community;
import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.entity.User;
import com.lemi.msloan.request.CommunityRequest;
import com.lemi.msloan.request.UserRequest;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.CommunityService;
import com.lemi.msloan.service.UserService;
import com.lemi.msloan.util.PhoneUtil;
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
@RequestMapping(value = "/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "communityPage")
    public ModelAndView userPager(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/community/community_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "addCommunityForm")
    public ModelAndView addUserForm(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/community/add_community");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "updateCommunityForm")
    public ModelAndView updateUserForm(Integer loginId,Integer communityId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/community/update_community");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("communityId", communityId);
        return modelAndView;
    }


    /**
     *
     * @param name 社区名称
     * @param address 社区地址
     * @param phone 联系号码
     * @param linkman 联系人
     * @return
     */
    @RequestMapping(value = "addCommunity")
    @ResponseBody
    public ApiResult addCommunity(String name,String address,String phone,String linkman,Integer loginId) {
        try {
            if(StringUtils.isBlank(name)){
                return new ApiResult(false, "社区名称不能为空", -1);
            }
            if(StringUtils.isBlank(linkman)){
                return new ApiResult(false, "社区联系人不能为空", -1);
            }
            if(!(PhoneUtil.isPhone(phone) || PhoneUtil.isMobileNO(phone))){
                return new ApiResult(false, "社区联系号码不正确为空", -1);
            }
            User user = userService.getByUserId(loginId);
            if (user == null) {
                return new ApiResult(false, "登录用户异常", -1);
            }
            if(user.getType().intValue() == 2){
                return new ApiResult(false, "无授权操作", -1);
            }
            Community community = new Community();
            community.setName(name);
            community.setAddress(address);
            community.setPhone(phone);
            community.setLinkman(linkman);
            communityService.save(community);
            return new ApiResult(true, "操作成功", 0, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }

    /**
     * 修改社区
     * @param name
     * @param address
     * @param phone
     * @param linkman
     * @param loginId
     * @param communityId
     * @return
     */
    @RequestMapping(value = "updateCommunity")
    @ResponseBody
    public ApiResult updateCommunity(String name,String address,String phone,String linkman,Integer loginId,Integer communityId) {
        try {
            if(communityId == 0) {
                return new ApiResult(false, "记录ID不能为空", -1);
            }
            if(StringUtils.isBlank(name)){
                return new ApiResult(false, "社区名称不能为空", -1);
            }
            if(StringUtils.isBlank(linkman)){
                return new ApiResult(false, "社区联系人不能为空", -1);
            }
            if(StringUtils.isBlank(phone)) {
                return new ApiResult(false, "社区联系号码不能为空", -1);
            }
            if(!(PhoneUtil.isPhone(phone) || PhoneUtil.isMobileNO(phone))){
                return new ApiResult(false, "社区联系号码不正确为空", -1);
            }
            User user = userService.getByUserId(loginId);
            if (user == null) {
                return new ApiResult(false, "登录用户异常", -1);
            }
            if(user.getType().intValue() == 2){
                return new ApiResult(false, "无授权操作", -1);
            }
            Community community = communityService.get(communityId);
            if(community == null){
                return new ApiResult(false, "数据不存在", -1);
            }
            community.setName(name);
            community.setAddress(address);
            community.setPhone(phone);
            community.setLinkman(linkman);
            communityService.update(community);
            return new ApiResult(true, "操作成功", 0, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }

    /**
     * 根据id获取记录
     * @param communityId
     * @return
     */
    @RequestMapping(value = "getCommunityById")
    @ResponseBody
    public ApiResult getCommunityById(Integer communityId) {
        try {
            if(communityId == null ) {
                return new ApiResult(false, "记录ID不能为空", -1);
            }
            Community community = communityService.get(communityId);
            return new ApiResult(true, "操作成功", 0, community);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }

    /**
     * 获取列表
     * @param
     * @return
     */
    @RequestMapping(value = "selectCommunity")
    @ResponseBody
    public ApiResult selectCommunity(String name,String phone,Integer pageNum,Integer pageSize) {
        try {
            CommunityRequest request = new CommunityRequest();
            request.setName(name);
            request.setPager(pageNum, pageSize);
            List<Community> list = communityService.selectCommunityPager(request);
            Integer count = communityService.selectCommunityCount(request);
            Map<String, Object> map = new HashMap<>();
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
     * 删除
     * @param communityId
     * @return
     */
    @RequestMapping(value = "deleteById")
    @ResponseBody
    public ApiResult deleteById(Integer communityId) {
        try {
            communityService.delete(communityId);
            return new ApiResult(true, "操作成功", 0, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }
}
