<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTf-8">
	<title>Home</title>
</head>
<body>

<P>  createPage.jsp </P>

<%
	String conPath = request.getContextPath();
%>

<form action="<%=conPath %>/student/create">
	이름 : <input type="text" name="name" value="${student.name }"> <br/>
	아이디 : <input type="text" name="id" value="${student.id }"> <br/>
	<input type="submit" value="전송"> <br/>
</form>
</body>
</html>
