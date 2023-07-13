
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