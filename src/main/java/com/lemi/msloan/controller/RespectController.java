package com.lemi.msloan.controller;

import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.RespectService;
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
     * @param grantState 发放状态
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
            respect.setGender(gender);
            respect.setCommunityName(communityName);
            respect.setDynamicYearMonth(dynamicYearMonth);
            respect.setGrantTime(grantTime);
            respect.setPhone(phone);
            respect.setRemark(remark);
            respect.setIssuStandard(issuStandard);
            respect.setAuditState(auditState);
            respect.setGrantState(grantState);
            respect.setChangeState(changeState);
            respectService.save(respect);
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
     * @param ageRange  年龄段
     * @return
     */
    @RequestMapping(value = "selectRespect")
    @ResponseBody
    public ApiResult selectRoster(String name, String idCard, Integer communityId, String phone, Integer pageNum, Integer pageSize,Integer changeState,String grantTimes,String ageRange) {
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
            List<Respect> orders = new ArrayList<Respect>();
            Workbook wb = null;
            Sheet sheet = null;
            Row row = null;
            String cellData = null;
            String cloumns[] = {"订单ID", "客户姓名", "手机号码", "身份证号", "产品类型", "审核状态"};
            wb = PoiTest.readExcel(source.getPath());
            int successCount = 0;
            int errorCount = 0;
            if (wb != null) {
                //获取第一个sheet
                sheet = wb.getSheetAt(0);
                //获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();
                //获取第一行
                /*for (int i = 2; i < rownum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        Order temp = new Order();
                        for (int j = 0; j < 6; j++) {
                            cellData = (String) PoiTest.getCellFormatValue(row.getCell(j));
                            if ("订单ID".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())){
                                    temp.setId(Integer.parseInt(cellData.trim()));
                                }
                            }
                            if ("审核状态".equals(cloumns[j])) {

                                String cellDataStr = cellData.trim();
                                if (!StringUtils.isBlank(cellDataStr)){
                                    cellDataStr = MsState.getOrderState(cellDataStr);
                                    System.out.println("cellDataStr="+cellDataStr);
                                    if ("资料审核中".equals(cellDataStr)){
                                        temp.setState(1);
                                    }else if ("审批通过".equals(cellDataStr)){
                                        temp.setState(2);
                                    }else if ("审批未通过".equals(cellDataStr)){
                                        temp.setState(3);
                                    }else{
                                        temp.setState(4);
                                    }
                                }else {
                                    temp.setState(4);
                                }
                            }
                        }
                        orders.add(temp);
                    } else {
                        return new ApiResult(false, "文件格式有误", -1, null);
                    }
                }
            }
            for (Order item:orders){
                if (item.getId() != null){
                    Order order = orderService.get(item.getId());
                    if (order != null){
                        //该订单积分未添加  给这个人添加积分
                        if(item.getState() != null && item.getState().intValue() !=4){
                            //该订单积分未添加  给这个人的推荐人添加积分
                            if(order.getIntegral() == null  || order.getIntegral().intValue() == 0){
                                Account account = accountService.get(order.getAccountId());
                                //获取上推荐人
                                Integer superId = account.getParent();
                                if(superId != null && superId.intValue() != 0){
                                    Account superAccount = accountService.get(superId);
                                    Integer remain = superAccount.getIntegral() == null ? 0 : superAccount.getIntegral();
                                    Integer confIntegral = getConfIntegral();
                                    order.setIntegral(getConfIntegral());
                                    superAccount.setIntegral(confIntegral+remain);
                                    accountService.update(superAccount);
                                }
                            }
                        }
                        order.setState(item.getState());
                        orderService.update(order);
                    }else {
                        errorCount ++;
                        continue;
                    }
                }else {
                    errorCount ++;
                    continue;
                }
            }
            successCount = orders.size()-errorCount;*/
            }
            return new ApiResult(true, "共"+orders.size()+"条记录，成功" + successCount + "条,失败" + errorCount + "条", 0, null);
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
    @RequestMapping(value = "downExcel")
    @ResponseBody
    public void downExcel(HttpServletResponse response, HttpSession session) {
        try {
            String rootPath = session.getServletContext().getRealPath("/");
            String uploadPath = rootPath + "temp/";
            String name = "account.xls";
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
