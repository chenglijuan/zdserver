package com.lemi.msloan.controller;

import com.lemi.msloan.entity.*;
import com.lemi.msloan.request.ExamineRequest;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.response.CommunityInfoResponse;
import com.lemi.msloan.response.CommunityMoneyInfoResponse;
import com.lemi.msloan.response.CommunityMoneyItemResponse;
import com.lemi.msloan.service.*;
import com.lemi.msloan.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

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

    @RequestMapping(value = "fftjExamineList")
    public ModelAndView fftjExamineList(Integer examineId, Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/examine/fftj_examine_list");
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
    public ApiResult findAllExamine(Integer loginId, Integer gender, Integer state, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {

        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }

        List<Examine> list = examineService.findAllExamine(gender, state, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, pageSize, pageNum);

        Integer count = examineService.findAllExamineCount(gender, state, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId);

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        map.put("count", count);

        return new ApiResult(true, "查询成功", 0, map);
    }

    /**
     * 导出
     *
     * @param gender
     * @param state
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
     */
    @RequestMapping(value = "exportExamine")
    public void exportExamine(HttpServletResponse response, Integer gender, Integer state, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId) {
        List<Examine> list = examineService.findAllExamine(gender, state, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, null, null);
        try {
            List<List<String>> strsList = new ArrayList<List<String>>();
            List<String> title = new ArrayList<String>();
            title.add("姓名");
            title.add("性别");
            title.add("年龄");
            title.add("出生年月");
            title.add("身份证号");
            title.add("户籍所在地");
            title.add("现住地");
            title.add("征地时间");
            title.add("征地时年龄");
            title.add("征地时所在村（组）");
            title.add("撤队安置情况");
            title.add("发放标准");
            title.add("开始发放时间");
            title.add("停止发放时间");
            title.add("动态享受年月");
            title.add("新增批次");
            title.add("变动情况");
            title.add("参保状态");
            title.add("失业状态");
            title.add("是否并轨");
            title.add("发放状态");
            strsList.add(title);
            for (int i = 0, length = list.size(); i < length; i++) {
                List<String> strings = new ArrayList<>();
                Examine examine = list.get(i);

                //姓名
                strings.add(examine.getName());

                //性别
                if (examine.getGender() != null) {
                    if (examine.getGender().intValue() == 1) {
                        strings.add("男");
                    } else {
                        strings.add("女");
                    }
                } else {
                    strings.add("");
                }

                //年龄
                strings.add(examine.getAge() + "");

                //出生年月
                if (examine.getBirthday() != null) {
                    strings.add(DateUtil.formatDate(examine.getBirthday(), "yyyy-MM-dd"));
                } else {
                    strings.add("");
                }

                //身份证号
                if (!StringUtils.isBlank(examine.getIdCard())) {
                    strings.add(examine.getIdCard());
                } else {
                    strings.add("");
                }

                //户籍所在地
                if (!StringUtils.isBlank(examine.getHouse())) {
                    strings.add(examine.getHouse());
                } else {
                    strings.add("");
                }

                //现住地
                if (!StringUtils.isBlank(examine.getAddress())) {
                    strings.add(examine.getAddress());
                } else {
                    strings.add("");
                }

                //征地时间
                if (examine.getVillageTime() != null) {
                    strings.add(DateUtil.formatDate(examine.getVillageTime(), "yyyy-MM-dd"));
                } else {
                    strings.add("");
                }

                //征地时年龄
                if (examine.getVillageAge() != null) {
                    strings.add(examine.getVillageAge() + "");
                } else {
                    strings.add("");
                }

                //征地时所在村（组）
                if (!StringUtils.isBlank(examine.getVillage())) {
                    strings.add(examine.getVillage());
                } else {
                    strings.add("");
                }
                //撤队安置情况
                if (examine.getCdState() != null) {
                    if (examine.getCdState().intValue() == 1) {
                        strings.add("未撤队先安置");
                    } else if (examine.getCdState().intValue() == 2) {
                        strings.add("撤队时安置");
                    } else if (examine.getCdState().intValue() == 3) {
                        strings.add("领取征地待业");
                    } else if (examine.getCdState().intValue() == 4) {
                        strings.add("领取一次性补偿金");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                //发放标准
                if (!StringUtils.isBlank(examine.getFfbj())) {
                    strings.add(examine.getFfbj());
                } else {
                    strings.add("");
                }
                //开始发放时间
                if (examine.getStartTime() != null) {
                    strings.add(DateUtil.formatDate(examine.getStartTime(), "yyyy-MM-dd"));
                } else {
                    strings.add("");
                }
                //停止发放时间
                if (examine.getStopTime() != null) {
                    strings.add(DateUtil.formatDate(examine.getStopTime(), "yyyy-MM-dd"));
                } else {
                    strings.add("");
                }
                //动态享受年月
                if (!StringUtils.isBlank(examine.getDtxsny())) {
                    strings.add(examine.getDtxsny());
                } else {
                    strings.add("");
                }
                //新增批次
                if (!StringUtils.isBlank(examine.getBatch())) {
                    strings.add(examine.getBatch());
                } else {
                    strings.add("");
                }
                //变动情况
                if (examine.getChanges() != null) {
                    if (examine.getCdState().intValue() == 1) {
                        strings.add("迁出");
                    } else if (examine.getCdState().intValue() == 2) {
                        strings.add("新增");
                    } else if (examine.getCdState().intValue() == 3) {
                        strings.add("死亡");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                //参保状态
                if (examine.getIsInsured() != null) {
                    if (examine.getIsInsured().intValue() == 1) {
                        strings.add("已参保");
                    } else if (examine.getIsInsured().intValue() == 2) {
                        strings.add("未参保");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                //失业状态
                if (examine.getUnemployment() != null) {
                    if (examine.getUnemployment().intValue() == 1) {
                        strings.add("领取失业金");
                    } else if (examine.getUnemployment().intValue() == 2) {
                        strings.add("未领取失业金");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                //是否并轨
                if (examine.getComping() != null) {
                    if (examine.getComping().intValue() == 1) {
                        strings.add("是");
                    } else if (examine.getComping().intValue() == 2) {
                        strings.add("否");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                //发放状态
                if (examine.getStatus() != null) {
                    if (examine.getStatus().intValue() == 1) {
                        strings.add("未开始");
                    } else if (examine.getStatus().intValue() == 2) {
                        strings.add("发放中");
                    } else if (examine.getStatus().intValue() == 3) {
                        strings.add("已暂停");
                    } else if (examine.getStatus().intValue() == 4) {
                        strings.add("已退出");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                strsList.add(strings);
            }
            String str = "征地人员社会救济金导出";
            String fileName = str + DateUtil.getDateString("yyyy-MM-dd", new Date());
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//            response.setHeader("Content-Disposition", "attachment;filename="
//                    + new String((fileName + ".xls").getBytes(), "iso-8859-1"));

            response.addHeader("Content-Disposition", "attachment;filename=" +
                    new String(fileName.getBytes("utf-8"), "iso-8859-1") + ".xls");

            ServletOutputStream out = response.getOutputStream();
            OutputStream os = out;
            String excelTitle = "征地人员社会救济金人员名单";

            POIExcelUtil.writerDataInExcelIo(strsList, os, excelTitle, title.size());
        } catch (Exception e) {
            //e.printStackTrace();
        }
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
                                String name, Integer gender, String birthday, String address, String village, Integer isMove, Integer communityId, String house, Integer status, String villageTime, Integer villageAge, Integer cdState, Integer exitType) {
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
        if (status.intValue() == 4) {
            examine.setExitType(exitType);
            examine.setExitTime(new Date());
        }
        examine.setIdCard(idCard);
        examine.setName(name);
        examine.setGender(gender);
        examine.setBirthday(DateUtil.getDateToString(birthday, "yyyy-MM-dd"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //添加开始发放时间和结束发放时间
        if (examine.getBirthday() != null && examine.getGender() != null) {
            // 如果是男  50岁开始发放
            if (examine.getGender().intValue() == 1) {
                examine.setStartTime(AgeUtils.getTimeByBirth(50, sdf.format(examine.getBirthday())));
                examine.setStopTime(AgeUtils.getTimeByBirth(70, sdf.format(examine.getBirthday())));
            } else {
                examine.setStartTime(AgeUtils.getTimeByBirth(40, sdf.format(examine.getBirthday())));
                examine.setStopTime(AgeUtils.getTimeByBirth(70, sdf.format(examine.getBirthday())));
                //如果是女的  40岁开始发放
            }
        }

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
                                       String name, Integer gender, String birthday, String address, String village, Integer isMove, Integer communityId, String house, Integer status, String villageTime, Integer villageAge, Integer cdState, Integer exitType) {
        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }
//        if (isInsured == null) {
//            return new ApiResult(false, "请选择是否参保", -1);
//        }
//        if (unemployment == null) {
//            return new ApiResult(false, "请选择失业状态", -1);
//        }
//        if (unemployment != null) {
//            if (unemployment.intValue() == 1) {
//                if (StringUtils.isBlank(unStart) || StringUtils.isBlank(unEnd)) {
//                    return new ApiResult(false, "请选择领取失业金开始时间和截止时间", -1);
//                }
//            }
//        }
//        if (comping == null) {
//            return new ApiResult(false, "请选择是否并轨", -1);
//        }
//        if (changes == null) {
//            return new ApiResult(false, "请选择变动情况", -1);
//        }
//        if (gender == null) {
//            return new ApiResult(false, "请选择性别", -1);
//        }
//        if (isMove == null) {
//            return new ApiResult(false, "请选择是否迁出", -1);
//        }
//        if (communityId == null) {
//            return new ApiResult(false, "请选择现所属社区", -1);
//        }
//        if (status == null) {
//            return new ApiResult(false, "请选择发放状态", -1);
//        }
//        if (status.intValue() == 3) {
//            if (stopType == null) {
//                return new ApiResult(false, "请选择暂停原因", -1);
//            }
//        }
//        if (villageAge == null) {
//            return new ApiResult(false, "请输入征地时年龄", -1);
//        }
//        if (cdState == null) {
//            return new ApiResult(false, "请选择撤队时安置情况", -1);
//        }
//        if (StringUtils.isBlank(startTime)) {
//            return new ApiResult(false, "请输入开始发放时间", -1);
//        }
//        if (StringUtils.isBlank(stopTime)) {
//            return new ApiResult(false, "请输入停止发放时间", -1);
//        }
//        if (StringUtils.isBlank(dtxsny)) {
//            return new ApiResult(false, "请输入动态享受年月", -1);
//        }
//        if (StringUtils.isBlank(ffbj)) {
//            return new ApiResult(false, "请输入发放标准", -1);
//        }
//        if (StringUtils.isBlank(name)) {
//            return new ApiResult(false, "请输入姓名", -1);
//        }
//        if (StringUtils.isBlank(birthday)) {
//            return new ApiResult(false, "请输入出生年月", -1);
//        }
//        if (StringUtils.isBlank(address)) {
//            return new ApiResult(false, "请输入常住地址", -1);
//        }
//        if (StringUtils.isBlank(village)) {
//            return new ApiResult(false, "请输入征地时所在村（组）", -1);
//        }
//        if (StringUtils.isBlank(house)) {
//            return new ApiResult(false, "请输入现户籍所在地", -1);
//        }
//        if (StringUtils.isBlank(villageTime)) {
//            return new ApiResult(false, "请输入征地时间", -1);
//        }
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
//        if (status.intValue() == 4) {
//            if (exitType == null) {
//                return new ApiResult(false, "请选择退出类型", -1);
//            }
//        }
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //添加开始发放时间和结束发放时间
        if (examine.getBirthday() != null && examine.getGender() != null) {
            // 如果是男  50岁开始发放
            if (examine.getGender().intValue() == 1) {
                examine.setStartTime(AgeUtils.getTimeByBirth(50, sdf.format(examine.getBirthday())));
                examine.setStopTime(AgeUtils.getTimeByBirth(70, sdf.format(examine.getBirthday())));
            } else {
                examine.setStartTime(AgeUtils.getTimeByBirth(40, sdf.format(examine.getBirthday())));
                examine.setStopTime(AgeUtils.getTimeByBirth(70, sdf.format(examine.getBirthday())));
                //如果是女的  40岁开始发放
            }
        }
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
        if (status.intValue() == 4) {
            examine.setExitType(exitType);
            examine.setExitTime(new Date());
        }
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
    public ApiResult getExamineWillStart(Integer gender, Integer loginId, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {

        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }

        List<Examine> list = examineService.getExamineWillStart(gender, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, pageSize, pageNum);

        Integer count = examineService.getExamineWillStartCount(gender, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId);

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
    public ApiResult getExamineWillStop(Integer gender, Integer loginId, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }
        List<Examine> list = examineService.getExamineWillStop(gender, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, pageSize, pageNum);

        Integer count = examineService.getExamineWillStopCount(gender, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId);

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

        if (file == null) {
            return "请上传文件";
        }
        String fileOriginalFilename = UUID.randomUUID() + file.getOriginalFilename().replace(",", "");
        String rootPath = session.getServletContext().getRealPath("/");
        String uploadPath = rootPath + "temporary/order/";
        File source = new File(uploadPath + fileOriginalFilename);
        try {
            file.transferTo(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (!FileUtil.checkExcelVaild(source)) {
                return "文件格式不正确";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Community> communityList = communityService.findAll();
        final List<Examine> examines = new ArrayList<Examine>();
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
                            }
                        }
                        if ("姓名".equals(cloumns[j])) {
                            if (!StringUtils.isBlank(cellData.trim())) {
                                temp.setName(cellData.trim());
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
                                    String communityName = cellData.trim();
                                    Community community = sss(communityList, communityName);
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
                                Date startTime = DateUtil.getDateToString(cellData.trim() + "-01", "yyyy-MM-dd");
                                temp.setStartTime(startTime);
                            }
                        }
                        if ("停止发放时间".equals(cloumns[j])) {
                            if (!StringUtils.isBlank(cellData.trim())) {
                                Date stopTime = DateUtil.getDateToString(cellData.trim() + "-01", "yyyy-MM-dd");
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
                                Date unStart = DateUtil.getDateToString(cellData.trim() + "-01", "yyyy-MM-dd");
                                temp.setUnStart(unStart);
                            }
                        }
                        if ("领取失业金截止时间".equals(cloumns[j])) {
                            if (!StringUtils.isBlank(cellData.trim())) {
                                Date unEnd = DateUtil.getDateToString(cellData.trim() + "-01", "yyyy-MM-dd");
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
                }
            }
            int total = 0;
            for (int i = 0; i < examines.size(); i++) {
                if (!StringUtils.isBlank(examines.get(i).getIdCard()) && !StringUtils.isBlank(examines.get(i).getName())) {
                    total++;
                } else {

                }
            }
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    for (Examine item : examines) {

                        //添加开始发放时间和结束发放时间
                        if (item.getBirthday() != null) {
                            // 如果是男  50岁开始发放
                            if (item.getGender().intValue() == 1) {
                                item.setStartTime(AgeUtils.getTimeByBirth(50, sdf.format(item.getBirthday())));
                                item.setStopTime(AgeUtils.getTimeByBirth(70, sdf.format(item.getBirthday())));
                            } else {
                                item.setStartTime(AgeUtils.getTimeByBirth(40, sdf.format(item.getBirthday())));
                                item.setStopTime(AgeUtils.getTimeByBirth(70, sdf.format(item.getBirthday())));
                                //如果是女的  40岁开始发放
                            }
                        }

                        if (!StringUtils.isBlank(item.getIdCard()) && !StringUtils.isBlank(item.getName())) {
                            Examine examine = examineService.getBeanByIdCard(item.getIdCard());
                            if (examine == null) {
                                item.setTime(new Date());
                                examineService.save(item);
                                Integer examineId = item.getId();
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

                                //设置开始发放和结束发放时间
                                examine.setStartTime(item.getStartTime());
                                examine.setStopTime(item.getStopTime());

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
                                if (roster != null) {
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
                                } else {
                                    roster = new Roster();
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
                                    rosterService.save(roster);
                                }

                            }
                        }
                    }
                }
            });
            return "数据正在导入";
        }

        return "导入失败";
    }

    private Community sss(List<Community> communityList, String name) {
        Community c = null;
        for (Community community : communityList) {
            if (community.getName().equals(name.trim())) {

                c = community;
                break;
            }
        }
        return c;
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
    public ApiResult startExamine(Integer loginId, Integer examineId, String startTime, String stopTime, String dtxsny, String ffbj, String batch, Integer isInsured, Integer unemployment, String unStart, String unEnd, Integer comping, Integer changes, String remark) {

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
                        if (!StringUtils.isBlank(remark)) {
                            examine.setRemark1(remark);
                        }
                        examine.setTime1(new Date());
                        examineService.update(examine);
                    } else {
                        if (!StringUtils.isBlank(remark)) {
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
    public ApiResult endExamine(Integer examineId, Integer loginId, String remark, Integer exitType) {

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
                if (!StringUtils.isBlank(remark)) {
                    examine.setRemark2(remark);
                }
                examine.setTime2(new Date());
                examine.setExitType(exitType);
                examineService.update(examine);
            } else {
                if (!StringUtils.isBlank(remark)) {
                    examine.setRemark4(remark);
                }
                examine.setTime4(new Date());
                examine.setStatus(4);
                examine.setExitTime(new Date());
                examine.setExitType(exitType);
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
        if (!StringUtils.isBlank(nextTime)) {
            if (status.intValue() == 7) {
                examine.setNextTime(DateUtil.getDateToString(nextTime, "yyyy-MM-dd"));
            } else if (status.intValue() == 8) {
                examine.setNextOut(DateUtil.getDateToString(nextTime, "yyyy-MM-dd"));
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
    public ApiResult findAgainExamine(Integer gender, Integer loginId, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }

        List<Examine> list = examineService.findAgainExamine(gender, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, pageSize, pageNum);

        Integer count = examineService.findAgainExamineCount(gender, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId);

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
    public ApiResult findUndeterminedExamine(Integer gender, Integer loginId, String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {
        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }

        List<Examine> list = examineService.findUndeterminedExamine(gender, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, pageSize, pageNum);

        Integer count = examineService.findUndeterminedExamineCount(gender, house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId);

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        map.put("count", count);

        return new ApiResult(true, "查询成功", 0, map);
    }

    /**
     * 查询待处理数量
     *
     * @param loginId
     * @return
     */
    @RequestMapping(value = "getTotalCount")
    @ResponseBody
    public ApiResult getTotalCount(Integer loginId) {
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
        Integer startCount = examineService.getExamineWillStartCount(null, null, null, null, null, null, null, null, null, null, communityId);
        //退出预警的数量
        Integer endCount = examineService.getExamineWillStopCount(null, null, null, null, null, null, null, null, null, null, communityId);
        //征地待复审的数量
        Integer againExamineCount = examineService.findAgainExamineCount(null, null, null, null, null, null, null, null, null, null, communityId);
        //尊老金城市待审核的数量
        Integer respectTownCount = respectService.selectRemindRespectCount(communityId, 1, 1);
        //尊老金农村待审核的数量
        Integer respectCountryCount = respectService.selectRemindRespectCount(communityId, 2, 1);


        //长寿金待审核的数量
//        Integer respectLongCount = respectService.selectRemindRespectCount(communityId,3,1);

        Map<String, Integer> map = new HashMap();

        map.put("startCount", startCount);
        map.put("endCount", endCount);
        map.put("respectTownCount", respectTownCount);
        map.put("respectCountryCount", respectCountryCount);
        map.put("againExamineCount", againExamineCount);
        map.put("total", startCount + endCount + respectTownCount + respectCountryCount + againExamineCount);
        return new ApiResult(true, "查询成功", 0, map);
    }

    @RequestMapping(value = "getExamineStatistic")
    @ResponseBody
    public ApiResult getExamineStatistic(String beginTime, String endTime) {


        return null;
    }


    /**
     * 增减明细查询
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "getCommunityInfo")
    @ResponseBody
    public ApiResult getCommunityInfo(String beginTime, String endTime) {
        List<Community> communities = communityService.findAll();

        Map<String, Object> map = new HashMap<>();

        //新增人员
        int added_total = 0;
        //就业退出
        int job_total = 0;
        //并轨退出
        int comping_total = 0;
        //5560退出
        int _total = 0;
        //退休退出
        int retire_total = 0;
        //死亡退出
        int death_total = 0;
        //其它退出
        int other_total = 0;

        Integer exitType = null;

        List<CommunityInfoResponse> list = new ArrayList<>();

        for (Community community : communities) {
            Integer communityId = community.getId();
            //社区时间段内新增的人数
            Integer added_count = examineService.getAddedCountByCommunityId(communityId, beginTime, endTime);
            added_total += added_count;
            //社区时间段内就业退出的人数
            exitType = 1;
            Integer job_count = examineService.getExitCountByCommunityId(communityId, beginTime, endTime, exitType);
            job_total += job_count;
            //社区时间段内并轨退出的人数
            exitType = 2;
            Integer comping_count = examineService.getExitCountByCommunityId(communityId, beginTime, endTime, exitType);
            comping_total += comping_count;
            //社区时间段内5560退出的人数
            exitType = 3;
            Integer _count = examineService.getExitCountByCommunityId(communityId, beginTime, endTime, exitType);
            _total += _count;
            //社区时间段内退休退出的人数
            exitType = 4;
            Integer retire_count = examineService.getExitCountByCommunityId(communityId, beginTime, endTime, exitType);
            retire_total += retire_count;
            //社区时间段内死亡退出的人数
            exitType = 5;
            Integer death_count = examineService.getExitCountByCommunityId(communityId, beginTime, endTime, exitType);
            death_total += death_count;
            //社区时间段内其他退出的人数
            exitType = 6;
            Integer other_count = examineService.getExitCountByCommunityId(communityId, beginTime, endTime, exitType);
            other_total += other_count;


            CommunityInfoResponse communityInfoResponse = new CommunityInfoResponse();
            communityInfoResponse.setCommunity(community);
            communityInfoResponse.setAddedCount(added_count);
            communityInfoResponse.setJobCount(job_count);
            communityInfoResponse.setCompingCount(comping_count);
            communityInfoResponse.set_count(_count);
            communityInfoResponse.setRetireCount(retire_count);
            communityInfoResponse.setDeathCount(death_count);
            communityInfoResponse.setOtherCount(other_count);
            list.add(communityInfoResponse);

        }

        map.put("list", list);
        map.put("addedTotal", added_total);
        map.put("jobTotal", job_total);
        map.put("compingTotal", comping_total);
        map.put("_total", _total);
        map.put("retireTotal", retire_total);
        map.put("deathTotal", death_total);
        map.put("otherTotal", other_total);
        return new ApiResult(true, "查询成功", 0, map);
    }


    @RequestMapping(value = "getCommunityMoneyInfo")
    @ResponseBody
    public ApiResult getCommunityMoneyInfo(String beginTime, String endTime) {
        List<CommunityMoneyInfoResponse> list = new ArrayList<>();
        List<Community> communities = communityService.findAll();
        SimpleDateFormat monthSdf = new SimpleDateFormat("yyyy-MM");
        String currentMonth = monthSdf.format(new Date());
        try {
            List<String> monthList = getMonthBetween(beginTime, endTime);
            for (Community community : communities) {
                Integer communityId = community.getId();
                CommunityMoneyInfoResponse communityMoneyInfoResponse = new CommunityMoneyInfoResponse();
                List<CommunityMoneyItemResponse> itemList = new ArrayList<>();
                for (String month : monthList) {
                    CommunityMoneyItemResponse communityMoneyItemResponse = new CommunityMoneyItemResponse();
                    //如果是当前月 查询实时的数据
                    Map<String, String> dateItem = DateUtil.getFirstdayLastdayMonth(DateUtil.getDateToString(month + "-01", "yyyy-MM-dd"));
                    String first = dateItem.get("first");
                    String last = dateItem.get("last");
                    if (currentMonth.equals(month)) {
                        saveNotExitExamine(communityId, first, last);
                    }

                    Date startDate = DateUtil.getDateToString(first, "yyyy-MM-dd");
                    Date endDate = DateUtil.getDateToString(last, "yyyy-MM-dd");
//                    Integer total_money = examineStatisticService.getTotalMoneyByCommunity(communityId, startDate, endDate);
                    Integer total_count = examineStatisticService.getTotalCountByCommunity(communityId, startDate, endDate);
                    Integer total_money = total_count.intValue() * 180;
                    communityMoneyItemResponse.setDate(month);
                    communityMoneyItemResponse.setTotalCount(total_count);
                    communityMoneyItemResponse.setTotalMoney(total_money);
                    itemList.add(communityMoneyItemResponse);
                }
                communityMoneyInfoResponse.setCommunity(community);
                communityMoneyInfoResponse.setList(itemList);
                list.add(communityMoneyInfoResponse);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ApiResult(true, "查询成功", 0, list);
    }

    public void saveNotExitExamine(Integer communityId, String startDate, String endDate) {
        List<Examine> list = examineService.getNotExitExamine(communityId, startDate, endDate);
        ExamineStatistic examineStatistic = null;
        Date current = new Date();
        for (Examine examine : list) {
            examineStatistic = new ExamineStatistic();
            examineStatistic.setExamineId(examine.getId());
            examineStatistic.setCommunityId(examine.getCommunityId());
            examineStatistic.setMoney(180.0);
            examineStatistic.setTime(current);
            examineStatisticService.save(examineStatistic);
        }
    }

    /**
     * 发放统计
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    /*@RequestMapping(value = "getCommunityMoneyInfo")
    @ResponseBody
    public ApiResult getCommunityMoneyInfo(String beginTime, String endTime) {
        List<CommunityMoneyInfoResponse> list = new ArrayList<>();
        List<Community> communities = communityService.findAll();
        try {
            List<String> monthList = getMonthBetween(beginTime, endTime);
            for (Community community : communities) {
                Integer communityId = community.getId();
                CommunityMoneyInfoResponse communityMoneyInfoResponse = new CommunityMoneyInfoResponse();
                List<CommunityMoneyItemResponse> itemList = new ArrayList<>();
                for (String month : monthList) {

                    CommunityMoneyItemResponse communityMoneyItemResponse = new CommunityMoneyItemResponse();

                    Map<String, String> dateItem = DateUtil.getFirstdayLastdayMonth(DateUtil.getDateToString(month + "-01", "yyyy-MM-dd"));
                    String first = dateItem.get("first");
                    String last = dateItem.get("last");
                    Date startDate = DateUtil.getDateToString(first, "yyyy-MM-dd");
                    Date endDate = DateUtil.getDateToString(last, "yyyy-MM-dd");
//                    Integer total_money = examineStatisticService.getTotalMoneyByCommunity(communityId, startDate, endDate);
                    Integer total_count = examineStatisticService.getTotalCountByCommunity(communityId, startDate, endDate);
                    Integer total_money = total_count.intValue() * 180;
                    communityMoneyItemResponse.setDate(month);
                    communityMoneyItemResponse.setTotalCount(total_count);
                    communityMoneyItemResponse.setTotalMoney(total_money);
                    itemList.add(communityMoneyItemResponse);
                }
                communityMoneyInfoResponse.setCommunity(community);
                communityMoneyInfoResponse.setList(itemList);
                list.add(communityMoneyInfoResponse);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ApiResult(true, "查询成功", 0, list);
    }*/
    private static List<String> getMonthBetween(String beginTime, String endTime) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(beginTime));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(endTime));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }


    /**
     * 退出单导出
     *
     * @param examineId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "exportExit")
    @ResponseBody
    public String exportExit(Integer examineId, HttpServletRequest request, HttpServletResponse response) {

        Examine examine = examineService.get(examineId);
        String fileName = UUIDUtile.getUUID();
        String path = request.getSession().getServletContext().getRealPath("/model/农村征地人员社会救济金退出通知单.docx");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", examine.getName());
        params.put("sex", examine.getGender() == null ? "" : examine.getGender().intValue() == 1 ? "男" : "女");
        params.put("card", examine.getIdCard());


        if (examine.getExitTime() != null) {
            try {
                params.put("year", DateUtil.formatDate(examine.getExitTime(), "yyyy"));
                params.put("month", DateUtil.formatDate(examine.getExitTime(), "MM"));
                params.put("day", DateUtil.formatDate(examine.getExitTime(), "dd"));
                params.put("age", examine.getAge());
                params.put("year1", DateUtil.formatDate(examine.getExitTime(), "yyyy"));
                params.put("month1", DateUtil.formatDate(examine.getExitTime(), "MM"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            params.put("year2", DateUtil.formatDate(new Date(), "yyyy"));
            params.put("month2", DateUtil.formatDate(new Date(), "MM"));
            params.put("day2", DateUtil.formatDate(new Date(), "dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        XwpfTUtil xwpfTUtil = new XwpfTUtil();
        XWPFDocument doc = null;
        InputStream is = null;
        FileOutputStream os = null;
        try {

            is = new FileInputStream(path);
            doc = new XWPFDocument(is);
            xwpfTUtil.replaceInPara(doc, params);

            File dir = new File(request.getSession().getServletContext().getRealPath("/model/upload/"));
            if (!dir.exists()) {
                dir.mkdirs();
            }
            os = new FileOutputStream(request.getSession().getServletContext().getRealPath("/model/upload/" + fileName + ".docx"));
            doc.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            xwpfTUtil.close(is);
            xwpfTUtil.close(os);
            return "model/upload/" + fileName + ".docx";
        }
    }


    /**
     * 征地统计页面
     *
     * @param loginId
     * @return
     */
    @RequestMapping(value = "examineStatisticPage")
    public ModelAndView examineStatisticPage(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/statistic/examine_statistic");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }


    /**
     * @param gender       性别
     * @param loginId      当前登录用户
     * @param house
     * @param name         姓名
     * @param idCard       省份证号码
     * @param comping
     * @param changes
     * @param status
     * @param unemployment
     * @param communityId
     * @param pageSize
     * @param pageNum
     * @param grantTimes
     * @return
     */
    @RequestMapping(value = "findFftjExamineList")
    @ResponseBody
    public ApiResult findFftjExamineList(Integer gender, Integer loginId, String house, String name, String idCard,
                                         Integer comping, Integer changes, Integer status, Integer unemployment,
                                         Integer communityId, Integer pageSize, Integer pageNum, String grantTimes) {
        try {

            ExamineRequest examineRequest = new ExamineRequest();
            examineRequest.setGender(gender);
            examineRequest.setHouse(house);
            examineRequest.setName(name);
            examineRequest.setIdCard(idCard);
            examineRequest.setComping(comping);
            examineRequest.setChanges(changes);
            examineRequest.setStatus(status);
            examineRequest.setUnemployment(unemployment);
            examineRequest.setPager(pageNum, pageSize);
            String beginTimes = "";
            String endTimes = "";

            if (!StringUtils.isBlank(grantTimes)) {
                grantTimes = grantTimes.replaceAll(" ", "");
                beginTimes = grantTimes.substring(0, 7) + "-01";

                int year = Integer.parseInt(grantTimes.substring(8, 12));
                int month = Integer.parseInt(grantTimes.substring(13,15));
                endTimes =DateUtil.getLastDayOfMonth(year,month) ;//
                //endTimes = grantTimes.substring(8, grantTimes.length()) + "-31";
            }
            User user = userService.get(loginId);
            if (user != null) {
                if (user.getType().intValue() == 2) {
                    Community community = communityService.selectByUserId(loginId);
                    if (community != null) {
                        communityId = community.getId();
                    }
                }
            }
            examineRequest.setCommunityId(communityId);
            examineRequest.setBeginStr(beginTimes);
            examineRequest.setEndStr(endTimes);
            List<Examine> list = examineService.findFftjExamineList(examineRequest);
            if (!StringUtils.isBlank(grantTimes)) {
                BigDecimal ffbj = new BigDecimal(180);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                long beginLong = sdf.parse(beginTimes).getTime();
                long endLong = sdf.parse(endTimes).getTime();
                long startLong = 0;
                long stopLong = 0;
                int distanceMonth = 0;
                for (Examine examine : list) {
                    //获取发放金额
                    startLong = examine.getStartTime().getTime();
                    stopLong = examine.getStopTime().getTime();
//                    (<![CDATA[(start_time >= #{beginStr} and start_time <= #{endStr} and stop_time >= #{endStr}) or ]]>
//            <![CDATA[(start_time <= #{beginStr} and stop_time >= #{endStr}) or]]>
//            <![CDATA[(start_time <= #{beginStr} and stop_time >= #{beginStr} and stop_time <= #{endStr})]]>)
//
//                    System.out.println("beginLong="+beginLong);
//                    System.out.println("endLong="+endLong);
//                    System.out.println("startLong="+startLong);
//                    System.out.println("stopLong="+stopLong);

                    if(startLong >= beginLong && startLong <= endLong && stopLong>= endLong){
                        //beginLong< startLong < endLong < stopLong
                        distanceMonth = DateUtil.monthDistance(startLong,endLong);
                    }else if(startLong <= beginLong && stopLong >= endLong){
                        // startLong < beginLong < endLong < stopLong
                        distanceMonth = DateUtil.monthDistance(beginLong,endLong);
                    }else if(startLong <= beginLong && stopLong >= beginLong && startLong <= endLong){
                        // startLong < beginLong < stopLong < endLong
                        distanceMonth = DateUtil.monthDistance(beginLong,stopLong);
                    }
//                    System.out.println("ffbj"+ffbj);
//                    System.out.println("distanceMonth"+distanceMonth);
                    examine.setFfje(ffbj.multiply(new BigDecimal(distanceMonth)));
                }
            }
            Integer count = examineService.findFftjExamineCount(examineRequest);
            Map<String, Object> map = new HashMap<>();
            map.put("list", list);
            map.put("count", count);
            return new ApiResult(true, "查询成功", 0, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", 0, null);
        }
    }

}


