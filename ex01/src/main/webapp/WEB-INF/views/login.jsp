<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">

	<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>

<body>
<aside id="rightside">
  <div class="side1">
    <% 
    String error = request.getParameter("loginError");
    if (error != null && "Y".equals(error)) {
      out.print("<script>alert('아이디 비밀번호를 확인해주세요')</script>");
    }
    
    
    String name = request.getParameter("name");
    
    if (name != null && !name.equals("")) {
      out.print("<h1>" + name + "님 환영합니다.</h1>");
    } else {
    %>
    <!-- 로그인 성공하면 div 안보이게 else에서만 출력되게 한다 -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#loginModal">
      로그인
    </button>
<form action ="/member/loginAction" method = "post">
    <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
 		 <div class="modal-content">
   			 <div class="modal-header">
    			  <h5 class="modal-title" id="loginModalLabel" style="color: #333;">로그인</h5>
     			 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
  		  </div>
          <div class="modal-body">
            <form action="./login/loginAction.do" method="post">
              <div class="mb-3">
                <label for="id" class="form-label">ID</label>
                <input type="text" name="id" id="id" class="form-control" placeholder="ID를 입력해주세요.">
              </div>
              <div class="mb-3">
                <label for="pw" class="form-label">Password</label>
                <input type="password" name="pw" id="pw" class="form-control" placeholder="PW를 입력해주세요.">
              </div>
              <button type="submit" class="btn btn-outline-primary">로그인</button>
            </form>
          </div>
          <div class="modal-footer">
            <input type="checkbox" name="saveCheck" value="Y" > 아이디 저장
            <a href="">회원가입</a>
            <a href="">ID찾기</a>
            <a href="">PW찾기</a>
          </div>
        </div>
      </div>
    </div>

    <% } %>
	</form>
  </div>
</aside>
</body>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>