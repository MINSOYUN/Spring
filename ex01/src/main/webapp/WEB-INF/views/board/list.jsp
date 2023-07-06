<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.narrow-button {
	  width: 100px; /* 원하는 너비로 조정 */
	}
	.button-container{
		text-align: right;
	}
</style>

<script type="text/javascript">

	function deleteBoard(){
	 	delNoList = document.querySelectorAll("[name=check]:checked");
	 	
	 	let bno = "";
	 	
	 	delNoList.forEach((checkbox) =>{
	 		bno += checkbox.value + ',';
	 	});
	 	
	 	bno = bno.substr(0, bno.length-1);
	 	console.log(bno);
	 	
	 	searchForm.action = "/board/delete";
	 	searchForm.bno.value=bno;
	 	searchForm.submit();  
 }
	
	function requestAction(url, bno) {
		searchForm.action = url;
	 	searchForm.bno.value=bno;
	 	searchForm.submit();  
	}

</script>
<title>Insert title here</title>

<!-- 부트스트랩 필요한 링크!  -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">


<!-- css 파일 -->
<link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>

<!-- 헤더 -->
<%@ include file="../common/header.jsp" %>

<main class="container">
  <div class="bg-light p-5 rounded">
    <h3>게시판</h3>
    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
    <p class="lead">This example is a quick exercise to illustrate how fixed to top navbar works. As you scroll, it will remain fixed to the top of your browser’s viewport.</p>
    <a class="btn btn-lg btn-primary" href="/board/write" role="button">ʟɪsᴛ &raquo;</a>
  </div>
<p></p>
<%@include file="../common/searchForm.jsp" %>
	총 게시글 수 : ${totalCnt } 건
	
<p></p>
 	<c:forEach items="${list}" var="vo" step="1">
 	<a onclick="requestAction('/board/view', ${vo.bno})" href="#" class=""></a>
		  <div class="list-group w-auto">
		  <!-- 체크 박스 -->
		  <div class="form-check">
			  <input class="form-check-input" type="checkbox" id="check"  name="check" value="${vo.bno }">
			  <label class="form-check-label" for="check">
			  </label>
		  </div>
		    <a href="/board/view?bno=${vo.bno }" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
		      <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">🎀
		      <div class="d-flex gap-2 w-100 justify-content-between">
		        <div>
		          <h6 class="mb-0">${vo.title }</h6>
		          <p class="mb-0 opacity-75">${vo.writer }</p>
		        </div>
		        <small class="opacity-50 text-nowrap">${vo.regDate }</small>
		      </div>
		    </a>
		    </div>
   </c:forEach>
   <p></p>
   <%@ include file= "../common/pageNavi.jsp" %>
   <div class="button-container">
   	<button type="button" class="btn btn-outline-danger narrow-button" onclick="deleteBoard()">삭제</button>
   </div>
</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>