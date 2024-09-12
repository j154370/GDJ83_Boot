package com.goodee.app.members;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.goodee.app.validate.MemberAddGroup;
import com.goodee.app.validate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberVo implements UserDetails, OAuth2User{
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String username;
	//@Pattern(groups= {MemberAddGroup.class}, regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}")
	@NotBlank(groups= {MemberAddGroup.class})
	private String password;
	private String passwordCheck;
	@NotBlank(groups= {MemberAddGroup.class, MemberUpdateGroup.class})
	private String name;
	@Email(groups= {MemberAddGroup.class, MemberUpdateGroup.class})
	private String email;
	@Past(groups= {MemberAddGroup.class, MemberUpdateGroup.class})
	private Date birth;
	private boolean enabled;
	private List<RoleVO> vos;
	// Oauth2User
	// token 정보 저장
	private Map<String, Object> attributes;
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO : vos) {
			GrantedAuthority authority = new SimpleGrantedAuthority(roleVO.getRole_name());
			authorities.add(authority);
		}
		
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	// 소셜 로그인 오버라이드
	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}
	
	
	

}
