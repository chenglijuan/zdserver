package com.lemi.msloan.controller;

import com.lemi.msloan.entity.AuditRemark;
import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.entity.User;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.*;
import com.lemi.msloan.util.DateUtil;
import com.lemi.msloan.util.FileUtil;
import com.lemi.msloan.util.PhoneUtil;
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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
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
    private CommunityService communityService;

    @Autowired
    private AuditRemarkService auditRemarkService;

    /**
     *
     * @param loginId
     * @param pageType 1. 城镇  2.农村
     * @return
     */
    @RequestMapping(value = "respectPager")
    public ModelAndView respectPager(Integer loginId,Integer pageType) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/respect_list");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("pageType", pageType);
        return modelAndView;
    }

    @RequestMapping(value = "addRespect")
    public ModelAndView addRespect(Integer loginId,Integer pageType) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/add_respect");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("pageType", pageType);
        return modelAndView;
    }

    @RequestMapping(value = "updateRespect")
    public ModelAndView updateRespect(Integer respectId, Integer loginId,Integer pageType) {
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
    public ModelAndView updateLongevity(Integer loginId,Integer respectId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/update_longevity");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("respectId", respectId);
        return modelAndView;
    }

    @RequestMapping(value = "auditRespect")
    public ModelAndView auditRespect(Integer loginId,Integer respectId,Integer pageType) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/audit_respect");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("respectId", respectId);
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
        try {
            Respect respect = new Respect();
            respect.setIdCard(idCard);
            respect.setName(name);
            respect.setType(type);
            respect.setGender(gender);
            respect.setHouse(house);
            respect.setBirthday(birthday);
            //街道 1  社区 2
            if(user.getType().intValue() == 1){
                respect.setCommunityId(communityId);
                respect.setCommunityName(communityName);
            }else{
                respect.setCommunityId(user.getCommunityId());
                respect.setCommunityName(user.getCommunityName());
            }
            respect.setDynamicYearMonth(dynamicYearMonth);
            respect.setGrantTime(grantTime);
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
            respect.setIdCard(idCard);
            respect.setName(name);
            respect.setType(type);
            respect.setGender(gender);
            respect.setHouse(house);
            respect.setBirthday(birthday);
            //街道 1  社区 2
            if(user.getType().intValue() == 1){
                respect.setCommunityId(communityId);
                respect.setCommunityName(communityName);
            }else{
                respect.setCommunityId(user.getCommunityId());
                respect.setCommunityName(user.getCommunityName());
            }
            respect.setDynamicYearMonth(dynamicYearMonth);
            respect.setGrantTime(grantTime);
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
                                  Integer changeState, String grantTimes, Integer type,Integer auditState,Integer loginId) {
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
                String beginTimes = grantTimes.substring(0, 10);
                String endTimes = grantTimes.substring(11, grantTimes.length());
                respectRequest.setGrantBeginTime(beginTimes);
                respectRequest.setGrantEndTime(endTimes);
            }
            Date nowDate = new Date();
            if(type != null && type.intValue() == 3){
                //String birthdayBegin = DateUtil.getYearsbefore(nowDate, 80);
                String birthdayEnd = DateUtil.getYearsbefore(nowDate, 80);
                //respectRequest.setBirthdayBegin(birthdayBegin);
                respectRequest.setType(null);
                respectRequest.setBirthdayEnd(birthdayEnd);
            }else{
                String birthdayBegin = DateUtil.getYearsbefore(nowDate, 79);
                String birthdayEnd = DateUtil.getYearsbefore(nowDate, 70);
                respectRequest.setBirthdayBegin(birthdayBegin);
                respectRequest.setBirthdayEnd(birthdayEnd);
                respectRequest.setType(type);
            }
            respectRequest.setChangeState(changeState);
            //如果是社区管理员  只能查看 该社区的数据
            if(user.getType().intValue() == 1){
                respectRequest.setCommunityId(communityId);
            }else if(user.getType().intValue() == 2){
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
    public ApiResult importRespect(@RequestParam(value = "file", required = false) CommonsMultipartFile file, HttpSession session,Integer loginId) {
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
     * @param respectId
     * @param remark
     * @param state 2  通过  3 未通过
     * @return
     */
    @RequestMapping(value = "auditReaspectById")
    @ResponseBody
    public ApiResult auditReaspectById(Integer respectId,String remark,Integer state,Integer loginId,Integer pageType) {
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
     * @param respectId
     * @param loginId
     * @return
     */
    @RequestMapping(value = "getRemarksByRespectId")
    @ResponseBody
    public ApiResult getRemarksByRespectId(Integer respectId,Integer loginId) {
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

}