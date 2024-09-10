package com.goodee.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링에서 자체적으로 만든 security가 아니라, 우리가 직접 만든 security를 쓰겠다는 어노테이션
public class SecurityConfig {
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		
		// Security에서 제외할 파일들
		return web -> web
						.ignoring()
						.requestMatchers("/images/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vendor/**")
						.requestMatchers("/favicon/**");
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
		
		security
				.cors()
				.and()
				.csrf()
				.disable();
		
		// 권한에 관련된 설정
		security.authorizeHttpRequests(
					(authorizeRequest)->
						authorizeRequest
							.requestMatchers("/").permitAll()
							.requestMatchers("/qna/list").permitAll()
							.requestMatchers("/qna/*").authenticated()
							.requestMatchers("/notice/list", "/notice/detial").permitAll()
							.requestMatchers("/notice/*").hasRole("ADMIN")
							.requestMatchers("/manager/*").hasAnyRole("MANAGER", "ADMIN")
							.requestMatchers("/member/add", "/member/login").permitAll()
							.requestMatchers("/member/*").authenticated()
							.anyRequest().permitAll()					
				) // authorizeHttpRequest 종료
		
				  // form 로그인 관련 설정
				.formLogin(
							login ->
								login
									 .loginPage("/member/login") // 내가 만든 로그인 페이지 주소
									 .defaultSuccessUrl("/")	// 로그인 성공시 가는 url
									 .failureUrl("/member/login") // 로그인 실패시 가는 url
									 // id에 해당하는 파라미터 이름, 디폴트는 username
									 //.usernameParameter("")
									 // password에 해당하는 파라미터 이름, 디폴트는 password
									 //.passwordParameter("")
									 .permitAll()
						)
				
		
		
		;
		
		return security.build();
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
