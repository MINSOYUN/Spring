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
    <title>Fixed top navbar example · Bootstrap v5.2</title>

	<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link href="/resources/css/style.css" rel="stylesheet">
	<!-- link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet"> -->


<script type="text/javascript">

function requestAction(url){
	viewFrm.action = url;
	viewFrm.submit();
	
}

window.onload = function(){
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
	
}
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

</head>
<body>

<%@ include file="../common/header.jsp" %>


<main class="container">
  <div class="bg-light p-5 rounded">
    <h3>게시판</h3>
    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
    <p class="lead">This example is a quick exercise to illustrate how fixed to top navbar works. As you scroll, it will remain fixed to the top of your browser’s viewport.</p>
    <a class="btn btn-lg btn-primary" href="/board/list" role="button">리스트 &raquo;</a>
  </div>
<p></p> <p></p>
	<div class="list-group w-auto">
		<form name="viewFrm" method="get"> <!-- get 방식 !! -->
			게시글번호 : <input type="text" name="bno" value="${board.bno }" readonly>
			<c:forEach items="${board}" var="vo" step="1">
		  		<div class="mb-3">
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
			</c:forEach>			
				<div class="d-grid gap-2 d-md-flex justify-content-md-center">
						<button type="submit" class="btn btn-primary btn-sm" onclick="requestAction('/board/edit')">수정하기</button>
						<button type="button" class="btn btn-secondary btn-sm" onclick="requestAction('/board/delete')">삭제하기</button>
						<button type="reset" class="btn btn-secondary btn-sm">작성 취소</button>
						<button type="button" class="btn btn-primary btn-sm" onclick="location.href='./list';">목록 보기</button>
				</div>	
		</form>
	</div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>