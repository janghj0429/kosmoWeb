<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%!
	String id, pw, name;
%>

<%
	request.setCharacterEncoding("UTF-8");
	
	name = request.getParameter("name");
	id = request.getParameter("id");
	pw = request.getParameter("pw");
	
	if(id.equals("abcde") && pw.equals("12345")){
		session.setAttribute("id",id);
		session.setAttribute("name",name);
		
		response.sendRedirect("welcome.jsp");
	}else{
		response.sendRedirect("login.html");
	}
%>


</body>
</html>