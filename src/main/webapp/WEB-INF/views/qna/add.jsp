<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>QNA Add</h1>
	
	<form:form modelAttribute="qnaVO">
	<!-- path는 VO의 getter setter 이름 -->
		<form:input path="board_title"/><br>
		<form:errors path="board_title"></form:errors><br>
		<form:input path="board_writer"/><br>
		<form:errors path="board_writer"></form:errors><br>
		<form:textarea path="board_contents"/><br>
		<input type="file" name="attaches"><br>
		<input type="file" name="attaches"><br>
		<input type="file" name="attaches"><br>
		<button>Add</button>
	</form:form>
</body>
</html>