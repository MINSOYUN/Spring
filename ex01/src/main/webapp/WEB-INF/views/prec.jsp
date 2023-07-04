<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>=======vo=========</h3>
name: ${member.name }
age: ${member.age }

	<a href="/prectice/request">1. request!</a> <br>
	<a href="/pectice/param?name=소윤&age=28">2. 파라미터!</a> <br>
	<a href="/pectice/mappingVO?name=소윤&age=28">3. 멤버 vo!</a> <br>
	<a href="/pectice/mappingArr?id=1&id=2&id=3">4. id Arr!</a> <br>
	<a href="/pectice/mappingList?id=1&id=2&id=3">5. id List!</a> <br>
	
	<script type="text/javascript">
		function voList(){
		let url = "/prectice/memberList?list[0].name=소윤&list[0].age=28&list[1].name=준호&list[1].age=34";
		url = encodeURI(url);
		alert(url);
		location.href=url;
		
		}
	</script>
	<a href="#" onclick="voList()">6.객체리스트!</a>
</body>
</html>