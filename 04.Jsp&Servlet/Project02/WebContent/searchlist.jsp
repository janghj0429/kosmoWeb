<%@page import="project.jsp.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
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
	      <th>카테고리</th>
	      <th>번호</th>
	      <th>이름</th>
	      <th>제목</th>
	      <th>날짜</th>
	      <th>히트</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach items="${list}" var="dto">
	    <tr>
	      	<td>${dto.bCategory}</td>
			<td>${dto.bId}</td>
			<td>${dto.bName}</td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
				<a href="nctcontent_view.do?bId=${dto.bId}&kind=view">${dto.bTitle }</a></td>
			<td>${dto.bDate }</td>
			<td>${dto.bHit }</td>
	    </tr>
	    </c:forEach>
	    
	    <tr>
			<td colspan="4" ><a class="btn btn-outline-dark" href="main.jsp" float="right">메인</a></td>
			<td colspan="2" style="float:right;">
				<form class="form-inline my-2 my-lg-0" action="search.do" method="post">
		    		<select name="column" class="custom-select custom-select-sm">
						<option value="bName" >이름</option>
						<option value="bContent" >내용</option>
						<option value="bTitle" >제목</option>
					</select>
			 	   <input class="form-control mr-sm-2" type="search" name="word" value="" placeholder="Search" aria-label="Search">
			 	   <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	    		</form>			
			</td>
		</tr>
	    
	    <tr>
			<td colspan="6">
			<!-- 처음 -->
			<c:choose>
			<c:when test="${(page.curPage -1) < 1}">
				[ &lt;&lt; ]
			</c:when>
			<c:otherwise>
				<a href="search.do?page=1&column=${page.boardColumn}&word=${page.boardWord}">[ &lt;&lt; ]</a>
			</c:otherwise>
			</c:choose>
			<!-- 이전 -->
			<c:choose>
			<c:when test="${(page.curPage -1) < 1}">
				[ &lt; ]
			</c:when>
			<c:otherwise>
				<a href="search.do?page=${page.curPage - 1}&column=${page.boardColumn}&word=${page.boardWord}">[ &lt; ]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 개별 페이지 -->
			<c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
				<c:choose>
				<c:when test="${page.curPage == fEach}">
					[ ${fEach} ]&nbsp;
				</c:when>
				
				<c:otherwise>
					<a href="search.do?page=${fEach}&column=${page.boardColumn}&word=${page.boardWord}">[ ${fEach} ]</a>&nbsp;
				</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
			<c:when test="${(page.curPage + 1) > page.totalPage}">
				[&gt;]
			</c:when>
			<c:otherwise>
				<a href="search.do?page=${page.curPage + 1}&column=${page.boardColumn}&word=${page.boardWord}">[&gt;]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 끝 -->
			<c:choose>
			<c:when test="${page.curPage == page.totalPage}">
				[&gt;&gt;]
			</c:when>
			<c:otherwise>
				<a href="search.do?page=${page.totalPage}&column=${page.boardColumn}&word=${page.boardWord}">[&gt;&gt;]</a>
			</c:otherwise>
			</c:choose>
			</td>
		</tr>
	  </tbody>
	</table>
	
	boardCategory : ${page.boardCategory}<br>
	
	totalCount : ${page.totalCount}<br>
	listCount : ${page.listCount}<br>
	totalPage : ${page.totalPage}<br>
	curPage : ${page.curPage}<br>
	pageCount : ${page.pageCount}<br>
	startPage : ${page.startPage}<br>
	endPage : ${page.endPage}<br>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>