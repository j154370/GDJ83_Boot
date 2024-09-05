package com.goodee.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("add")
	public void add() throws Exception{
		
	}
	
	@PostMapping("add")
	public String add(MemberVo memberVo) throws Exception{
		
		int result = memberService.add(memberVo);
		
		return "redirect:/";
	}
	
	@GetMapping("login")
	public void login() throws Exception{
		
	}
	
	@PostMapping("login")
	public String login(Model model, MemberVo memberVo, HttpSession session) throws Exception{
		
		memberVo = memberService.detail(memberVo);
		
		if(memberVo != null) {
			session.setAttribute("member", memberVo);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
}
