<%@ page import="java.io.File" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>파일목록보기</h2>
	<ul>
	<c:forEach items="${fileMap }" var="file">
		<li>
			파일명 : 
			<a href="download?fileName=${file.key }&oriFileName=test.png">${file.key }</a>
					&nbsp;&nbsp;
			파일크기 : ${file.value }
		</li>	
	</c:forEach>
	</ul>
	
	<hr>
	<h2>파일업로드</h2>
	<a href="./uploadForm">파일업로드폼 바로가기</a>
</body>
</html>