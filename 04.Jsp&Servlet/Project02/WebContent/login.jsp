<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("ValidMem") != null){ %>
 	<jsp:forward page="main.jsp"></jsp:forward>
<% } %>    
<script src="naveridlogin_js_sdk_2.0.0.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	
	<script>
		
	</script>
	
	<style>
		
		div{
			margin:2 auto;
			
		}
		#con{
			width:800px;
			margin:5px auto;
			overflow:hidden;
		}
		
		#header{
			width:400px;
			margin:5px auto;
			line-height:100px;
		}
		
		.max-small{
			width:auto; height:auto;
			max-width:380px;
			max-hehight:200px;
		}
		
		#form-group{
			width:500px;
			margin:2px auto;
		}
		
		#wrap{
			width:100px;
			height:30px;
			margin:2px auto;
		
		}
		
		#footer{
			width:760px;
			margin:2px auto;
		}
	</style>
	
	
	
	<!-- 페이스북 -->
	
	<script src="http://code.jquery.com/jquery.js"></script>

	<script>
	  window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '560975147728224',
	      cookie     : true,
	      xfbml      : true,
	      version    : 'v3.2'
	    });
	
	    FB.getLoginStatus(function(response) {
	    	console.log(response);
	      statusChangeCallback(response);
	    });
	  };
	
	  // Load the SDK asynchronously
	  (function(d, s, id) {
	    var js, fjs = d.getElementsByTagName(s)[0];
	    if (d.getElementById(id)) return;
	    js = d.createElement(s); js.id = id;
	    js.src = "https://connect.facebook.net/en_US/sdk.js";
	    fjs.parentNode.insertBefore(js, fjs);
	  }(document, 'script', 'facebook-jssdk'));
	
	  function statusChangeCallback(response) {
	    if (response.status === 'connected') {
	      getINFO();
	    } else {
	    	 $('#login').css('display', 'block');
	    }
	  }
		  
	  function fbLogin () {
	    FB.login(function(response){
	      statusChangeCallback(response);
	    }, {scope: 'public_profile, email'});
	  }
	
	  function fbLogout () {
	    FB.logout(function(response) {
	      
	    	var iframe = document.createElement("iframe");
	        iframe.src= "https://www.facebook.com/logout.php";
	    });
	  }
	
	  function getINFO() {
	    FB.api('/me?fields=id,name,picture.width(100).height(100).as(picture_small)', function(response) {
	      console.log(response);        
	      var mId = response.id;
	      console.log(mId);
	      fbLogout();
	      window.location.replace("SnsCon?mId="+mId);
	    });
	  }
	
	</script>
	

	
	
    <title></title>
  </head>
  <body>
    
    <div id="con">
    	<div id="header"> 
    		
    	</div>
    	<form action="login.do" method="post">
		  <div class="form-group">
		    <label for="formGroupExampleInput">Id</label>
		    <input type="text" class="form-control" id="mId" name="mId" placeholder="ID input">
		  </div>
		  <div class="form-group">
		    <label for="formGroupExampleInput2">Password</label>
		    <input type="password" class="form-control" id="mPw" name="mPw" placeholder="Password input">
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		  <input type="button" value="회원가입" class="btn btn-primary" onclick="javascript:window.location='join.jsp'">
		</form>

	</div>
		
		
		
		
		<!-- 구글 -->
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
			var mId = profile.getId();
			if(mId != null){
				window.location.replace("SnsCon?mId="+mId);
			}else{
				alert("로그인 실패");
			}
			signOut();
		}
		function signOut() {
		    var auth2 = gapi.auth2.getAuthInstance();
		    auth2.signOut().then(function () {
		     	console.log('User signed out.');  
		    });
		    auth2.disconnect();
		}	
		
		</script>
		
		
			<!-- 구글 -->
			
			<div id="login" class="g-signin2" data-width="300" data-height="50" data-longtitle="true"data-onsuccess="onSignIn">
				
			</div>
			
			<!-- 페이스북 -->
			<div id="login" style="display: block;width:300; height:50;">
		    	<input type="button" class="btn btn-primary btn-lg btn-block"onclick="fbLogin();" value="페이스북로그인" /><br>
			</div>

			
			
			<!-- 카카오 -->
			<div id="login" style="display: block; width:300; height:50;" >
		   		<a id="custom-login-btn" href="javascript:loginWithKakao()">
		   		<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
		    	</a>
			</div>
			
			<div id="logout" style="display: none;">
			    <input type="button" class="btn btn-success" onclick="signOut();" value="로그아웃" /><br>
			</div>
			
			<script type='text/javascript'>
		    Kakao.init('7bfa4c1c9f2b22e77c3f8d3234e8c8a8');
		    function loginWithKakao() {
		      // 로그인 창을 띄웁니다.
		      Kakao.Auth.login({
		        success: function(authObj) {
		          //alert(JSON.stringify(authObj));
		          signIn(authObj);
		        },
		        fail: function(err) {
		          alert(JSON.stringify(err));
		        }
		      });
		    };
		
		    function signIn(authObj) {
		        //console.log(authObj);
		        Kakao.API.request({
		            url: '/v2/user/me',
		            success: function(res) {
		                //console.log(res);
		                console.log(res.id);
		                var mId = res.id;
		                $('#login').css('display', 'none');
		               	$('#logout').css('display', 'block');
		               	window.location.replace("SnsCon?mId="+mId);
		               	signOut();
		             }
		         })
			}
		
		    function signOut() {
			    Kakao.Auth.logout(function () {
			    	$('#login').css('display', 'block');
			    	$('#logout').css('display', 'none');
			    });
			} 
			</script>
			
			
			
			
			<!-- 네이버아이디로로그인 버튼 노출 영역 -->
			<div id="naverIdLogin" style="width: 300 !important;"></div>
		
		<!-- //네이버아이디로로그인 버튼 노출 영역 -->
		<script type="text/javascript">
		var naverLogin = new naver.LoginWithNaverId(
			{
				clientId: "5YyvEgGrov8XSYaxDz7G",
				callbackUrl: "http://localhost:8081/Project02/callback.jsp",
				isPopup: false, /* 팝업을 통한 연동처리 여부 */
				
				loginButton: {color: "green", type: 3, width: 300, height: 50} /* 로그인 버튼의 타입을 지정 */
				
			}
		);
		
	   /* 설정정보를 초기화하고 연동을 준비 */
		naverLogin.init();
		</script>
    

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>