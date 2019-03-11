
<%@page import="java.sql.Connection" %>
<%@page import="com.study.jsp.MemberDto" %>
<%@page import="com.study.jsp.MemberDao" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
   		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>

	
	
	<form action="modifyOk.do" method="post">
		아이디 : <%= id %><br>
		비밀번호 : <input type="text" name="pw" size="10"><br>
		
		이름 :<%= name %><br>
		메일 : <input type="text" name="eMail" size="20"><br>
		주소 : <input type="text" name="address" size="50"><br>

		<input type="submit" value="정보수정">	    
		
	</form>

</body>
</html>