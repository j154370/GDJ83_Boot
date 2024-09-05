package com.goodee.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	
	public int add(MemberVo memberVo) throws Exception{
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
