package com.goodee.app.config.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.ibatis.javassist.expr.Instanceof;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String message = "로그인 실패";
		
		log.error("Exception : {}", exception);
		
		if(exception instanceof BadCredentialsException) {
			// 비밀번호가 틀렸을 경우 BadCredentialsException이 나옴
			message = "비밀번호를 확인하세요";
		}
		
		if(exception instanceof InternalAuthenticationServiceException) {
			// id가 틀렸을 경우 InternalAuthenticationServiceException가 나옴
			message = "존재하지 않는 아이디입니다";
		}
		
		if(exception instanceof AccountExpiredException) {
			// 계정의 유효기간이 만료된 경우
			message = "유효기간이 만료되었습니다";
		}
		
		if(exception instanceof LockedException) {
			// 계정이 잠겼을 경우
			message = "사용할 수 없는 계정입니다. 관리자에게 문의하세요";
		}
		
		if(exception instanceof CredentialsExpiredException) {
			// 비밀번호가 만료되었을 경우
			message = "비밀번호의 유효기간이 종료되었습니다";
		}
		
		if(exception instanceof DisabledException) {
			// isEnabled가 false일 경우
			// 휴면계정으로 넘어갈 경우
			message = "휴면 계정입니다";
		}
		
		// 한글로 하면 깨지기 때문에 인코딩 과정을 넣어줌
		message = URLEncoder.encode(message, "UTF-8");
		
		response.sendRedirect("/member/login?message=" + message);
		
	}
}
