package com.goodee.app.config.security;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.goodee.app.members.MemberVo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler{
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		// AccessToken 로그아웃 : 사용자의 AccessToken 필요
		MemberVo memberVo = (MemberVo)authentication.getPrincipal();
		log.info("*****authentication : {}", memberVo.getAccessToken());
		
		if(memberVo.getSns() == null) {
			response.sendRedirect("/");
			return;
		}
		if(memberVo.getSns().equals("kakao")) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer "+memberVo.getAccessToken());
			
			HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(headers);
			
			ResponseEntity<String> res = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/logout", req, String.class);
			log.info("================logout id : {}", res.getBody());
			response.sendRedirect("/");
		}
	}

}
