<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/a79d7cf7c1.js" crossorigin="anonymous"></script>
<script type="text/javascript">
	function getLits(){
		let bno = document.querySelector('#bno').value;
		// 자바스크립트를 사용해 얻은 bno의 value 값을 변수에 넣는다
		fetch('repl/list/'+bno)
		.then(response => response.json())
		.then(list => replView(list))
	}
	
	function replView(list){
		console.log(list);
		list.forEach((reply, index) =>{
			reply.innerHTML += '<br>' + reply.bno;
			 reply.innerHTML += '<br>' + reply.bno;
			 reply.innerHTML += '<br>' + reply.rno;
			 reply.innerHTML += '<br>' + reply.reply;
			 reply.innerHTML += '<br>' + reply.replyer;
			 reply.innerHTML += '<br>' + reply.replydate;
			 console.log(reply);
			 
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
	}
	
	window.onload= function(){
		getList();
		
		btnWrite.addEventListener('click', function(){
			let bno = document.querySelector('#bno').value;
			let reply = document.querySelector('#reply').value;
			let replyer = document.querySelector('#replyer').value;
			
			let replyObj = {bno : bno, reply : Reply, replyer : replyer};
			
			let replyJson = JSON.stringify(replyObj);
			fetch('repl/insert', {method:'post', headers : {'Content-Type' : 'application/json'}, body : replyJson})
			.then(response => response.json())
			// 새 함수 실행
			.then(map => replyWriteRes(map));
		}
	}
	
	function replyWriteRes(map){
		if(map.result == 'success'){
			getList();
		} else{
			alert(map.message);
		}
	}
	
		function replyDelete(rno){
			fetch('/reply/delete/'+rno)
			.then(response => response.json())
			.then(map => replyWriteRes(map))
			
		}
	}
	
	
	
</script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>


<body>

<h3>답글달기</h3>
<div class="input-group mb-3">
  <input type="text" id="bno" value=83 placeholder="게시글번호">
  <input type="text" id="replyer" placeholder="작성자명">
  <input type="hidden" id="page" id="page" value="1">
  <input type="text" id ="reply" class="form-control" placeholder="댓글을 작성해주세요" aria-label="Recipient's username" aria-describedby="basic-addon2">
  <span class="input-group-text" id="btnWrite">댓글작성</span>
</div>
<div id="replyDiv"></div>


</body>
</html>