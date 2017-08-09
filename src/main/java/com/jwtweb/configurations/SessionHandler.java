package com.jwtweb.configurations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SessionHandler extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.getSession().setMaxInactiveInterval(-1);
        
        response.addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        System.out.println("HI"+request.getRequestURL()+" "+request.getMethod());
        response.addHeader(HttpHeaders.CONTENT_TYPE, "application/jsonp");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
    	return true;
    }
}