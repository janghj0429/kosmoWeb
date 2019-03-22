<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>구글 아이디로 로그인</title>
	<script src="http://code.jquery.com/jquery.js"></script>
	
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<meta name="google-signin-client_id" content="100588982777-89g7j4695h2nc5q5cbuibmjpbsha7o36.apps.googleusercontent.com">

	<script>
	function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		console.log('ID: ' + profile.getId()); 
		console.log('Name: ' + profile.getName());
		console.log('Image URL: ' + profile.getImageUrl());
		console.log('Email: ' + profile.getEmail()); 
		  
		$('#login').css('display','none');
		$('#logout').css('display','block');
		$('#upic').attr('src',profile.getImageUrl());
		$('#uname').html('[ '+profile.getName()+' ]');
	}

	function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	     	console.log('User signed out.');
	     	
	     	$('#login').css('display', 'block');
			$('#logout').css('display', 'none');
			$('#upic').attr('src','');
			$('#uname').html('');
	    });
	}
	</script>

</head>
<body>

<div id="login" class="g-signin2" data-onsuccess="onSignIn"></div>

<div id="logout" style="display: none;">
	<input type="button" onclick="signOut();" value="로그아웃" /><br>
	
	<img id="upic" src=""><br>
	<span id="uname"></span>
</div>



</body>
</html>