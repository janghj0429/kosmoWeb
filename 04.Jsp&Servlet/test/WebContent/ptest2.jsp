<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
</head>
	<script>
	//번역을 위해서 버튼 이벤트를 위해서 사용하는것
	
	function jsonSend(){
		var data = $("#tranForm").serialize();
		$.ajax({
			url:"/test/translate",
			type:"POST",
			data: data, //json 보내는 방법
			success:function(data){ //서블렛을 통한 결과값 수령
				console.log(data);
				//alert(data);
				
				//String 의 값을 object 형식으로 변환
				var result_obj = JSON.parse(data);
				console.log(result_obj);
				console.log(result_obj.message.result.translatedText);
				//결과값을 textarea에 넣기 위해
				
				$("#resultText").val(result_obj.message.result.translatedText);
			},
			error:function(e){
				console.log(e);
				alert("번역실패");
			}
		});
	}
	</script>
<body>

<form id="tranForm" name="tranForm">
	<textarea id="sendText" class="form-control" name="content" col="40" row="4"></textarea>
	<select name="source" class="custom-select custom-select-sm">
		<option value="ko" >한국어</option>
		<option value="en">영어</option>
	</select>
	<select name="target" class="custom-select custom-select-sm">
		<option value="en">영어</option>
		<option value="ko">한국어</option>
		<option value="de">독일어</option>
		<option value="es">스페인어</option>
		<option value="ru">러시아어</option>
	</select>
	<input type="button" onclick="jsonSend();" value="번역하기">
	<textarea id="resultText" class="form-control" name="content" col="40" row="4"></textarea>
</form>	
</body>
</html>