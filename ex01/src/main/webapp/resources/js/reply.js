console.log('========')
/*
	* fetch Get 방식
	*/
	function fetchGet(url, callback){
		console.log(url);
		console.log(callback);
		
		try {
		// url 요청
		fetch(url)
		// 요청 결과 json 문자열을 javascript 객체로 반환
		.then (response => response.json())
		// 콜백 함수 실행
		.then(map =>callback(map));
			
		} catch (e) {
			consol.log('fetchGet', e);
		}
	}
	
	
	/*
	* fetch Post 방식
	*/
	function fetchPost(url, obj, callback){
		console.log(url);
		console.log(obj);
		console.log(callback);
		try {
		fetch(url, {method : 'post', headers : {'Content-Type' : 'application/json'}, body : JSON.stringify(obj)})
		.then(response => response.json())
		.then(map => callback(map));
			
		} catch (e) {
			consol.log('fetchPost',e);
		}
	}
	
	
	// 댓글 조회 및 출력
	// 매개변수 page 입력 -> 페이징 처리
	function getReplyList(){
		/**
		 * falsey : false, 0, "",NaN, undefined, null
		 * -> page에 입력된 값이 없으면 1을 세팅
		 * falsey한 값 이외의 값이 들어 있으면 true를 반환
		 */
		
		let bno = document.querySelector('#bno').value;
		let page = document.querySelector("#page").value;
		
		console.log('bno : ', bno);
		// console.log('/reply/list/'+bno+'/1'); 와 같음
		console.log(`/reply/list/${bno}/${page}`);
		
		// uri: 요청경로
		// callback: 응답 결과를 받아 실행시킬 함수
		fetchGet(`/reply/list/${bno}/${page}`, replyView)
	}
	
	function getPage(page){
		//id page 값을 page로 저장
		document.querySelector("#page").value = page ; 
		getReplyList();  // 댓글 조회 function 호출
	}
	
	// 댓글 화면에 출력
	function replyView(map){
		let pageDto = map.pageDto;
		let list = map.list
		console.log('list', list);
		console.log('pageDto', pageDto);
		
		// 배열은 있으니까 !list 가 아니라 list 사이즈(length)를 확인하여 메세지 처리
		if(list.length == 0){
			replyDiv.innerHTML = '<em>No registered comments</em>';
			
		} else{
			
			let replyDivStr = 
				'<table class="table text-break text-center">  '
				+'  <thead>                         '
				+'    <tr>                          '
				+'      <th class="col-1">#</th>      '
				+'      <th class="col-9">댓글</th>  '
				+'      <th class="col-2">작성자</th>   '
				+'    </tr>                         '
				+'  </thead>                        '
				+'  <tbody>                         ';
				
				// 리스트를 돌며 출력
				list.forEach( reply=>{
					replyDivStr += 
						// rno도 유일한 값이기 때문에 index 대신 사용 가능
						// data-value -> 입력된 내용을 확인 하기 위해서 dataset.value로 값을 꺼내옴
					'    <tr id="tr'+reply.rno+'" data-value="'+reply.reply+'" >'  // ex) tr1, tr2                          '
					+'      <th scope="row">'+reply.rno+'</th>      '  // 댓글 번호
					+'      <td class="text-start">'+reply.reply+'';  // 댓글 내용
					
					// replayer.vlaje : 로그안한 아이디
					// replayDivSTr = 
					if(replyer.value == reply.replyer){
						replyDivStr +=
					+	'<i class="fa-solid fa-pen-to-square" onclick="replyEdit('+reply.rno+')"></i>'  // 수정
					+			'<i class="fa-regular fa-square-minus" onclick="replyDelete('+reply.rno+')"></i>'  // 삭제
					+'		</td>               '
					+'      <td>'+reply.replyer   // 댓글 작성자 + 작성일
					+' 		<br>'+reply.replydate+'</td>   '
					+'    </tr>   ';
					
				}
				
				replyDivStr +=
				'  </tbody>   '
				+'</table>      '; 
				
				// 화면에 출력
				// innerHTML은 덮어 쓰여지기 때문에 변수 하나 설정 후 넣는다
				replyDiv.innerHTML = replyDivStr;
				
				
				// 페이지 블럭 생성 ==================== 백틱 사용
				let pageBlock= 
				  `<nav aria-label="...">    `
					+ `  <ul class="pagination justify-content-end">   `;
				
					if(pageDto.prev){
						pageBlock +=
							// 백틱 ${i}로 설정 가능
						 `    <li class="page-item disabled" onclick="getPage(${pageDto.startNo-1})">       `
						+ `      <a class="page-link">Previous</a>     `
						+ `    </li>   `;    
						
					}
				
				      for(i=pageDto.startNo; i<=pageDto.endNo; i++){
				    	  // 삼항 연산자 사용 -> 같다면 버튼 활성화
				    	  let activeStr = (pageDto.criteria.page==i)?'active':'';
				    	  pageBlock +=
				    	  `    <li class="page-item ${activeStr}" onclick="getPage(${i})">`
				    	  + ` <a class="page-link" href="#">`+i+`</a></li>  `;
				    	  
				      }                                  
				
				if(pageDto.next){
					pageBlock +=
					 `    <li class="page-item" onclick="getPage(${pageDto.endNo+1})">   `
					+ `      <a class="page-link" href="#">Next</a>    `
					+ `    </li> `;
					
				}
				pageBlock +=
				 `  </ul>    `
				+ `</nav>`  ;
				
				replyDiv.innerHTML += pageBlock;     // replyDivStr 이미 넣었으므로 +=
		});
		
	}
	}
	
	
	// 답글 등록
	function replyWrite(){
		// 답글 작성 시 필요한 데이터 수집(bno, reply, replyer) -> 로그인 한 사용자만 등록 가능
		// 값 왜 value 값으로 가져와야하는지....
		// 답글 작성 시 필요한 데이터 수집
		let bno = document.querySelector('#bno').value;
		// <div class="input-group"> 에 id값 주어 작성
		let reply = document.querySelector('#reply').value; 
		let replyer = document.querySelector('#replyer').value;
		
		// 전달할 객체로 생성
		let obj = {bno:bno, reply: reply, replyer:replyer};
		console.log(obj);
		
		// url : 요청 경로 -> /reply/insert 
		// obj : JSON 형식으로 전달할 데이터
		// callback : 콜백함수 (응답 받아 실행 할 함수)
		fetchPost('/reply/insert', obj, replyRes);
	}
	
	// 답글 등록, 수정, 삭제의 결과를 처리하는 함수 / 결과 int로 나오기 때문!
	function replyRes(map){
		console.log(map);
		if(map.result=='success'){
			alert(map.msg);
			getReplyList();
		}else{
			alert(map.msg);
		}
		// 성공 시 리스트 조회 및 출력
		// 실패시 메시지 알람
	}
	
	function replyDelete(rno){
		console.log("rno : ", rno);
		// map
		fetchGet('/reply/delete/'+rno, replyRes);
	}
	
	function replyEdit(rno){   // 해당 박스 선택해서 새 박스로 대체
		let tr = document.querySelector("#tr"+rno);
		let replyTxt = tr.dataset.value; 
		console.log("tr : ", tr);
		tr.innerHTML = '<td colspan="3">'    // 덮어씌우는 것이므로 += 가 아니라 =로 작성
		+ ' <div class="input-group"> '
		+ '  	<span class="input-group-text">답글수정</span>'
		+ '  	<input type="text"  aria-label="First name" class="form-control" id="reply'+rno+'" value="'+replyTxt+'">   '  // 원래 댓글 + 수정된 댓글
		+ '  	<input type="button" aria-label="Last name" class="input-group-text" onclick="replyEditAction('+rno+')" value="수정하기"> '
		+ '  </div>   </td>  ';
	}
	
	function replyEditAction(rno){  // 입력받은 수정 내용으로 새 댓글 작성
		// 파라미터 수집
		let reply = document.querySelector('#reply'+rno).value;    // 댓글 내용
		// jS 객체로 수집
		let obj = {rno:rno, reply:reply};
		// 서버에 요청
		fetchPost('/reply/editAction', obj, replyRes);
	}