<%@page import="org.json.simple.JSONArray" %>
<%@page import="org.json.simple.JSONObject" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="uploadOk.jsp" method="post" enctype="multipart/form-data">
		파일 : <input type="file" name="filename"><br />
		<input type="submit" value="File Upload">
	</form>

</body>
</html>