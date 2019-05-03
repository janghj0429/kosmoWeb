<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<P>  Test : ${name}. </P>

<%
	String name = (String)request.getAttribute("name");
%>
jsp 로 <%=name %>
<p> <img src="resources/img/desert.png"></p>

<p> <img src="resources01/img/image4.png"></p>


</body>
</html>
