<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><spring:message code="board.name"></spring:message></h1>
	
	<table>
		<thead>
			<tr>
				<th>num</th>
				<th>title</th>
				<th>writer</th>
				<th>date</th>
			</tr>
		</thead>
		<tbody>
			<C:forEach items="${list }" var="vo">
				<tr>
					<th>${vo.board_num}</th>
					<th>${vo.board_title}</th>
					<th>${vo.board_writer}</th>
					<th>${vo.create_date}</th>
				</tr>
			</C:forEach>
		</tbody>
	</table>
	<a href="./add">ADD</a>
</body>
</html>