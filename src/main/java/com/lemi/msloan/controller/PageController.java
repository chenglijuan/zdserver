package com.lemi.msloan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2019/4/18.
 */
@Controller
@RequestMapping(value = "/page")
public class PageController {


    @RequestMapping(value = "header")
    public ModelAndView header(Integer loginId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/common/header");
        modelAndView.addObject("loginId",loginId);
        return modelAndView;
    }

    @RequestMapping(value = "footer")
    public ModelAndView footer() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/common/footer");
        return modelAndView;
    }

    @RequestMapping(value = "lefter")
    public ModelAndView lefter() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/common/lefter");
        return modelAndView;
    }


}
