package com.olx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	 @Override
	public void configure(HttpSecurity http) throws Exception {
		
		/*http.authorizeRequests()
		.antMatchers("/user").hasAnyRole("USER", "ADMIN")
		.antMatchers("/admin").hasAnyRole("ADMIN")
		.antMatchers("/all").permitAll()
		.and()
		.formLogin()
		;*/
		 
		 http.csrf().disable()
		     .authorizeRequests()
			.antMatchers("/user/authenticate").permitAll()
			.and()
			.formLogin()
			;
		
	}
	 
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{ //authentication
		
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception{
		return super.authenticationManager();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
