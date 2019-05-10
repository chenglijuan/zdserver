package com.lemi.msloan.task;

import com.lemi.msloan.entity.Examine;
import com.lemi.msloan.service.ExamineService;
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
    private ExamineService examineService;

    @Scheduled(cron = "0 0 0 1/1 * ?") // 每天执行一次
    public void job1() {

        Date date = new Date();

        if (isLastDayOfMonth(date)){

            //获取所有发放中的征地人员
            List<Examine> list = examineService.getByStatus();




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