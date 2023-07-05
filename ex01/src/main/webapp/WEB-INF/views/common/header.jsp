<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="/board/list">Fixed navbar</a>
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
        
        <c:if test="${empty sessionScope.userId }" var="res1">
        <li class="nav-item" class="nav-item ml-auto">
          <a class="nav-link" dropdown-toggle href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">마이페이지</a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="#">회원정보상세보기</a></li>
	     		<li><a class="dropdown-item" href="#">로그아웃</a></li>
       		 </ul>
        </li>
         </c:if>
         <c:if test="${not res1 }">
         	<li class="nav-item">
          	<a class="nav-link disabled">로그인</a>
       		</li>
         </c:if>
        
      </ul>
      
      <form class="d-flex" role="search">
      	<div class="modal-body">
			<select	name="searchField">
					<option value="title" ${param.searchField eq "title" ? "selected" : ""}>제목</option>
					<option value= "content" ${param.searchField eq "content" ? "selected" : ""}>내용</option>
					<option value= "writer" ${param.searchField eq "writer" ? "selected" : ""}>작성자</option>
			</select>
		</div>	
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>

</body>
</html>