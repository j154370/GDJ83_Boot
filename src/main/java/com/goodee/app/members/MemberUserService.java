package com.goodee.app.members;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberUserService extends DefaultOAuth2UserService implements UserDetailsService{
	
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
	
	// 소셜 로그인시 사용할 메서드
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		ClientRegistration registration = userRequest.getClientRegistration();
		
		String sns = registration.getRegistrationId();
		
		OAuth2User auth2User = super.loadUser(userRequest);
		
		if(sns.equals("kakao")) {
			auth2User = this.useKakao(auth2User);
		}
		if(sns.equals("naver")) {
			
		}
		
		return auth2User;
	}
	
	private OAuth2User useKakao(OAuth2User oauth2User) throws OAuth2AuthenticationException {
		log.error("=======================================");
		log.error("ID : {}", oauth2User.getName());
		log.error("Attribute : {}", oauth2User.getAttributes());
		log.error("Authorities : {}", oauth2User.getAuthorities());
		
		Map<String, Object> attribute = oauth2User.getAttributes();
		Map<String, Object> properties = (Map<String, Object>)attribute.get("properties");
		
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername(oauth2User.getName());
		memberVo.setName(properties.get("nickname").toString());
		
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRole_name("ROLE_USER");
		list.add(roleVO);
		memberVo.setVos(list);
		
		return memberVo;
	}

}
