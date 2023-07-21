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
	<link href="/resources/css/style.css" rel="stylesheet">
	<!-- link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet"> -->

<script type="text/javascript">
	function requestAction(url){
		writeFrm.action = url;
		writeFrm.submit();
		
	};
	
	
	function getFileList(){
	// /file/list/bno
	let bno = '${board.bno}';

	if(bno){
		fetch('/file/list/'+bno)
		.then(response => response.json()) // Object 형식으로 반환
		.then(map => viewFileList(map))
	}
}
	
	
function viewFileList(map){
	console.log(map);
	let content='';
	
	if(map.list.length > 0){
		
		content +=  '<div class="mb-3">                                 '
		+ '  <label for="attachFile" class="form-label">file</label>  '
		+ '  <div class="form-control" id="attachFile">               ';
		
		map.list.forEach(function(item, index){ 
			
			let savePath = encodeURIComponent(item.savePath);  // uri 인코딩
			console.log("savePath : ", savePath);
			// controller 와 같게 href 작성
			content += '<a href="/file/download?fileName=' + savePath + '">'
			+ item.filename + '</a>'
			// item 에서부터 bno와 uuid
			+ '<i onclick = "attachFileDelete(this)" data-bno = "' + item.bno + '" data-uuid= "' + item.uuid + '"'
			+ ' class="fa-regular fa-square-minus"></i>'
			+ '<br>';
		})
		content += '  </div>         '
					+ '</div>			';
		
	} else{
		content = "등록된 파일이 없습니다"
	}
	
	divFileupload.innerHTML = content;
}
</script>
</head>
<body>

<%@ include file="../common/header.jsp" %>

<main class="container">
  <div class="bg-light p-5 rounded">
    <h3>Board</h3>
    <p class="lead" style="font-style: italic;">Creating a bulletin board using bootstrap</p>
    <p class="lead">This example is a quick exercise to illustrate how fixed to top navbar works. As you scroll, it will remain fixed to the top of your browser’s viewport.</p>
    <a class="btn btn-outline-success" href="/board/list" role="button">List &raquo;</a>
  </div>
<p></p> <p></p>
		  <div class="list-group w-auto">
				<form name="writeFrm" method="post" action="/board/write" enctype="multipart/form-data">
						 <div class="mb-3">
						  <label for="title" class="form-label">board title</label>
						  <input name="title" id="title" type="text" class="form-control" value="${board.title }" placeholder="게시글의 제목을 입력하세요" >
						</div>
						<div class="mb-3">
						  <label for="content" class="form-label">board content</label>
						  <textarea name="content" class="form-control" id="content" rows="3" placeholder="게시글의 내용을 입력하세요" >${board.bno }</textarea>
						</div>
						<div class="mb-3">
						  <label for="writer" class="form-label">board writer</label>
						  <input type="text" id="writer" class="form-control" name="writer" value="${board.writer }" placeholder="작성자를 입력하세요" >
						</div>
						<!-- 작성화면일 때 -->
						<c:if test="${empty board.writer} ">
							<input type="text" class=""form-control" id="writer" name="writer" readonly value="${userId }">
						</c:if>
						<!-- 수정화면일 때 -->
						<c:if test="${not empty board.writer} ">
							<input type="text" class=""form-control" id="writer" name="writer" readonly value="${board.writer }">
						</c:if>
						<!-- 파일 -->
						<div class="mb-3">
						  <label for="file" class="form-label">file</label>
						  <input type="file" id="file" class="form-control" name="files" multiple="multiple">
						</div>
						
						<div class="d-grid gap-2 d-md-flex justify-content-md-center">
						<!-- bno가 비어있지 않으면 수정하기 버튼 출력 -->
						<c:if test="${not empty board.bno }" var="res">
							<input type="text" name="bno" value="${board.bno }">
							<button type="submit" class="btn btn-primary btn-sm" onclick="requestAction('/board/editAction')">수정하기</button>
						</c:if>
						<c:if test="${not res }">
							<button type="submit" class="btn btn-primary btn-sm">글쓰기</button>
						</c:if>
							<button type="reset" class="btn btn-secondary btn-sm">작성 취소</button>
						</div>
				</form>
		  </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>