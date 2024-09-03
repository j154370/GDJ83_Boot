<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>QnA List</h1>
	
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
</body>
</html>