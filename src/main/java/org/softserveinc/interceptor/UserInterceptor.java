package org.softserveinc.interceptor;

import org.softserveinc.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Scorpio
 * Date: 09.12.14
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
public class UserInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (null == modelAndView){
            return;
        }
    modelAndView.addObject(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }


}
