package com.lemi.msloan.controller;

import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.entity.Roster;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.RespectService;
import com.lemi.msloan.service.RosterService;
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

    @RequestMapping(value = "respectPager")
    public ModelAndView respectPager( Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/respect_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "addRespect")
    public ModelAndView addRespect( Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/add_respect");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "updateRespect")
    public ModelAndView updateRespect(Integer respectId, Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/update_respect");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("respectId",respectId);
        return modelAndView;
    }

    @RequestMapping(value = "countryPager")
    public ModelAndView countryPager( Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/country_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "addConntryRespect")
    public ModelAndView addConntryRespect( Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/add_country_respect");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "updateCountryRespect")
    public ModelAndView updateCountryRespect(Integer respectId, Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/respect/update_country_respect");
        modelAndView.addObject("loginId", loginId);
        modelAndView.addObject("respectId",respectId);
        return modelAndView;
    }


    /**
     * 新增数据
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
     * @param grantState 发放状态 1.已暂停  2. 发放
     * @param loginId  当前登录用户
     * @return
     */
    @RequestMapping(value = "insertRespect")
    @ResponseBody
    public ApiResult insertRespect(String idCard, String name, Integer gender, String birthday,Integer type, String house,Integer communityId,
                                  String communityName,String dynamicYearMonth,String grantTime,String phone,String remark,Integer issuStandard,
                                  Integer auditState,Integer grantState,Integer loginId,Integer changeState)
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
        if ( null == communityId) {
            return new ApiResult(false, "请输入社区", -1);
        }
        try{
            Respect respect = new Respect();
            respect.setIdCard(idCard);
            respect.setName(name);
            respect.setType(type);
            respect.setGender(gender);
            respect.setHouse(house);
            respect.setBirthday(birthday);
            respect.setCommunityId(communityId);
            respect.setCommunityName(communityName);
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
            return new ApiResult(true, "操作成功", 0,null);
        } catch (Exception e){
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1,null);
        }
    }

    /**
     *
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
    public ApiResult updateRespectData(Integer respectId,String idCard, String name, Integer gender, String birthday,Integer type, String house,
                                       Integer communityId, String communityName,String dynamicYearMonth,String grantTime,String phone,
                                       String remark,Integer issuStandard, Integer auditState,Integer grantState,Integer loginId,Integer changeState)
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
        if ( null == communityId) {
            return new ApiResult(false, "请输入社区", -1);
        }
        try{
            Respect respect = respectService.get(respectId);
            if(respect == null){
                return new ApiResult(false, "记录不存在", -1);
            }
            respect.setIdCard(idCard);
            respect.setName(name);
            respect.setType(type);
            respect.setGender(gender);
            respect.setHouse(house);
            respect.setBirthday(birthday);
            respect.setCommunityId(communityId);
            respect.setCommunityName(communityName);
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
            return new ApiResult(true, "操作成功", 0,null);
        } catch (Exception e){
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1,null);
        }
    }


    /**
     * 查询列表
     * @param name 姓名
     * @param idCard 身份证号码
     * @param communityId  社区
     * @param phone  手机号码
     * @param pageNum
     * @param pageSize
     * @param changeState 变动情况说明1.迁出 2.死亡
     * @param grantTimes  起始发放时间
     * @return
     */
    @RequestMapping(value = "selectRespect")
    @ResponseBody
    public ApiResult selectRoster(String name, String idCard, Integer communityId, String phone, Integer pageNum, Integer pageSize,
                                  Integer changeState,String grantTimes,Integer type) {
        try{
            RespectRequest respectRequest = new RespectRequest();
            if(!StringUtils.isBlank(name)){
                respectRequest.setName(name);
            }
            if(!StringUtils.isBlank(idCard)){
                respectRequest.setIdCard(idCard);
            }
            if(!StringUtils.isBlank(phone)){
                respectRequest.setPhone(phone);
            }
            if(!StringUtils.isBlank(grantTimes)){
                grantTimes = grantTimes.replaceAll(" ","");
                String beginTimes = grantTimes.substring(0,10);
                String endTimes = grantTimes.substring(11,grantTimes.length());
                respectRequest.setGrantBeginTime(beginTimes);
                respectRequest.setGrantEndTime(endTimes);
            }
            respectRequest.setType(type);
            respectRequest.setChangeState(changeState);
            respectRequest.setCommunityId(communityId);
            respectRequest.setPager(pageNum,pageSize);
            List<Respect> list = respectService.selectRespectPager(respectRequest);
            Integer count = respectService.selectRespectCount(respectRequest);
            Map<String, Object> map = new HashMap<>();
            map.put("list", list);
            map.put("count", count);
            if(count != null && count.intValue() > 0){
                Float totalPage = count * 1.0f / pageSize;
                map.put("totalPage",Math.ceil(totalPage));
            }else{
                map.put("totalPage",1);
            }
            return new ApiResult(true, "操作成功", 0,map);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1,null);
        }
    }

    /**
     * 根据id获取数据
     * @param respectId
     * @return
     */
    @RequestMapping(value = "getRespectById")
    @ResponseBody
    public ApiResult getRespectById(Integer respectId) {
        try{
            if(respectId == null){
                return new ApiResult(false, "参数不能为空", -1,null);
            }
            Respect respect = respectService.get(respectId);
            return new ApiResult(true, "操作成功", 0,respect);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResult(false, "操作失败", -1,null);
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
    public ApiResult importRespect(@RequestParam(value = "file", required = false) CommonsMultipartFile file, HttpSession session) {
        try {
            if (file == null) {
                return new ApiResult(false, "请上传文件", -1, null);
            }
            String fileOriginalFilename = UUID.randomUUID()+file.getOriginalFilename().replace(",", "");
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
            String cloumns[] = {"姓名","性别", "出生年月", "身份证号", "户籍所在地", "联系电话"};
            wb = PoiTest.readExcel(source.getPath());
            int successCount = 0;
            int errorCount = 0;
            if (wb != null) {
                //获取第一个sheet
                sheet = wb.getSheetAt(0);
                //获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();
                //获取第一行
               labe: for (int i = 2; i < rownum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        Respect temp = new Respect();
                        for (int j = 0; j < 6; j++) {
                            cellData = (String) PoiTest.getCellFormatValue(row.getCell(j));
                            if ("姓名".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim()))
                                {
                                    temp.setName(cellData.trim());
                                } else{
                                    continue labe;
                                }
                            }
                            if ("性别".equals(cloumns[j])) {
                                String cellDataStr = cellData.trim();
                                if (!StringUtils.isBlank(cellDataStr)){
                                    if ("男".equals(cellDataStr)){
                                        temp.setGender(1);
                                    }else {
                                        temp.setGender(2);
                                    }
                                }
                            }
                            if("出生年月".equals(cloumns[j])){
                                if (!StringUtils.isBlank(cellData.trim()))
                                    temp.setBirthday(cellData.trim());
                            }
                            if("身份证号".equals(cloumns[j])){
                                if (!StringUtils.isBlank(cellData.trim()))
                                {
                                    temp.setIdCard(cellData.trim());
                                } else{
                                    continue labe;
                                }
                            }
                            if("户籍所在地".equals(cloumns[j])){
                                if (!StringUtils.isBlank(cellData.trim()))
                                    temp.setHouse(cellData.trim());
                            }
                            if("联系电话".equals(cloumns[j])){
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
            for (Respect respect:respects ) {
                List<String> rosterIdCards = rosterService.getAllIdCards();
                //如果身份证号码存在  设置为农村
                if(rosterIdCards.contains(respect.getIdCard())){
                    respect.setType(2);
                }else{
                    respect.setType(1);
                }
            }
            if(respects != null && respects.size() > 0){
                respectService.insertBatchData(respects);
            }
            return new ApiResult(false, "操作失败", -1, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ApiResult(false, "操作失败", -1, null);
    }

    /**
     *
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
}
