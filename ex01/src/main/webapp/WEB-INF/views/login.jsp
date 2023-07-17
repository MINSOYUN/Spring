<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- fetchGet, fetchPost 파일 연결 -->
<script type="text/javascript" src="/resources/js/common.js"></script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    <title>해삐-해삐 ha._.ppi</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }

      html,
      body {
        height: 100%;
      }

      body {
        display: flex;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: white;
      }

      .form-signin {
        max-width: 330px;
        padding: 15px;
      }

      .form-signin .form-floating:focus-within {
        z-index: 2;
      }

      .middle{
	    border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        margin-bottom: -1px;
        
	  }
      .start  {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
      }
	  
      .end  {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
      }
    </style>
    
<script type="text/javascript">
	// 페이지가 다 로드되고 버튼이 클릭해야 id 가 넘어온다
	window.addEventListener('load', function(){
		
		// ============= 로그인 버튼 =============
		btnLogin.addEventListener('click', function(e){  // 받아와야 제거 가능
			// 기본 이벤트 제거
			e.preventDefault(); // 폼 제출 등의 동작이 발생하지 않고 정의한 함수 실행
			
			// 파라미터 수집
			let obj = { id : document.querySelector("#id").value,
						pw : document.querySelector("#pw").value }
					console.log(obj);
			// common.js의 fetchPost 사용 -> 요청
			fetchPost('/loginAction', obj, loginCheck)
		});
		
		
		btnSigninView.addEventListener('click', function(){ // 로그인
            signupForm.style.display='none';  // 회원가입 폼
            signinForm.style.display='';  // 로그인 폼
          });

          
        btnSignupView.addEventListener('click', function(){  // 회원가입
            signinForm.style.display='none';  // 로그인 폼
            signupForm.style.display='';  // 회원가입 폼
          });
        
          
          
        
        // =========== 회원가입 - 아이디 입력 칸 ===========
        // blur : 이벤트 이름 -> 포커싱 나가면 이벤트 실행
        signUpId.addEventListener('blur', function(){
        	// 아이디 체크 -> 서버에 방문 필요!
        	
        	if(! signUpId.value){
        		signupMsg.innerHTML='아이디를 입력 해주세요';
        		return;
        	}
			
        	// 파라미터 세팅
        	let obj = {id : signUpId.value};   // <input> 태그 id(signUpId)에서 입력 받은 id의 value 값
        	console.log("obj : ", obj)
        	
        	// (map) => function idCheck(map)
        	fetchPost('/idCheck', obj, (map)=>{
        		// ======== 회원가입 - id 중복 체크 ===========
        		if(map.result == 'success'){
        			// 사용 가능- > input 태그 id -> idCheckRes 기본값 0 성공 시 1
        			idCheckRes.value='1'; 
        			signUpName.focus();  // 비밀번호로 넘어감
        		} else{
        			// 사용 불가능
        			idCheckRes.value='0';
        			signUpId.focus(); // 다시 아이디 입력
        			signUpId.value=''; 
        		}	
        			// div id -> signupMsg 아이디 사용 여부에 대한 문장 삽입
        		signupMsg.innerHTML = map.msg;
          	});
          
    	});	
        	
        
          // ======== 회원가입 - 비밀번호 칸 ===============
          pwCheck.addEventListener('blur', function(){
        	  // 비밀번호 체크 -> 서버에 방문x!
        	  
        	  if(!signUpPw.value){  // 비밀번호 입력 x
        		  signupMsg.innerHTML = '비밀번호를 입력해주세요';
        	  		return;
        	  }
        	  if(!pwCheck.value){  // 비밀번호 재입력 x
        		  signupMsg.innerHTML = '비밀번호를 한번 더 입력해주세요';
        		  signUpPw.focus();
      	  			return;
        	  }
        	  if( signUpPw.value == pwCheck.value){  // 비밀번호 = 비밀번호 확인
        		  signupMsg.innerHTML = '비밀번호가 일치합니다';
        		  pwCheckRes.value = 1;   // pw 의 value 기본값 0
        	  } else{
        		  signupMsg.innerHTML = '비밀번호가 일치하지 않습니다';
        		  pwCheckRes.value = 0;  // value 값 0으로 설정
        		  signUpPw.focus();  // 비밀번호재입력 포커스
        		  pwCheck.value='';
        		  signUpPw.value='';  // 확인 입력 값 초기화
        	  }
        	  
        	  
          });
          
          
          btnSignup.addEventListener('click', function(e){
        	 // 기본 이벤트 초기화
        	e.preventDefault();
        	 
        	let id = signUpId.value;
        	let name = signUpName.value;
        	let pw= signUpPw.value;
        	
          if(!id){
        	  signupMsg.innerHTML="아이디를 입력해주세요";
        	  return;
          }
          if(!name){
        	  signupMsg.innerHTML="이름을 입력해주세요";
        	  return;
          }
          if(!pw){
        	  signupMsg.innerHTML="비밀번호를 입력해주세요";
        	  return;
          }
          
          // 아이디 중복체크 확인
          if(idCheckRes.value !=1){ 
        	  signupMsg.innerHTML="중복되는 아이디가 있습니다";
        	  signUpId.focus();
        	  return;
        	  
          }
          
          // 비밀번호 일치 확인
          if(pwCheckRes.value!=1){  
        	  signupMsg.innerHTML="비밀번호가 일치하는지 다시 한번 확인해주세요";
        	  pwCheck.focus();
        	  return;
        	  
          }
          
          obj ={id:id, pw:pw, name:name}
          
          console.log("회원가입 obj", obj)
          fetchPost('/register', obj, (map)=>{
	      		if(map.result == 'success'){   // 회원가입 성공 -> 게시판
	      			location.href='./login?msg='+map.msg;
	    		} else{
	    			// 회원가입실패
	    			signupMsg.innerHTML="회원 가입에 실패하였습니다";
	    		}	
			});
       });
          
});  // window.onload
	
	// 로그인 체크
	function loginCheck(map){
		console.log(map);
		
		if(map.result=='success'){
			// 로그인 성공 -> list 로 이동
			// membercontroller의 map.url
			location.href=map.url;
		} else{
			// 실패 -> 메세지 처리
			alert(map.msg);
			msg.innerHTML=map.msg;   // div id
		}
	}
	
	
