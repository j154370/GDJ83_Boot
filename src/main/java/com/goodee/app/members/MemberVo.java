package com.goodee.app.members;

import java.sql.Date;
import java.util.List;

import com.goodee.app.validate.MemberAddGroup;
import com.goodee.app.validate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberVo {
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String username;
	@Pattern(groups= {MemberAddGroup.class}, regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}")
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

}
