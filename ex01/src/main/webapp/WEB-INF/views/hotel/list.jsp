<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hotel list</title>

<!-- 부트스트랩 필요한 링크!  -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<!-- css 파일 -->
<link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>

<!-- 헤더 -->
<%@ include file="../common/hotelHeader.jsp" %>

<main class="container">
  <div class="bg-light p-5 rounded">
    <h3>Hotel</h3>
    <p class="lead" style="font-style: italic;">Creating a bulletin board using bootstrap</p>
    <p class="lead">This example is a quick exercise to illustrate how fixed to top navbar works. As you scroll, it will remain fixed to the top of your browser’s viewport.</p>
    <a class="btn btn-outline-info" href="/hotel/write" role="button">Register &raquo;</a>
  </div>
<p></p>

<!-- 검색 폼 -->
<%@include file="../common/searchForm.jsp" %>
	<p></p>
	
 	<c:forEach items="${list}" var="vo" step="1">
		  <div class="list-group w-auto">
 			<a onclick="requestAction('/hotel/view', ${vo.num})" href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
		  <!-- 체크 박스 -->
		  <div class="form-check">
			  <input class="form-check-input" type="checkbox" id="check"  name="check" value="${vo.num }">
			  <label class="form-check-label" for="check">
			  </label>
		  </div>
		      <img src="/resources/img/mouse-cursor.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
		      <div class="d-flex gap-2 w-100 justify-content-between">
		        <div>
		          <h6 class="mb-0">${vo.name }</h6>
		          <p class="mb-0 opacity-75">${vo.writer }</p>
		        </div>
		        <small class="opacity-50 text-nowrap">${vo.postdate }</small>
		      </div>
		    </a>
		    </div>
   </c:forEach>
</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>