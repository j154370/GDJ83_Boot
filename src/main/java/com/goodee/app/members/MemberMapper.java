package com.goodee.app.members;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	public int add(MemberVo memberVo) throws Exception;
	
	public MemberVo detail(MemberVo memberVo)throws Exception;
	
	public int addRole(Map<String, Object> map)throws Exception;

}
