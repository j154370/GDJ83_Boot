<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>my page</h1>
	
	<sec:authentication property="principal" var="memberVO"/>
	<h3>${memberVO.username }</h3>
	<h3>${memberVO.name }</h3>
	<h3><sec:authentication property="principal.email"/></h3>
	<h3><sec:authentication property="name"/></h3>
	<a href="/member/update">회원 수정</a>
</body>
</html>