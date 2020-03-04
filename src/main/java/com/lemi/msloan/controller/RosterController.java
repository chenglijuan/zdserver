package com.lemi.msloan.controller;

import com.lemi.msloan.entity.Community;
import com.lemi.msloan.entity.Examine;
import com.lemi.msloan.entity.Roster;
import com.lemi.msloan.entity.User;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.CommunityService;
import com.lemi.msloan.service.ExamineService;
import com.lemi.msloan.service.RosterService;
import com.lemi.msloan.service.UserService;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequestMapping(value = "roster")
@Controller
public class RosterController {

    @Autowired
    private RosterService rosterService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private ExamineService examineService;

    @Autowired
    private UserService userService;

    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    @RequestMapping(value = "updateRosterPager")
    public ModelAndView updateRosterPager(Integer rosterId, Integer loginId) {
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
     * @param idCard      身份证
     * @param name        姓名
     * @param gender      性别 1：男 2：女
     * @param birthday    出生年月
     * @param address     常住地址
     * @param village     征地时所在村（组）
     * @param isMove      是否迁出 1：否  2：是
     * @param communityId 现所属社区Id
     * @param house       现户籍所在地
     * @param status      发放状态 1：未开始 2：发放中 3：已暂停 4：已退出
     * @param remark      备注
     * @return
     */
    @RequestMapping(value = "insertRoster")
    @ResponseBody
    public ApiResult insertRoster(Integer loginId, String idCard, String name, Integer gender, String birthday, String address, String village, Integer isMove, Integer communityId, String house, Integer status, String remark) {

        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }

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
            return new ApiResult(false, "请输入征地时所在村组", -1);
        }

        if (StringUtils.isBlank(house)) {
            return new ApiResult(false, "请输入现户籍所在地", -1);
        }

        if (communityId == null) {
            return new ApiResult(false, "请选择现所属社区", -1);
        }

