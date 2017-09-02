package com.jwtweb.controllers;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	public static int counter = 0;
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String welcome(HttpServletRequest request,@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Principal principal){
		
		if (error != null) {
			request.setAttribute("error", "Invalid username and password!");
		}
	
		if (logout != null) {
			request.setAttribute("msg", "You've been logged out successfully.");
		}
		
		if(principal != null){
			return "redirect:/welcome";
		}
		
		logger.debug("[welcome] counter : {}", counter);
		return "index";
	}
	
	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String defaultPage(Principal principal, Authentication auth, HttpServletRequest request) {
	
	  request.setAttribute("title", "Spring Security Login Form - Database Authentication");
	  request.setAttribute("message", "This is User page!");
	  if(principal == null){
		 return "redirect:/";
	  }else{
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  if (!(authentication instanceof AnonymousAuthenticationToken)) {
		      String currentUserName = (String) authentication.getCredentials();
		      System.out.println(currentUserName);
		  }
		  Collection<?extends GrantedAuthority> granted = auth.getAuthorities();
		  String role;
		  for(int i=0;i<granted.size();i++){
		        role = granted.toArray()[i] + "";
		        System.out.println(role);
		        //verify if user contain role to view dashboard page default
		        if(role.equals("ROLE_ADMINISTRATOR")){
		        	request.setAttribute("message", "This is Admin page!");
		      	  	return "admin/welcome";
		        }              
		    }
	  }
	  return "user/welcome";

	}
	
	@GetMapping("commisionDetails")
	public String viewCommision(Principal principal){
		
		System.out.println("shi bc!"+principal);
		if(principal != null){
			return "viewCommision";
		}
		
		return "redirect:/";
	}

}
