package com.jwtweb.conf.security;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jwtweb.customauthenticationfilter.CustomAuthenticationProvider;
import com.jwtweb.customauthenticationfilter.CustomUsernamePasswordAuthenticationFilter;
import com.jwtweb.filter.CustomFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
		
	@Autowired
    CustomAuthenticationProvider authenticationProvider;

	@Override
    protected void configure(
      AuthenticationManagerBuilder auth) throws Exception {
  
        auth.authenticationProvider(authenticationProvider);
    }
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from user_roles where username=?");
	}
	
//    @Override
//    protected void configure(
//      AuthenticationManagerBuilder auth) throws Exception {
//  
//        auth.authenticationProvider(authProvider);
//    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
//		http.authorizeRequests()
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
//		
//	    http.formLogin().loginProcessingUrl("/checkLogin")
//			.defaultSuccessUrl("/welcome")
//			.loginPage("/").failureUrl("/?error=true")
//			.usernameParameter("username").passwordParameter("password");
//	    
//		http.logout().logoutSuccessUrl("/?logout=true");
//		http.exceptionHandling().accessDeniedPage("/403");
//		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMINISTRATOR')");
		http.formLogin().loginProcessingUrl("/checkLogin")
			.defaultSuccessUrl("/welcome")
			.loginPage("/").failureUrl("/?error=true")
			.usernameParameter("j_username").passwordParameter("j_password");
	    http.logout().logoutSuccessUrl("/?logout=true");
		http.exceptionHandling().accessDeniedPage("/403");
		http.csrf().disable();
		

	}

}
