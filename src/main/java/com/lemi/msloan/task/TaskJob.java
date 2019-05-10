package com.lemi.msloan.task;

import com.lemi.msloan.entity.Examine;
import com.lemi.msloan.entity.ExamineStatistic;
import com.lemi.msloan.service.ExamineService;
import com.lemi.msloan.service.ExamineStatisticService;
import com.lemi.msloan.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.servlet.ServletContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component("taskJob")
public class TaskJob {


    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ExamineStatisticService examineStatisticService;

    @Autowired
    private ExamineService examineService;

    @Scheduled(cron = "0 0 0 1/1 * ?") // 每天执行一次
    public void job1() {

        Date date = new Date();

        if (isLastDayOfMonth(date)){
            //获取所有发放中的征地人员
            List<Examine> list = examineService.getByStatus();
            ExamineStatistic examineStatistic = null;
            String today = DateUtil.getDateString("yyyy-MM-dd",new Date());
            for (Examine examine:list){
                examineStatistic = new ExamineStatistic();
                examineStatistic.setExamineId(examine.getId());
                examineStatistic.setCommunityId(examine.getCommunityId());
                examineStatistic.setMoney(180.0);
                examineStatistic.setTime(DateUtil.getDateToString(today,"yyyy-MM-dd"));
                examineStatisticService.save(examineStatistic);
            }
        }
    }

    /**
     * 判断传入的时间是否是月末
     * @param date
     * @return
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

}