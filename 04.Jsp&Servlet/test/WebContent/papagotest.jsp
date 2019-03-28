<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
</head>
<body>
	<textarea id="send_text" class="form-control" name="content" col="40" row="4"></textarea>
	<button id="jsonConvertStringSend" type="button" onclick="jsonConvertStringSend();">번역하기</button>
	<textarea id="result_text" class="form-control" name="content" col="40" row="4"></textarea>
	
	<script>
	//번역을 위해서 버튼 이벤트를 위해서 사용하는것
	function jsonConvertStringSend(){
		var test = {
				"original_str":document.getElementById("send_text")
		};
		jsonSend(test);
	}
	
	
	function jsonSend(test){
		$.ajax({
			url:"test/tran",
			type:"POST",
			data: test, //json 보내는 방법
			dataType: "json",
			success:function(data){ //서블렛을 통한 결과값 수령
				console.log(data);
				//alert(data);
				
				//String 의 값을 object 형식으로 변환
				var result_obj = JSON.parse(data);
				
				//결과값을 textarea에 넣기 위해
				$("#result_text").val(result_obj.message.result.translatedText);
			},
			error:function(e){
				console.log(e);
				alert("번역실패");
			}
		});
	}
	</script>
</body>
</html>