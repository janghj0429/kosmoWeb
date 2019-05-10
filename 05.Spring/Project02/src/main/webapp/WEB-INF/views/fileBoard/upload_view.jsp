<%@ page import="com.study.project.*" %>
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
	

	
    <title></title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="main.jsp">
	  	<img src="img/desert.png" width="30" class="d-inline-block align-top" alt="">Jhj</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="filelist">자료실</a>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          게시판
	        </a>
	        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item" href="wholelist">전체게시판</a>
	          <a class="dropdown-item" href="list?bCategory=0">공지사항</a>
	          <a class="dropdown-item" href="list?bCategory=1">자유게시판</a>
	          <div class="dropdown-divider"></div>
	          <a class="dropdown-item" href="list?bCategory=2">etc</a>
	        </div>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="secession">회원탈퇴</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="chatlogin" >채팅</a>
	      </li>
	    </ul>
	    <form class="form-inline my-2 my-lg-0" action="search" method="post">
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
	    
	<table class="table" >
	  <thead class="thead-dark">
	  	<tr>
		  	<th>파일업로드</th>
			<th></th>
		</tr>
	  </thead>
	  <tbody>
	  
	  	  <form action="upload"  method="post" enctype="multipart/form-data">
			    <tr>
					<td> 회원아이디=bName </td>
					<td><% if(session.getAttribute("mId") != null)
									out.println(session.getAttribute("mId"));%>
						<input type="hidden" name="fName" value="testfileboardname">			
					</td>			
				</tr>
				<tr>
					<td> 제목 </td>
					<td> <input type="text" name="fTitle" size="50"></td>
				</tr>
				<tr>
					<td> 내용 </td>
					<td> 
					<input type="text" name="fContent" >
					</td>
				</tr>
				<tr>
					<td> 파일첨부 </td>
					<td> <input type="file" class="btn btn-outline-dark" name="file"></td>
				</tr>
				<tr>
					<td colspan="2"> 
					<input type="submit" class="btn btn-outline-dark" value="업로드">
						
					<a class="btn btn-outline-dark" href="filelist" role="button">목록보기</a>	
						
					</td>	
			    </tr>
		   </form>
	  </tbody>
	</table>
	

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>