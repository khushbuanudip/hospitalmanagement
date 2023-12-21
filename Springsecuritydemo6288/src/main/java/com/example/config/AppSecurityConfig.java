package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.service.Impl.MyUserDetailService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {
	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailService();
	}

//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//			throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}

	@Bean

	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeHttpRequests().requestMatchers("/users/**").permitAll().and()
//				.authorizeHttpRequests().requestMatchers("/students/**").hasAnyAuthority("Admin")
//				.authenticated().and().authorizeHttpRequests()
//				.requestMatchers("/teachers/**").authenticated().and().formLogin().and().httpBasic();

		http.csrf().disable().authorizeHttpRequests().requestMatchers("/users/**").permitAll().and()
				.authorizeHttpRequests()

				.requestMatchers(HttpMethod.GET, "/students/**").hasAnyAuthority("ROLE_USER")
				.requestMatchers(HttpMethod.GET, "/teachers/**").hasAnyAuthority("ROLE_ADMIN").anyRequest()
				.authenticated().and().formLogin().permitAll().and().httpBasic();

		return http.build();
	}
}
