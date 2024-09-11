package com.goodee.app.members;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goodee.app.validate.MemberAddGroup;
import com.goodee.app.validate.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("add")
	public void add(MemberVo memberVo) throws Exception{
		
	}
	
	@PostMapping("add")
	public String add(@Validated(MemberAddGroup.class) MemberVo memberVo, BindingResult bindingResult) throws Exception{
		
		boolean check = memberService.memberValidate(memberVo, bindingResult);
		
		if(check) {
			return "/member/add";
		}
		
		int result = memberService.add(memberVo);
		
		return "redirect:/";
	}
	
	@GetMapping("login")
	public String login() throws Exception{
		
		SecurityContext context = SecurityContextHolder.getContext();
		if(context == null) {
			return "member/login";
		}
		
		String user = context.getAuthentication().getPrincipal().toString();
		if(user.equals("anonymousUser")) {
			return "member/login";
		}
		
		return "redirect:/";
	}
	
//	@PostMapping("login")
//	public String login(Model model, MemberVo memberVo, HttpSession session) throws Exception{
//		
//		memberVo = memberService.detail(memberVo);
//		
//		if(memberVo != null) {
//			session.setAttribute("member", memberVo);
//		}
//		
//		return "redirect:/";
//	}
	
	
	@GetMapping("mypage")
	public void mypage(HttpSession session) throws Exception{
		Enumeration<String> en = session.getAttributeNames();
		
		while(en.hasMoreElements()) {
			String name = en.nextElement();
			log.info("Name : {} ", name);
		}
		// 로그인한 사용자 정보를 꺼내오는 방법 1.
		SecurityContextImpl sc = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.info("SC : {} ", sc);
		
		// 2. 이렇게도 가능하다
		SecurityContext context = SecurityContextHolder.getContext();
		
		Authentication authentication = context.getAuthentication();
		
		// VO로 꺼내오는 방법
		MemberVo memberVo = (MemberVo)authentication.getPrincipal();
		
		// 아이디만 바로 꺼내오는 방법
		String username = authentication.getName();
	}
	
	@GetMapping("update")
	public String update(HttpSession session, Model model) throws Exception{
		
		MemberVo memberVo = (MemberVo)session.getAttribute("member");
		model.addAttribute("memberVo", memberVo);
		
		return "member/update";
	}
	
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVo memberVo, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()) {
			return "/member/add";
		}
		
		return "redirect:/member/mypage";
	}
}
