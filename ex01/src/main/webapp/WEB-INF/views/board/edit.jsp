<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<style type="text/css">
	.required-label{
        font-size: 12px;
        color: red;
        padding-left: 10px;
	}
</style>

<script type="text/javascript">
	function requestAction(url){
		writeFrm.action = url;
		writeFrm.submit();
		
	}
</script>

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
		<form name="writeFrm" method="post" action="/board/editAction">
			게시글번호 : <input type="text" name="bno" value="${board.bno }" readonly>
		  		<div class="mb-3">
					  <label for="title" class="form-label">board title<span class="required-label">*</span></label>
					  <input name="title" id="title" type="text" class="form-control" value="${board.title }">
					</div>
					<div class="mb-3">
					  <label for="content" class="form-label">board content<span class="required-label">*</span></label>
					  <textarea name="content" class="form-control" id="content" rows="3">${board.content }</textarea>
					</div>
					<div class="mb-3">
					  <label for="writer" class="form-label">board title<span class="required-label">*</span></label>
					  <input type="text" id="writer" class="form-control" name="writer" value="${board.writer }">
					</div>
				<div class="d-grid gap-2 d-md-flex justify-content-md-center">
						<button type="submit" class="btn btn-primary btn-sm" onclick="requestAction('/board/editAction')">수정하기</button>
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