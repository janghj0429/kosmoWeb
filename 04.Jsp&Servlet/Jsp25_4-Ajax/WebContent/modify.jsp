<%@page import="com.study.jsp.MemberDto" %>
<%@page import="com.study.jsp.MemberDao" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id = (String)session.getAttribute("id");
	MemberDao dao = MemberDao.getInstance();
	MemberDto dto = dao.getMember(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
	function updateform_check(){
		if($('#pw').val().length == 0){
			alert("비밀번호는 필수사항입니다.");
			$('#pw').focus();
			return;
		}
		
		if($('#pw').val() != $('#pw_check').val()){
			alert("비밀번호가 일치하지 않습니다.");
			$('#pw').focus();
			return;
		}
		
		if($('#eMail').val().length == 0){
			alert("메일은 필수사항입니다.");
			$('#eMail').focus();
			return;
		}
		
		submit_ajax();	
	}
	
	function submit_ajax(){
		var queryString = $("#reg_frm").serialize();
		$.ajax({
			url:'modifyOk.do',
			type:'post',
			dataType:'text',
			//data:$('#my-form').serialize(),
			data: queryString,
			success:function(json){
				console.log(json);
				var result = JSON.parse(json);
				if(result.code =="success"){
					alert(result.desc)
					window.location.replace("main.jsp");
				}else{
					alert(result.desc);
					window.location.replace("main.jsp");
				}
			}
		});
	}

	</script>
</head>
<body>
	<form id="reg_frm">
		아이디 : <%= dto.getId() %><br>
		비밀번호 : <input type="password" name="pw" id="pw"size="20"><br>
		비밀번호 확인 : <input type="password" name="pw_check" id="pw_check" size="20"><br>
		이름 : <%= dto.getName() %><br>
		메일 : <input type="text" name="eMail" size="20" id="eMail" value="<%= dto.geteMail() %>"><br>
		주소 : <input type="text" name="address" size="50" id="address" value="<%= dto.getAddress() %>"><br>
		<input type="button" value="수정" onclick="updateform_check();">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="취소" onclick="javascript:window.location='main.jsp'">
	</form>
</body>
</html>