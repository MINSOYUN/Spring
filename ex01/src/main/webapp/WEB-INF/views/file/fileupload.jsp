<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>

<script src="https://kit.fontawesome.com/a79d7cf7c1.js" crossorigin="anonymous"></script>

<script type="text/javascript">
window.addEventListener('load', function(){
	// 리스트 조회
	btnList.addEventListener('click', function(){
		getFileList();
	});
	
	
	// 파일업로드
	btnFileupload.addEventListener('click', function(){
		// 웹 개발에서 HTML 폼 데이터를 자바스크립트로 쉽게 전송하는 방법을 제공하는 API
		// FormData(form name)
		let formData = new FormData(fileuploadForm);
		formData.append('name', 'sso');
		
		console.log("formData : " , formData);
		//FormData 값 확인
		for(var pair of formData.entries()){
				console.log("pair : " , pair);
				console.log(pair[0] +':'+pair[1]);
				
				if(typeof(pair[1]) == 'object'){
					let fileName = [pair[1].name];
					let fileSize = [pair[1].size];
					
					// 파일 확장자(서버에 전송 가능한 형식인지 확인)
					// 크기 체크(최대 전송 가능한 용량 초과하지 않는지)
					if(checkExtenstion(fileName, fileSize)){
						return false;   // 왜???
					}
					
					console.log(['fileName', fileName]);
					console.log(['fileSize', fileSize]);
				}
		}
		
		// post 방식 (url, method, body)
		fetch('/file/fileuploadActionFetch', {method:'post', body:formData})
		.then(response => response.json())
		.then(map => fileuploadRes(map))
	});
	
})


	function checkExtenstion(fileName, fileSize){
		let maxSize = 1024 * 1024 * 10;
		// .exe, /sh, /zip, .alz 로 끝나는 문자열
		// 정규 표현식 : 특정 규칙을 가진 문자열을 검색 하거나 치환할 때 사용
		let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		
		// 파일 사이즈 체크
		if(maxSize <= fileSize){
			alert("파일 사이즈를 초과하였습니다.");
			return false;
		}
		
		// 파일 확장자 체크
		// 문자열에 정규식 패턴을 만족하는 값이 있으면 true, 없으면 false를 리턴
		if(regex.test(fileName)){
			alert("해당 확장자의 파일은 업로드 할 수 없습니다");
			return false;
		}
	}
	
	
	// fetch로 값을 받아오기 때문에 map을 받을 함수 필요
	// 따라서 msg 를 저장할 <div> 필요
	function fileuploadRes(map){
		if(map.result == 'success'){
			divFileuploadRes.innerHTML = map.msg;
			// 게시글 등록
		} else{
			alert(map.msg);
		}
	}
	
	
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
				
				// 
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
			
		} else{
			content = "등록된 파일이 없습니다"
		}
		
		divFileupload.innerHTML = content;
	}
	
	
	function attachFileDelete(e){
		console.log(e.dataset.bno, e.dataset.uuid);
		let bno = e.dataset.bno;
		let uuid = e.dataset.uuid;
		
		// 값이 유효하지 않는 경우 메세지 처리
		fetch('/file/delete/'+uuid+'/'+bno)
		.then(response => response.json()) 
		.then(map => deleteList(map))
	}
	
	

	function deleteList(map){
		if(map.result == 'success'){
			divFiledeleteRes.innerHTML = map.msg;
			getFileList();
		} else{
			alert(map.msg);
		}
	}
</script>
</head>
<body>

	<h3>파일업로드</h3>
	
	<form action="/file/fileuploadAction" method="post" enctype="multipart/form-data" name="fileuploadForm">
		<h4>파일선택</h4>
		게시글 번호 : <input type="text" name="bno" id="bno" value="83"> <br> <br>
		<input type="file" name="files" multiple="multiple"> <br> <br>
		<input type="file" name="files"> <br> <br>
		<input type="file" name="files"> <br> <br>
		<button type="submit">파일 업로드</button> <br> <br>
		<button type="button" id="btnFileupload">Fetch 파일업로드</button> <br> <br>
	</form>
	<div id="divFileuploadRes"> </div>
	
	<hr>
	<p></p>
	
	<h4>파일 리스트 조회</h4>
	<button type="button" id="btnList">파일 조회</button><p></p>
	<div id="divFiledeleteRes" style="font-style:italic"> </div>
	<p></p>
	<div id="divFileupload"></div>

</body>
</html>