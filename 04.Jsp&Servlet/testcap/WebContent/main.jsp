<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
메인페이지!!

<%=request.getParameter("mid")%>
<%=request.getParameter("mpw")%>
<%=request.getParameter("mname")%>
<%=request.getParameter("memail")%>
<%=request.getParameter("maddress")%>

</body>
</html>