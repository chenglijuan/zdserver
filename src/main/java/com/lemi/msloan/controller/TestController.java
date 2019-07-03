package com.lemi.msloan.controller;

import com.lemi.msloan.entity.*;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.request.UserRequest;
import com.lemi.msloan.response.ApiResult;
import com.lemi.msloan.service.*;
import com.lemi.msloan.util.AgeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2019/4/18.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private RespectService respectService;

    @Autowired
    private RespectSummaryService respectSummaryService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private RespectStatisticService respectStatisticService;

    /**
     * 根据ID获取用户信
     *
     * @return
     */
    @RequestMapping(value = "test")
    @ResponseBody
    public ApiResult getUserByUserId() {

        try {
//            RespectRequest respectRequest = new RespectRequest();
//            List<Respect> list = respectService.selectRespectPager(respectRequest);
//           // Date current = new Date();
//            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String year = "";
//            String month = "";
//            String day = "";
//
//            for (Respect respect : list) {
//                //System.out.println("");
//                String idCard = respect.getIdCard();
//
//                if(idCard != null){
//
//                    year = idCard.substring(6, 10);
//                    month = idCard.substring(10, 12);
//                    day = idCard.substring(12, 14);
//
//                    respect.setBirthday(year +"-"+month +"-"+day);
//                }
//                respectService.update(respect);
//            }
//            System.out.println("----list.size()--"+list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResult(true, "登录成功", 0, null);
    }


    @RequestMapping(value = "test1")
    @ResponseBody
    public ApiResult test1() {
        try {
            List<Community> communityList = communityService.findAll();
            //获取所有社区的数据
            RespectRequest respectRequest = new RespectRequest();
            List<RespectStatistic> insertList = new ArrayList<RespectStatistic>();
            Date current = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            int range1Count = 0;
            int range2Count = 0;
            int range3Count = 0;
            int range4Count = 0;
            int totalCount = 0;

            BigDecimal range1Money = new BigDecimal(0);
            BigDecimal range2Money = new BigDecimal(0);
            BigDecimal range3Money = new BigDecimal(0);
            BigDecimal range4Money = new BigDecimal(0);
            BigDecimal totalMoney = new BigDecimal(0);
            // 审核通过的  发放中
            respectRequest.setAuditState(2);
            for (Community community : communityList) {
                Integer communityId = community.getId();
                respectRequest.setCommunityId(communityId);
                //城镇
                respectRequest.setType(1);
                List<Respect> townList = respectService.selectRespectPager(respectRequest);
                RespectStatistic townStatistic = new RespectStatistic();
                range1Count = 0;
                range2Count = 0;
                range3Count = 0;
                range4Count = 0;
                totalCount = 0;
                range1Money = new BigDecimal(0);
                range2Money = new BigDecimal(0);
                range3Money = new BigDecimal(0);
                range4Money = new BigDecimal(0);
                totalMoney = new BigDecimal(0);
                for (Respect respect : townList) {
                    // 城镇  range1Count = 0  range1Money =0
                    int age = AgeUtils.getAgeFromBirthTime(respect.getBirthday());
                    int issuStandard = AgeUtils.getIssuStandard(age, respect.getType());
                    if (80 <= age && age <= 89) {
                        range2Count++;
                        range2Money = range2Money.add(new BigDecimal(issuStandard));
                    } else if (90 <= age && age <= 99) {
                        range3Count++;
                        range3Money = range3Money.add(new BigDecimal(issuStandard));
                    } else if (100 <= age) {
                        range4Count++;
                        range4Money = range4Money.add(new BigDecimal(issuStandard));
                    }
                    totalCount++;
                }
                // if(townList != null && townList.size() > 0){
                totalMoney = range2Money.add(range3Money).add(range4Money);
                townStatistic.setRange1Count(range1Count);
                townStatistic.setRange1Money(range1Money);
                townStatistic.setRange2Count(range2Count);
                townStatistic.setRange2Money(range2Money);
                townStatistic.setRange3Count(range3Count);
                townStatistic.setRange3Money(range3Money);
                townStatistic.setRange4Count(range4Count);
                townStatistic.setRange4Money(range4Money);
                townStatistic.setTotalCount(totalCount);
                townStatistic.setTotalMoney(totalMoney);
                townStatistic.setType(1);
                townStatistic.setCreateTime(current);
                townStatistic.setSummaryMonth(current);
                townStatistic.setCommunityId(communityId);
                townStatistic.setCommunityName(community.getName());
                insertList.add(townStatistic);
                //}

                //农村

                respectRequest.setType(2);
                List<Respect> countryList = respectService.selectRespectPager(respectRequest);
                RespectStatistic countryStatistic = new RespectStatistic();
                range1Count = 0;
                range2Count = 0;
                range3Count = 0;
                range4Count = 0;
                totalCount = 0;
                range1Money = new BigDecimal(0);
                range2Money = new BigDecimal(0);
                range3Money = new BigDecimal(0);
                range4Money = new BigDecimal(0);
                totalMoney = new BigDecimal(0);

                for (Respect respect : countryList) {
                    // 农村
                    int age = AgeUtils.getAgeFromBirthTime(respect.getBirthday());
                    int issuStandard = AgeUtils.getIssuStandard(age, respect.getType());
                    if (70 <= age && age <= 79) {
                        range1Count++;
                        range1Money = range1Money.add(new BigDecimal(issuStandard));
                    }
                    if (80 <= age && age <= 89) {
                        range2Count++;
                        range2Money = range2Money.add(new BigDecimal(issuStandard));
                    } else if (90 <= age && age <= 99) {
                        range3Count++;
                        range3Money = range3Money.add(new BigDecimal(issuStandard));
                    } else if (100 <= age) {
                        range4Count++;
                        range4Money = range4Money.add(new BigDecimal(issuStandard));
                    }
                    totalCount++;
                }
                //if(countryList != null && countryList.size() > 0){
                totalMoney = range1Money.add(range2Money).add(range3Money).add(range4Money);
                countryStatistic.setRange1Count(range1Count);
                countryStatistic.setRange1Money(range1Money);
                countryStatistic.setRange2Count(range2Count);
                countryStatistic.setRange2Money(range2Money);
                countryStatistic.setRange3Count(range3Count);
                countryStatistic.setRange3Money(range3Money);
                countryStatistic.setRange4Count(range4Count);
                countryStatistic.setTotalCount(totalCount);
                countryStatistic.setRange4Money(range4Money);
                countryStatistic.setTotalMoney(totalMoney);
                countryStatistic.setType(2);
                countryStatistic.setCreateTime(current);
                countryStatistic.setSummaryMonth(current);
                countryStatistic.setCommunityId(communityId);
                countryStatistic.setCommunityName(community.getName());
                insertList.add(countryStatistic);
                // }
            }
            if (insertList != null && insertList.size() > 0) {
                respectStatisticService.insertBatchData(insertList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResult(true, "登录成功", 0, null);

    }
}