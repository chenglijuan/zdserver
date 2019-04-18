package com.lemi.msloan.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.servlet.ServletContext;

@Component("taskJob")
public class TaskJob {


    @Autowired
    private ServletContext servletContext;

   /* @Scheduled(cron = "0 0 0/1 * * ?")
    public void job1() {
        try {
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            ServletContext servletContext = webApplicationContext.getServletContext();
            String accessToken = WeixinUtils.getAccessToken();
            servletContext.setAttribute("accessToken", accessToken);
            String jsapiTicket =WeixinUtils.getTicket(accessToken);
            servletContext.setAttribute("jsapiTicket", jsapiTicket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/



    //@Scheduled(cron = "0 0 0/1 * * ?")
    @Scheduled(cron = "0 0 0/1 * * ?") // 每隔1个小时执行一次
    public void job1() {
        try {
            servletContext.setAttribute("accessToken","");
            servletContext.setAttribute("accessToken_jssdk","");
            servletContext.setAttribute("jsapiTicket","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}