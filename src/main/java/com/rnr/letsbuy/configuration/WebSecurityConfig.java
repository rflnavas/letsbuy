package com.rnr.letsbuy.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 
 * @author rafael.navas.ruiz
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) {
		AuthenticationManagerBuilderStrategy.userDetailsService(auth);
	}
	
	/*
	 * It is important to highlight that Spring security enables CSRF preotection by default.
	 * As a consequence if you try to connect to the H2 console through the browser you will face to 
	 * something like this:
	 * 
	 * Invalid CSRF Token 'null' was found on the request parameter '_csrf' or header 'X-CSRF-TOKEN'
	 * 
	 * The point is, we would like to enable CSRF selectively so that all pages in our application except the console are activated
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */

	@Override
	public void configure(HttpSecurity http) throws Exception {
		log.debug("Setting security and authorizations");
		http
			.csrf() 
				//These steps are required
				//1 - Ignore CSRF for console
				//We comment this out for testing app with a non-browser app; Postman
				//.ignoringAntMatchers("/console/**")
			.disable()
				/* 2- We also have to keep in mind the X-FRAME-OPTION problem. We
				 * will see that modern browser such as chrome will throw a
				 * message similar to Refused to display
				 * 'http://localhost:8080/console/help.jsp?jsessionid=
				 * 25a3ded29c74b15d37140d8b61eb7d51' in a frame because it set
				 * 'X-Frame-Options' to 'deny'.
				 * 
				 * This has to do with the fact that 'X-Frame-Options' header key is set to true 
				 * by default in Spring Security.
				 * 
				 * This is a must-read:
				 * https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#headers-frame-options
				 * 
				 * This video demonstrates this issue
				 * https://youtu.be/KWcckqnUGo8?t=192
				 */
			//.and()
				/*If we ommit these lines we'll see a blank page when we access the console with the following content
					<body>
					    Sorry, Lynx not supported yet
					</body>
					https://stackoverflow.com/questions/40165915/why-does-the-h2-console-in-spring-boot-show-a-blank-screen-after-logging-in
				 */
				.headers()
					.frameOptions()
					.sameOrigin()	
			.and()
			.authorizeRequests()
				.antMatchers("/resources/**")
					.permitAll()
//				.antMatchers("/api/review")
//					.hasAnyRole("ROLE_USER", "ROLE_GUEST")
//					.anyRequest().authenticated()
//				.antMatchers("/api/**")
//				.hasAnyRole("USER", "GUEST")
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/login?logout")
				.permitAll();
		
	}
	
	/**
	 * Provides different strategies for configuring user authentications
	 * 
	 * @author rafael.navas.ruiz
	 *
	 */
	@Component
	static class AuthenticationManagerBuilderStrategy{
		
		private static Logger log = LoggerFactory.getLogger(AuthenticationManagerBuilderStrategy.class);
		
		
		private static UserDetailsService userService;
		
		@Autowired
		AuthenticationManagerBuilderStrategy(UserDetailsService userService) {
			AuthenticationManagerBuilderStrategy.userService = userService;
		}
		
		public static void inMemoryAuthentication(AuthenticationManagerBuilder auth){
			try {
				log.info("Auth type : inMemoryAuthentication");
				auth.inMemoryAuthentication()
						.withUser("rafa").password("1234").roles("ADMIN", "USER")
						.and()
						.withUser("maria").password("5678").roles("GUEST", "USER");
			} catch (Exception e) {
				log.error("Error in WebSecurityConfig (inMemory)", e);
			}
		}
		
		public static void userDetailsService(AuthenticationManagerBuilder auth) {
			log.info("Auth type : userDetailsService");
			try {
				auth.userDetailsService(userService);
			} catch (Exception e) {
				log.error("Error in WebSecurityConfig (DB)", e);
			}
			
		}
	}
	
	
}
