package com.jwtweb.configurations;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider 
			implements AuthenticationProvider{

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
		System.out.println("HI"+name+" "+password);
        return new UsernamePasswordAuthenticationToken(
	              name, password);
	}

	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(
		      UsernamePasswordAuthenticationToken.class);
	}

}
