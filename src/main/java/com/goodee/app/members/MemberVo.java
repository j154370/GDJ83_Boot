package com.goodee.app.members;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVo {
	
	private String username;
	private String password;
	private String name;
	private String email;
	private Date birth;
	private boolean enabled;
	private List<RoleVO> vos;

}
