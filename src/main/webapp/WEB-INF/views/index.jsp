<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Index Page</h1>
	<img alt="" src="/images/dog5.png">
	
	<spring:message code="hello"></spring:message>
	<spring:message code="hello2" text="구르트"></spring:message>
	
	<sec:authorize access="!isAuthenticated()">
		<h1>Login 안함</h1>
		<a href="/member/login">Login</a>
		<a href="/oauth2/authorization/kakao?prompt=login">kakao 로그인</a>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<h1>Login 성공</h1>
		<sec:authentication property="principal" var="memberVO"/>
		<spring:message code="member.login.message"
		arguments="${memberVO.username },${memberVO.email }" argumentSeparator=","></spring:message>
		<c:forEach items="${memberVO.vos }" var="r">
			<h3>${r.role_name}</h3>
		</c:forEach>
		<a href="/member/logout">로그아웃</a>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ADMIN')">
		<h1>관리자 전용</h1>
	</sec:authorize>

</body>
</html>