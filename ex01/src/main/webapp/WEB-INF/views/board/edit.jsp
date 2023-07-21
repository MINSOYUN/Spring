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
	window.onload=function(){
		getFileList();
	}
	function requestAction(url){
		writeFrm.action = url;
		writeFrm.submit();
		
	}
	
	function getFileList(){
		// /file/list/bno
		let bno = '${board.bno}';
		fetch('/file/list/'+bno)
		.then(response => response.json()) // Object 형식으로 반환
		.then(map => viewFileList(map))
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
${param.pageNo }
${param.searchFiled }
<main class="container">
  <div class="bg-light p-5 rounded">
    <h3>Board</h3>
    <p class="lead" style="font-style: italic;">Creating a bulletin board using bootstrap</p>
    <p class="lead">This example is a quick exercise to illustrate how fixed to top navbar works. As you scroll, it will remain fixed to the top of your browser’s viewport.</p>
    <a class="btn btn-outline-success" href="/board/write" role="button">List &raquo;</a>
  </div>
<p></p> <p></p>
	<div class="list-group w-auto">
		<form name="writeFrm" method="post" action="/board/editAction" enctype="multipart/form-data">
				<!-- 페이지, 검색 유지 -> form 안에 작성해주어야 post 되면서 넘어간다
				form 에선 name 값으로 ! 넘어간다! -->
				<c:if test=" ${not empty param.pageNo }">
					<input type="text" name="pageNo" value="${param.pageNo}">
				</c:if>
				<c:if test=" ${empty param.pageNo }">
					<input type="text" name="pageNo" value="1">
				</c:if>
				
				<input type="hidden" name="pageNo" value="${param.pageNo ==''?'1':param.pageNo }">
				<input type="hidden" name="searchField" value="${param.searchField }">
				<input type="hidden" name="searchWord" value="${param.searchWord }">
				<input type="hidden" name="bno" value="${board.bno }" readonly>
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
					<!-- 파일 -->
					<div class="mb-3">
						  <label for="file" class="form-label">file</label>
						  <input type="file" id="file" class="form-control" name="files" multiple="multiple">
					</div>
				<div class="d-grid gap-2 d-md-flex justify-content-md-center">
						<button type="button" id="btnEdit" class="btn btn-outline-success btn-sm" onclick="requestAction('/board/editAction')">Completed</button>
						<button type="button" id="btnDelete" class="btn btn-outline-secondary btn-sm" onclick="requestAction('/board/delete')">Delete</button>
						<button type="reset" class="btn btn-outline-secondary btn-sm">Reset</button>
				</div>	
		</form>
	</div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>