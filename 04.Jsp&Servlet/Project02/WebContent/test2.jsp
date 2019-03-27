<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>레이아웃종합</title>
	
	<style>
		div{
			border:1px solid #cccccc;
			padding:5px; margin:5px;
			text-align:center;
		}
		#contain{
			width:800px;
			margin:0 auto;	
			border:2px solid #cccccc;
			font-size:1.5em;
		}
		
		#header{
			height:100px;
			border:2px solid #cccccc;
			margin:5px;
			padding:5px;
			text-align:center;
		}
		
		#nav{
			height:100px;
			border:2px solid #cccccc;
			margin:5px;
			padding:5px;
			text-align:center;
		}
		#sp{
		}
		
		#messages{
			height:500px;
			position:relative;
			border:2px solid #cccccc;
			margin:5px;
			padding:5px;
		}
		#content{
			width:568px;
			height:475px;
			float:left;
			margin:5px;
			padding:5px;
			border:2px solid #cccccc;
		}
		
		#banner{
			width:160px;
			height:475px;
			float:left;
			margin:5px;
			padding:5px;
			border:2px solid #cccccc;
		}
		
		#footer{
			height:100px;
			border:2px solid #cccccc;
			margin:5px;
			padding:5px;
			text-align:center;
		}
		
	</style>
</head>
<body>
<div id="contain">
	<div id="header">HEADER</div>
	<div id="messages"></div>
	<div id="nav">NAVIGATION
		<br/>
		<span>menu01</span>
		<span>menu01</span>
		<span>menu01</span>
		<span>menu01</span>
		<span>menu01</span>
	</div>
		


</div>	
</body>
</html>