        Roster item = rosterService.getByIdCard(idCard);
        if (item != null) {
            return new ApiResult(false, "身份证号码已存在", -1);
        }
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
        roster.setTime(new Date());
        int result = rosterService.save(roster);
        if (result == 1) {
            Integer rosterId = roster.getId();
            Examine examine = new Examine();
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
            examine.setTime(new Date());
            examine.setState(5);
            int res = examineService.save(examine);
            if (res == 1) {
                Integer examineId = examine.getId();
                Roster rosterItem = rosterService.get(rosterId);
                rosterItem.setExamineId(examineId);
                rosterService.update(rosterItem);
                return new ApiResult(true, "新增成功", 0);
            } else {
                rosterService.delete(rosterId);
                return new ApiResult(false, "新增失败", -1);
            }

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
    public ApiResult selectRoster(Integer loginId, String name, String idCard, Integer communityId, Integer isMove, Integer status, Integer age, Integer pageNum, Integer pageSize) {

        User user = userService.get(loginId);
        if (user != null) {
            if (user.getType().intValue() == 2) {
                Community community = communityService.selectByUserId(loginId);
                if (community != null) {
                    communityId = community.getId();
                }
            }
        }

        List<Roster> list = rosterService.selectRoster(name, idCard, communityId, isMove, status, age, pageNum, pageSize);

        Integer count = rosterService.selectRoster(name, idCard, communityId, isMove, status, age);

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        map.put("count", count);

        return new ApiResult(true, "新增成功", 0, map);
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
     * @param house         现户籍所在地
     * @param status        发放状态 1：未开始 2：发放中 3：已暂停 4：已退出
     * @param remark        备注
     * @return
     */
    @RequestMapping(value = "updateRosterById")
    @ResponseBody
    public ApiResult updateRosterById(Integer loginId, Integer rosterId, String idCard, String name, Integer gender, String birthday, String address, String village, Integer isMove, Integer communityId, String house, Integer status, String remark) {

        if (rosterId == null) {
            return new ApiResult(false, "请上传参数", -1);
        }

        Roster roster = rosterService.get(rosterId);
        Examine examine = examineService.get(roster.getExamineId());
        if (roster == null) {
            return new ApiResult(false, "花名册信息不存在", -1);
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
                examine.setIdCard(idCard);
            }
        }

        if (!StringUtils.isBlank(name)) {
            if (!roster.getName().equals(name)) {
                roster.setName(name);
                examine.setName(name);
            }
        }
        if (gender != null) {
            if (roster.getGender() != gender) {
                roster.setGender(gender);
                examine.setGender(gender);
            }
        }
        if (!StringUtils.isBlank(birthday)) {
            if (!roster.getBirthday().equals(birthday)) {
                roster.setBirthday(DateUtil.getDateToString(birthday, "yyyy-MM-dd"));
                examine.setBirthday(DateUtil.getDateToString(birthday, "yyyy-MM-dd"));
            }
        }
        if (!StringUtils.isBlank(address)) {
            if (!roster.getAddress().equals(address)) {
                roster.setAddress(address);
                examine.setAddress(address);
            }
        }
        if (!StringUtils.isBlank(village)) {
            if (!roster.getVillage().equals(village)) {
                roster.setVillage(village);
                examine.setVillage(village);
            }
        }
        if (isMove != null) {
            if (roster.getIsMove() != isMove) {
                roster.setIsMove(isMove);
                examine.setIsMove(isMove);
            }
        }
        if (communityId != null) {
            if (roster.getCommunityId() != communityId) {
                roster.setCommunityId(communityId);
                examine.setCommunityId(communityId);
            }
        }
        if (!StringUtils.isBlank(house)) {
            if (!roster.getHouse().equals(house)) {
                roster.setHouse(house);
                examine.setHouse(house);
            }
        }
        if (status != null) {
            if (roster.getStatus() != status) {
                roster.setStatus(status);
                examine.setStatus(status);
            }
        }
        if (!StringUtils.isBlank(remark)) {
            roster.setRemark(remark);
        }
        rosterService.update(roster);
        examineService.update(examine);
        return new ApiResult(true, "编辑成功", 0);
    }


    /**
     * 花名册导入
     *
     * @param file
     * @param session
     * @return
     */
    @RequestMapping(value = "importRoster", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String importRoster(Integer loginId, @RequestParam(value = "file", required = false) CommonsMultipartFile file, HttpSession session) {
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
            List<Community> communityList = communityService.findAll();
            final List<Roster> rosters = new ArrayList<Roster>();
            Workbook wb = null;
            Sheet sheet = null;
            Row row = null;
            String cellData = null;
            String cloumns[] = {"身份证号", "姓名", "性别", "出生年月", "常住地址", "征地时所在村组", "是否迁出", "现所属社区", "现户籍所在地", "发放状态", "备注信息"};
            wb = PoiTest.readExcel(source.getPath());
            if (wb != null) {
                sheet = wb.getSheetAt(0);
                int rownum = sheet.getPhysicalNumberOfRows();
                for (int i = 2; i < rownum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        Roster temp = new Roster();
                        for (int j = 0; j < 10; j++) {
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
                                    String communityName = cellData.trim();
                                    Community community = sss(communityList,communityName);
                                    if (community != null) {
                                        temp.setCommunityId(community.getId());
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
                        }
                        rosters.add(temp);
                    } else {
                        return "文件格式有误";
                    }
                }
            }
            int total = 0;
            for (int i=0;i<rosters.size();i++) {
                if (!StringUtils.isBlank(rosters.get(i).getIdCard())){
                    total ++;
                }else {

                }
            }
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<rosters.size();i++) {
                        Roster item = rosters.get(i);
                        if (!StringUtils.isBlank(item.getIdCard())){
                            Roster roster = rosterService.getByIdCard(item.getIdCard());
                            if (roster == null) {
                                Examine examine = new Examine();
                                examine.setIdCard(item.getIdCard());
                                examine.setName(item.getName());
                                examine.setGender(item.getGender());
                                examine.setBirthday(item.getBirthday());
                                examine.setAddress(item.getAddress());
                                examine.setVillage(item.getVillage());
                                examine.setIsMove(item.getIsMove());
                                examine.setCommunityId(item.getCommunityId());
                                examine.setHouse(item.getHouse());
                                examine.setRemark(item.getRemark());
                                examine.setStatus(item.getStatus());
                                examine.setTime(new Date());
                                examine.setState(5);
                                examineService.save(examine);
                                Integer examineId = examine.getId();
                                item.setExamineId(examineId);
                                item.setTime(new Date());
                                rosterService.save(item);
                            } else {
                                Examine examine = examineService.get(roster.getExamineId());
                                examine.setIdCard(item.getIdCard());
                                examine.setName(item.getName());
                                examine.setGender(item.getGender());
                                examine.setBirthday(item.getBirthday());
                                examine.setAddress(item.getAddress());
                                examine.setVillage(item.getVillage());
                                examine.setIsMove(item.getIsMove());
                                examine.setCommunityId(item.getCommunityId());
                                examine.setHouse(item.getHouse());
                                examine.setRemark(item.getRemark());
                                examine.setStatus(item.getStatus());
                                examineService.update(examine);
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
                            }
                        }
                    }
                }
            });
            return "数据正在导入，本次将新增或更新" + total + "条数据";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "操作失败";
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

    private Community sss(List<Community> communityList,String name){
        Community c = null;
        for (Community community:communityList){
            if (community.getName().equals(name.trim())){

                c = community;
                break;
            }
        }
        return c;
    }
}