</script>

</head>
  
<body class="text-center">
    
	<main class="form-signin w-100 m-auto">
		
		  <!-- =============== 로그인 폼 ============== -->
		  <form name='signinForm'>
			    <img class="mb-4" src="/resources/img/다운로드.jpeg" alt="popcorn" width="120" height="100">
			    <h2 class="h3 mb-3 fw-normal">Sign in</h2>
			    <div id="msg">${param.msg }</div> <!-- ======== 메세지 출력 ============= --> <p></p>
			
			    <div class="form-floating">
			      <input type="text" id="id" class="form-control start" placeholder="id" required>
			      <label for="id">id</label>
			    </div>
			    <div class="form-floating">
			      <input type="password" id="pw" class="form-control end" placeholder="Password" required>
			      <label for="pw">Password</label>
			    </div>
			
			    <div class="checkbox mb-3">
			      <label>
			        <input type="checkbox" value="remember-me"> Remember id
			      </label>
			    </div>
			    <button class="w-100 btn btn-outline-info" type="submit" id='btnLogin'>Login</button>
		  </form>
	
		  <!-- ================ 회원가입폼 ================ -->
		  <form name='signupForm' style='display: none;'>  <!-- 기본값 display: none -->
		  	<input type="hidden" value="0" id="idCheckRes">   <!--  id 체크 -->
		  	<input type="hidden" value="0" id="pwCheckRes"> 	<!--  pw 체크 -->
		  
			    <img class="mb-4" src="/resources/img/다운로드.jpeg" alt="popcorn" width="120" height="100">
			    <h2 class="h3 mb-3 fw-normal">Sign up</h2>
			    <div id="signupMsg"></div>
			    <div class="form-floating">
			      <input type="text" class="form-control start" id="signUpId" placeholder="id" required>
			      <label for="id">id</label>
			    </div>
			    <div class="form-floating">
			      <input type="text" class="form-control middle" id="signUpName" placeholder="name" required>
			      <label for="signUpName">name</label>
			    </div>
			    <div class="form-floating">
			      <input type="password" class="form-control middle" id="signUpPw" placeholder="Password" required>
			      <label for="signUpPw">Password</label>
			    </div>
			    <div class="form-floating">
			      <input type="password" class="form-control end" id="pwCheck" placeholder="Password" required>
			      <label for="pwCheck">PasswordCheck</label>
			    </div>
			    <button class="w-100 btn btn-outline-info" type="submit" id='btnSignup'>Join Membership</button>
		  </form>
	  <p></p>
	  <button class="btn btn-outline-primary" id='btnSigninView'>Login</button>	<!-- id는 페이지당 하나 -->  
	  <button class="btn btn-outline-primary" id='btnSignupView'>Join</button>
	  <p class="mt-5 mb-3 text-muted">&copy; ha (੭˙ ˘ ˙)੭ ppi</p>
	</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>

</html>