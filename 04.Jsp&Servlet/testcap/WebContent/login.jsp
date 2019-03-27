<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	
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
						window.location.replace("main.jsp");//여기서 가입페이지에결합
					}else{
						alert(result.desc);
						changeCaptcha();
					}
				}
			});
		});
	});
</script>
<body>
	<div id="div01">
	</div>
	<form id="form01">
		<input type="hidden" id="key" name="key">
		<input type="text" name="value">
		<button type="button" id="btn01">전송</button>
	</form>
		<input id="reLoad" type="button" value="새로고침" />
</body>
</html>