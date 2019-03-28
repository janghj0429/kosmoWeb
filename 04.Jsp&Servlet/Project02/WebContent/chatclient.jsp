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
	
	<style>
		#messages{
			height:400px;
			border:1px;
			padding:5px;
			overflow:auto;
		}
	</style>
	
	<script type="text/javascript" language="javascript">
		function enterkey(){
			if(window.event.keyCode == 13){
				send();
				$("#messageinput").val("");
			}	
		}
	
	</script>
	
    <title></title>
    
    <%
    request.setCharacterEncoding("UTF-8");
	String chatName = request.getParameter("chatName");
	String userId = (String)session.getAttribute("mId");
	if(chatName == null){
	%>
		<jsp:forward page="chatlogin.jsp"/>
	<%
	}else{
		session.setAttribute("chatName", chatName);
		request.setAttribute("chatName", chatName);
	}
	%>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="main.jsp">
	  	<img src="Desert.png" width="30" class="d-inline-block align-top" alt="">Jhj</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="filelist.do">자료실</a>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          게시판
	        </a>
	        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item" href="wholelist.do">전체게시판</a>
	          <a class="dropdown-item" href="list.do?bCategory=0"<%session.setAttribute("bCategory", 0);%>>공지사항</a>
	          <a class="dropdown-item" href="list.do?bCategory=1"<%session.setAttribute("bCategory", 1);%>>자유게시판</a>
	          <div class="dropdown-divider"></div>
	          <a class="dropdown-item" href="list.do?bCategory=2"<%session.setAttribute("bCategory", 2);%>>etc</a>
	        </div>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="secession.jsp">회원탈퇴</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="chatlogin.jsp" >채팅</a>
	      </li>
	    </ul>
	    <form class="form-inline my-2 my-lg-0" action="search.do" method="post">
	    	<select name="column" class="custom-select custom-select-sm">
					<option value="bName" >이름</option>
					<option value="bContent" >내용</option>
					<option value="bTitle" >제목</option>
			</select>
		    <input class="form-control mr-sm-2" type="search" name="word" value="" placeholder="Search" aria-label="Search">
		    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	    </form>
	  </div>
	</nav>
    <table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th>채팅창</th>
	      <th></th>
	      <th></th>
	      <th></th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th colspan="4">
	      <!-- Server responses get written here -->
	      	<div id="messages"></div>
	      </th>
	    </tr>
	  </tbody>
	</table>
	
	<table class="table">
	  <thead class="thead-light">
	    <tr>
	      <th>아이디</th>
	      <th><%= userId %></th>
	      <th>대화명</th>
	      <th><%=chatName %></th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      	<th>입력</th>
	      	<td colspan="3"><input class="btn btn-outline-dark" type="text" id="messageinput" onkeyup="enterkey();" /></td>
	    </tr>
	    <tr>
	    	<form id="tranForm" name="tranForm">
			<td><textarea id="sendText" class="form-control" name="content" col="40" row="4"></textarea></td>
			<td>
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
			</td>
			<td>
				<input type="button" onclick="jsonSend();"class="btn btn-outline-dark" value="번역하기">
			</td>
			<td>
				<textarea id="resultText" class="form-control" name="content" col="40" row="4"></textarea>
			</td>
			</form>	 
	    </tr>
	    <tr>
	      <td><button class="btn btn-outline-dark"  type="button" onclick="openSocket();">open</button></td>
	      <td><button class="btn btn-outline-dark"  type="button" onclick="send();">send</button></td>
	      <td><button class="btn btn-outline-dark"  type="button" onclick="closeSocket();">close</button></td>
	      <td><button class="btn btn-outline-dark"  type="button" onclick="exit();">exit</button></td>
	    </tr>
	    
	  </tbody>
	</table>


	
	<script src="http://code.jquery.com/jquery.js"></script>
	
	<script>
	$(document).ready(function() {
		openSocket();
	});
	
	
	//번역을 위해서 버튼 이벤트를 위해서 사용하는것
	
	function jsonSend(){
		var data = $("#tranForm").serialize();
		$.ajax({
			url:"/Project02/translate",
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
				$("#messageinput").val(result_obj.message.result.translatedText).focus();
				$("#resultText").val(result_obj.message.result.translatedText);
			},
			error:function(e){
				console.log(e);
				alert("번역실패");
			}
		});
	}
	</script>
	
	
	
	<!-- Script to utilize the WebSocket -->
	<script type="text/javascript">
		var webSocket;
		var messages = document.getElementById("messages");
		
		function openSocket(){
			//Ensure only one connection is open at a time
			if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
				writeResponse("WebSocket is already opened.");
				return;
			}
			
			//Create a new instance of the websocket
			//webSocket = new WebSocket("ws://localhost/   *ProjectName*   /echo");
			webSocket = new WebSocket("ws://localhost:8081/Project02/websocketendpoint2");
			
			/**
			*Bind functions to the listeners for the websocket.
			*/
			webSocket.onopen = function(event){
				//For reason I can't determine, onopen gets called twice
				//and the first time event.dats is undefined.
				//Leave a comment if you know the answer.
				if(event.data == undefined)
					return;
				
				writeResponse(event.data);
			};
			
			webSocket.onmessage = function(event){
				writeResponse(event.data);
			};
			
			webSocket.onclose = function(event){
				writeResponse("Connection closed");
			};
		}
		
		/*
		*Sends the value of the text input to the server
		*/
		function send(){
			var chatName = "<%= chatName %>";
			var text = document.getElementById("messageinput").value;
			webSocket.send(chatName + ":" + text);
		}
		
		function closeSocket(){
			webSocket.close();
		}
		
		function exit(){
			alert("퇴장")
			location.href="main.jsp"
			webSocket.close();
		}
		
		function writeResponse(text){
			var chatName = "<%=chatName%>";
			var nameLength = chatName.length;
			var name = text.substr(0, chatName.length);
			var str = text;
			messages.innerHTML += "<br/>"  +text;
		}
	</script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>