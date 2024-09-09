package com.goodee.app.members;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/member/*")
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
	
	@GetMapping("mypage")
	public void mypage() throws Exception{
		
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
