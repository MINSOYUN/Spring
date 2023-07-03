<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	하이~ <br>
	name: ${name } <br>
	age: ${age } <br>
	=========== <br>
	<h3>vo 객체 출력</h3>
	name: ${member.name } <br>
	age: ${member.age } <br>
	dueDate: ${member.dueDate } <br>
	${message } <br>
	=========== <br>
	<h3>배열 출력</h3>
	ids : ${ids } <br>
	=========== <br>

<!-- get 방식 -->
<a href="/mapping/requestMapping">requestMapping 호출</a> <br>
<!-- post 방식 -->

<form action="/mapping/requestMapping" method="post">
	<input type="submit" value="전송">
</form> <br>

<a href="/mapping/getMapping?name=모모&age=13">getMapping 호출</a> <br>

<a href="/mapping/getMappingVO?name=모모&age=13&dueDate=2023/07/03">getMappingVO 호출</a> <br>

<a href="/mapping/getMappingArr?ids=id1&ids=id2&ids=id3">getMappingArr 호출</a> <br>

<a href="/mapping/getMappingList?ids=id1&ids=id2&ids=id3">getMappingList 호출</a> <br>

<h3>객체 리스트를 파라미터로 전달</h3>
	파라미터로 전달 방법:
	list[0].name=momo&list[0].age=123&list[1].name=admin&list[1].age=12 -> 오류 발생 -> script로 처리 <br>
	
	<script type="text/javascript">
		function voList(){
			let url = "/mapping/getMappingMemberList?list[0].name=momo&list[0].age=123&list[1].name=admin&list[1].age=12";
			url = encodeURI(url);
			alert(url);
			location.href = url;
		}
	</script>
	<a href="#" onclick="voList()">객체 리스트 전달</a> <br>
</body>
</html>