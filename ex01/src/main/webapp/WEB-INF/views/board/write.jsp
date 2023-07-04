<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>게시판 글쓰기</h3>
<form name="writeFrm" method="post" action="/board/writeAction">
<table border="1" width="90%">
    <tr>
        <td>제목</td>
        <td>
            <input type="text" name="title" style="width:90%;" value="${board.title }"/>
        </td>
    </tr>
    <tr>
        <td>내용</td>
        <td>
            <textarea name="content" style="width:90%;height:100px;" value="${board.content }"></textarea>
        </td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>
            <input type="text" name="writer" style="width:150px;"  value="${board.write }"/>
        </td>
    </tr>
    <tr>
    	<td>
    		 <button type="submit">작성 완료</button>
    	</td>
    </tr>
</table>    
</form>
</body>
</html>