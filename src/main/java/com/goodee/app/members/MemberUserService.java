package com.goodee.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberUserService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	
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

}
