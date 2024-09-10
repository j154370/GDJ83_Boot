package com.goodee.app.members;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MemberServiceTest {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void test() throws Exception{
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("mindllre96");
		memberVo.setPassword(passwordEncoder.encode("mindllre96"));
		int result = memberMapper.pwUpdate(memberVo);
		assertEquals(1, result);
	}

}
