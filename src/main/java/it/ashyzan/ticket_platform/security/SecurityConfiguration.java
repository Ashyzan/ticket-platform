package it.ashyzan.ticket_platform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    
    @Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
                
		// qui va messa l'url da tenere sotto controllo
		// ... endpoints
	          //  .formLogin(formLogin -> formLogin.loginPage("/login.html")
	                
		.requestMatchers("/ticket/create/").hasAuthority("ADMIN")
		.requestMatchers(HttpMethod.POST, "/pizzeria/**").hasAuthority("ADMIN")
		.requestMatchers("/css/**", "/js/**", "/webjars/**", "/img/**").permitAll()
//		.requestMatchers("/admin").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers("/**").permitAll().and().formLogin().and().logout()

		.and().exceptionHandling().and().csrf().disable();
		
//csrf cross site request forgery in questo caso è disabilitato epr permettere la post
		return http.build();

	            

	}
	
	@Bean
	DatabaseUserDetailsService userDetailsService() {
	    return new DatabaseUserDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	authProvider.setUserDetailsService(userDetailsService());
	authProvider.setPasswordEncoder(passwordEncoder());
	return authProvider;
	}
	
	

}
