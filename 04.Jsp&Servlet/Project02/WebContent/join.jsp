<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
	function mId_check(){
		
	}
	function form_check(){
		if($('#mid').val().length == 0){
			alert("아이디는 필수사항입니다.");
			$('#mid').focus();
			return;
		}
		
		if($('#mid').val().length < 4){
			alert("아이디는 4글자 이상이어야 합니다.");
			$('#mid').focus();
			return;
		}
		
		if($('#mpw').val().length == 0){
			alert("비밀번호는 필수사항입니다.");
			$('#mpw').focus();
			return;
		}
		
		if($('#mpw').val() != $('#mpw_check').val()){
			alert("비밀번호가 일치하지 않습니다.");
			$('#mpw').focus();
			return;
		}
		
		if($('#mname').val().length == 0){
			alert("이름은 필수사항입니다.");
			$('#mname').focus();
			return;
		}
		
		if($('#memail').val().length == 0){
			alert("메일은 필수사항입니다.");
			$('#memail').focus();
			return;
		}
		
		if($('#maddress').val().length == 0){
			alert("메일은 필수사항입니다.");
			$('#mmaddress').focus();
			return;
		}
		document.join_form.submit();
		//submit_ajax();
	}
	function changeCaptcha(){
		$.ajax({
			url : "cal.jsp",
			dataType:"json",
			success : function(data) {
				console.log(data.key);
				$("#key").val(data.key);
				$("#div01").html("<img src='captchaImage/"+data.captchaImageName+"'>");
			}
		});	
	}
	
	function btnOn(){
		var btn;
		btn = document.getElementById('btn');
		btn.disabled = false;
	}

	$(document).ready(function() {
		changeCaptcha();
		
		//$('#reLoad').click(function(){ changeCaptcha(); }); //새로고침버튼에 클릭이벤트 등록
		$('#reLoad').on("click",function(){
			changeCaptcha();
		});
		
		$("#btn01").on("click",function(){
			var form01Data = $("#form01").serialize();
			console.log(form01Data);
			$.ajax({
				url : "cal.jsp",
				data : form01Data,
				dataType:"json",
				success : function(data) {
					console.log(data);
					var result = data;
					if(result.code == "true" ){
						alert(result.desc);
						btnOn();
					}else{
						alert(result.desc);
						changeCaptcha();
					}
				}
			});
		});
	});
	function submit_ajax(){
		var queryString = $("#join_form").serialize();
		$.ajax({
			//url:'/Jsp212/joinOk.jsp',
			url:'/Project02/join.do',
			type:'post',
			dataType:'text',
			//data:$('#my-form').serialize(),
			data: queryString,
			success:function(json){
				console.log(json);
				var result = JSON.parse(json);
				if(result.code =="success"){
					alert(result.desc)
				}else{
					alert(result.desc);
					window.location.replace("join.jsp");
				}
			}
		});
	}
	</script>
	
	<style>
		div{
			width:800px;
			margin:2px auto;
		}
	</style>
	
    <title></title>
  </head>
  <body>
	<form id="join_form" name="join_form" action="join.do" method="post">
	
	  	<div class="form-group">
	      	<label for="inputId">ID</label>
	      	<input type="text" class="form-control" id="mid" name="mid" placeholder="ID">
	  	</div>
	  	<div class="form-group">
	      	<label for="inputPassword">Password</label>
	      	<input type="password" class="form-control" id="mpw" name="mpw" placeholder="Password">
	  	</div>
	  	<div class="form-group">
	      	<label for="inputPassword">Password check</label>
	      	<input type="password" class="form-control" id="mpw_check" name="mpw_check" placeholder="Password_check">
	  	</div>
	  	<div class="form-group">
	      	<label for="inputName">Name</label>
	      	<input type="text" class="form-control" name="mname" id="mname" placeholder="Name">
	  	</div>
	  	<div class="form-group">
	      	<label for="inputEmail">Email</label>
	      	<input type="email" class="form-control" id="memail" name="memail" placeholder="Email"><br/>
	  	</div>
	  	<div class="form-group">
	  	  	<label for="inputAddress">Address </label>
	    	<input type="text" class="form-control" name="maddress" id="maddress"  placeholder="Apartment, studio, or floor">
		</div>	  
	</form>
	
    <div id="div01" style="text-align:center;">
	</div>
	<form id="form01" style="text-align:center;">
		<input type="hidden" id="key" name="key">
		<input type="text" name="value" style="margin:0 auto;">
		<button type="button" class="btn btn-primary" id="btn01">전송</button>
		<input id="reLoad" class="btn btn-primary" type="button" value="새로고침" />
	</form>
	<div style="text-align:center;">
		<input type="button" id="btn" class="btn btn-primary" disabled="disabled" onclick="form_check();" value="Sign In">	  
	</div>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>