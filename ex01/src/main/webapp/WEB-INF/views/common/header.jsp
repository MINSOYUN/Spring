<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://kit.fontawesome.com/a79d7cf7c1.js" crossorigin="anonymous"></script>
</head>

<!-- 메세지 모달 스크립트 -->
<script type="text/javascript">
	//let msg = '${msg}';
	let msg = '${msg}';
	/* if(msg != ''){
		alert(msg);
		history.go(-1);
	} */
	
	window.addEventListener('load', function(){
		if(msg != ''){
			// 메세지 출력
			document.querySelector(".modal-body").innerHTML = msg;
			
			// 버튼 출력 제어
			document.querySelector("#btnModalSave").style.display='none';
			
			// 모달 생성
			let myModal = new bootstrap.Modal(document.getElementById('myModal'), {
				  keyboard: false
				})
			
			// 모달 보여주기
			myModal.show();
		}
		
	}); 
		
</script>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">알림</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
        <button type="button" id="btnModalSave" class="btn btn-primary">저장</button>
      </div>
    </div>
  </div>
</div>


<body>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="/board/list">BBS 🐹</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled">Disabled</a>
        </li>
        
        <li class="nav-item" class="nav-item ml-auto">
          <a class="nav-link" dropdown-toggle href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">MyPage</a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="#">My Information</a></li>
	     		<li><a class="dropdown-item" href="#">Logout</a></li>
       		 </ul>
        </li>
       	<li class="nav-item">
          	<a class="nav-link" href="../login">Login</a>
   		</li>
        
      </ul>
      
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>

</body>
</html>