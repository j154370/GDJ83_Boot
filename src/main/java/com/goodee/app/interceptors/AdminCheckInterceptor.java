package com.goodee.app.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.goodee.app.members.MemberVo;
import com.goodee.app.members.RoleVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		MemberVo memberVO = (MemberVo) request.getSession().getAttribute("member");

		if (memberVO == null) {
			response.sendRedirect("/member/login");
			return false;
		}

		for (RoleVO roleVO : memberVO.getVos()) {
			if (roleVO.getRole_num() == 3) {
				return true;
			}
		}

		request.setAttribute("msg", "권한이 필요합니다.");
		request.setAttribute("path", "/");
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
		view.forward(request, response);
		return false;

	}
}

