package com.lemi.msloan.controller;

import com.lemi.msloan.entity.Community;
import com.lemi.msloan.entity.Roster;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.CommunityService;
import com.lemi.msloan.service.RosterService;
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

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RequestMapping(value = "roster")
@Controller
public class RosterController {

    @Autowired
    private RosterService rosterService;

    @Autowired
    private CommunityService communityService;

    @RequestMapping(value = "updateRosterPager")
    public ModelAndView updateRosterPager(Integer rosterId,Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/roster/update_roster");
        modelAndView.addObject("rosterId", rosterId);
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "allExamineListPage")
    public ModelAndView allExamineListPage(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/examine/all_examine_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "startWarningExamineListPage")
    public ModelAndView startWarningExamineListPage(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/examine/start_warning_examine_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "endWarningExamineListPage")
    public ModelAndView endWarningExamineListPage(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/examine/end_warning_examine_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "addExaminePage")
    public ModelAndView addExaminePage(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/examine/add_examine");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "examineListPage")
    public ModelAndView examineListPage(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/examine/examine_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "undeterminedExamineListPage")
    public ModelAndView undeterminedExamineListPage(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/examine/undetermined_examine_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "indexPage")
    public ModelAndView indexPage(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/index");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "rosterList")
    public ModelAndView rosterList(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/roster/roster_list");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    @RequestMapping(value = "addRosterPage")
    public ModelAndView addRosterPage(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/roster/add_roster");
        modelAndView.addObject("loginId", loginId);
        return modelAndView;
    }

    /**
     * 新增花名册
     *
     * @param idCard        身份证
     * @param name          姓名
     * @param gender        性别 1：男 2：女
     * @param birthday      出生年月
     * @param address       常住地址
     * @param village       征地时所在村（组）
     * @param isMove        是否迁出 1：否  2：是
     * @param communityId   现所属社区Id
     * @param communityName 现所属社区名称
     * @param house         现户籍所在地
     * @param status        发放状态 1：未开始 2：发放中 3：已暂停 4：已退出
     * @param remark        备注
     * @return
     */
    @RequestMapping(value = "insertRoster")
    @ResponseBody
    public ApiResult insertRoster(String idCard, String name, Integer gender, String birthday, String address, String village, Integer isMove, Integer communityId, String communityName, String house, Integer status, String remark) {

        if (StringUtils.isBlank(idCard)) {
            return new ApiResult(false, "请输入身份证号码", -1);
        }
        if (idCard.length() == 15 || idCard.length() == 18) {
            if (!IdCardUtil.cardCodeVerifySimple(idCard)) {
                return new ApiResult(false, "15位或18位身份证号码不正确", -1);
            } else {
                if (idCard.length() == 18 && !IdCardUtil.cardCodeVerify(idCard)) {
                    return new ApiResult(false, "18位身份证号码不符合国家规范", -1);
                }
            }
        } else {
            return new ApiResult(false, "身份证号码长度必须等于15或18位", -1);
        }

        if (StringUtils.isBlank(birthday)) {
            return new ApiResult(false, "请选择出生年月", -1);
        }

        if (StringUtils.isBlank(address)) {
            return new ApiResult(false, "请输入常住地址", -1);
        }

        if (StringUtils.isBlank(name)) {
            return new ApiResult(false, "请输入姓名", -1);
        }

        if (gender == null) {
            return new ApiResult(false, "请选择性别", -1);
        }

        if (StringUtils.isBlank(village)) {
            return new ApiResult(false, "请输入征地时所在村（组）", -1);
        }

        if (StringUtils.isBlank(house)) {
            return new ApiResult(false, "请输入现户籍所在地", -1);
        }

        if (communityId == null) {
            return new ApiResult(false, "请选择现所属社区", -1);
        }

        Roster roster = rosterService.getByIdCard(idCard);
        if (roster != null) {
            return new ApiResult(false, "身份证号码已存在", -1);
        }

        int result = rosterService.insertRoster(idCard, name, gender, birthday, address, village, isMove, communityId, communityName, house, status, remark);
        if (result == 1) {
            return new ApiResult(true, "新增成功", 0);
        } else {
            return new ApiResult(false, "新增失败", -1);
        }
    }

    /**
     * 花名册查询
     *
     * @param name        姓名
     * @param idCard      身份证
     * @param communityId 社区ID
     * @param isMove      是否迁出 1：否  2：是
     * @param status      发放状态 1：未开始 2：发放中 3：已暂停 4：已退出
     * @param age         年龄
     * @param pageNum     页码
     * @param pageSize    每页数据
     * @return
     */
    @RequestMapping(value = "selectRoster")
    @ResponseBody
    public ApiResult selectRoster(String name, String idCard, Integer communityId, Integer isMove, Integer status, Integer age, Integer pageNum, Integer pageSize) {

        List<Roster> list = rosterService.selectRoster(name, idCard, communityId, isMove, status, age, pageNum, pageSize);

        Integer count = rosterService.selectRoster(name, idCard, communityId, isMove, status, age);

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        map.put("count", count);

        return new ApiResult(true, "新增成功", 0, map);
    }

    /**
     * 征地社会救济金
     *
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
    @RequestMapping(value = "selectRosterExamine")
    @ResponseBody
    public ApiResult selectRosterExamine(String house, String name, String idCard, Integer comping, Integer age, Integer changes, Integer status, Integer unemployment, Integer isInsured, Integer communityId, Integer pageSize, Integer pageNum) {

        List<Roster> list = rosterService.selectRosterExamine(house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId, pageSize, pageNum);

        Integer count = rosterService.selectRosterExamineCount(house, name, idCard, comping, age, changes, status, unemployment, isInsured, communityId);

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        map.put("count", count);

        return new ApiResult(true, "查询成功", 0, map);
    }

    /**
     * 查询到龄进入预警的花名册
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "selectStartWarning")
    @ResponseBody
    public ApiResult selectStartWarning(Integer pageNum, Integer pageSize) {

        List<Roster> list = rosterService.selectStartWarning(pageNum, pageSize);
        Integer count = rosterService.selectStartWarningCount();
        Map<String, Object> map = new HashMap<>();

        map.put("list", list);
        map.put("count", count);

        return new ApiResult(true, "查询成功", 0, map);
    }

    /**
     * 查询到龄退出预警的花名册
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "selectEndWarning")
    @ResponseBody
    public ApiResult selectEndWarning(Integer pageNum, Integer pageSize) {

        List<Roster> list = rosterService.selectEndWarning(pageNum, pageSize);
        Integer count = rosterService.selectEndWarningCount();
        Map<String, Object> map = new HashMap<>();

        map.put("list", list);
        map.put("count", count);

        return new ApiResult(true, "查询成功", 0, map);
    }

    /**
     * 查询待审核
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "selectExamine")
    @ResponseBody
    public ApiResult selectExamine(Integer pageNum, Integer pageSize) {
        List<Roster> list = rosterService.selectExamine(pageNum, pageSize);
        Integer count = rosterService.selectExamineCount();
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("count", count);
        return new ApiResult(true, "查询成功", 0, map);
    }

    /**
     * 查询待定人员名单
     *
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "selectUndetermined")
    @ResponseBody
    public ApiResult selectUndetermined(Integer pageSize, Integer pageNum) {
        List<Roster> list = rosterService.selectUndetermined(pageNum, pageSize);
        Integer count = rosterService.selectUndeterminedCount();
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("count", count);
        return new ApiResult(true, "查询成功", 0, map);
    }

    /**
     * 根据Id获取花名册信息
     *
     * @param rosterId 花名册ID
     * @return
     */
    @RequestMapping(value = "selectRosterById")
    @ResponseBody
    public ApiResult selectRosterById(Integer rosterId) {

        if (rosterId == null) {
            return new ApiResult(false, "请上传参数", -1);
        }

        Roster roster = rosterService.get(rosterId);

        if (roster == null) {
            return new ApiResult(false, "花名册信息不存在", -1);
        }

        int age = AgeUtils.getAgeFromBirthTime(DateUtil.getDateString("yyyy-MM-dd", roster.getBirthday()));

        roster.setAge(age);

        return new ApiResult(true, "查询成功", 0, roster);
    }


    /**
     * 根据花名册ID编辑
     *
     * @param rosterId      花名册ID
     * @param idCard        身份证
     * @param name          姓名
     * @param gender        性别 1：男 2：女
     * @param birthday      出生年月
     * @param address       常住地址
     * @param village       征地时所在村（组）
     * @param isMove        是否迁出 1：否  2：是
     * @param communityId   现所属社区Id
     * @param communityName 现所属社区名称
     * @param house         现户籍所在地
     * @param status        发放状态 1：未开始 2：发放中 3：已暂停 4：已退出
     * @param remark        备注
     * @return
     */
    @RequestMapping(value = "updateRosterById")
    @ResponseBody
    public ApiResult updateRosterById(Integer rosterId, String idCard, String name, Integer gender, String birthday, String address, String village, Integer isMove, Integer communityId, String communityName, String house, Integer status, String remark) {

        if (rosterId == null) {
            return new ApiResult(false, "请上传参数", -1);
        }

        Roster roster = rosterService.get(rosterId);

        if (roster == null) {
            return new ApiResult(false, "花名册信息不存在", -1);
        }

        if (!StringUtils.isBlank(idCard)) {
            if (!roster.getIdCard().equals(idCard)) {
                if (idCard.length() == 15 || idCard.length() == 18) {
                    if (!IdCardUtil.cardCodeVerifySimple(idCard)) {
                        return new ApiResult(false, "15位或18位身份证号码不正确", -1);
                    } else {
                        if (idCard.length() == 18 && !IdCardUtil.cardCodeVerify(idCard)) {
                            return new ApiResult(false, "18位身份证号码不符合国家规范", -1);
                        }
                    }
                } else {
                    return new ApiResult(false, "身份证号码长度必须等于15或18位", -1);
                }
                roster.setIdCard(idCard);
            }
        }

        if (!StringUtils.isBlank(name)) {
            if (!roster.getName().equals(name)) {
                roster.setName(name);
            }
        }
        if (gender != null) {
            if (roster.getGender() != gender) {
                roster.setGender(gender);
            }
        }
        if (!StringUtils.isBlank(birthday)) {
            if (!roster.getBirthday().equals(birthday)) {
                roster.setBirthday(DateUtil.getDateToString(birthday, "yyyy-MM-dd"));
            }
        }
        if (!StringUtils.isBlank(address)) {
            if (!roster.getAddress().equals(address)) {
                roster.setAddress(address);
            }
        }
        if (!StringUtils.isBlank(village)) {
            if (!roster.getVillage().equals(village)) {
                roster.setVillage(village);
            }
        }
        if (isMove != null) {
            if (roster.getIsMove() != isMove) {
                roster.setIsMove(isMove);
            }
        }
        if (communityId != null) {
            if (roster.getCommunityId() != communityId) {
                roster.setCommunityId(communityId);
            }
        }
        if (!StringUtils.isBlank(house)) {
            if (!roster.getHouse().equals(house)) {
                roster.setHouse(house);
            }
        }
        if (status != null) {
            if (roster.getStatus() != status) {
                roster.setStatus(status);
            }
        }
        if (!StringUtils.isBlank(remark)) {
            roster.setRemark(remark);
        }
        rosterService.update(roster);
        return new ApiResult(true, "编辑成功", 0);
    }


    /**
     * 花名册导入
     *
     * @param file
     * @param session
     * @return
     */
    @RequestMapping(value = "importRoster")
    @ResponseBody
    public ApiResult importRoster(@RequestParam(value = "file", required = false) CommonsMultipartFile file, HttpSession session) {
        try {
            if (file == null) {
                return new ApiResult(false, "请上传文件", -1, null);
            }
            String fileOriginalFilename = UUID.randomUUID() + file.getOriginalFilename().replace(",", "");
            String rootPath = session.getServletContext().getRealPath("/");
            String uploadPath = rootPath + "temporary/order/";
            File source = new File(uploadPath + fileOriginalFilename);
            file.transferTo(source);
            if (!FileUtil.checkExcelVaild(source)) {
                return new ApiResult(false, "文件格式不正确", null);
            }
            List<Roster> rosters = new ArrayList<Roster>();
            Workbook wb = null;
            Sheet sheet = null;
            Row row = null;
            String cellData = null;
            String cloumns[] = {"身份证号", "姓名", "性别", "出生年月", "常住地址", "征地时所在村（组）", "是否迁出", "现所属社区", "现户籍所在地", "发放状态", "备注信息"};
            wb = PoiTest.readExcel(source.getPath());
            int successCount = 0;
            int errorCount = 0;
            if (wb != null) {
                sheet = wb.getSheetAt(0);
                int rownum = sheet.getPhysicalNumberOfRows();
                for (int i = 2; i < rownum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        Roster temp = new Roster();
                        for (int j = 0; j < 12; j++) {
                            cellData = (String) PoiTest.getCellFormatValue(row.getCell(j));
                            if ("订单ID".equals(cloumns[j])) {
                                if (!StringUtils.isBlank(cellData.trim())) {
                                    temp.setId(Integer.parseInt(cellData.trim()));
                                }
                            }
                        }
                        rosters.add(temp);
                    } else {
                        return new ApiResult(false, "文件格式有误", -1, null);
                    }
                }
            }

            return new ApiResult(true, "共" + rosters.size() + "条记录，成功" + successCount + "条,失败" + errorCount + "条", 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ApiResult(false, "操作失败", -1);
    }

    /**
     * 获取所以社区
     *
     * @return
     */
    @RequestMapping(value = "findAllCommunity")
    @ResponseBody
    public ApiResult findAllCommunity() {
        List<Community> list = communityService.findAll();
        return new ApiResult(true, "操作成功", 0, list);
    }

}