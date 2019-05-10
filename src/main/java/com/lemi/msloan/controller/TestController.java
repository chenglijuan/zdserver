package com.lemi.msloan.controller;

import com.lemi.msloan.entity.Community;
import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.entity.User;
import com.lemi.msloan.request.UserRequest;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.CommunityService;
import com.lemi.msloan.service.RespectService;
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
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private RespectService respectService;
    /**
     * 根据ID获取用户信
     * @return
     */
    @RequestMapping(value = "test")
    @ResponseBody
    public ApiResult getUserByUserId() {
       try{
           Integer communityId = null;
           Integer respectTownCount = respectService.selectRemindRespectCount(communityId,1,1);
           //尊老金农村待审核的数量
           Integer respectCountryCount = respectService.selectRemindRespectCount(communityId,2,1);
           //长寿金待审核的数量
           Integer respectLongCount = respectService.selectRemindRespectCount(communityId,3,1);


           System.out.println("respectTownCount="+respectTownCount);
           System.out.println("respectCountryCount="+respectCountryCount);
           System.out.println("respectLongCount="+respectLongCount);


           List<Respect> town = respectService.selectRemindRespect(communityId,1,1);

           List<Respect> country = respectService.selectRemindRespect(communityId,2,1);

           List<Respect> longs = respectService.selectRemindRespect(communityId,3,1);


           return new ApiResult(true, "登录成功", 0, null);
       }catch (Exception e){
           e.printStackTrace();
           return new ApiResult(false, "操作失败", -1, null);
       }
    }



}
