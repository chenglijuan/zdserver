package com.lemi.msloan.task;

import com.lemi.msloan.entity.Respect;
import com.lemi.msloan.entity.RespectSummary;
import com.lemi.msloan.request.RespectRequest;
import com.lemi.msloan.service.RespectService;
import com.lemi.msloan.service.RespectSummaryService;
import com.lemi.msloan.util.AgeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: chenglijuan
 * @Data: 2019/5/11  下午3:50
 * @Decription:
 * @Modified:
 */
@Component("respectTaskJob")
public class RespectTaskJob {


    @Autowired
    private RespectService respectService;

    @Autowired
    private RespectSummaryService respectSummaryService;

    //@Scheduled(cron = "0 0 0 1/1 * ?") // 每天执行一次
    public void insertRespectSummary() {
        try {
            RespectRequest respectRequest = new RespectRequest();
            // 审核通过的  发放中
            respectRequest.setAuditState(2);
            respectRequest.setGrantState(2);
            List<RespectSummary> insertList = new ArrayList<RespectSummary>();
            List<Respect> list = respectService.selectRespectPager(respectRequest);
            Date current = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Respect respect:list) {
                RespectSummary respectSummary = new RespectSummary();
                respectSummary.setCommunityId(respect.getCommunityId());
                respectSummary.setRespectId(respect.getId());
                respectSummary.setSummaryMonth(current);
                //
                int age = AgeUtils.getAgeFromBirthTime(respect.getBirthday());
                int issuStandard = AgeUtils.getIssuStandard(age,respect.getType());
                respectSummary.setMoney(new BigDecimal(issuStandard));
                insertList.add(respectSummary);
            }
            if(insertList != null && insertList.size() > 0 ){
                respectSummaryService.insertBatchData(insertList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
