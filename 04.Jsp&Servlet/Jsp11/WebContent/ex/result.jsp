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
	String name, id, operator;
	int result;
%>

<%
	request.setCharacterEncoding("UTF-8");
	
	name = request.getParameter("name");
	id = request.getParameter("id");
	operator = request.getParameter("major");

	
	if(operator.equals("+")){
		result = Integer.parseInt(name) + Integer.parseInt(id);
		out.println("두수의 합은 " + result);
	}
	if(operator.equals("-")){
		result = Integer.parseInt(name) - Integer.parseInt(id);
		out.println("두수의 차는 " + result);
	}
	if(operator.equals("*")){
		result = Integer.parseInt(name) * Integer.parseInt(id);
		out.println("두수의 곱은 " + result);
	}
	if(operator.equals("/")){
		result = Integer.parseInt(name) / Integer.parseInt(id);
		out.println("두수의 나누기는 " + result);
	}
%>

<br>
결과는 <%= result %>입니다. <br><br>

<a href="form2.html">다시 계산하기</a>

</body>
</html>