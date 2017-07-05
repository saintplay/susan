package com.susan.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration 
  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userService;

	@Autowired
	public void configureClobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers( "/", "/about").permitAll()
			.antMatchers("/css/*","/img/*","/js/*").permitAll()
			.antMatchers("/reservas").hasAnyRole("USER, ADMIN")
			.antMatchers("/reservas/marcarpagado").hasAnyRole("ADMIN")
			.antMatchers("/hoteles", "/hoteles/**").hasAnyRole("ADMIN")
			.antMatchers("/habitaciones", "/habitaciones/**").hasAnyRole("ADMIN")
			.antMatchers("/reservar","/reservar/*").hasAnyRole("USER")
			.anyRequest().authenticated()
			.and()
			
			.formLogin().loginPage("/login").
			loginProcessingUrl("/logincheck")
			.usernameParameter("username").
			passwordParameter("password")
			.defaultSuccessUrl("/login/loginsuccess").permitAll()
			.and()
			
			.logout().logoutUrl("/logout").
			logoutSuccessUrl("/login?logout")
			.permitAll();		
	}

}
