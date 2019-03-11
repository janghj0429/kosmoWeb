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
		Cookie cookie = new Cookie("id",id);
		cookie.setMaxAge(60);
		response.addCookie(cookie);
		
		Cookie cookie2 = new Cookie("name",name);
		cookie2.setMaxAge(60);
		response.addCookie(cookie2);
		
		response.sendRedirect("welcome.jsp");
	}else{
		response.sendRedirect("login.heml");
	}
%>


</body>
</html>