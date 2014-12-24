package org.softserveinc.interceptor;

import org.softserveinc.service.UserProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor extends HandlerInterceptorAdapter{

    @Inject
    UserProvider userProvider;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (null == modelAndView){
            return;
        }
    modelAndView.addObject(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }


}
