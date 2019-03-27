<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="captcha.CaptCha"%>
    
    
    <%
            	try{

                    out.clear();
            		System.out.println(request.getParameter("rand"));

                    pageContext.pushBody();

                    new CaptCha().getCaptCha(request, response);
                    System.out.println("캡차이미지.jsp 정상");
            	} catch(Exception e){
            		System.out.println("캡차이미지.jsp 익셉션");
                    e.printStackTrace();

            	}
            %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>