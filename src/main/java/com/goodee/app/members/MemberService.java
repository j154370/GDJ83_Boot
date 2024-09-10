package com.goodee.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername(username);
		try {
			memberVo = memberMapper.detail(memberVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberVo;
	}


	// 검증 메서드
	public boolean memberValidate(MemberVo memberVo, BindingResult bindingResult) throws Exception{
		boolean check = false;
		// check = false면 검증 성공(에러 없음)
		// check = true면 검증 실패(에러 있음)
		
		// 0. 기본 검증값(어노테이션 검증의 결과)
		check = bindingResult.hasErrors();
		
		// 1. password일치 검증
		if(!memberVo.getPassword().equals(memberVo.getPasswordCheck())) {
			check = true;
			bindingResult.rejectValue("passwordCheck", "keyNameAnithing");
		}
		
		// 2. id 중복 검사
		MemberVo result = memberMapper.detail(memberVo);
		if(result != null) {
			check = true;
			bindingResult.rejectValue("username", "keyNameAnithing2");
		}
		
		return check;
	}
	
	
	public int add(MemberVo memberVo) throws Exception{
		
		// 회원가입시 DB에 비밀번호를 넣기 전에 암호화 하는 과정
		memberVo.setPassword(passwordEncoder.encode(memberVo.getPassword()));
		
		int result = memberMapper.add(memberVo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVo.getUsername());
		map.put("role_num", 1);
		
		result = memberMapper.addRole(map);
		
		return result;
	};
	
	public MemberVo detail(MemberVo memberVo)throws Exception{
		MemberVo result = memberMapper.detail(memberVo);
		
		if(result != null) {
			if(result.getPassword().equals(memberVo.getPassword())) {
				return result;
			}
		}
		
		return null;
	};
	

}
