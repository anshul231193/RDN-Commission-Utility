package com.jwtweb.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class CustomFilter extends GenericFilterBean {
	 
    public void doFilter(
      ServletRequest request, 
      ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    	chain.doFilter(request, response);
        System.out.println(request.getParameter("j_username"));
    }

}