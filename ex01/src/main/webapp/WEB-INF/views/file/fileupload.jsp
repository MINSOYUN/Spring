<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
<script type="text/javascript">
window.addEventListener('load', function(){
	btnList.addEventListener('click', function(){
		getFileList();
	})
	
})
	
	function getFileList(){
		// /file/list/bno
		let bno = document.querySelector("#bno").value;
		fetch('/file/list/'+bno)
		.then(response => response.json()) // Object 형식으로 반환
		.then(map => viewFileList(map))
	}
	
	function viewFileList(map){
		console.log(map);
		let content='';
		if(map.list.length > 0){
			map.list.forEach(function(item, index){
				content += item.filename + "/" + item.savePath + '<br>';
			})
		} else{
			content = "등록된 파일이 없습니다"
		}
		divFileupload.innerHTML = content;
	}
</script>
</head>
<body>

	<h3>파일업로드</h3>
	res:${param.msg}
	
	<form action="/file/fileuploadAction" method="post" enctype="multipart/form-data" name="fileuploadForm">
		<h4>파일선택</h4>
		bno: <input type="text" name="bno" id="bno" value=83> <br>
		<input type="file" name="files" multiple="multiple"> <br>
		<input type="file" name="files"> <br>
		<input type="file" name="files"> <br>
		<button type="submit">파일 업로드</button> <br>
	</form>
	
	==================
	
	<h4>파일 리스트 조회</h4>
	<button type="button" id="btnList">리스트 조회</button>
	<div id="divFileupload"></div>

</body>
</html>