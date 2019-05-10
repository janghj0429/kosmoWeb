<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일 목록 보기</title>
</head>
<body>

	<h2>파일 목록 보기</h2>
	<ul>
	<c:forEach items="${fileMap}" var="file">
		<li>
			파일명 : <a href="download?fileName=${file.key}&oriFileName=test.png">${file.key}</a>
						&nbsp;&nbsp;
						<!-- 오리파일네임으로 다운로드하게 변경해보기 -->
			파일크기 : ${file.value}
			
		</li>
	</c:forEach>
	</ul>

</body>
</html>