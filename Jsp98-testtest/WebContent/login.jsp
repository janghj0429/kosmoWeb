<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script src="http://code.jquery.com/jquery.js"></script>
	<script>
	function form_check(){
		if($('#id').val().length == 0){
			alert("아이디를 입력하세요.");
			$('#id').focus();
			return;
		}
		
		if($('#id').val().length < 4){
			alert("아이디는 4글자 이상이어야 합니다.");
			$('#id').focus();
			return;
		}
		
		if($('#pw').val().length == 0){
			alert("비밀번호는 필수사항입니다.");
			$('#pw').focus();
			return;
		}
		submit_ajax();
	}
	function submit_ajax(){
		var queryString = $("#login_form").serialize();
		$.ajax({
			//url:'/Jsp212/joinOk.jsp',
			url:'/Jsp98/loginOk.do',
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
					alert(result.desc)
					window.location.replace("login.jsp");
				}
			}
		});
	}
	</script>
<body>

	<form name="login_form"  method="post">
		아이디 : <input type="text" name="id" id="id"
						value ="<% if(session.getAttribute("id") != null) 
										out.println(session.getAttribute("id"));
						%>"><br>
		비밀번호 : <input type="password" name="pw" id="pw"><br><p>
		<input type="button" value="로그인" onclick="form_check();">&nbsp;&nbsp;
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">
	</form>

</body>
</html>