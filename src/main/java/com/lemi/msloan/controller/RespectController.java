package com.lemi.msloan.controller;

import com.lemi.msloan.entity.*;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.request.StatisticRequest;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.response.RespectSummayResult;
import com.lemi.msloan.response.StatisticResult;
import com.lemi.msloan.service.*;
import com.lemi.msloan.util.*;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: chenglijuan
 * @Data: 2019/4/18  下午9:23
 * @Decription:
 * @Modified:
 */
@Controller
@RequestMapping(value = "respect")
public class RespectController {


    @Autowired
    private RespectService respectService;

    @Autowired
    private RosterService rosterService;

    @Autowired
    private UserService userService;

    @Autowired
    private RespectStatisticService respectStatisticService;

    @Autowired
    private AuditRemarkService auditRemarkService;

    @Autowired
    private CommunityService communityService;

    /**
     * @param loginId
     * @param pageType 1. 城镇  2.农村
     * @return
     */
    @RequestMapping(value = "respectPager")
    public ModelAndView respectPager(Integer loginId, Integer pageType, Integer dealAuditState) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/respect_list");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("pageType", pageType);
        modelAndView.addObject("dealAuditState", dealAuditState);
        return modelAndView;
    }

    @RequestMapping(value = "addRespect")
    public ModelAndView addRespect(Integer loginId, Integer pageType) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/add_respect");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("pageType", pageType);
        return modelAndView;
    }

    @RequestMapping(value = "updateRespect")
    public ModelAndView updateRespect(Integer respectId, Integer loginId, Integer pageType) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/update_respect");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("respectId", respectId);
        modelAndView.addObject("pageType", pageType);
        return modelAndView;
    }

    //长寿金
    @RequestMapping(value = "longevityPager")
    public ModelAndView longevityPager(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/longevity_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "addLongevity")
    public ModelAndView addLongevity(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/add_longevity");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "updateLongevity")
    public ModelAndView updateLongevity(Integer loginId, Integer respectId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/update_longevity");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("respectId", respectId);
        return modelAndView;
    }

    @RequestMapping(value = "auditRespect")
    public ModelAndView auditRespect(Integer loginId, Integer respectId, Integer pageType) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/audit_respect");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("respectId", respectId);
        modelAndView.addObject("pageType", pageType);
        return modelAndView;
    }

    @RequestMapping(value = "respectSummaryList")
    public ModelAndView respectSummaryList(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/respect_summary_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "respectStatistic")
    public ModelAndView respectStatistic(Integer loginId, Integer pageType) {
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("page/statistic/statistic_respect");
        modelAndView.setViewName("page/statistic/respect_statistic");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("pageType", pageType);
        return modelAndView;
    }


    /**
     * 新增数据
     *
     * @param idCard           身份证号码
     * @param name             姓名
     * @param gender           性别 1. 男 2 女
     * @param birthday         出生年月
     * @param type             类型 1 城镇 2 农村
     * @param house            现户籍所在地
     * @param communityId      社区
     * @param communityName    社区名称
     * @param dynamicYearMonth 动态享受年月
     * @param grantTime        起始发放时间
     * @param phone            手机号码
     * @param remark           变动情况说明
     * @param issuStandard     发放标准
     * @param auditState       审核状态
     * @param grantState       发放状态 1.已暂停  2. 发放
     * @param loginId          当前登录用户
     * @return
     */
    @RequestMapping(value = "insertRespect")
    @ResponseBody
    public ApiResult insertRespect(String idCard, String name, Integer gender, String birthday, Integer type, String house, Integer communityId,
                                   String communityName, String dynamicYearMonth, String grantTime, String phone, String remark, Integer issuStandard,
                                   Integer auditState, Integer grantState, Integer loginId, Integer changeState) {
        if (StringUtils.isBlank(idCard)) {
            return new ApiResult(false, "请输入身份证号码", -1);
        }
        if (StringUtils.isBlank(name)) {
            return new ApiResult(false, "请输入姓名", -1);
        }
        if (gender == null) {
            return new ApiResult(false, "请输入性别", -1);
        }
        if (StringUtils.isBlank(birthday)) {
            return new ApiResult(false, "请输入出生年月", -1);
        }
        User user = userService.getByUserId(loginId);
        if (user == null) {
            return new ApiResult(false, "登录用户异常", -1);
        }
        Respect idCardRespect = respectService.getDataByIdCard(idCard);
        if (idCardRespect != null) {
            return new ApiResult(false, "身份证号码已经存在", -1);
        }
        try {
            Respect respect = new Respect();
            respect.setIdCard(idCard);
            respect.setName(name);
            respect.setType(type);
            respect.setGender(gender);
            respect.setHouse(house);
            respect.setBirthday(birthday);
            //街道 1  社区 2
            if (user.getType().intValue() == 1) {
                respect.setCommunityId(communityId);
                respect.setCommunityName(communityName);
            } else {
                respect.setCommunityId(user.getCommunityId());
                respect.setCommunityName(user.getCommunityName());
            }
            //respect.setDynamicYearMonth(dynamicYearMonth);
            if (!StringUtils.isBlank(grantTime)) {
                respect.setGrantTime(grantTime + "-01");
            }
            respect.setPhone(phone);
            respect.setRemark(remark);
            respect.setIssuStandard(issuStandard);
            respect.setAuditState(auditState);
            respect.setGrantState(grantState);
            respect.setChangeState(changeState);
            respect.setAuditState(1);
            respect.setOperator(loginId);
            respect.setCreateTime(new Date());
            respectService.save(respect);
            return new ApiResult(true, "操作成功", 0, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }

    /**
     * @param idCard
     * @param name
     * @param gender
     * @param birthday
     * @param type
     * @param house
     * @param communityId
     * @param communityName
     * @param dynamicYearMonth
     * @param grantTime
     * @param phone
     * @param remark
     * @param issuStandard
     * @param auditState
     * @param grantState
     * @param loginId
     * @param changeState
     * @return
     */
    @RequestMapping(value = "updateRespectData")
    @ResponseBody
    public ApiResult updateRespectData(Integer respectId, String idCard, String name, Integer gender, String birthday, Integer type, String house,
                                       Integer communityId, String communityName, String dynamicYearMonth, String grantTime, String phone,
                                       String remark, Integer issuStandard, Integer auditState, Integer grantState, Integer loginId, Integer changeState) {
        if (StringUtils.isBlank(idCard)) {
            return new ApiResult(false, "请输入身份证号码", -1);
        }
        if (StringUtils.isBlank(name)) {
            return new ApiResult(false, "请输入姓名", -1);
        }
        if (gender == null) {
            return new ApiResult(false, "请输入性别", -1);
        }
        if (StringUtils.isBlank(birthday)) {
            return new ApiResult(false, "请输入出生年月", -1);
        }
        User user = userService.getByUserId(loginId);
        if (user == null) {
            return new ApiResult(false, "登录用户异常", -1);
        }
        try {
            Respect respect = respectService.get(respectId);
            if (respect == null) {
                return new ApiResult(false, "记录不存在", -1);
            }
            Respect idCardRespect = respectService.getDataByIdCard(idCard);
            if (idCardRespect != null && idCardRespect.getId().intValue() != respect.getId()) {
                return new ApiResult(false, "身份证号码已经存在", -1);
            }
            respect.setIdCard(idCard);
            respect.setName(name);
            respect.setType(type);
            respect.setGender(gender);
            respect.setHouse(house);
            respect.setBirthday(birthday);
            //街道 1  社区 2
            if (user.getType().intValue() == 1) {
                respect.setCommunityId(communityId);
                respect.setCommunityName(communityName);
            } else {
                respect.setCommunityId(user.getCommunityId());
                respect.setCommunityName(user.getCommunityName());
            }
            // respect.setDynamicYearMonth(dynamicYearMonth);
            if (!StringUtils.isBlank(grantTime)) {
                if (grantTime.length() < 10) {
                    respect.setGrantTime(grantTime + "-01");
                } else {
                    respect.setGrantTime(grantTime);
                }
            }
            respect.setPhone(phone);
            respect.setRemark(remark);
            respect.setIssuStandard(issuStandard);
            respect.setAuditState(auditState);
            respect.setGrantState(grantState);
            respect.setChangeState(changeState);
            respect.setAuditState(1);
            respect.setOperator(loginId);
            respect.setUpdateTime(new Date());
            respectService.update(respect);
            return new ApiResult(true, "操作成功", 0, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }


    /**
     * 查询列表  （70到79周岁的）70<= age <=79
     *
     * @param name        姓名
     * @param idCard      身份证号码
     * @param communityId 社区
     * @param phone       手机号码
     * @param pageNum
     * @param pageSize
     * @param changeState 变动情况说明1.迁出 2.死亡
     * @param grantTimes  起始发放时间
     * @return
     */
    @RequestMapping(value = "selectRespect")
    @ResponseBody
    public ApiResult selectRespect(String name, String idCard, Integer communityId, String phone, Integer pageNum, Integer pageSize,
                                   Integer changeState, String grantTimes, Integer type, Integer auditState, Integer loginId) {
        try {
            User user = userService.getByUserId(loginId);
            if (user == null) {
                return new ApiResult(false, "登录用户异常", -1);
            }
            RespectRequest respectRequest = new RespectRequest();
            if (!StringUtils.isBlank(name)) {
                respectRequest.setName(name);
            }
            if (!StringUtils.isBlank(idCard)) {
                respectRequest.setIdCard(idCard);
            }
            if (!StringUtils.isBlank(phone)) {
                respectRequest.setPhone(phone);
            }
            respectRequest.setAuditState(auditState);
            if (!StringUtils.isBlank(grantTimes)) {
                grantTimes = grantTimes.replaceAll(" ", "");
                String beginTimes = grantTimes.substring(0, 7) + "-01";
                String endTimes = grantTimes.substring(8, grantTimes.length()) + "-01";
                respectRequest.setGrantBeginTime(beginTimes);
                respectRequest.setGrantEndTime(endTimes);
            }
            Date nowDate = new Date();
            //type 1 城镇  2.农村  type =3  查询长寿金  90岁 或者下个月90岁  4 已故人员名单  5查询全部
            if (type != null && type.intValue() == 3) {
                Date nextMonth = DateUtil.getNLastMonthInfo(nowDate, 1);
                String birthdayEnd = DateUtil.getYearsbefore(nextMonth, 90);
                respectRequest.setType(null);
                respectRequest.setBirthdayEnd(birthdayEnd);
                respectRequest.setChangeState(changeState);
                //respectRequest.setChangeState(1);
            } else if (type != null && type.intValue() == 4) {
                //已故人员名单   变更情况说明是死亡
                respectRequest.setChangeState(2);
            } else if (type != null && (type.intValue() == 1 || type.intValue() == 2)) {
                //大于70周岁都算 小于90
                Date nextMonth = DateUtil.getNLastMonthInfo(nowDate, 1);
                String birthdayBegin = DateUtil.getYearsbefore(nextMonth, 90);
                String birthdayEnd = DateUtil.getYearsbefore(nowDate, 70);
                respectRequest.setBirthdayBegin(birthdayBegin);
                respectRequest.setBirthdayEnd(birthdayEnd);
                respectRequest.setType(type);
                respectRequest.setChangeState(changeState);
                //respectRequest.setChangeState(1);
            } else if (type != null && type.intValue() == 5) {
                //已故人员名单   变更情况说明是死亡
                respectRequest.setChangeState(changeState);
            }
            //如果是社区管理员  只能查看 该社区的数据
            if (user.getType().intValue() == 1) {
                respectRequest.setCommunityId(communityId);
            } else if (user.getType().intValue() == 2) {
                respectRequest.setCommunityId(user.getCommunityId());
            }
            respectRequest.setPager(pageNum, pageSize);
            List<Respect> list = respectService.selectRespectPager(respectRequest);
            Integer count = respectService.selectRespectCount(respectRequest);
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
     * 根据id获取数据
     *
     * @param respectId
     * @return
     */
    @RequestMapping(value = "getRespectById")
    @ResponseBody
    public ApiResult getRespectById(Integer respectId) {
        try {
            if (respectId == null) {
                return new ApiResult(false, "参数不能为空", -1, null);
            }
            Respect respect = respectService.get(respectId);
            return new ApiResult(true, "操作成功", 0, respect);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }

    /**
     * 订单导入状态变更
     *
     * @param file
     * @param session
     * @return
     */
    @RequestMapping(value = "importRespect")
    @ResponseBody
    public ApiResult importRespect(@RequestParam(value = "file", required = false) CommonsMultipartFile file, HttpSession session, Integer loginId) {
        try {
            if (file == null) {
                return new ApiResult(false, "请上传文件", -1, null);
            }
            User user = userService.getByUserId(loginId);
            if (user == null) {
                return new ApiResult(false, "登录用户异常", -1);
            }
            String fileOriginalFilename = UUID.randomUUID() + file.getOriginalFilename().replace(",", "");
            String rootPath = session.getServletContext().getRealPath("/");
            String uploadPath = rootPath + "temporary/";
            File source = new File(uploadPath + fileOriginalFilename);
            file.transferTo(source);
            if (!FileUtil.checkExcelVaild(source)) {
                return new ApiResult(false, "文件格式不正确", null);
            }
            List<Respect> respects = new ArrayList<Respect>();

            Workbook wb = null;
            Sheet sheet = null;
            Row row = null;
            String cellData = null;
            String cloumns[] = {"姓名", "性别", "出生年月", "身份证号", "户籍所在地", "联系电话"};
            wb = PoiTest.readExcel(source.getPath());
            if (wb != null) {
                //获取第一个sheet
                sheet = wb.getSheetAt(0);
                //获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();
                //获取第一行
                labe:
                for (int i = 1; i < rownum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        Respect temp = new Respect();
                        for (int j = 0; j < 6; j++) {
                            cellData = (String) PoiTest.getCellFormatValue(row.getCell(j));
                            if ("姓名".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setName(cellData.trim());
                                } else {
                                    continue labe;
                                }
                            }
                            if ("性别".equals(cloumns[j])) {
                                String cellDataStr = cellData.trim();
                                if (!StringUtils.isBlank(cellDataStr)) {
                                    if ("男".equals(cellDataStr)) {
                                        temp.setGender(1);
                                    } else {
                                        temp.setGender(2);
                                    }
                                }
                            }
                            if ("出生年月".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim()))
                                    temp.setBirthday(cellData.trim());
                            }
                            if ("身份证号".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setIdCard(cellData.trim());
                                } else {
                                    continue labe;
                                }
                            }
                            if ("户籍所在地".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim()))
                                    temp.setHouse(cellData.trim());
                            }
                            if ("联系电话".equals(cloumns[j])) {
                                if (PhoneUtil.isMobileNO(cellData) || PhoneUtil.isPhone(cellData)) {
                                    temp.setPhone(cellData.trim());
                                }
                            }
                        }
                        respects.add(temp);
                    } else {
                        return new ApiResult(false, "文件格式有误", -1, null);
                    }
                }
            }
            if (respects != null && respects.size() > 0) {
                Date current = new Date();
                List<Respect> saveRespects = new ArrayList<Respect>();
                //校验省份证号码
                List<String> idCards = new ArrayList<String>();
                List<String> rosterIdCards = rosterService.getAllIdCards();
                List<String> respectIdcards = respectService.getAllIdCards();
                for (Respect respect : respects) {
                    String idCard = respect.getIdCard();
                    //身份证号码已经存在不添加
                    if (respectIdcards.contains(idCard)) {
                        continue;
                    }
                    if (idCards.contains(idCard)) {
                        continue;
                    }
                    //如果身份证号码存在  设置为农村
                    if (rosterIdCards.contains(idCard)) {
                        respect.setType(2);
                    } else {
                        respect.setType(1);
                    }
                    idCards.add(idCard);
                    respect.setAuditState(1);
                    respect.setCreateTime(current);
                    respect.setCommunityName(user.getCommunityName());
                    respect.setCommunityId(user.getCommunityId());
                    saveRespects.add(respect);
                }
                if (saveRespects != null && saveRespects.size() > 0) {
                    respectService.insertBatchData(saveRespects);
                }
            }

            return new ApiResult(true, "操作成功", 0, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ApiResult(false, "操作失败", -1, null);
    }

    /**
     * @param response
     * @param session
     */
    @RequestMapping(value = "downRespectExcel")
    @ResponseBody
    public void downExcel(HttpServletResponse response, HttpSession session) {
        try {
            String rootPath = session.getServletContext().getRealPath("/");
            String uploadPath = rootPath + "temp/";
            String name = "respectUpload.xlsx";
            String path = uploadPath + name;
            File file = new File(path);
            // 取得文件名。
            String filename = URLEncoder.encode(name, "UTF-8");
            // 取得文件的后缀名。
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            InputStream inStream = new FileInputStream(path);
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 审核
     *
     * @param respectId
     * @param remark
     * @param state     2  通过  3 未通过
     * @return
     */
    @RequestMapping(value = "auditReaspectById")
    @ResponseBody
    public ApiResult auditReaspectById(Integer respectId, String remark, Integer state, Integer loginId, Integer pageType) {
        try {
            User user = userService.getByUserId(loginId);
            if (user == null) {
                return new ApiResult(false, "登录用户异常", -1);
            }
            Respect respect = respectService.get(respectId);
            if (respect == null) {
                return new ApiResult(false, "记录为空", -1);
            }
            AuditRemark remark1 = new AuditRemark();
            remark1.setRespectId(respectId);
            remark1.setRemark(remark);
            remark1.setAuditState(state);
            remark1.setOperator(loginId);
            remark1.setCreateTime(new Date());
            remark1.setOperatorName(user.getNickname());
            auditRemarkService.save(remark1);
            respect.setAuditState(state);
            respectService.update(respect);
            return new ApiResult(true, "操作成功", 0, pageType);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }

    /**
     * 根据记录id获取审核记录
     *
     * @param respectId
     * @param loginId
     * @return
     */
    @RequestMapping(value = "getRemarksByRespectId")
    @ResponseBody
    public ApiResult getRemarksByRespectId(Integer respectId, Integer loginId) {
        try {
            User user = userService.getByUserId(loginId);
            if (user == null) {
                return new ApiResult(false, "登录用户异常", -1);
            }
            List<AuditRemark> list = auditRemarkService.selectByRespectId(respectId);
            return new ApiResult(true, "操作成功", 0, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }


    /**
     * 导出
     *
     * @param response
     */
    @RequestMapping(value = "exportRespect")
    public void exportOrder(HttpServletResponse response, String name, String idCard, Integer communityId, String phone,
                            Integer changeState, String grantTimes, Integer type, Integer auditState, Integer loginId) {
        try {
            User user = userService.getByUserId(loginId);
            if (user == null) {
                return;
            }
            RespectRequest respectRequest = new RespectRequest();

            if (!StringUtils.isBlank(name)) {
                name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
                respectRequest.setName(name);
            }
            if (!StringUtils.isBlank(idCard)) {
                idCard = new String(idCard.getBytes("ISO-8859-1"), "UTF-8");
                respectRequest.setIdCard(idCard);
            }
            if (!StringUtils.isBlank(phone)) {
                phone = new String(phone.getBytes("ISO-8859-1"), "UTF-8");
                respectRequest.setPhone(phone);
            }
            respectRequest.setAuditState(auditState);
            if (!StringUtils.isBlank(grantTimes)) {
                grantTimes = new String(grantTimes.getBytes("ISO-8859-1"), "UTF-8");
                grantTimes = grantTimes.replaceAll(" ", "");
                String beginTimes = grantTimes.substring(0, 7) + "-01";
                String endTimes = grantTimes.substring(8, grantTimes.length()) + "-01";
                respectRequest.setGrantBeginTime(beginTimes);
                respectRequest.setGrantEndTime(endTimes);
            }
            Date nowDate = new Date();
            //type 1 城镇  2.农村  type =3  查询长寿金  90岁 或者下个月90岁  4 已故人员名单  5查询全部
            if (type != null && type.intValue() == 3) {
                Date nextMonth = DateUtil.getNLastMonthInfo(nowDate, 1);
                String birthdayEnd = DateUtil.getYearsbefore(nextMonth, 90);
                respectRequest.setType(null);
                respectRequest.setBirthdayEnd(birthdayEnd);
                respectRequest.setChangeState(changeState);
                //respectRequest.setChangeState(1);
            } else if (type != null && type.intValue() == 4) {
                //已故人员名单   变更情况说明是死亡
                respectRequest.setChangeState(2);
            } else if (type != null && (type.intValue() == 1 || type.intValue() == 2)) {
                //大于70周岁都算 小于90
                Date nextMonth = DateUtil.getNLastMonthInfo(nowDate, 1);
                String birthdayBegin = DateUtil.getYearsbefore(nextMonth, 90);
                String birthdayEnd = DateUtil.getYearsbefore(nowDate, 70);
                respectRequest.setBirthdayBegin(birthdayBegin);
                respectRequest.setBirthdayEnd(birthdayEnd);
                respectRequest.setType(type);
                respectRequest.setChangeState(changeState);
                //respectRequest.setChangeState(1);
            } else if (type != null && type.intValue() == 5) {
                //已故人员名单   变更情况说明是死亡
                respectRequest.setChangeState(changeState);
            }
            //如果是社区管理员  只能查看 该社区的数据
            if (user.getType().intValue() == 1) {
                respectRequest.setCommunityId(communityId);
            } else if (user.getType().intValue() == 2) {
                respectRequest.setCommunityId(user.getCommunityId());
            }
            List<Respect> list = respectService.selectRespectPager(respectRequest);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<List<String>> strsList = new ArrayList<List<String>>();
            List<String> title = new ArrayList<String>();
            title.add("姓名");
            title.add("尊老金类型");
            title.add("身份证");
            title.add("性别");//1.男2.女
            title.add("出生年月");
            title.add("年龄");
            title.add("联系方式");
            title.add("类型");//1.城镇 2.农村
            title.add("现户籍所在地");
            title.add("社区名称");
            title.add("动态享受年月");
            title.add("起始发放时间");
            title.add("发放标准(元)");
            title.add("审核状态"); //1.待审核2.审核通过 3.审核为通过'
            title.add("发放状态");//发放状态1.已暂停 2.发放
            title.add("变动情况说明");//1.迁出 2.死亡
            strsList.add(title);
            for (int i = 0, length = list.size(); i < length; i++) {
                List<String> strings = new ArrayList<>();
                Respect respect = list.get(i);
                //姓名
                strings.add(respect.getName());
                if (respect.getType() != null) {
                    if (respect.getType().intValue() == 1) {
                        strings.add("城镇");
                    } else if (respect.getType().intValue() == 2) {
                        strings.add("农村");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                //身份证
                strings.add(respect.getIdCard());
                //性别
                if (respect.getGender() != null) {
                    if (respect.getGender().intValue() == 1) {
                        strings.add("男");
                    } else if (respect.getGender().intValue() == 2) {
                        strings.add("女");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                //出生年月
                if (!StringUtils.isBlank(respect.getBirthday())) {
                    strings.add(respect.getBirthday());
                } else {
                    strings.add("");
                }
                int respectAge = 0;
                if (!StringUtils.isBlank(respect.getBirthday())) {
                    respectAge = DateUtil.getAgeByBirth(sdf.parse(respect.getBirthday()));
                    strings.add(respectAge + "");
                } else {
                    strings.add("");
                }
                //联系方式
                if (!StringUtils.isBlank(respect.getPhone())) {
                    strings.add(respect.getPhone());
                } else {
                    strings.add("");
                }
                //类型1.城镇 2.农村
                if (respect.getType() != null) {
                    if (respect.getType().intValue() == 1) {
                        strings.add("城镇");
                    } else if (respect.getType().intValue() == 2) {
                        strings.add("农村");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                //现户籍所在地
                if (!StringUtils.isBlank(respect.getHouse())) {
                    strings.add(respect.getHouse());
                } else {
                    strings.add("");
                }
                //社区名称
                if (!StringUtils.isBlank(respect.getCommunityName())) {
                    strings.add(respect.getCommunityName());
                } else {
                    strings.add("");
                }
                //动态享受年月
                if (!StringUtils.isBlank(respect.getGrantTime())) {
                    String end = respect.getGrantTime();
                    Date current = new Date();
                    int betweenMonth = DateUtil.getMonthBetween(sdf.parse(end), current);
                    /*int year = betweenMonth / 12;
                    int month = betweenMonth % 12;*/
                    strings.add(betweenMonth + "月");
                } else {
                    strings.add("");
                }
                //起始发放时间
                if (!StringUtils.isBlank(respect.getGrantTime())) {
                    strings.add(respect.getGrantTime());
                } else {
                    strings.add("");
                }
                if (respectAge > 0 && respect.getType() != null) {
                    strings.add(AgeUtils.getIssuStandard(respectAge, respect.getType()) + "");
                } else {
                    strings.add(0 + "");
                }
                //审核状态 1.待审核2.审核通过 3.审核未通过
                if (respect.getAuditState() != null) {
                    if (respect.getAuditState().intValue() == 1) {
                        strings.add("待审核");
                    } else if (respect.getAuditState().intValue() == 2) {
                        strings.add("审核通过");
                    } else if (respect.getAuditState().intValue() == 3) {
                        strings.add("审核未通过");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                //发放状态 发放状态1.已暂停 2.发放
                if (respect.getGrantState() != null) {
                    if (respect.getGrantState().intValue() == 1) {
                        strings.add("已暂停");
                    } else if (respect.getGrantState().intValue() == 2) {
                        strings.add("发放中");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                //变动情况说明 1.迁出 2.死亡
                if (respect.getChangeState() != null) {
                    if (respect.getChangeState().intValue() == 1) {
                        strings.add("迁出");
                    } else if (respect.getChangeState().intValue() == 2) {
                        strings.add("死亡");
                    } else {
                        strings.add("");
                    }
                } else {
                    strings.add("");
                }
                strsList.add(strings);
            }
            String str = "尊老金导出";
            String fileName = str + DateUtil.getDateString("yyyy-MM-dd", new Date());
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();
            OutputStream os = out;
            String excelTitle = "尊老金列表";

            POIExcelUtil.writerDataInExcelIo(strsList, os, excelTitle, 15);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* @RequestMapping(value = "getRespectSummary")
     @ResponseBody*/
    public ApiResult getRespectSummary(Integer communityId, Integer type, HttpSession session, Integer pageSize, Integer pageNum) {
        try {
            String loginId = (String) session.getAttribute("loginId");
            if (StringUtils.isBlank(loginId)) {
                return new ApiResult(false, "登录用户异常", -1, null);
            }
            User user = userService.getByUserId(Integer.parseInt(loginId));
            RespectRequest respectRequest = new RespectRequest();
            // 1. 管理员  2. 社区管理员
            if (user.getType().intValue() == 1) {

            } else if (user.getType().intValue() == 2) {
                communityId = user.getCommunityId();
            }
            respectRequest.setCommunityId(communityId);
            respectRequest.setType(type);
            respectRequest.setPager(pageNum, pageSize);
            List<RespectStatistic> list = respectStatisticService.getRespectStatisticPager(respectRequest);
            int count = respectStatisticService.getRespectStatisticCount(respectRequest);
            Map<String, Object> map = new HashMap<>();
            map.put("list", list);
            map.put("count", count);
            if (count > 0) {
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

    @RequestMapping(value = "getStatisticRespect")
    @ResponseBody
    public ApiResult getStatisticRespect(Integer type, String grantTimes, HttpSession session) {
        try {
            String loginId = (String) session.getAttribute("loginId");
            if (StringUtils.isBlank(loginId)) {
                return new ApiResult(false, "登录用户异常", -1, null);
            }
            System.out.println("grantTimes=" + grantTimes);
            User user = userService.getByUserId(Integer.parseInt(loginId));
            List<String> months = new ArrayList<String>();
            //查询近半年
            if (StringUtils.isBlank(grantTimes)) {
                months = DateUtil.getHalfLastMonth();
            } else {
                grantTimes = grantTimes.replaceAll(" ", "");
                String beginTimes = grantTimes.substring(0, 7) + "-01";
                String endTimes = grantTimes.substring(8, grantTimes.length()) + "-31";
                months = DateUtil.getMonthListBetween(beginTimes,endTimes);
            }
            List<Community> communityIds = new ArrayList<Community>();
            if (user.getType().intValue() == 1) {
                communityIds = communityService.findAll();
            } else if (user.getType().intValue() == 2) {
                //communityIds.add(user.getCommunityId());
                //如果是管理员查询所有的社区的数据
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            StatisticRequest request = new StatisticRequest();
            List<StatisticResult> statisticResults = new ArrayList<StatisticResult>();
            for (Community community: communityIds) {
                StatisticResult statisticResult = new StatisticResult();
                statisticResult.setCommunity(community);
                List<RespectStatistic> statistics = new ArrayList<RespectStatistic>();
                //查询
                for (String month:months) {
                    request.setCommunityId(community.getId());
                    request.setType(type);
                    request.setBeginTime(month+"-01");
                    request.setEndTime(month+"-31");
                    RespectStatistic monthStatistic = respectStatisticService.getStatisticByMonth(request);
                    if(monthStatistic == null){
                        monthStatistic = new RespectStatistic();
                        monthStatistic.setTotalCount(0);
                        monthStatistic.setTotalMoney(new BigDecimal(0));
                        monthStatistic.setSummaryMonth(sdf.parse(month+"-01"));
                    }
                    statistics.add(monthStatistic);
                }
                statisticResult.setRespectStatisticList(statistics);
                statisticResults.add(statisticResult);
            }
            Map<String,Object> result = new HashMap<String,Object>();
            result.put("statisticResults",statisticResults);
            result.put("months",months);
            return new ApiResult(true, "操作成功", 0, result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1, null);
        }
    }


}
