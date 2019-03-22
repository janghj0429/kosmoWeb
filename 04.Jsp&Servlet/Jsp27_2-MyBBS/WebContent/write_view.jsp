<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- include libraries(jQuery, bootstrap) -->
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	$(document).ready(function(){
		$('#summernote').summernote({
			option:{disableDragAndDrop:false},
			lang:'ko-KR',
			height:300
			
		});
	});
</script>
</head>
<body>
		
		<table width="600" cellpadding="0" cellspacing="0" border="1">
			<form action="write.do" method="post">
				<tr>
					<td width="50"> 이름 </td>
					<td> <input type="text" name="bName" size="50"></td>
				</tr>
				<tr>
					<td> 제목 </td>
					<td> <input type="text" name="bTitle" size="50"></td>
				</tr>
				<tr>
					<td> 내용 </td>
					<td> <textarea name="bContent" id="summernote" row="10"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"> 
						<input type="submit" value="입력">&nbsp;&nbsp;
						<a href="list.do">목록보기</a>
					</td>	
				</tr>
			</form>
		</table>
		
</body>
</html>