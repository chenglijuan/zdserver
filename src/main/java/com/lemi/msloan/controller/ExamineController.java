package com.lemi.msloan.controller;

import com.lemi.msloan.entity.Examine;
import com.lemi.msloan.entity.Roster;
import com.lemi.msloan.entity.User;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.ExamineService;
import com.lemi.msloan.service.RosterService;
import com.lemi.msloan.service.UserService;
import com.lemi.msloan.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/19.
 */
@Controller
@RequestMapping(value = "examine")
public class ExamineController {

    @Autowired
    private ExamineService examineService;

    @Autowired
    private RosterService rosterService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "updateExaminePager")
    public ModelAndView updateExaminePager(Integer examineId, Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/examine/update_examine");
        modelAndView.addObject("examineId", examineId);
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "auditExaminePager")
    public ModelAndView auditExaminePager(Integer examineId, Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/examine/audit_examine");
        modelAndView.addObject("examineId", examineId);
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    /**
     * 查询
     *
     * @param state        审核状态：1.审核通过  2.审核不通过  3、待定 4、待复审 5、未审核
     * @param house        现户籍所在地
     * @param name         姓名
     * @param idCard       身份证号码
     * @param comping      是否并轨 1：是 2：否
     * @param age          年龄
     * @param changes      变动情况 1：迁出  2：新增  3：死亡
     * @param status       发放状态 1：未开始 2：发放中 3：已暂停 4：已退出
     * @param unemployment 失业状态 1：领取失业金 2：未领取失业金
     * @param isInsured    是否参保 1：是 2：否
     * @param communityId  现所属社区ID
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "findAllExamine")
    @ResponseBody
    public ApiResult findAllExamine(Integer state, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {

        List<Examine> list = examineService.findAllExamine(state, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, pageSize, pageNum);

        Integer count = examineService.findAllExamineCount(state, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId);

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        map.put("count", count);

        return new ApiResult(true, "查询成功", 0, map);
    }

    /**
     * 新增征地人员社会救济金
     *
     * @param unStart      领取失业金开始时间
     * @param unEnd        领取失业金截止时间
     * @param stopType     暂停发放原因：1.就业  2.退休  3.其他
     * @param stopReason   暂停发放备注
     * @param startTime    开始发放时间
     * @param stopTime     停止发放时间
     * @param dtxsny       动态享受年月
     * @param ffbj         发放标准
     * @param isInsured    是否参保 1：是 2：否
     * @param unemployment 失业状态 1：领取失业金 2：未领取失业金
     * @param comping      是否并轨 1：是 2：否
     * @param changes      变动情况 1：迁出  2：新增  3：死亡
     * @param remark       备注
     * @param batch        新增批次
     * @param state        状态：1.审核通过  2.审核不通过  3、待定 4、待复审 5、未审核
     * @param idCard       身份证号码
     * @param name         姓名
     * @param gender       性别 1：男 2:女
     * @param birthday     出生年月
     * @param address      常住地址
     * @param village      征地时所在村（组）
     * @param isMove       是否迁出 1：否  2：是
     * @param communityId  现所属社区ID
     * @param house        现户籍所在地
     * @param status       发放状态 1：未开始 2：发放中 3：已暂停 4：已退出
     * @param villageTime  征地时间
     * @param villageAge  征地时年龄
     * @param cdState      撤队时安置情况  1.未撤队先安置  2.撤队时安置 3.领取征地待业  4.领取一次性补偿金
     * @return
     */
    @RequestMapping(value = "addExamine")
    @ResponseBody
    public ApiResult addExamine(String phone, String unStart, String unEnd, Integer stopType, String stopReason, String startTime, String stopTime, String dtxsny, String ffbj, Integer isInsured, Integer unemployment, Integer comping, Integer changes, String remark, String batch, Integer state, String idCard,
                                String name, Integer gender, String birthday, String address, String village, Integer isMove, Integer communityId, String house, Integer status, String villageTime, Integer villageAge, Integer cdState) {
        if (isInsured == null) {
            return new ApiResult(false, "请选择是否参保", -1);
        }
        if (unemployment == null) {
            return new ApiResult(false, "请选择失业状态", -1);
        }
        if (unemployment != null) {
            if (unemployment.intValue() == 1) {
                if (StringUtils.isBlank(unStart) || StringUtils.isBlank(unEnd)) {
                    return new ApiResult(false, "请选择领取失业金开始时间和截止时间", -1);
                }
            }
        }
        if (comping == null) {
            return new ApiResult(false, "请选择是否并轨", -1);
        }
        if (changes == null) {
            return new ApiResult(false, "请选择变动情况", -1);
        }
        if (gender == null) {
            return new ApiResult(false, "请选择性别", -1);
        }
        if (isMove == null) {
            return new ApiResult(false, "请选择是否迁出", -1);
        }
        if (communityId == null) {
            return new ApiResult(false, "请选择现所属社区", -1);
        }
        if (status == null) {
            return new ApiResult(false, "请选择发放状态", -1);
        }
        if (status.intValue() == 3) {
            if (stopType == null) {
                return new ApiResult(false, "请选择暂停原因", -1);
            }
        }
        if (villageAge == null) {
            return new ApiResult(false, "请输入征地时年龄", -1);
        }
        if (cdState == null) {
            return new ApiResult(false, "请选择撤队时安置情况", -1);
        }
        if (StringUtils.isBlank(startTime)) {
            return new ApiResult(false, "请输入开始发放时间", -1);
        }
        if (StringUtils.isBlank(stopTime)) {
            return new ApiResult(false, "请输入停止发放时间", -1);
        }
        if (StringUtils.isBlank(dtxsny)) {
            return new ApiResult(false, "请输入动态享受年月", -1);
        }
        if (StringUtils.isBlank(ffbj)) {
            return new ApiResult(false, "请输入发放标准", -1);
        }
        if (StringUtils.isBlank(batch)) {
            return new ApiResult(false, "请输入新增批次", -1);
        }
        if (StringUtils.isBlank(name)) {
            return new ApiResult(false, "请输入姓名", -1);
        }
        if (StringUtils.isBlank(birthday)) {
            return new ApiResult(false, "请输入出生年月", -1);
        }
        if (StringUtils.isBlank(address)) {
            return new ApiResult(false, "请输入常住地址", -1);
        }
        if (StringUtils.isBlank(village)) {
            return new ApiResult(false, "请输入征地时所在村（组）", -1);
        }
        if (StringUtils.isBlank(house)) {
            return new ApiResult(false, "请输入现户籍所在地", -1);
        }
        if (StringUtils.isBlank(villageTime)) {
            return new ApiResult(false, "请输入征地时间", -1);
        }
        if (StringUtils.isBlank(idCard)) {
            return new ApiResult(false, "请输入身份证号", -1);
        }
        Integer count = examineService.getByIdCard(idCard);
        if (count.intValue() != 0) {
            return new ApiResult(false, "身份证号已存在", -1);
        }
        Examine examine = new Examine();
        examine.setStartTime(DateUtil.getDateToString(startTime, "yyyy-MM-dd"));
        examine.setStopTime(DateUtil.getDateToString(stopTime, "yyyy-MM-dd"));
        examine.setDtxsny(dtxsny);
        examine.setPhone(phone);
        examine.setFfbj(ffbj);
        examine.setIsInsured(isInsured);
        examine.setUnemployment(unemployment);
        examine.setComping(comping);
        examine.setChanges(changes);
        examine.setRemark(remark);
        examine.setBatch(batch);
        examine.setState(5);
        examine.setIdCard(idCard);
        examine.setName(name);
        examine.setGender(gender);
        examine.setBirthday(DateUtil.getDateToString(birthday, "yyyy-MM-dd"));
        examine.setAddress(address);
        examine.setVillage(village);
        examine.setIsMove(isMove);
        examine.setCommunityId(communityId);
        examine.setHouse(house);
        examine.setStatus(status);
        examine.setVillageTime(DateUtil.getDateToString(villageTime, "yyyy-MM-dd"));
        examine.setVillageAge(villageAge);
        examine.setCdState(cdState);
        if (!StringUtils.isBlank(unEnd)) {
            examine.setUnEnd(DateUtil.getDateToString(unEnd, "yyyy-MM-dd"));
        }
        if (!StringUtils.isBlank(unStart)) {
            examine.setUnEnd(DateUtil.getDateToString(unStart, "yyyy-MM-dd"));
        }
        if (stopType != null) {
            examine.setStopType(stopType);
        }
        if (!StringUtils.isBlank(stopReason)) {
            examine.setStopReason(stopReason);
        }
        examine.setTime(new Date());
        int result = examineService.save(examine);
        if (result == 1) {
            Integer examineId = examine.getId();
            Roster roster = new Roster();
            roster.setIdCard(idCard);
            roster.setName(name);
            roster.setGender(gender);
            roster.setBirthday(DateUtil.getDateToString(birthday, "yyyy-MM-dd"));
            roster.setAddress(address);
            roster.setVillage(village);
            roster.setIsMove(isMove);
            roster.setCommunityId(communityId);
            roster.setHouse(house);
            roster.setRemark(remark);
            roster.setStatus(status);
            roster.setExamineId(examineId);
            roster.setTime(new Date());
            int res = rosterService.save(roster);
            if (res == 1) {
                return new ApiResult(false, "新增征地人员社会救济金成功", -1);
            } else {
                examineService.delete(examineId);
                return new ApiResult(false, "新增征地人员社会救济金失败", -1);
            }
        } else {
            return new ApiResult(false, "新增征地人员社会救济金失败", -1);
        }
    }

    /**
     * 编辑征地人员社会救济金
     *
     * @param unStart      领取失业金开始时间
     * @param unEnd        领取失业金截止时间
     * @param stopType     暂停发放原因：1.就业  2.退休  3.其他
     * @param stopReason   暂停发放备注
     * @param examineId    征地人员社会救济金ID
     * @param startTime    开始发放时间
     * @param stopTime     停止发放时间
     * @param dtxsny       动态享受年月
     * @param ffbj         发放标准
     * @param isInsured    是否参保 1：是 2：否
     * @param unemployment 失业状态 1：领取失业金 2：未领取失业金
     * @param comping      是否并轨 1：是 2：否
     * @param changes      变动情况 1：迁出  2：新增  3：死亡
     * @param remark       备注
     * @param batch        新增批次
     * @param state        状态：1.审核通过  2.审核不通过  3、待定 4、待复审 5、未审核
     * @param idCard       身份证号码
     * @param name         姓名
     * @param gender       性别 1：男 2:女
     * @param birthday     出生年月
     * @param address      常住地址
     * @param village      征地时所在村（组）
     * @param isMove       是否迁出 1：否  2：是
     * @param communityId  现所属社区ID
     * @param house        现户籍所在地
     * @param status       发放状态 1：未开始 2：发放中 3：已暂停 4：已退出
     * @param villageTime  征地时间
     * @param villageAge  征地时年龄
     * @param cdState      撤队时安置情况  1.未撤队先安置  2.撤队时安置 3.领取征地待业  4.领取一次性补偿金
     * @return
     */
    @RequestMapping(value = "updateExamineById")
    @ResponseBody
    public ApiResult updateExamineById(String phone, String unStart, String unEnd, Integer stopType, String stopReason, Integer examineId, String startTime, String stopTime, String dtxsny, String ffbj, Integer isInsured, Integer unemployment, Integer comping, Integer changes, String remark, String batch, Integer state, String idCard,
                                       String name, Integer gender, String birthday, String address, String village, Integer isMove, Integer communityId, String house, Integer status, String villageTime, Integer villageAge, Integer cdState) {
        if (isInsured == null) {
            return new ApiResult(false, "请选择是否参保", -1);
        }
        if (unemployment == null) {
            return new ApiResult(false, "请选择失业状态", -1);
        }
        if (unemployment != null) {
            if (unemployment.intValue() == 1) {
                if (StringUtils.isBlank(unStart) || StringUtils.isBlank(unEnd)) {
                    return new ApiResult(false, "请选择领取失业金开始时间和截止时间", -1);
                }
            }
        }
        if (comping == null) {
            return new ApiResult(false, "请选择是否并轨", -1);
        }
        if (changes == null) {
            return new ApiResult(false, "请选择变动情况", -1);
        }
        if (gender == null) {
            return new ApiResult(false, "请选择性别", -1);
        }
        if (isMove == null) {
            return new ApiResult(false, "请选择是否迁出", -1);
        }
        if (communityId == null) {
            return new ApiResult(false, "请选择现所属社区", -1);
        }
        if (status == null) {
            return new ApiResult(false, "请选择发放状态", -1);
        }
        if (status.intValue() == 3) {
            if (stopType == null) {
                return new ApiResult(false, "请选择暂停原因", -1);
            }
        }
        if (villageAge == null) {
            return new ApiResult(false, "请输入征地时年龄", -1);
        }
        if (cdState == null) {
            return new ApiResult(false, "请选择撤队时安置情况", -1);
        }
        if (StringUtils.isBlank(startTime)) {
            return new ApiResult(false, "请输入开始发放时间", -1);
        }
        if (StringUtils.isBlank(stopTime)) {
            return new ApiResult(false, "请输入停止发放时间", -1);
        }
        if (StringUtils.isBlank(dtxsny)) {
            return new ApiResult(false, "请输入动态享受年月", -1);
        }
        if (StringUtils.isBlank(ffbj)) {
            return new ApiResult(false, "请输入发放标准", -1);
        }
        if (StringUtils.isBlank(batch)) {
            return new ApiResult(false, "请输入新增批次", -1);
        }
        if (StringUtils.isBlank(name)) {
            return new ApiResult(false, "请输入姓名", -1);
        }
        if (StringUtils.isBlank(birthday)) {
            return new ApiResult(false, "请输入出生年月", -1);
        }
        if (StringUtils.isBlank(address)) {
            return new ApiResult(false, "请输入常住地址", -1);
        }
        if (StringUtils.isBlank(village)) {
            return new ApiResult(false, "请输入征地时所在村（组）", -1);
        }
        if (StringUtils.isBlank(house)) {
            return new ApiResult(false, "请输入现户籍所在地", -1);
        }
        if (StringUtils.isBlank(villageTime)) {
            return new ApiResult(false, "请输入征地时间", -1);
        }
        if (StringUtils.isBlank(idCard)) {
            return new ApiResult(false, "请输入身份证号", -1);
        }
        if (examineId == null) {
            return new ApiResult(false, "编辑的征地人员社会救济金ID为空", -1);
        }
        Examine examine = examineService.get(examineId);

        if (examine == null) {
            return new ApiResult(false, "编辑的征地人员社会救济金不存在", -1);
        }

        if (!examine.getIdCard().equals(idCard)) {
            Integer count = examineService.getByIdCard(idCard);
            if (count.intValue() != 0) {
                return new ApiResult(false, "身份证号已存在", -1);
            }
        }
        examine.setStartTime(DateUtil.getDateToString(startTime, "yyyy-MM-dd"));
        examine.setStopTime(DateUtil.getDateToString(stopTime, "yyyy-MM-dd"));
        examine.setDtxsny(dtxsny);
        examine.setFfbj(ffbj);
        examine.setPhone(phone);
        examine.setIsInsured(isInsured);
        examine.setUnemployment(unemployment);
        examine.setComping(comping);
        examine.setChanges(changes);
        examine.setRemark(remark);
        examine.setBatch(batch);
        examine.setState(5);
        examine.setIdCard(idCard);
        examine.setName(name);
        examine.setGender(gender);
        examine.setBirthday(DateUtil.getDateToString(birthday, "yyyy-MM-dd"));
        examine.setAddress(address);
        examine.setVillage(village);
        examine.setIsMove(isMove);
        examine.setCommunityId(communityId);
        examine.setHouse(house);
        examine.setStatus(status);
        examine.setVillageTime(DateUtil.getDateToString(villageTime, "yyyy-MM-dd"));
        examine.setVillageAge(villageAge);
        examine.setCdState(cdState);
        examine.setTime(new Date());
        examineService.update(examine);
        Roster roster = rosterService.getByExamineId(examineId);
        roster.setIdCard(idCard);
        roster.setName(name);
        roster.setGender(gender);
        roster.setBirthday(DateUtil.getDateToString(birthday, "yyyy-MM-dd"));
        roster.setAddress(address);
        roster.setVillage(village);
        roster.setIsMove(isMove);
        roster.setCommunityId(communityId);
        roster.setHouse(house);
        roster.setRemark(remark);
        roster.setStatus(status);
        roster.setTime(new Date());
        if (!StringUtils.isBlank(unEnd)) {
            examine.setUnEnd(DateUtil.getDateToString(unEnd, "yyyy-MM-dd"));
        }
        if (!StringUtils.isBlank(unStart)) {
            examine.setUnEnd(DateUtil.getDateToString(unStart, "yyyy-MM-dd"));
        }
        if (stopType != null) {
            examine.setStopType(stopType);
        }
        if (!StringUtils.isBlank(stopReason)) {
            examine.setStopReason(stopReason);
        }
        rosterService.update(roster);
        return new ApiResult(true, "编辑成功", 0);
    }

    /**
     * 根据ID查询征地人员社会救济金
     *
     * @param examineId
     * @return
     */
    @RequestMapping(value = "getExamineById")
    @ResponseBody
    public ApiResult getExamineById(Integer examineId) {
        if (examineId == null) {
            return new ApiResult(false, "编辑的征地人员社会救济金ID为空", -1);
        }
        Examine examine = examineService.get(examineId);

        if (examine == null) {
            return new ApiResult(false, "编辑的征地人员社会救济金不存在", -1);
        }

        return new ApiResult(true, "查询成功", 0, examine);
    }

    /**
     * 查询到龄进入预警
     *
     * @param house        现户籍所在地
     * @param name         姓名
     * @param idCard       身份证号码
     * @param comping      是否并轨 1：是 2：否
     * @param age          年龄
     * @param changes      变动情况 1：迁出  2：新增  3：死亡
     * @param status       发放状态 1：未开始 2：发放中 3：已暂停 4：已退出
     * @param unemployment 失业状态 1：领取失业金 2：未领取失业金
     * @param isInsured    是否参保 1：是 2：否
     * @param communityId  现所属社区ID
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "getExamineWillStart")
    @ResponseBody
    public ApiResult getExamineWillStart(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {

        List<Examine> list = examineService.getExamineWillStart(house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, pageSize, pageNum);

        Integer count = examineService.getExamineWillStartCount(house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId);

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        map.put("count", count);

        return new ApiResult(true, "查询成功", 0, map);

    }

    /**
     * 查询到龄退出预警
     *
     * @param house        现户籍所在地
     * @param name         姓名
     * @param idCard       身份证号码
     * @param comping      是否并轨 1：是 2：否
     * @param age          年龄
     * @param changes      变动情况 1：迁出  2：新增  3：死亡
     * @param status       发放状态 1：未开始 2：发放中 3：已暂停 4：已退出
     * @param unemployment 失业状态 1：领取失业金 2：未领取失业金
     * @param isInsured    是否参保 1：是 2：否
     * @param communityId  现所属社区ID
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "getExamineWillStop")
    @ResponseBody
    public ApiResult getExamineWillStop(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        List<Examine> list = examineService.getExamineWillStop(house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, pageSize, pageNum);

        Integer count = examineService.getExamineWillStopCount(house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId);

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        map.put("count", count);

        return new ApiResult(true, "查询成功", 0, map);
    }

    /**
     * 征地人员社会救济金审核操作
     *
     * @param loginId   当前登录ID
     * @param examineId 征地人员社会救济金ID
     * @param state     状态：1.审核通过  2.审核不通过  3、待定 4、待复审 5、未审核
     * @return
     */
    @RequestMapping(value = "examineSH")
    @ResponseBody
    public ApiResult examineSH(Integer loginId, Integer examineId, Integer state) {

        if (loginId == null) {
            return new ApiResult(false, "登录用户ID为空", -1);
        }
        if (examineId == null) {
            return new ApiResult(false, "征地人员社会救济金ID为空", -1);
        }
        if (state == null) {
            return new ApiResult(false, "状态为空", -1);
        }
        User user = userService.get(loginId);
        if (user == null) {
            return new ApiResult(false, "登录用户不存在", -1);
        }
        Examine examine = examineService.get(examineId);
        if (examine == null) {
            return new ApiResult(false, "征地人员社会救济金不存在", -1);
        }
        examine.setState(state);
        if (user.getType().intValue() == 2) {
            if (state.intValue() == 1) {
                examine.setState(4);
            }
        }
        examineService.update(examine);
        return new ApiResult(true, "审核成功", 0);
    }
}
