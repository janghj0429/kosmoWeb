<!DOCTYPE html>
<html lang="kr">

<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<title>NaverLoginSDK</title>
</head>

<script type="text/javascript">
  var naver_id_login = new naver_id_login("DwQyae7MeSDHCL8qE_oa", "http://localhost:8081/ApiLogin/04-Naver/callback.jsp");
  // 네이버 사용자 프로필 조회
  alert(naver_id_login.oauthParams.access_token);
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
    alert(naver_id_login.getProfileData('email'));
    alert(naver_id_login.getProfileData('nickname'));
    alert(naver_id_login.getProfileData('age'));
    alert(naver_id_login.getProfileData('id'));
	 
  }
</script>
</body>

</html>