package com.lemi.msloan.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2018/3/19.
 */
@Component
public class HandFilter implements Filter {
    private String excludedPage;
    private String[] excludedPages;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPage = filterConfig.getInitParameter("excludedPages");
        if (excludedPage != null && excludedPage.length() > 0) {
            excludedPages = excludedPage.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        boolean flag = false;
        for (String page : excludedPages) {
            if (request.getServletPath().equals(page)) {
                flag = true;
            }
        }
        if (flag) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String userId = (String) session.getAttribute("loginId");
            System.out.println("当前登录用户loginId" + userId);
            if (!StringUtils.isBlank(userId)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("/user/userLoginPage");
                return;
            }
        }
    }

    @Override
    public void destroy() {

    }
}
