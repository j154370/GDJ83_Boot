package com.goodee.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.goodee.app.interceptors.AdminCheckInterceptor;
import com.goodee.app.interceptors.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 어떤 url이 왔을 때 어떤 interceptor를 실행할 것인지 설정
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/qna/*")
				.excludePathPatterns("/qna/list");
		
		registry.addInterceptor(adminCheckInterceptor)
				.addPathPatterns("/admin/*");
	}
	
	

}
