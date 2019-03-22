<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function goPopup() {
    var requestValue = document.getElementById("Name").value;                  // 전송 파라미터 값
    var theURL = "/servlet/servlet.ExampleOpenUrl?requestValue="+requestValue; // 전송 URL
    // 팝업 창 로드 (window.open)
    window.open("http://localhost:8081/ApiLogin/04-Naver/callback.jsp", "scrollbars=no,width=900,height=500,menubar=false");
    // window.open(URL,"팝업 구분값(팝업 1개일 경우 상관없음)","팝업 창 옵션")
}
 
 
// Popup Option
 
toolbar = yes/no       //상단 도구창 출력 여부
menubar = yes/no       //상단 메뉴 출력 여부
location = yes/no      //메뉴아이콘 출력 여부
directories = yes/no   //제목 표시줄 출력 여부
status = yes/no        //하단의 상태바 출력 여부
scrollbals = yes/no    //스크롤바 사용 여부
resizable = yes/no     //팝업창의 사이즈 변경 가능 여부
 
width = 10           //팝업창의 가로 길이 설정 (10px)
height = 10          //팝업창의 세로 길이 설정 (10px)
top = 10             //팝업창이 뜨는 위치(화면 위에서부터의 거리 지정) (10px)
left = 10            //팝업창이 뜨는 위치(화면 왼쪽에서부터의 거리 지정) (10px)
 
 
// 팝업 창 닫기
 
function selectValue(){
   
    var requestValue = document.getElementById("Name").value;                   // 전송 파라미터 값
    var theURL = "/servlet/servlet.ExampleCloseUrl?requestValue="+requestValue; // 전송 URL
    // 호출 한 부모 페이지에서 URL 호출
    opener.window.location = url;
    // 호출 한 뒤 현재 팝업 창 닫기 이벤트
    close();
} 
</script>
<input type="button" value="팝업" onclick="goPopup();">
</body>
</html>