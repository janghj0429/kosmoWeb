<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@page import="captcha.AudioCaptCha" %>
<%@page import="nl.captcha.Captcha" %>

    <%
        String getAnswer = request.getParameter("ans");
    	System.out.println(getAnswer);
        if ("y".equals(getAnswer)) {
               Captcha captcha = (Captcha)session.getAttribute(Captcha.NAME);
               getAnswer = captcha.getAnswer();
               out.print(getAnswer);
               return;
        }
        out.clear();
        pageContext.pushBody();
        new AudioCaptCha().getAudioCaptCha(request, response, getAnswer);
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