package com.lemi.msloan.controller;

import com.lemi.msloan.entity.Community;
import com.lemi.msloan.entity.Examine;
import com.lemi.msloan.entity.Roster;
import com.lemi.msloan.entity.User;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.*;
import com.lemi.msloan.util.DateUtil;
import com.lemi.msloan.util.FileUtil;
import com.lemi.msloan.util.PoiTest;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.lang.reflect.Type;
import java.util.*;

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

    @Autowired
    private CommunityService communityService;

    @Autowired
    private RespectService respectService;

    @Autowired
    private ExamineStatisticService examineStatisticService;

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
    public ApiResult findAllExamine(Integer loginId, Integer state, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {

        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }

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
     * @param villageAge   征地时年龄
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
     * @param villageAge   征地时年龄
     * @param cdState      撤队时安置情况  1.未撤队先安置  2.撤队时安置 3.领取征地待业  4.领取一次性补偿金
     * @return
     */
    @RequestMapping(value = "updateExamineById")
    @ResponseBody
    public ApiResult updateExamineById(Integer loginId, String phone, String unStart, String unEnd, Integer stopType, String stopReason, Integer examineId, String startTime, String stopTime, String dtxsny, String ffbj, Integer isInsured, Integer unemployment, Integer comping, Integer changes, String remark, String batch, Integer state, String idCard,
                                       String name, Integer gender, String birthday, String address, String village, Integer isMove, Integer communityId, String house, Integer status, String villageTime, Integer villageAge, Integer cdState) {
        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }
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
        if (examine.getCommunityId() != null) {
            Community community = communityService.get(examine.getCommunityId());
            examine.setCommunityName(community.getName());
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
    public ApiResult getExamineWillStart(Integer loginId, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {

        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }

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
    public ApiResult getExamineWillStop(Integer loginId, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }
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
    public ApiResult examineSH(Integer loginId, Integer examineId, Integer state, Integer isInsured, Integer unemployment, String unStart, String unEnd, Integer comping, Integer changes, Integer status, Integer stopType, String stopReason) {

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
        if (!StringUtils.isBlank(stopReason)) {
            examine.setStopReason(stopReason);
        }
        if (stopType != null) {
            examine.setStopType(stopType);
        }
        if (status != null) {
            examine.setStatus(status);
            Roster roster = rosterService.getByExamineId(examineId);
            roster.setStatus(status);
            rosterService.update(roster);
        }
        if (changes != null) {
            examine.setChanges(changes);
        }
        if (comping != null) {
            examine.setComping(comping);
        }
        if (isInsured != null) {
            examine.setIsInsured(isInsured);
        }
        if (unemployment != null) {
            examine.setUnemployment(unemployment);
        }
        if (!StringUtils.isBlank(unStart)) {
            examine.setUnStart(DateUtil.getDateToString(unStart, "yyyy-MM-dd"));
        }
        if (!StringUtils.isBlank(unEnd)) {
            examine.setUnEnd(DateUtil.getDateToString(unEnd, "yyyy-MM-dd"));
        }
        examineService.update(examine);
        return new ApiResult(true, "审核成功", 0);
    }

    /**
     * 导入
     *
     * @param loginId
     * @param file
     * @param session
     * @return
     */
    @RequestMapping(value = "importExamine", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String importExamine(Integer loginId, @RequestParam(value = "file", required = false) CommonsMultipartFile file, HttpSession
            session) {

        try {
            if (file == null) {
                return "请上传文件";
            }
            String fileOriginalFilename = UUID.randomUUID() + file.getOriginalFilename().replace(",", "");
            String rootPath = session.getServletContext().getRealPath("/");
            String uploadPath = rootPath + "temporary/order/";
            File source = new File(uploadPath + fileOriginalFilename);
            file.transferTo(source);
            if (!FileUtil.checkExcelVaild(source)) {
                return "文件格式不正确";
            }
            List<Examine> examines = new ArrayList<Examine>();
            Workbook wb = null;
            Sheet sheet = null;
            Row row = null;
            String cellData = null;
            String cloumns[] = {"身份证号", "姓名", "性别", "出生年月", "常住地址", "征地时所在村组", "是否迁出",
                    "现所属社区", "现户籍所在地", "发放状态", "备注信息", "开始发放时间", "停止发放时间", "动态享受年月",
                    "发放标准", "是否参保", "失业状态", "领取失业金开始时间", "领取失业金截止时间", "是否并轨", "变动情况", "新增批次",
                    "审核状态", "暂停发放原因", "暂停发放备注", "征地时间", "征地时年龄", "撤队时安置情况", "联系电话"};
            wb = PoiTest.readExcel(source.getPath());
            if (wb != null) {
                sheet = wb.getSheetAt(0);
                int rownum = sheet.getPhysicalNumberOfRows();
                for (int i = 2; i < rownum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        Examine temp = new Examine();
                        for (int j = 0; j < 29; j++) {
                            cellData = (String) PoiTest.getCellFormatValue(row.getCell(j));
                            if ("身份证号".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setIdCard(cellData.trim());
                                } else {
                                    return i + "行" + (j + 1) + "列数据，身份证号不能为空。";
                                }
                            }
                            if ("姓名".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setName(cellData.trim());
                                } else {
                                    return i + "行" + (j + 1) + "列数据，姓名不能为空。";
                                }
                            }
                            if ("性别".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    if (cellData.trim().equals("男")) {
                                        temp.setGender(1);
                                    } else if (cellData.trim().equals("女")) {
                                        temp.setGender(2);
                                    }
                                }
                            }
                            if ("出生年月".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    Date birthday = DateUtil.getDateToString(cellData.trim(), "yyyy-MM-dd");
                                    temp.setBirthday(birthday);
                                }
                            }
                            if ("常住地址".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setAddress(cellData.trim());
                                }
                            }
                            if ("征地时所在村组".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setVillage(cellData.trim());
                                }
                            }
                            if ("是否迁出".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    if (cellData.trim().equals("是")) {
                                        temp.setIsMove(1);
                                    } else if (cellData.trim().equals("否")) {
                                        temp.setIsMove(1);
                                    }
                                }
                            }
                            if ("现所属社区".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    if (loginId != null) {
                                        User user = userService.get(loginId);
                                        if (user != null) {
                                            if (user.getType().intValue() == 2) {
                                                Community community = communityService.selectByUserId(loginId);
                                                if (community != null) {
                                                    temp.setCommunityId(community.getId());
                                                }
                                            } else {
                                                String communityName = cellData.trim();
                                                Community community = communityService.getByName(communityName);
                                                if (community != null) {
                                                    temp.setCommunityId(community.getId());
                                                }
                                            }
                                        } else {
                                            String communityName = cellData.trim();
                                            Community community = communityService.getByName(communityName);
                                            if (community != null) {
                                                temp.setCommunityId(community.getId());
                                            }
                                        }
                                    } else {
                                        String communityName = cellData.trim();
                                        Community community = communityService.getByName(communityName);
                                        if (community != null) {
                                            temp.setCommunityId(community.getId());
                                        }
                                    }
                                }
                            }
                            if ("现户籍所在地".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setHouse(cellData.trim());
                                }
                            }
                            if ("发放状态".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    if (cellData.trim().equals("未开始")) {
                                        temp.setStatus(1);
                                    } else if (cellData.trim().equals("发放中")) {
                                        temp.setStatus(2);
                                    } else if (cellData.trim().equals("已暂停")) {
                                        temp.setStatus(3);
                                    } else if (cellData.trim().equals("已退出")) {
                                        temp.setStatus(4);
                                    }
                                }
                            }
                            if ("备注信息".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setRemark(cellData.trim());
                                }
                            }
                            if ("开始发放时间".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    Date startTime = DateUtil.getDateToString(cellData.trim()+"-01", "yyyy-MM-dd");
                                    temp.setStartTime(startTime);
                                }
                            }
                            if ("停止发放时间".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    Date stopTime = DateUtil.getDateToString(cellData.trim()+"-01", "yyyy-MM-dd");
                                    temp.setStopTime(stopTime);
                                }
                            }
                            if ("动态享受年月".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setDtxsny(cellData.trim());
                                }
                            }
                            if ("发放标准".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setFfbj(cellData.trim());
                                }
                            }
                            if ("是否参保".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    if (cellData.trim().equals("是")) {
                                        temp.setIsInsured(1);
                                    } else if (cellData.trim().equals("否")) {
                                        temp.setIsInsured(2);
                                    }
                                }
                            }
                            if ("失业状态".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    if (cellData.trim().equals("领取失业金")) {
                                        temp.setUnemployment(1);
                                    } else if (cellData.trim().equals("未领取失业金")) {
                                        temp.setUnemployment(2);
                                    }
                                }
                            }
                            if ("领取失业金开始时间".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    Date unStart = DateUtil.getDateToString(cellData.trim()+"-01", "yyyy-MM-dd");
                                    temp.setUnStart(unStart);
                                }
                            }
                            if ("领取失业金截止时间".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    Date unEnd = DateUtil.getDateToString(cellData.trim()+"-01", "yyyy-MM-dd");
                                    temp.setUnEnd(unEnd);
                                }
                            }
                            if ("是否并轨".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    if (cellData.trim().equals("是")) {
                                        temp.setComping(1);
                                    } else if (cellData.trim().equals("否")) {
                                        temp.setComping(2);
                                    }
                                }
                            }
                            if ("变动情况".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    if (cellData.trim().equals("迁出")) {
                                        temp.setChanges(1);
                                    } else if (cellData.trim().equals("新增")) {
                                        temp.setChanges(2);
                                    } else if (cellData.trim().equals("死亡")) {
                                        temp.setChanges(3);
                                    }
                                }
                            }
                            if ("新增批次".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setBatch(cellData.trim());
                                }
                            }
                            if ("审核状态".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    if (cellData.trim().equals("审核通过")) {
                                        temp.setState(1);
                                    } else if (cellData.trim().equals("审核不通过")) {
                                        temp.setState(2);
                                    } else if (cellData.trim().equals("待定")) {
                                        temp.setState(3);
                                    } else if (cellData.trim().equals("待复审")) {
                                        temp.setState(4);
                                    } else if (cellData.trim().equals("未审核")) {
                                        temp.setState(5);
                                    }
                                }
                            }
                            if ("暂停发放原因".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    if (cellData.trim().equals("就业")) {
                                        temp.setStopType(1);
                                    } else if (cellData.trim().equals("退休")) {
                                        temp.setStopType(2);
                                    } else if (cellData.trim().equals("其他")) {
                                        temp.setStopType(3);
                                    }
                                }
                            }
                            if ("暂停发放备注".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setStopReason(cellData.trim());
                                }
                            }
                            if ("征地时间".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    Date villageTime = DateUtil.getDateToString(cellData.trim(), "yyyy-MM-dd");
                                    temp.setVillageTime(villageTime);
                                }
                            }
                            if ("征地时年龄".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setVillageAge(Integer.parseInt(cellData.trim()));
                                }
                            }
                            if ("撤队时安置情况".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    if (cellData.trim().equals("未撤队先安置")) {
                                        temp.setCdState(1);
                                    } else if (cellData.trim().equals("撤队时安置")) {
                                        temp.setCdState(2);
                                    } else if (cellData.trim().equals("领取征地待业")) {
                                        temp.setCdState(3);
                                    } else if (cellData.trim().equals("领取一次性补偿金")) {
                                        temp.setCdState(4);
                                    }
                                }
                            }
                            if ("联系电话".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setPhone(cellData.trim());
                                }
                            }
                        }
                        examines.add(temp);
                    } else {
                        return "文件格式有误";
                    }
                }
            }
            Integer insertCount = 0;
            Integer updateCount = 0;
            for (Examine item : examines) {
                Examine examine = examineService.getBeanByIdCard(item.getIdCard());
                if (examine == null) {
                    item.setTime(new Date());
                    examineService.save(item);
                    Integer examineId = examine.getId();
                    Roster roster = new Roster();
                    roster.setIdCard(item.getIdCard());
                    roster.setName(item.getName());
                    roster.setGender(item.getGender());
                    roster.setBirthday(item.getBirthday());
                    roster.setAddress(item.getAddress());
                    roster.setVillage(item.getVillage());
                    roster.setIsMove(item.getIsMove());
                    roster.setCommunityId(item.getCommunityId());
                    roster.setHouse(item.getHouse());
                    roster.setRemark(item.getRemark());
                    roster.setStatus(item.getStatus());
                    roster.setTime(new Date());
                    roster.setExamineId(examineId);
                    rosterService.save(roster);
                    insertCount++;
                } else {
                    examine.setStartTime(item.getStartTime());
                    examine.setStopTime(item.getStopTime());
                    examine.setDtxsny(item.getDtxsny());
                    examine.setPhone(item.getPhone());
                    examine.setFfbj(item.getFfbj());
                    examine.setIsInsured(item.getIsInsured());
                    examine.setUnemployment(item.getUnemployment());
                    examine.setComping(item.getComping());
                    examine.setChanges(item.getChanges());
                    examine.setRemark(item.getRemark());
                    examine.setBatch(item.getBatch());
                    examine.setState(item.getState());
                    examine.setIdCard(item.getIdCard());
                    examine.setName(item.getName());
                    examine.setGender(item.getGender());
                    examine.setBirthday(item.getBirthday());
                    examine.setAddress(item.getAddress());
                    examine.setVillage(item.getVillage());
                    examine.setIsMove(item.getIsMove());
                    examine.setCommunityId(item.getCommunityId());
                    examine.setHouse(item.getHouse());
                    examine.setStatus(item.getStatus());
                    examine.setVillageTime(item.getVillageTime());
                    examine.setVillageAge(item.getVillageAge());
                    examine.setCdState(item.getCdState());
                    if (item.getUnEnd() != null) {
                        examine.setUnEnd(item.getUnEnd());
                    }
                    if (item.getUnStart() != null) {
                        examine.setUnEnd(item.getUnStart());
                    }
                    if (item.getStopType() != null) {
                        examine.setStopType(item.getStopType());
                    }
                    if (!StringUtils.isBlank(item.getStopReason())) {
                        examine.setStopReason(item.getStopReason());
                    }
                    examineService.update(examine);
                    Roster roster = rosterService.getByIdCard(item.getIdCard());
                    roster.setIdCard(item.getIdCard());
                    roster.setName(item.getName());
                    roster.setGender(item.getGender());
                    roster.setBirthday(item.getBirthday());
                    roster.setAddress(item.getAddress());
                    roster.setVillage(item.getVillage());
                    roster.setIsMove(item.getIsMove());
                    roster.setCommunityId(item.getCommunityId());
                    roster.setHouse(item.getHouse());
                    roster.setRemark(item.getRemark());
                    roster.setStatus(item.getStatus());
                    rosterService.update(roster);
                    updateCount++;
                }
            }
            return "共导入" + examines.size() + "条数据，新增" + insertCount + "条，更新" + updateCount + "条";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "操作失败";
    }


    /**
     * 征地人员已故名单
     *
     * @param examineId
     * @param loginId
     * @return
     */
    @RequestMapping(value = "deceasedPage")
    public ModelAndView deceasedPage(Integer examineId, Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/examine/deceased_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    /**
     * 进入审核
     *
     * @param examineId
     * @param startTime
     * @param stopTime
     * @param dtxsny
     * @param ffbj
     * @param batch
     * @param isInsured
     * @param unemployment
     * @param unStart
     * @param unEnd
     * @param comping
     * @param changes
     * @return
     */
    @RequestMapping(value = "startExamine")
    @ResponseBody
    public ApiResult startExamine(Integer loginId, Integer examineId, String startTime, String stopTime, String dtxsny, String ffbj, String batch, Integer isInsured, Integer unemployment, String unStart, String unEnd, Integer comping, Integer changes,String remark) {

        if (examineId != null) {
            Examine examine = examineService.get(examineId);
            if (examine != null) {
                if (!StringUtils.isBlank(startTime)) {
                    examine.setStartTime(DateUtil.getDateToString(startTime, "yyyy-MM-dd"));
                }
                if (!StringUtils.isBlank(stopTime)) {
                    examine.setStopTime(DateUtil.getDateToString(stopTime, "yyyy-MM-dd"));
                }
                if (!StringUtils.isBlank(dtxsny)) {
                    examine.setDtxsny(dtxsny);
                }
                if (!StringUtils.isBlank(ffbj)) {
                    examine.setFfbj(ffbj);
                }
                if (!StringUtils.isBlank(batch)) {
                    examine.setBatch(batch);
                }
                if (isInsured != null) {
                    examine.setIsInsured(isInsured);
                }
                if (unemployment != null) {
                    examine.setUnemployment(unemployment);
                }
                if (!StringUtils.isBlank(unStart)) {
                    examine.setUnStart(DateUtil.getDateToString(unStart, "yyyy-MM-dd"));
                }
                if (!StringUtils.isBlank(unEnd)) {
                    examine.setUnEnd(DateUtil.getDateToString(unEnd, "yyyy-MM-dd"));
                }
                if (comping != null) {
                    examine.setComping(comping);
                }
                if (changes != null) {
                    examine.setChanges(changes);
                }
                if (loginId != null) {
                    User user = userService.get(loginId);
                    if (user.getType() == 2) {
                        examine.setStatus(5);
                        if (!StringUtils.isBlank(remark)){
                            examine.setRemark1(remark);
                        }
                        examine.setTime1(new Date());
                        examineService.update(examine);
                    } else {
                        if (!StringUtils.isBlank(remark)){
                            examine.setRemark3(remark);
                        }
                        examine.setTime3(new Date());
                        examine.setStatus(2);
                        examineService.update(examine);
                        Roster roster = rosterService.getByExamineId(examineId);
                        if (roster != null) {
                            roster.setStatus(2);
                            rosterService.update(roster);
                        }
                    }
                }

                return new ApiResult(true, "进入成功", 0);
            } else {
                return new ApiResult(false, "不存在", -1);
            }
        } else {
            return new ApiResult(false, "ID为空", -1);
        }

    }

    /**
     * 退出
     *
     * @param examineId
     * @return
     */
    @RequestMapping(value = "endExamine")
    @ResponseBody
    public ApiResult endExamine(Integer examineId, Integer loginId,String remark) {

        if (examineId == null) {
            return new ApiResult(false, "ID为空", -1);
        }
        Examine examine = examineService.get(examineId);
        if (examine == null) {
            return new ApiResult(false, "不存在", -1);
        }
        if (loginId != null) {
            User user = userService.get(loginId);
            if (user.getType() == 2) {
                examine.setStatus(6);
                if (!StringUtils.isBlank(remark)){
                    examine.setRemark2(remark);
                }
                examine.setTime2(new Date());
                examineService.update(examine);
            } else {
                if (!StringUtils.isBlank(remark)){
                    examine.setRemark4(remark);
                }
                examine.setTime4(new Date());
                examine.setStatus(4);
                examineService.update(examine);
                Roster roster = rosterService.getByExamineId(examineId);
                if (roster != null) {
                    roster.setStatus(4);
                    rosterService.update(roster);
                }
            }
        }

        return new ApiResult(true, "退出成功", 0);
    }

    /**
     * 待定
     *
     * @param examineId
     * @return
     */
    @RequestMapping(value = "toDaiDing")
    @ResponseBody
    public ApiResult toDaiDing(Integer examineId, Integer status, String nextTime) {
        if (examineId == null) {
            return new ApiResult(false, "ID为空", -1);
        }
        Examine examine = examineService.get(examineId);
        if (examine == null) {
            return new ApiResult(false, "不存在", -1);
        }
        examine.setStatus(status);
        if (!StringUtils.isBlank(nextTime)){
            if (status.intValue() == 7){
                examine.setNextTime(DateUtil.getDateToString(nextTime,"yyyy-MM-dd"));
            }else if (status.intValue() == 8){
                examine.setNextOut(DateUtil.getDateToString(nextTime,"yyyy-MM-dd"));
            }

        }

        examineService.update(examine);
        return new ApiResult(true, "操作成功", 0);
    }

    /**
     * 查询待复审
     *
     * @param loginId
     * @param house
     * @param name
     * @param idCard
     * @param comping
     * @param age
     * @param changes
     * @param status
     * @param unemployment
     * @param isInsured
     * @param communityId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "findAgainExamine")
    @ResponseBody
    public ApiResult findAgainExamine(Integer loginId, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }

        List<Examine> list = examineService.findAgainExamine(house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, pageSize, pageNum);

        Integer count = examineService.findAgainExamineCount(house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId);

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        map.put("count", count);

        return new ApiResult(true, "查询成功", 0, map);
    }

    /**
     * 查询待定
     *
     * @param loginId
     * @param house
     * @param name
     * @param idCard
     * @param comping
     * @param age
     * @param changes
     * @param status
     * @param unemployment
     * @param isInsured
     * @param communityId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "findUndeterminedExamine")
    @ResponseBody
    public ApiResult findUndeterminedExamine(Integer loginId, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }

        List<Examine> list = examineService.findUndeterminedExamine(house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, pageSize, pageNum);

        Integer count = examineService.findUndeterminedExamineCount(house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId);

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        map.put("count", count);

        return new ApiResult(true, "查询成功", 0, map);
    }

    @RequestMapping(value = "getTotalCount")
    @ResponseBody
    public ApiResult getTotalCount(Integer loginId){
        Integer communityId = null;
        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }

        //进入预警的数量
        Integer startCount = examineService.getExamineWillStartCount(null, null, null, null, null, null, null, null, null, communityId);
        //退出预警的数量
        Integer endCount = examineService.getExamineWillStopCount(null, null, null, null, null, null, null, null, null, communityId);
        //征地待复审的数量
        Integer againExamineCount = examineService.findAgainExamineCount(null, null, null, null, null, null, null, null, null, communityId);
        //尊老金城市待审核的数量
        Integer respectTownCount = respectService.selectRemindRespectCount(communityId,1,1);
        //尊老金农村待审核的数量
        Integer respectCountryCount = respectService.selectRemindRespectCount(communityId,2,1);


        //长寿金待审核的数量
//        Integer respectLongCount = respectService.selectRemindRespectCount(communityId,3,1);

        Map<String,Integer> map = new HashMap();

        map.put("startCount",startCount);
        map.put("endCount",endCount);
        map.put("respectTownCount",respectTownCount);
        map.put("respectCountryCount",respectCountryCount);
        map.put("againExamineCount",againExamineCount);
        map.put("total",startCount+endCount+respectTownCount+respectCountryCount+againExamineCount);
        return new ApiResult(true, "查询成功", 0, map);
    }

    @RequestMapping(value = "getExamineStatistic")
    @ResponseBody
    public ApiResult getExamineStatistic(String beginTime,String endTime){

        return null;
    }

}


