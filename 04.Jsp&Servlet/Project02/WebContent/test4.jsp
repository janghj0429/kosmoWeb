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
	
    <title></title>
    
    <%
	String chatName = request.getParameter("chatName");
	String userId = (String)session.getAttribute("mId");
	if(chatName == null){
	%>
		<jsp:forward page="chatlogin.jsp"/>
	<%
	}else{
		session.setAttribute("chatName", chatName);
	}
	%>
</head>
<body>
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
	      	<div id="messages">출11력</div>
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
	      <td colspan="3"><input type="text" id="messageinput" /></td>
	    </tr>
	    <tr>
	      <td><button class="btn btn-outline-dark"  type="button" onclick="openSocket();">open</button></td>
	      <td><button class="btn btn-outline-dark"  type="button" onclick="send();">send</button></td>
	      <td><button class="btn btn-outline-dark"  type="button" onclick="closeSocket();">close</button></td>
	      <td><button class="btn btn-outline-dark"  type="button" onclick="exit();">exit</button></td>
	    </tr>
	    
	  </tbody>
	</table>
	
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
			writeResponse("0");
			//Create a new instance of the websocket
			//webSocket = new WebSocket("ws://localhost/   *ProjectName*   /echo");
			webSocket = new WebSocket("ws://localhost:8081/Project02/websocketendpoint2");
			writeResponse("1");
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
			webSocket.send(chatName + "|" + text);
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
			messages.innerHTML += "<br/>" + text;
		}
	</script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>