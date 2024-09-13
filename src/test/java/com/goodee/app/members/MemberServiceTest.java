package com.goodee.app.members;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MemberServiceTest {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void passwordUpdateTest() throws Exception{
		// admin, 123123 -> admin
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("admin");
		memberVo.setPassword("123123");
		String newpassword="admin";
		
		MemberVo check = memberMapper.detail(memberVo);
		
		log.info("MemberVO : {}", memberVo);
		log.info("check : {}", check);
				
		if(passwordEncoder.matches(memberVo.getPassword(), check.getPassword())) {
			log.info("===============성공=============");
		}
		
		assertEquals(memberVo.getPassword(), check.getPassword());
	}
	
	
//	@Test
//	void test() throws Exception{
//		MemberVo memberVo = new MemberVo();
//		memberVo.setUsername("admin");
//		memberVo.setPassword(passwordEncoder.encode("123123"));
//		int result = memberMapper.pwUpdate(memberVo);
//		assertEquals(1, result);
//	}

}
