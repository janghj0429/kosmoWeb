<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
	
	function form_check(){
		if($('#id').val().length == 0){
			alert("아이디는 필수사항입니다.");
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
		
		if($('#pw').val() != $('#pw_check').val()){
			alert("비밀번호가 일치하지 않습니다.");
			$('#pw').focus();
			return;
		}
		
		if($('#name').val().length == 0){
			alert("이름은 필수사항입니다.");
			$('#name').focus();
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
			//url:'/Jsp212/joinOk.jsp',
			url:'/Jsp212/JoinProcess',
			type:'post',
			dataType:'text',
			//data:$('#my-form').serialize(),
			data: queryString,
			success:function(json){
				console.log(json);
				var result = JSON.parse(json);
				if(result.code =="success"){
					alert(result.desc)
					window.location.replace("login.jsp");
				}else{
					alert(result.desc);
				}
			}
		});
	}
	</script>

</head>
<body>
	
		<form id="reg_frm">
			아이디 : <input type="text" id="id" name="id" size="20"><br>
			비밀번호 : <input type="password" id="pw" name="pw" size="20"><br>
			비밀번호 확인 : <input type="password" id="pw_check" name="pw_check" size="20"><br>
			이름 : <input type="text" name="name" id="name" size="20"><br>
			메일 : <input type="text" name="eMail" id="eMail" size="20"><br>
			주소 : <input type="text" name="address" id="address" size="50"><br>
			<input type="button" value="회원가입" onclick="form_check();">&nbsp;&nbsp;&nbsp;
			<input type="button" value="로그인" onclick="javascript:window.location='login.jsp'">
		</form>
	
</body>
</html>