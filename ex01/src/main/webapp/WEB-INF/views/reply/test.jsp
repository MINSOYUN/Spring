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
	* 댓글 추가!!
	* 댓글 작성 이벤트 실행 시 insert 실행되고 getList() 호출
	*/
	// 버튼이 생성되고 나서 이벤트 실행
	window.onload = function(){
		// 페이지가 올라오고 실행해야 조회가 되기 때문에 onload 후 실행
		// 페이지 열리자마자 함수 실행 -> 리스트 조회 및 출력
		getList();  // 페이지 처음 불려졌을 때
		

		// 버튼이 클릭되면 이벤트 실행
		btnWrite.addEventListener('click', function(){
			// 1. 파라미터 수집 -> 변수에 저장
			let bno = document.querySelector('#bno').value;
			let reply = document.querySelector('#reply').value;
			let replyer = document.querySelector('#replyer').value;
			
			// 콘솔창에 출력
			console.log('bno', bno);  // 게시글 번호
			console.log('reply', reply); // 댓글
			console.log('replyer', replyer); // 댓글 작성자
			
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
			// ==========  fetchPost('/reply/insert', replyObj, replyWriteRes);
			fetch('/reply/insert', {method : 'post', headers : {'Content-Type' : 'application/json'}, body : replyJson})
			// 5. 서버에 요청
			.then(response => response.json())
			// 새 함수 실행
			.then(map => replyWriteRes(map));
			
		});
	}
	
	
	
	
	/*
	* 댓글 조회!!
	*/
	// 1. 서버에 댓글 리스트 요청
	function getList(){
		let bno = document.querySelector("#bno").value;   // 83
		let page = document.querySelector("#page").value;   // 1 -> 기본 값?

		// ==========  fetchGet 함수 사용 -> fetchGet('/reply/list/'+bno+'/'+page, replyView)  ===========
		
		// url 요청 결과를 받아옵니다
		fetch('/reply/list/'+bno+'/'+page)
		// response.json() : 받아온 url 결과 response 를 json object형식 반환
		.then(response => response.json())
		// 오브젝트로 반환 받은 결과 list를 함수에 전달
		.then(map => replyView(map));  
	}

	
	
	function getPage(page){  // #page value 값 변경
		document.querySelector("#page").value=page;
		getList();	
	}
	
	
	
	/*
	* 2. 리스트를 화면에 출력
	*/
	function replyView(map){
		let list = map.list;   // map으로부터 꺼내서 변수로 저장
		let pageDto = map.pageDto;
		
		// 콘솔창에 리스트 출력
		console.log(list);
		console.log(pageDto);
		
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
			 console.log(reply);
		
			// 답글을 Div에 출력
			replyDiv.innerHTML 
			+= '<figure id="reply' +index+ '" data-value="'+reply.reply+'">'   // 홑따옴표로 묶어도 id 검색 가능
			 + '<blockquote class="blockquote">'
			 +    ' <p>'+reply.reply
			 +	  '<i class="fa-regular fa-pen-to-square" onclick="replyEdit('+ index +','+reply.rno+')";></i>'  // 수정 버튼 -> index, rno 함께 넘겨줌
			 + 	  '<i class="fa-solid fa-trash-arrow-up" onclick="replyDelete('+ reply.rno +')";></i>'  // 삭제버튼 -> rno번 삭제
			 +'     </p>'
			 +  '</blockquote>'
			 
			 +   '<figcaption class="blockquote-footer">'
			 +    ''+ reply.replyer +' <cite title="Source Title"> '+ reply.replydate +'</cite>'   // 댓글 작성자 / 댓글 작성일
			 +   '</figcaption>'
			 + '</figure>';
		 });
		 
		 
		let pageBlock ='';   // innerHTML 로 생성하면 for문 만나서 <nav> 닫힌다
		// 모달 -> 페이지 블럭 생성
		pageBlock +=
			' <nav aria-label="...">'
			+ '  <ul class="pagination">';
		
		// prev 버튼
		if(pageDto.startNo){
			pageBlock +=
				'    <li class="page-item" onclick="getPage('+(pageDto.startNo-1)+')">'
			+ 	'      <span class="page-link">Previous</span>'
			+ 	'    </li>';
		}
		
		// 반복해서 페이지 번호 출력
		for(i=pageDto.startNo; i<=pageDto.endNo; i++){
			// 페이지번호와 i 가 같다면 class active
			let activeStr = (pageDto.criteria.pageNo == i) ? 'active':'';
			// 페이지 번호 생성(반복문 startNo ~ endNo)
			pageBlock +=
				'    <li class="page-item '+activeStr+'" onclick="getPage('+ i +')">' 
				+ '<a class="page-link" href="#">'+i+'</a></li>';
				console.log('test: ' + i);
		}

		// next 버튼
		if(pageDto.endNo){
		pageBlock +=
			 	'    <li class="page-item" onclick="getPage('+(pageDto.endNo+1)+')">'
			+ 	'      <a class="page-link" href="#">Next</a>'
			+ 	'    </li>';
			
		}
		
		pageBlock +=
				'  </ul>'
			+ '</nav>';
		
		replyDiv.innerHTML += pageBlock;
	}
	
	
	
	/*
	* 댓글 삭제!
	*/
	function replyDelete(rno){
		fetch('/reply/delete/'+rno)
		.then(response => response.json())
		.then(map => replyWriteRes(map))
		
	}
	
	
	
	/*
	* 수정 화면 보여주기
	* 수정버튼에 있는 rno도 가져와 쿼리에 작성
	* 매개변수 순서대로 작성하기!
	*/
	function replyEdit(index, rno){
		// id가 reply인! -> 수정하기 버튼 클릭하면 input 창
		let editbox = document.querySelector("#reply"+ index);  // index 배열 -> 해당하는 댓글 정보 <div>
		let replyTxt = editbox.dataset.value;  // 댓글 내용 왜???
		
		editbox.innerHTML = ''
					 + '<div class="input-group mb-3">'
					 + '<input type="text" id ="editReply'+rno+'" value="'+replyTxt+'" class="form-control" placeholder="댓글을 수정해주세요" aria-describedby="basic-addon2">'
					 + '<span class="input-group-text" id="btnEdit" onclick="replyEditAction('+rno+')">댓글수정</span>'
					 + '</div>';
	}
	
	
	/*
	* 수정 된 내용 반영
	* rno는 중복없는 값이기 때문에 index처럼 사용
	*/
	function replyEditAction(rno){
		// 파라미터 수집하여 변수에 저장
		let reply = document.querySelector('#editReply'+rno).value;  // id querySelector 로 정보 불러오기
		
		// 객체로 사용하기위해 자바스크립트 생성
		let replyObj = {
				rno : rno    // 화면에서 받아온다
				, reply : reply
				
		};
		
		// json 형식으로 변환
		let replyJson = JSON.stringify(replyObj);
		console.log("replyJson", replyJson)
		
		// fetch 함수
		fetch('/reply/editAction', {method : 'post', headers : {'Content-Type' : 'application/json'}, body : replyJson})
		.then(response => response.json())
		.then(map => replyWriteRes(map));
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
<div class="input-group mb-3">
  <input type="text" id="replyer" placeholder="작성자명">
  <input type="hidden" id="page" id="page" value="1">
  <input type="text" id ="reply" class="form-control" placeholder="댓글을 작성해주세요" aria-label="Recipient's username" aria-describedby="basic-addon2">
  <span class="input-group-text" id="btnWrite">댓글작성</span>
</div>
<div id="replyDiv"></div>
</body>
</html>