<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/a79d7cf7c1.js" crossorigin="anonymous"></script>

<script type="text/javascript">

	
	/*
	* 댓글 조회!!
	*/
	// 1. 서버에 댓글 리스트 요청
	function getList(){
		let bno = document.querySelector("#bno").value;
		// url 요청 결과를 받아옵니다
		fetch('/reply/list/'+bno)
		// response.json() : 받아온 url 결과 response 를 json object형식 반환
		.then(response => response.json())
		// 오브젝트로 반환 받은 결과 list를 함수에 전달
		.then(list => replyView(list));  
	}

	
	
	/*
	* 댓글 조회 + 디자인
	*/
	function replyView(list){
		// 콘솔창에 리스트 출력
		console.log(list)
		
		// div 초기화
		replyDiv.innerHTML = '';
		
		 
		 // 댓글 리스트(reply)로부터 댓글을 하나씩 읽어와서 div에 출력
		 list.forEach((reply, index) =>{
			 console.log();
			 
			 reply.innerHTML += '<br>' + reply.bno;
			 reply.innerHTML += '<br>' + reply.rno;
			 reply.innerHTML += '<br>' + reply.reply;
			 reply.innerHTML += '<br>' + reply.replyer;
			 reply.innerHTML += '<br>' + reply.replydate;
		
			// 답글을 Div에 출력
			replyDiv.innerHTML 
			+= '<figure id="reply' +index+ '">'   // 
			 + '<blockquote class="blockquote">'
			 +    ' <p>'+reply.reply
			 +	  '<i class="fa-regular fa-pen-to-square"></i>'  // 수정 버튼
			 + 	  '<i class="fa-solid fa-trash-arrow-up" onclick="replayDelete('+ reply.rno +')"></i>'  // 삭제버튼 -> rno번 삭제
			 +'     </p>'
			 +  '</blockquote>'
			 
			 +   '<figcaption class="blockquote-footer">'
			 +    ''+ reply.replyer +' <cite title="Source Title"> '+ reply.replydate +'</cite>'   // 댓글 작성자 / 댓글 작성일
			 +   '</figcaption>'
			 + '</figure>';
		 });
	}
	
	
	
	/*
	* 댓글 삭제!
	*/
	function replayDelete(rno){
		fetch('/reply/delete/'+rno)
		.then(response => response.json())
		.then(map => replyWriteRes(map))
		
	}
	
	
	
	/*
	* 댓글 추가!!
	* 댓글 작성 이벤트 실행 시 insert 실행되고 getList() 호출
	*/
	// 버튼이 생성되고 나서 이벤트 실행
	window.onload = function(){
		// 페이지가 올라오고 실행해야 조회가 되기 때문에 onload 후 실행
		// 페이지 열리자마자 함수 실행 -> 리스트 조회 및 출력
		getList();
		

		// 버튼이 클릭되면 이벤트 실행
		btnWrite.addEventListener('click', function(){
			// 1. 파라미터 수집 -> 변수에 저장
			let bno = document.querySelector('#bno').value;
			let reply = document.querySelector('#reply').value;
			let replyer = document.querySelector('#replyer').value;
			
			// 콘솔창에 출력
			console.log('bno', bno);
			console.log('reply', reply);
			console.log('replyer', replyer);
			
			// 2. 저장한 값들로 전송할 데이터를 javascript 객체로 생성 -> .으로 접근 가능함!
			// JSON.parse
			// 이름 : 값
			let replyObj = {
					bno : bno
					, reply : reply
					, replyer : replyer
					
			};
			
			// 3. 생성된 객체를 json 타입으로 변환 -> 하나의 문자열
			// JSON.stringify
			let replyJson = JSON.stringify(replyObj);
			
			// 콘솔창에 출력하여 차이 보기!
			console.log('자바스크립트 타입 ====== replyObj', replyObj);
			console.log('JSON 타입 ==== replyJson', replyJson);
			
			// 4. 서버에 요청 -> post JSON 형식의 문자열 전송
			// /reply/insert post로 보낸다 -> insert 완
			fetch('/reply/insert', {method : 'post', headers : {'Content-Type' : 'application/json'}, body : replyJson})
			// 5. 서버에 요청
			.then(response => response.json())
			// 새 함수 실행
			.then(map => replyWriteRes(map));
			
		});
	}
	
	
	
	/*
	* res>0
	*/
	function replyWriteRes(map){
		if(map.result == 'success'){
			// 성공 -> 리스트 조회 및 출력
			// 추가 된 댓글까지 댓글 리스트 재조회
			getList();
		} else{
			// 실패
			alert(map.message);
		}
	}
	
</script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<h3>답글달기</h3>
<input type="hidden" id ="bno" name="bno" value="83">
<div class="input-group mb-3">
  <input type="text" id="replyer" placeholder="작성자명">
  <input type="text" id ="reply" class="form-control" placeholder="댓글을 작성해주세요" aria-label="Recipient's username" aria-describedby="basic-addon2">
  <span class="input-group-text" id="btnWrite">댓글작성</span>
</div>
<div id="replyDiv"></div>
</body>
</html>