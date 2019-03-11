<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:forward page="forward_param.jsp">
		<jsp:param name="id" value="ababab"/>
		<jsp:param name="pw" value="1112233"/>
	</jsp:forward>

</body>
</html>