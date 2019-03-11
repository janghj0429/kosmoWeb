<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<form action="joinOk.do" method="post">
			아이디 : <input type="text" name="id" size="10"><br>
			비밀번호 : <input type="text" name="pw" size="10"><br>
			이름 : <input type="text" name="name" size="10"><br>
			
			메일 : <input type="text" name="eMail" size="20"><br>
			주소 : <input type="text" name="address" size="50"><br>
			<input type="submit" value="회원가입"><input type="reset" value="취소">
			<input type="reset" value="로그인" onclick="javascript:window.location='login.jsp'">
		</form>
	
</body>
</html>