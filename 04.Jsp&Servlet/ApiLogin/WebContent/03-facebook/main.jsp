<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="test.jsp"></script>
<script>
function Logout() {

    fbLogout();

    location.href = "test.jsp";

}


</script>
	

<body>

<a href="#none" title="로그아웃" onclick="Logout();">


main
</body>
</html>