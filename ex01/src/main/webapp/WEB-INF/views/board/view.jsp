<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    <title>Fixed top navbar example · Bootstrap v5.2</title>

	<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	
	<!-- 스타일 시트 -->
	<link href="/resources/css/style.css" rel="stylesheet">
	<!-- JS -->
	<script src="/resources/js/reply.js"> </script>


<script type="text/javascript">

	window.onload=function(){
		// id 값.
		btnEdit.addEventListener('click', function(){
			viewFrm.action = '/board/edit';
			viewFrm.submit();
		});
		
		btnDelete.addEventListener('click', function(){
			viewFrm.action = '/board/delete';   // bno의 값 viewFrm이 가지고 있어 따로 명시할 필요 x
			viewFrm.submit();
		});
		
		btnList.addEventListener('click', function(){
			viewFrm.action = '/board/list';
			viewFrm.submit();
		});
		
		// 댓글 목록 조회 및 출력
		getReplyList();
		
		// 답글 등록 버튼
		btnReplyWrite.addEventListener('click', function(){
			replyWrite();
		});
		
	};


</script>


<%@ include file="../common/header.jsp" %>


<main class="container">
  <div class="bg-light p-5 rounded">
    <h3>Board</h3>
    <p class="lead" style="font-style: italic;">Creating a bulletin board using bootstrap</p>
    <p class="lead">This example is a quick exercise to illustrate how fixed to top navbar works. As you scroll, it will remain fixed to the top of your browser’s viewport.</p>
    <a class="btn btn-outline-success" href="/board/list" role="button">List &raquo;</a>
  </div>
<p></p> <p></p>
<!-- 상세 보기 -->
	<div class="list-group w-auto">
		<form name="viewFrm" method="get"> <!-- get 방식 !! -->
		
		<!-- 파라미터 작성 -->
		<input type="hidden" name="pageNo" value="${param.pageNo }">
		<input type="hidden" name="searchField" value="${param.searchField }">
		<input type="hidden" name="searchWord" value="${param.searchWord }">
		<input type="hidden" name="bno" id="bno" value="${board.bno }" readonly>
		
		  		<div class="mb-3">
		  			<!-- label for와 input name 같게
		  				value 값은 input 에 -->
					  <label for="title" class="form-label">board title</label>
					  <input name="title" id="title" type="text" class="form-control" value="${board.title }" readonly>
					</div>
					<div class="mb-3">
					  <label for="content" class="form-label">board content</label>
					  <textarea name="content" class="form-control" id="content" rows="3" readonly>${board.content }</textarea>
					</div>
					<div class="mb-3">
					  <label for="writer" class="form-label">board writer</label>
					  <input type="text" id="writer" class="form-control" name="writer" value="${board.writer }" readonly>
					</div>
				<div class="d-grid gap-2 d-md-flex justify-content-md-center">
						<button type="button" id="btnEdit" class="btn btn-outline-success btn-sm">Update</button>
						<button type="button" id="btnDelete" class="btn btn-outline-secondary btn-sm">Delete</button>
						<button type="button" id="btnList" class="btn btn-outline-primary btn-sm" >ViewList</button>
				</div>	
		</form>
	</div>
	<p></p>
	<input type="hidden" id="replyer" value="작성자">
	<input type="hidden" id="page" value="1">
	<p></p>
  <div class="input-group">
  	<span class="input-group-text">Comment</span>
  	<input type="text" 
  				aria-label="First name" 
  				class="form-control"
  				id="reply" placeholder="Please leave comments" style="font-style: italic;">
  	<input type="button" 
			  	id="btnReplyWrite" 
			  	aria-label="Last name" 
			  	class="input-group-text" 
			  	value="Register">
  </div>
	<!-- 댓글 리스트 -->
	<div id="replyDiv">
	</div>
	<!-- <%@include file="../reply/test.jsp" %>  -->
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>