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
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)//Nos permite escribir anotacion para controlar el acceso a los metodos segun la seguridad
public class SecurityConfiguration 
  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userService;

	//Servira para añadire el userDetails que se creo con anterioridad
	@Autowired
	public void configureClobal(AuthenticationManagerBuilder auth) throws Exception{
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);

		http.authorizeRequests()
			.antMatchers( "/", "/about", "hoteles").permitAll()
			.antMatchers("/css/*","/img/*","/js/*").permitAll()
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
		
		http.addFilterBefore(filter,CsrfFilter.class);
		
	}

}
