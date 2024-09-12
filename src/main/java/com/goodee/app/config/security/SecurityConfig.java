package com.goodee.app.config.security;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.goodee.app.members.MemberUserService;

@Configuration
@EnableWebSecurity // 스프링에서 자체적으로 만든 security가 아니라, 우리가 직접 만든 security를 쓰겠다는 어노테이션
public class SecurityConfig {
	
	@Autowired
	private SecurityLoginSuccessHandler handler;
	
	@Autowired
	private SecurityLoginFailHandler handler2;
	
	@Autowired
	private SecurityLogoutSuccessHandler handler3;
	
	@Autowired
	private MemberUserService memberUserService;
	
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
									 //.defaultSuccessUrl("/")	// 로그인 성공시 가는 url
									 .successHandler(handler)
									 .failureHandler(handler2)
									 //.failureUrl("/member/login?message="+message) // 로그인 실패시 가는 url
									 // id에 해당하는 파라미터 이름, 디폴트는 username
									 //.usernameParameter("")
									 // password에 해당하는 파라미터 이름, 디폴트는 password
									 //.passwordParameter("")
									 .permitAll()
						)
				
				// 로그아웃 관련 설정
				.logout(
						logout ->
							logout
								  .logoutUrl("/member/logout") 	// 로그아웃으로 사용할 url 지정
								  .logoutSuccessHandler(handler3)
								  //.logoutSuccessUrl("/") 	// 로그아웃 성공시 이동할 url
								  .invalidateHttpSession(true) 	// true면 session 만료
								  //.deleteCookies(null) 쿠키 삭제
						)
				
				.rememberMe(
						remember ->
							remember
									.rememberMeParameter("rememberMe")
									.tokenValiditySeconds(600) // 토큰의 유효시간 설정
									.key("rememberMe") // 토큰 생성시 사용되는 값, 필수 값, 개발자가 원하는 값 설정
									.userDetailsService(memberUserService) // 인증절차(로그인) 진행할 UserDetailsService
									.authenticationSuccessHandler(handler) // 로그인 성공시 진행할 Handler
									.useSecureCookie(false)
						)
				
				.sessionManagement(
						sessionManager ->
							sessionManager
										  .maximumSessions(1) // 최대 허용 개수, -1은 무한대
										  // false는 기존 사용자 세션 만료, true는 새 사용자 세션 만료
										  .maxSessionsPreventsLogin(false)
										  // 세션이 만료되었을 경우 redirect할 URL
										  .expiredUrl("/member/login")
						)
				
				// 소셜 로그인
				.oauth2Login(
						oauth2 ->
							oauth2
								  .userInfoEndpoint(
										  	user -> user.userService(memberUserService)
										  )
						)
				
				
		
		
		;
		
		return security.build();
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
