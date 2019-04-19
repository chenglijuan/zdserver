package com.lemi.msloan.controller;

import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.response.ApiResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @Author: chenglijuan
 * @Data: 2019/4/18  下午9:23
 * @Decription:
 * @Modified:
 */
@Controller
@RequestMapping(value = "respect")
public class RespectController {

    @RequestMapping(value = "respectPager")
    public ModelAndView respectPager(Integer rosterId, Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/respect_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "addRespect")
    public ModelAndView addRespect(Integer rosterId, Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/add_respect");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    /**
     *
     * @param idCard 身份证号码
     * @param name  姓名
     * @param gender 性别 1. 男 2 女
     * @param birthday  出生年月
     * @param type  类型 1 城镇 2 农村
     * @param house 现户籍所在地
     * @param communityId  社区
     * @param communityName  社区名称
     * @param dynamicYearMonth 动态享受年月
     * @param grantTime 起始发放时间
     * @param phone  手机号码
     * @param remark 变动情况说明
     * @param issuStandard 发放标准
     * @param auditState  审核状态
     * @param grantState 发放标准
     * @param loginId  当前登录用户
     * @return
     */
    @RequestMapping(value = "insertRespect")
    @ResponseBody
    public ApiResult insertRespect(String idCard, String name, Integer gender, String birthday,Integer type, String house,Integer communityId,
                                  String communityName,String dynamicYearMonth,String grantTime,String phone,String remark,Integer issuStandard,
                                  Integer auditState,Integer grantState,Integer loginId)
    {
        if (StringUtils.isBlank(idCard)) {
            return new ApiResult(false, "请输入身份证号码", -1);
        }
        if (StringUtils.isBlank(name)) {
            return new ApiResult(false, "请输入姓名", -1);
        }
        if (gender == null ) {
            return new ApiResult(false, "请输入性别", -1);
        }
        if (StringUtils.isBlank(birthday)) {
            return new ApiResult(false, "请输入出生年月", -1);
        }
        if (StringUtils.isBlank(house)) {
            return new ApiResult(false, "请输入现户籍所在地", -1);
        }
        if ( null == communityId) {
            return new ApiResult(false, "请输入社区", -1);
        }
        try{
            Respect respect = new Respect();
            respect.setIdCard(idCard);
            respect.setName(name);
            respect.setType(type);
            respect.setCommunityName(communityName);
            respect.setDynamicYearMonth(dynamicYearMonth);
            respect.setGrantTime(grantTime);
            respect.setPhone(phone);
            respect.setRemark(remark);
            respect.setIssuStandard(issuStandard);
            respect.setAuditState(auditState);
            respect.setGrantState(grantState);

            return new ApiResult(true, "操作成功", 0);
        } catch (Exception e){
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1);
        }
    }
}
